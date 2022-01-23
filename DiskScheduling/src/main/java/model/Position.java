package model;

/**
 * @author JasonGu
 * @date 2021/12/3 12:18
 */

public class Position implements Cloneable ,Comparable<Position>{
    private int location;
    private boolean visited = false;

    public Position(int location){
        this.location = location;
    }

    public int getLocation(){
        return this.location;
    }

    public void setLocation(int location){
        this.location = location;
    }

    public boolean isVisited(){
        return visited;
    }

    public void toVisited(){
        this.visited = true;
    }

    @Override
    public String toString() {
//        return "Postion{" +
//                "location=" + location +
//                ", visited=" + visited +
//                '}';
        return String.valueOf(location);
    }

    @Override
    public int compareTo(Position o) {
        return Integer.compare(this.getLocation(), o.getLocation());
    }
}
