package segmentation.index;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/4/7.
 */
public class ComparatorBySize implements Comparator<Interval> {

    public int compare(Interval o1, Interval o2) {
        if(o1.size()!=o2.size())
            return o1.size()-o2.size();
        return o1.getStart()-o2.getStart();
    }
}
