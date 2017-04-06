package segmentation.Trie;

/**
 * Created by Administrator on 2017/4/6.
 */
public class TrieConfig {

    /**
     * 允许序列交叉重叠
     * 比如 同时有模式串 Overlap 和Over
     */
    boolean allowOverlap = true;

    /**
     * 只读取完整单词，中文不存在此现象
     */
    boolean onlyWholeWord = false;

    /**
     * 是否区分大小写
     */
    boolean caseInsentiveWord = true;

    public boolean isOnlyWholeWord() {
        return onlyWholeWord;
    }

    public void setOnlyWholeWord(boolean onlyWholeWord) {
        this.onlyWholeWord = onlyWholeWord;
    }

    public boolean isAllowOverlap() {
        return allowOverlap;
    }

    public void setAllowOverlap(boolean allowOverlap) {
        this.allowOverlap = allowOverlap;
    }

    public boolean isCaseInsentiveWord() {
        return caseInsentiveWord;
    }

    public void setCaseInsentiveWord(boolean caseInsentiveWord) {
        this.caseInsentiveWord = caseInsentiveWord;
    }
}
