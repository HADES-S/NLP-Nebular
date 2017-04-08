package segmentation.Trie;

import com.sun.org.apache.xalan.internal.xsltc.CollatorFactory;
import com.sun.org.apache.xpath.internal.SourceTree;
import segmentation.index.Interval;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Administrator on 2017/4/6.
 * 负责构建索引、添加模式串、
 */
public class Trie {

    private TrieConfig trieConfig;
    State rootState = new UnicodeState();
    private boolean failureStatesConstructed = false;

    public Trie()
    {
           this(new TrieConfig(),true);
    }

    public Trie(TrieConfig config,boolean isUnicode){
        this.trieConfig = config;
        if(isUnicode){
            this.rootState = new UnicodeState();
        }else {
            System.out.println("暂不支持英文");
        }
    }

    public Trie removeOverlap(){
        this.trieConfig.setAllowOverlap(false);
        return this;
    }


    public Trie onlyWholeWords(){
        this.trieConfig.setOnlyWholeWord(true);
        return this;
    }

    public Trie caseInsensitive(){
        this.trieConfig.setCaseInsentiveWord(true);
        return this;
    }

    public void addEmits(){

    }

    public Collection<Emit> parseText(String text){
        checkForConstructedFailureState();

        int position = 0;
        State currentState = this.rootState;
        List<Emit> collectedEmits = new ArrayList<Emit>();
        for(Character character:text.toCharArray()){
            if(trieConfig.isCaseInsentiveWord()){
                character = Character.toLowerCase(character);
            }
            currentState = getState(currentState,character);
            storeEmits(position,currentState,collectedEmits);
            ++position;
        }
        if(trieConfig.isOnlyWholeWord())
        {
            removePatialMatches(text,collectedEmits);
        }
        if(trieConfig.isAllowOverlap()){
            IntervalTree intervalTree = new Interval()
        }
    }

    public void removePatialMatches(String searchText,List<Emit> collectedEmits){
        long size = searchText.length();
        List<Emit> removeEmits = new ArrayList<Emit>();
        for(Emit emit:collectedEmits){
            if((emit.getStart()==0||!Character.isAlphabetic(searchText.charAt(emit.getStart()-1))&&
                    (emit.getEnd()+1==size||!Character.isAlphabetic(searchText.charAt(emit.getEnd()+1)))))
            {
                continue;
            }
            removeEmits.add(emit);
        }
    }



    public State getState(State currentState,Character character){
        State newCurrentState = currentState.nextState(character);
        while (newCurrentState==null){
            currentState = currentState.getFailure();
            newCurrentState = currentState.nextState(character);
        }
        return newCurrentState;
    }

    public void checkForConstructedFailureState(){
        if(!this.failureStatesConstructed)
        {
            constructFailureStates();
        }
    }

    public void constructFailureStates(){
        Queue<State> queue = new LinkedBlockingQueue<State>();
        for(State depthOneState:this.rootState.getState()){
            depthOneState.setFailure(this.rootState);
            queue.add(depthOneState);
        }

        //多线程时防止重新建立
        this.failureStatesConstructed = true;

        /**
         *  宽度优先遍历 深度>1的点建立failure表,给每个节点添加对应的failure
         *  类似于KMP算法找公共前缀
         */
        while (!queue.isEmpty()){
            State currentState = queue.remove();//当前层
            for(Character transition:currentState.getTransitions()){
                State targetState = currentState.nextState(transition);//子节点
                queue.add(targetState);
                State traceFailureState =  currentState.getFailure();
                while (traceFailureState.nextState(transition)==null){
                    traceFailureState = traceFailureState.getFailure();
                }
                State newFailureState = traceFailureState.getFailure();
                targetState.setFailure(newFailureState);
                targetState.addEmits(newFailureState.getEmit());
            }
        }
    }
    public static void storeEmits(int position, State currentState, List<Emit> collectedEmits){
        Collection<String> emits = currentState.getEmit();
        if(emits!=null&&!emits.isEmpty()){
            for(String emit:emits){
                collectedEmits.add(new Emit(position-emit.length()+1,position,emit));
            }
        }
    }

}
