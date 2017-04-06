package segmentation.Trie;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
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

    public void addEmits()

    public void constructFailureStates(){
        Queue<State> queue = new LinkedBlockingQueue<State>();
        for(State depthOneState:this.rootState.getState()){
            depthOneState.setFailure(this.rootState);
            queue.add(depthOneState);
        }

        //多线程时防止重新建立
        this.failureStatesConstructed = true;

        //宽度优先遍历 深度>1的点建立failure表,bfs遍历，给每个节点添加对应的failure
        while (!queue.isEmpty()){
            State currentState = queue.remove();
            for(Character transition:currentState.getTransitions()){
                State targetState = currentState.nextState(transition);
                queue.add(targetState);
                State traceFailureState =  currentState.getFailure();
                while (traceFailureState.nextState(transition)==null){
                    traceFailureState = traceFailureState.getFailure();
                }
                State newFailureState = traceFailureState.getFailure();
                targetState.setFailure(newFailureState);
                targetState.addEmit
            }
        }
    }
}
