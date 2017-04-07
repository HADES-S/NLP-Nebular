package segmentation.index;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by Administrator on 2017/4/5.
 */


public interface Intervalable extends Comparable{
    /**
     * 起点位置
     * @return
     */
    public int getStart();
    ArrayList<Integer> arr1 = new ArrayList<Integer>();
    /**
     * 终点位置
     * @return
     */
    public int getEnd();

    /**
     * 长度
     * @return
     */
    public int size();
}
