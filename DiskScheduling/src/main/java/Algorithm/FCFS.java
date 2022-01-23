package Algorithm;

/**
 * @author JasonGu
 * @date 2021/12/3 12:20
 */

import model.Disk;
import model.Position;
import java.util.ArrayList;

public class FCFS extends Disk{
    private ArrayList<Position> positionArrayList = new ArrayList<>();
    public FCFS(Position initPosition, ArrayList<Position> positionArrayList) {
        super(initPosition);
        for(Position i : positionArrayList){
            this.positionArrayList.add(new Position(i.getLocation()));
        }
    }

    @Override
    public Position getNext() {
        int i = 0;
        for(; i< positionArrayList.size() && positionArrayList.get(i).isVisited(); i++){

        }

        // postionArrayList.get(i).isVisited() == false || i>=postionArrayList.size()
        if(i>= positionArrayList.size()){
            return null;
        }

        this.addLength(Math.abs(this.getCurrentPostion().getLocation() - positionArrayList.get(i).getLocation()));
        positionArrayList.get(i).toVisited();
        this.setCurrentPostion(positionArrayList.get(i));
        this.drawFlow.add(this.getCurrentPostion());
        return positionArrayList.get(i);
    }
    @Override
    public void run(){
        while(true){
            //System.out.println(drawFlow);
            if(this.getNext()==null) {
                break;
            }
        }
    }
}

