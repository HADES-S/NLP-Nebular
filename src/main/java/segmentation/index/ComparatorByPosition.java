package segmentation.index;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/4/7.
 * 通过初始位置的先后来进行比较
 */
public class ComparatorByPosition implements Comparator<Intervalable> {

    public int compare(Intervalable o1, Intervalable o2) {
        return o1.getStart()-o2.getStart();
    }
}
