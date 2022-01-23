package Run;

import Algorithm.FIFO;
import Algorithm.LRU;
import Algorithm.OPTimal;
import DataStructure.Page;
import DataStructure.Position;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author JasonGu
 * @date 2021/12/9 22:52
 * @version v1.0
 */

public class Main {
    /**
     * n 地址序列数
     */
    ArrayList<Position> positionArrayList;
    // 生成n=400条地址序列,前半部空间和后半部空间呈均匀分布
    final public static int n=400;
    public Main()
    {
        // 地址流数组
        positionArrayList = new ArrayList<>();
        // 生成n=400条地址序列,前半部空间和后半部空间呈均匀分布
        Random();
        Page pages = new Page();
        pages.setPages(positionArrayList);
//        pages.PrintPage1();
//        pages.PrintPage2();
        System.out.println("页框数      FIFO缺页率         LRU 缺页率          OPT 缺页率");
        //循环运行，使用户内存容量从 4 页框到 40页框
        for(int i=4;i<=40;i++)
        {
            FIFO fifo =  new FIFO(i,pages.pages);
            LRU lru =  new LRU(i,pages.pages);
            OPTimal opt = new OPTimal(i,pages.pages);
            double p1 = fifo.calculate(n);
            double p2 = lru.calculate(n);
            double p3 = opt.calculate(n);
            System.out.println("["+String.format("%2d",i)+"]"
                    +" FIFO缺页率："+String.format("%5.4f",p1)
                    +"   "+"LRU 缺页率："+String.format("%5.4f",p2)
                    +"   "+"OPT缺页率："+String.format("%5.4f",p3));

        }
        System.out.println("\n页框数      FIFO命中率         LRU 命中率          OPT 命中率");
        //循环运行，使用户内存容量从 4 页框到 40页框
        for(int i=4;i<=40;i++)
        {
            FIFO fifo =  new FIFO(i,pages.pages);
            LRU lru =  new LRU(i,pages.pages);
            OPTimal opt = new OPTimal(i,pages.pages);
            double q1 = fifo.calculate2(n);
            double q2 = lru.calculate2(n);
            double q3 = opt.calculate2(n);
            System.out.println("["+String.format("%2d",i)+"]"
                    +" FIFO命中率："+String.format("%5.4f",q1)
                    +"   "+"LRU 命中率："+String.format("%5.4f",q2)
                    +"   "+"OPT命中率："+String.format("%5.4f",q3));

        }

    }

    private void Random()
    {
        int m=0;

        for(int i=0;i<n;i+=4)
        {
            // 生成随机数据
            Random random = new Random();
            // [0,199]随机选一数m,记录到地址流数组中(这是非是顺序执行)
            m = random.nextInt(200);
            positionArrayList.add(new Position(m));
            // 顺序执行一条指令，将m+1记录进数组
            positionArrayList.add(new Position(m+1));
            // [200,399]随机选一数m*,记录到地址流数组中(这是非是顺序执行)
            m = random.nextInt(200)+200;
            positionArrayList.add(new Position(m));
            // 顺序执行一条指令，将m*+1记录进数组
            positionArrayList.add(new Position(m+1));
        }
    }

//    public static void main(String[] args) {
//        new Main();
//    }
}
