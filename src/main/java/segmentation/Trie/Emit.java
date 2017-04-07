package segmentation.Trie;

import segmentation.index.Interval;
import segmentation.index.Intervalable;

/**
 * Created by Administrator on 2017/4/6.
 * 模式串匹配结果
 */
public class Emit extends Interval implements Intervalable {

    /**
     * 匹配到的关键串
     */
    private final String keyword;

    public Emit(final int start, final int end ,final String keyword){
        super(start,end);
        this.keyword = keyword;
    }

    public String getKeyword(){return this.keyword;}

    public String toString(){
        return super.toString()+"="+this.keyword;
    }
}
