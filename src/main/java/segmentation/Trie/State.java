package segmentation.Trie;

import java.io.CharConversionException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Administrator on 2017/4/6.
 * 转换状态父类
 */
public abstract class State {

    /**
     * 模式串的长度，此字符串所处的深度
     */
    protected final int depth;

    /**
     * 根节点
     */
    protected final State rootState;

    /**
     * 如果匹配的时候未找到字符串，则跳转到此状态
     */
    private State failure = null;

    private Set<String> emits = null;

    /**
     * 构建深度为0的字符串
     */
    public State(){
        this(0);
    }

    /**
     * 构建自定义深度的字符串
     */
    public State(int depth){
        this.depth = depth;
        this.rootState = depth==0?this:null;
    }

    public int getDepth(){
        return this.depth;
    }


    /**
     * 在此状态基础上如果后继不存在，则为新字符构建的状态
     * @param character
     * @return
     */
    public abstract State addState(Character character);

    /**
     *获取该节点的模式串
     * @return
     */
    public Collection<String> getEmit(){
        return this.emits==null? Collections.<String>emptyList():this.emits;
    }

    protected State getFailure(){
        return failure;
    }

    /**
     * 设置failure状态
     * @param failState
     */
    public void setFailure(State failState)
    {
        this.failure = failState;
    }

    /**
     *
     * @param character 按此字符转移到下一状态
     * @return 转移结果
     */
    public abstract State nextState(Character character);


    /**
     * 获取当前状态所有可能的后面状态
     * @return 状态集合
     */
    public abstract Collection<State> getState();

    /**
     * 获取当前状态所有可能的下一字符
     * @return 字符集合
     */
    public abstract Collection<Character> getTransitions();

    /**
     *
     * @param character
     * @return
     */
    public  abstract State nextStateIgnoreRootState(Character character,boolean ignore);

    public void addEmits(Collection<String> emits){
        for(String emit:emits){
            addEmit(emit);
        }
    }

    public void addEmit(String emit){
        if(this.emits==null)
            this.emits = new TreeSet<String>();
        this.emits.add(emit);
    }

}
