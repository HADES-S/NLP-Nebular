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
        return end-start+1;
    }

    public Interval(final int start,final int end){
        this.start = start;
        this.end = end;
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

    /**
     * 是否与另一个区间交叉（有一部分重叠）
     * @param other
     * @return
     */
    public boolean overlapWith(Interval other){
        return this.start<=other.getEnd()&&this.getEnd()>=other.getStart();
    }

    /**
     * 区间是否覆盖了该点
     * @param point
     * @return
     */
    public boolean overlapswith(int point){
        return this.start<=point&&this.end>=point;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Interval))
            return false;
        Interval other =(Interval)o;
        return this.start == other.getStart()&&
                this.end == other.getEnd();
    }

    @Override
    public int hashCode(){
        return this.start%100 + this.end%100;
    }

    @Override
    public String toString(){
        return this.start+":"+this.end;
    }
}
