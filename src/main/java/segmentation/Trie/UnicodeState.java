package segmentation.Trie;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/4/6.
 * 定义Trie树的状态
 */
public class UnicodeState extends State {

    private Map<Character,State> success = new TreeMap<Character, State>();

    public UnicodeState(){
        super();
    }

    public UnicodeState(int depth){
        super(depth);
    }

    @Override
    public State addState(Character character) {
        State nextState = nextStateIgnoreRootState(character,true);
        if(nextState== null){
            nextState = new UnicodeState(this.depth+1);
            this.success.put(character,nextState);
        }
        return nextState;
    }

    @Override
    public State nextState(Character character) {
        return nextStateIgnoreRootState(character,true);
    }


    @Override
    public Collection<State> getState() {
        return this.success.values();
    }

    @Override
    public Collection<Character> getTransitions() {
        return this.success.keySet();
    }

    @Override
    public State nextStateIgnoreRootState(Character character, boolean ignore) {
        State nextState = this.success.get(character);
        if(!ignore&&nextState==null&&this.rootState!=null)
        {
            nextState = this.rootState;
        }
        return nextState;
    }

    public State nextState(Character character,boolean ignore){
        return nextStateIgnoreRootState(character,ignore);
    }

}
