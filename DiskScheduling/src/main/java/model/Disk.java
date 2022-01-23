package model;

/**
 * @author JasonGu
 * @date 2021/12/3 12:17
 */

import java.util.ArrayList;

public abstract class Disk {
    private int length;
    private Position currentPosition;
    public ArrayList<Position> drawFlow = new ArrayList<>();

    public Disk(Position initPosition){
        // 设置初始点
        this.currentPosition = initPosition;
        this.currentPosition.toVisited();
        this.drawFlow.add(this.currentPosition);

        // 设置寻道初始长度
        this.length = 0;
    }

    public void addLength(int length){
        this.length += length;
    }

    // ----------  getter and setter  ------------ //

    public Position getCurrentPostion() {
        return currentPosition;
    }

    public void setCurrentPostion(Position currentPosition) {
        this.currentPosition = currentPosition;
    }
    public int getLength(){
        return this.length;
    }

    public abstract void run();
    public abstract Position getNext();
}

