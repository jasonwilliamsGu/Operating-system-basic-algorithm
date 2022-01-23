import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author JasonGu
 * @date 2021/12/18
 * 读写线程
 */

public class MultiThread extends Thread{
    /**
     * 表示正在进行读操作的线程数量
     */
    static int readcount = 0;
    /**
     * x为修改readcount的互斥信号量，wsem为读写互斥信号量
     */
    static int x=1,wsem=1;

    private int NO;
    // 线程编号
    private String type;
    // 线程类型
    private int time_apply;
    // 申请读写时间
    private int time_last;
    // 读写持续时间

    /**
     * 定义一个互斥锁🔒，用于实现原子操作
     */
    static Lock lock = new ReentrantLock();

    public MultiThread(int NO,String type,int time_apply,int time_last){
        this.NO = NO;
        this.type = type;
        this.time_apply = time_apply;
        this.time_last = time_last;
    }

    @Override
    public void run(){
        if(type.equals("R")){
            // 读者
            while(true){
                try {
                    Thread.sleep(500);
                    // 暂停执行500ms
                }catch (InterruptedException ex){
                    Logger.getAnonymousLogger(MultiThread.class.getName()).log(Level.SEVERE,null,ex);
                    // 日志处理
                }
                if(time_apply==ClockThread.getcurtime()){
                    // 当前时间为申请读写时间
                    Status.modify_apply(NO);
                    // 修改状态为“已申请读写”
                    System.out.println("线程"+NO+":"+type+"申请 读取！"+"当前时间:"+ClockThread.getcurtime());
                    break;
                }
            }
            semWaitx();
            // readcount互斥信号量的wait操作
            while (true) {
                if(getX()>=0){
                    break;
                }
            }
            readcount++;
            semSignalx();

            if(readcount == 1){
                semWaitwsem(); // 数据加锁
                //wsem的wait操作
            }else{
                while (true){
                    if(Status.check_reader(NO)==true){
                        // 检查在这个线程之前有没有正在读的线程
                        break;
                    }
                    try {
                        sleep(500);
                        // 暂停执行500ms
                    }catch (InterruptedException ex){
                        Logger.getAnonymousLogger(MultiThread.class.getName()).log(Level.SEVERE,null,ex);
                        // 日志处理
                    }
                }
            }
            Status.modify_start(NO);
            // 修改状态为“已开始读写”
            System.out.println("线程"+NO+":"+type+"开始 读取！"+"当前时间:"+ClockThread.getcurtime());


            while (true){
                try {
                    sleep(1000);
                    // 暂停执行1000ms
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                time_last--;
                //读写持续时间减1s
                if(time_last==0){
                    System.out.println("线程"+NO+":"+type+"已完成 读作业"+"，当前时间:"+ClockThread.getcurtime());
                    Status.modify_finish(NO);
                    break;
                }
            }


            semWaitx();
            // readcount互斥信号量的wait操作
            while (true) {
                if(getX()>=0){
                    break;
                }
            }
            readcount--;
            // 正在进行操作的线程量减1
            if(readcount==0){
                semSignalwsem();    // 数据解锁,释放访问权
                // wssem的signal操作
            }
            semSignalx();
        }else if(type.equals("W")){
            // 写者
            while(true){
                try {
                    Thread.sleep(500);
                    // 暂停执行500ms
                }catch (InterruptedException ex){
                    Logger.getAnonymousLogger(MultiThread.class.getName()).log(Level.SEVERE,null,ex);
                    // 日志处理
                }
                if(time_apply==ClockThread.getcurtime()){
                    // 当前时间为申请读写时间
                    Status.modify_apply(NO);
                    // 修改状态为“已申请读写”
                    System.out.println("线程"+NO+":"+type+"申请 写入！"+"当前时间:"+ClockThread.getcurtime());
                    break;
                }
            }
            semWaitwsem();
            //wsem的wait操作
            Status.modify_start(NO);
            // 修改状态为已开始读写
            System.out.println("线程"+NO+":"+type+"开始 写入！"+"当前时间:"+ClockThread.getcurtime());

            while (true){
                try {
                    Thread.sleep(1000);
                    // 暂停执行1000ms
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                time_last--;
                //读写持续时间减1s
                if(time_last==0){
                    System.out.println("线程"+NO+":"+type+"已完成 写操作"+"，当前时间:"+ClockThread.getcurtime());
                    Status.modify_finish(NO);
                    break;
                }
            }
            semSignalwsem();
        }
    }

    public int getX(){
        return x;
    }

    public int getWsem(){
        return wsem;
    }

    /**
     * readacount的互斥信号量的wait操作
     */
    public void semWaitx(){
        x--;
    }

    /**
     * wsem的伪操作
     */
    public void semWaitwsem(){
        lock.lock();
        //获得锁
        while (true){
            try {
                sleep(100);
                // 暂停执行100ms
            }catch (InterruptedException ex){
                Logger.getAnonymousLogger(MultiThread.class.getName()).log(Level.SEVERE,null,ex);
                // 日志处理
            }
            if(getWsem()==1){
                if(Status.check(NO)==false){
                    // 检查从第一个创建的线程到在当前的线程的前一个创建的线程之中，有没有已经申请读写，但还没有开始读写的线程
                    continue;
                }
                break;
            }
        }
        wsem--;
        lock.unlock();
        // 释放锁
    }

    /**
     * readacount互斥信号量的signal操作
     */
    public void semSignalx(){
        x++;
    }

    /**
     * wsem的signal操作
     */
    public void semSignalwsem(){
        wsem++;
    }

}
