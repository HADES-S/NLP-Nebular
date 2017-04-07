package segmentation.index;

/**
 * Created by Administrator on 2017/4/6.
 */
public class Interval implements Intervalable{

    /**
     * 起点
     */
    private int start;
    /**
     * 终点
     */
    private int end;

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int size() {
        return 0;
    }

    public int compareTo(Object o) {
        if(!(o instanceof Intervalable))
        {
            return -1;
        }
        Intervalable other = (Intervalable) o;
        int comparison = this.start -other.getStart();
        return comparison !=0? comparison:this.end - other.getEnd();
    }

    @Override
    public int hashCode(){
        return this.start%100 + this.end%100;
    }


}
