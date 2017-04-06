package segmentation;

/**
 * Created by Administrator on 2017/4/5.
 */
public interface Intervalable {
    /**
     * 起点位置
     * @return
     */
    public int getStart();

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
