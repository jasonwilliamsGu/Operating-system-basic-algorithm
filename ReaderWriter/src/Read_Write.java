import java.util.Map;
import java.util.Scanner;

/**
 * @author JasonGu
 * @date 2021/12/18
 * @version v1.0
 * 主程序
 */

public class Read_Write {
    public static void main(String[] args) {
        int i,n,j;
        String type="";
        // 读者或写者标志
        int time_last;
        int time_base=1;
        Scanner input = new Scanner(System.in);
        System.out.print("请输入读者与写者的总数：");
        j = input.nextInt();
        Status.initialize(j);
        // 初始化线程状态
        ClockThread clk = new ClockThread(0);
        // 初始化当前时间为0
        clk.start();
        System.out.println("线程序号    角色     何时开始读写         读写持续时间");
        for(i=1;i<=j;i++){
            n=(int)(Math.random()*2);
            if(n==0){
                type="R";
            }else{
                type="W";
            }
            time_last = 1+(int)(Math.random()*5);
            // 获得1到5之间的随机整数
            (new MultiThread(i,type,time_base,time_last)).start();
            // 开始运行读者/写者线程
            Status.ini_type(type);
            // 初始化线程类型
//            System.out.println("线程"+i+"已创建，角色为:"+type+"，申请读写时间为："+time_base+"读写持续时间为："+time_last);
            System.out.println(String.format("%4d",i)+"       "+type+"      "+String.format("%5d",time_base)
                    +"                 "+String.format("%4d",time_last));
            // 输出线程信息
            time_base+= i+(int)(Math.random()*3);
            //下一个线程生成与该线程隔0到2秒的申请读写时间
        }
    }
}
