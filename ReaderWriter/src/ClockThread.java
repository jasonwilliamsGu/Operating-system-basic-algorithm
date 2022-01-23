/**
 * @author JasonGu
 * @date 2021/12/18
 * 时钟线程，用于记录时间
 */

public class ClockThread extends Thread{
    private static int curtime;
    // 当前时间

    public ClockThread(int curtime){
        ClockThread.curtime = curtime;
    }

    @Override
    public void run(){
        while (true){
            try {
                Thread.sleep(1000);
                // 暂时停止1000ms,即1s
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            curtime++;
            System.out.println("CLK:"+curtime);
            if(Status.all_finish()){
                // 判断所有线程是否已完成
                System.out.println("所有线程已完成！");
                break;
            }
        }
    }

    public static int getcurtime(){
        return curtime;
    }
}
