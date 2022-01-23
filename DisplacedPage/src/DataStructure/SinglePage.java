package DataStructure;
import java.util.ArrayList;

/**
 * @author JasonGu
 * @date 2021/12/10 0:13
 */

/**
 * 页
 * 1个页存放10条指令（地址）
 */
public class SinglePage implements Cloneable ,Comparable<SinglePage>{
    private ArrayList<Position> locations;
    private boolean visited = false;
    /**
     * i 该页在页地址流中的序列
     */
    private int i;

    public SinglePage(){
        locations = new ArrayList<>();
    }

    public void addLocation(Position location){
        boolean x = false;
        for(int i=0;i<locations.size();i++)
        {
            if(location.compareTo(this.locations.get(i))==0){
                x = true;
                break;
        }
        }
        if(!x)  this.locations.add(location);
    }

    public int getLocations() {
        return i;
    }

    /**
     * 设置在页地址流中的序列
     * @param i 序列
     */
    public void setLocations(int i){
        this.i = i;
    }

    public boolean isVisited(){
        return visited;
    }

    public void toVisited(){
        this.visited = true;
    }


    public void PrintLocation() {
//        return "Postion{" +
//                "location=" + location +
//                ", visited=" + visited +
//                '}';
        System.out.print("第"+String.format("%-2d",this.i)+"页 -- ");
        for(Position location:locations){
            System.out.print(String.format("%-4d",location.getLocation())+" ");
        }
        System.out.println();
    }

    @Override
    public int compareTo(SinglePage o) {
        return Integer.compare(this.i, o.i);
    }
}

