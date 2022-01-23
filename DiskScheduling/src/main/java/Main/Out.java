package Main;

/**
 * @author JasonGu
 * @date 2021/12/3 12:24
 */

import Algorithm.C_SCAN;
import Algorithm.FCFS;
import Algorithm.SCAN;
import Algorithm.SSTF;
import model.Position;

import java.util.ArrayList;

public class Out {
    public static int n = 400;

    public static FCFS outFCFS(Position initPosition, ArrayList<Position> positionArrayList) {
        //----------------------------------------------------------------------- FCFS
        System.out.println("\n-------------  FCFS算法  -------------");
        FCFS fcfs = new FCFS(initPosition, positionArrayList);

//        System.out.println("// ************* FCFS run start ************* //");
        fcfs.run();
//        System.out.println("// ************* FCFS run end ************* //\n");

        System.out.println("绘图序列: " + fcfs.drawFlow);
        System.out.println("寻道长度: " + fcfs.getLength());
        System.out.println("平均寻道长度: " + fcfs.getLength() / n);
        return fcfs;
    }

    public static SSTF outSSTF(Position initPosition, ArrayList<Position> positionArrayList) {
        //--------------------------------------------------------------------- SSTF
        System.out.println("\n-------------  SSTF算法  -------------");
        SSTF sstf = new SSTF(initPosition, positionArrayList);

//        System.out.println("// ************* SSTF run start ************* //");
        sstf.run();
//        System.out.println("// ************* SSTF run end ************* //\n");

        System.out.println("绘图序列: " + sstf.drawFlow);
        System.out.println("寻道长度: " + sstf.getLength());
        System.out.println("平均寻道长度: " + sstf.getLength() / n);
        return sstf;
    }

    public static SCAN outSCAN1(Position initPosition, ArrayList<Position> positionArrayList) {
        //----------------------------------------------------------------------- SCAN - 增大方向1
        System.out.println("\n-------------  SCAN算法[增大方向]  -------------");
        SCAN scan1 = new SCAN(initPosition, positionArrayList, 1);

//        System.out.println("// ************* SCAN-1 run start ************* //");
        scan1.run();
//        System.out.println("// ************* SCAN-1 run end ************* //\n");

        System.out.println("绘图序列: " + scan1.drawFlow);
        System.out.println("寻道长度: " + scan1.getLength());
        System.out.println("平均寻道长度: " + scan1.getLength() / n);
        return scan1;
    }

    public static SCAN outSCAN0(Position initPosition, ArrayList<Position> positionArrayList) {
        //------------------------------------------------------------------------ SCAN - 减小方向0
        System.out.println("\n-------------  SCAN算法[减小方向]  -------------");
        SCAN scan0 = new SCAN(initPosition, positionArrayList, 0);

//        System.out.println("// ************* SCAN-0 run start ************* //");
        scan0.run();
//        System.out.println("// ************* SCAN-0 run end ************* //\n");

        System.out.println("绘图序列: " + scan0.drawFlow);
        System.out.println("寻道长度: " + scan0.getLength());
        System.out.println("平均寻道长度: " + scan0.getLength() / n);
        return scan0;
    }

    public static C_SCAN outC_SCAN1(Position initPosition, ArrayList<Position> positionArrayList) {
        //------------------------------------------------------------------------ C-SCAN - 增大方向1
        System.out.println("\n-------------  C-SCAN算法[增大方向]  -------------");
        C_SCAN c_scan1 = new C_SCAN(initPosition, positionArrayList, 1);

//        System.out.println("// ************* C-SCAN-0 run start ************* //");
        c_scan1.run();
//        System.out.println("// ************* C-SCAN-0 run end ************* //\n");

        System.out.println("绘图序列: " + c_scan1.drawFlow);
        System.out.println("寻道长度: " + c_scan1.getLength());
        System.out.println("平均寻道长度: " + c_scan1.getLength() / n);
        return c_scan1;
    }

    public static C_SCAN outC_SCAN0(Position initPosition, ArrayList<Position> positionArrayList) {
        //------------------------------------------------------------------------ C-SCAN - 减小方向0
        System.out.println("\n-------------  C-SCAN算法[减小方向]  -------------");
        C_SCAN c_scan0 = new C_SCAN(initPosition, positionArrayList, 0);

//        System.out.println("// ************* C-SCAN-0 run start ************* //");
        c_scan0.run();
//        System.out.println("// ************* C-SCAN-0 run end ************* //\n");

        System.out.println("绘图序列: " + c_scan0.drawFlow);
        System.out.println("寻道长度: " + c_scan0.getLength());
        System.out.println("平均寻道长度: " + c_scan0.getLength() / n);

        return c_scan0;
    }
}
