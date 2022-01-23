package Algorithm;

/**
 * @author JasonGu
 * @date 2021/12/3 12:22
 */

import model.Disk;
import model.Position;

import java.util.ArrayList;

public class SSTF extends Disk {
    private ArrayList<Position> positionArrayList = new ArrayList<>();
    public SSTF(Position initPosition, ArrayList<Position> positionArrayList) {
        super(initPosition);
        for(Position i : positionArrayList){
            this.positionArrayList.add(new Position(i.getLocation()));
        }
    }

    @Override
    public void run() {
        while(true){
            //System.out.println(drawFlow);
            if(this.getNext()==null) {
                break;
            }
        }
    }

    @Override
    public Position getNext() {
        int min_location = Integer.MAX_VALUE;
        int min_index = positionArrayList.size();

        for(int i = 0; i< positionArrayList.size(); i++){
//            System.out.println(!postionArrayList.get(i).isVisited());
//            System.out.println(Math.abs(getCurrentPostion().getLocation() - postionArrayList.get(i).getLocation()) < min_location);
            if((!positionArrayList.get(i).isVisited()) && (Math.abs(getCurrentPostion().getLocation() - positionArrayList.get(i).getLocation()) < min_location)){
                min_index = i;
                min_location = Math.abs(getCurrentPostion().getLocation() - positionArrayList.get(i).getLocation());
            }
        }

        // min_index = postionArrayList.size();
        if(min_index == positionArrayList.size()){
            return null;
        }

        this.addLength(min_location);
        positionArrayList.get(min_index).toVisited();
        this.setCurrentPostion(positionArrayList.get(min_index));
        this.drawFlow.add(this.getCurrentPostion());
        return positionArrayList.get(min_index);
    }
}

