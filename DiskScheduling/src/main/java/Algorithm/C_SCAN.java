package Algorithm;

/**
 * @author JasonGu
 * @date 2021/12/3 12:23
 */

import model.Disk;
import model.Position;

import java.util.ArrayList;
import java.util.Collections;

public class C_SCAN extends Disk {
    private ArrayList<Position> positionArrayList = new ArrayList<>();
    // 方向 1: 增大方向 0: 减小方向
    private int flag;
    public C_SCAN(Position initPosition, ArrayList<Position> positionArrayList, int flag) {
        super(initPosition);
        this.flag = flag;
        for(Position i : positionArrayList){
            this.positionArrayList.add(new Position(i.getLocation()));
        }
        Collections.sort(this.positionArrayList);
        System.out.println("排序后的序列: " + this.positionArrayList); // 小到大
    }

    @Override
    public void run() {
        if(flag == 1){
            // 增大方向
            while(true){
                //System.out.println(drawFlow);
                if(this.getNext()==null) {
                    break;
                }
            }
            // 最小的没有被访问过
            if(!this.positionArrayList.get(0).isVisited()){
                this.addLength(Math.abs(positionArrayList.get(0).getLocation() - getCurrentPostion().getLocation()));
                positionArrayList.get(0).toVisited();
                this.setCurrentPostion(positionArrayList.get(0));
                this.drawFlow.add(this.getCurrentPostion());
                while(true){
                    //System.out.println(drawFlow);
                    if(this.getNext()==null) {
                        break;
                    }
                }
            }
        }else if(flag == 0){
            // 减小方向
            while(true){
                //System.out.println(drawFlow);
                if(this.getNext()==null) {
                    break;
                }
            }
            // 最大的没有被访问过
            if(!this.positionArrayList.get(this.positionArrayList.size()-1).isVisited()){
                this.addLength(Math.abs(positionArrayList.get(this.positionArrayList.size()-1).getLocation() - getCurrentPostion().getLocation()));
                positionArrayList.get(this.positionArrayList.size()-1).toVisited();
                this.setCurrentPostion(positionArrayList.get(this.positionArrayList.size()-1));
                this.drawFlow.add(this.getCurrentPostion());
                while(true){
                    //System.out.println(drawFlow);
                    if(this.getNext()==null) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    public Position getNext() {
        if(flag == 1) {
            // 增大方向
            int i = 0;
            for(; i< positionArrayList.size(); i++){
                if(!positionArrayList.get(i).isVisited() && positionArrayList.get(i).getLocation() >= getCurrentPostion().getLocation()){
                    break;
                }
            }

            // i = postionArrayList.size();
            if(i == positionArrayList.size()){
                return null;
            }

            this.addLength(Math.abs(positionArrayList.get(i).getLocation() - getCurrentPostion().getLocation()));
            positionArrayList.get(i).toVisited();
            this.setCurrentPostion(positionArrayList.get(i));
            this.drawFlow.add(this.getCurrentPostion());
            return positionArrayList.get(i);
        }else if(flag == 0){
            // 减小方向
            int i = positionArrayList.size()-1;
            for(;i >= 0; i--){
                if(!positionArrayList.get(i).isVisited() && positionArrayList.get(i).getLocation() <= getCurrentPostion().getLocation()){
                    break;
                }
            }
            // i = -1;
            if(i == -1){
                return null;
            }

            this.addLength(Math.abs(positionArrayList.get(i).getLocation() - getCurrentPostion().getLocation()));
            positionArrayList.get(i).toVisited();
            this.setCurrentPostion(positionArrayList.get(i));
            this.drawFlow.add(this.getCurrentPostion());
            return positionArrayList.get(i);
        }else{
            return null;
        }
    }
}

