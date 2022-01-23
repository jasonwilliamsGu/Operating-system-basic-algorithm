import java.util.ArrayList;

/**
 * @author JasonGu
 * @date 2021/12/18
 * 用于记录线程状态的数组
 */

public class Status {
    private static ArrayList<Integer> stat = new ArrayList<>();
    // 记录线程状态的数组
    private static ArrayList<String> thr = new ArrayList<>();
    // 记录线程类型的数组（reader/wirter)

    /**
     * 根据线程总数初始化stat数组每个元素为0
     * @param n 线程总数
     */
    public static void initialize(int n){
        for(int i=0;i<n;i++) {
            stat.add(0);
        }
    }

    /**
     * 初始化线程类型
     * @param s 线程类型
     */
    public static void ini_type(String s){
        thr.add(s);
    }

    /**
     * 检查从第一个创建的线程到当前线程的前一个创建的读者线程中，有没有正在读的线程
     * @param n 当前线程
     * @return 可不可以开始读
     */
    public static boolean check_reader(int n){
        int flag = 0;
        for(int i=0;i<n-1;i++){
            if(thr.get(i)=="R"){
                // 是读者
                if(stat.get(i)==2){
                    // 正在读
                    flag = 1;
                    break;
                }
            }
        }
        if(flag==1){
            return true;
            // 可以开始读
        }else{
            return false;
            // 不可以开始读
        }
    }

    /**
     * 修改状态为“已申请读写”
     * @param n
     */
    public static void modify_apply(int n){
        stat.set(n-1,1);
    }

    /**
     * 修改状态为“已开始读写”
     * @param n
     */
    public static void modify_start(int n){
        stat.set(n-1,2);
    }

    /**
     * 修改状态为“已完成读写”
     * @param n
     */
    public static void modify_finish(int n){
        stat.set(n-1,3);
    }

    /**
     * 检查从第一个线程到当前前一个线程中，有没有已申请读或写，但还没有开始读或写的线程
     * @param n
     * @return 能不能执行wsem--
     */
    public static boolean check(int n){
        int flag = 0;
        for(int i=0;i<n-1;i++){
            if(stat.get(i)==1){
                flag = 1;
                break;
            }
        }
        if(flag==1){
            return false;
            // 不能执行wsem--
        }else{
            return true;
            // 能执行wsem--
        }
    }

    /**
     *
     * @return 返回所有线程的完成情况
     */
    public static boolean all_finish(){
        int flag = 0;
        for(int i=0;i<stat.size();i++){
            if(stat.get(i)!=3){
                flag = 1;
                break;
            }
        }
        if(flag == 0){
            return true;
            // 所有线程已完成
        }else{
            return false;
            // 有线程未完成
        }
    }
    public Object getObj(){
        return new Object();
    }
}
