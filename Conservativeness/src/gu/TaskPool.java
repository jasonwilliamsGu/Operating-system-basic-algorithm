package gu;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

/**
 * @author JasonGu
 * @date 2021/11/18 0:06
 */

public class TaskPool {
    public TaskPool(ArrayList<String> list) {

//        ExecutorService pool = Executors.newFixedThreadPool(list.size());
        /*
        Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
        Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池
        Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池
         */
        for (String filename : list)
        {
            AnalyseJavaTask task = new AnalyseJavaTask(filename);
            task.run();
//            pool.execute(task);
        }
//        pool.shutdown();
//        PrintTaskPool(pool);
    }

    private void PrintTaskPool(ExecutorService pool){
        Boolean i =true;
        // 信号量，看主线程是否执行了
        while (i)
        {
            if(pool.isTerminated())
            {
//                System.out.println("所有源程序文件的各类总和如下：");
//                System.out.println("字符个数总和："+gu.AnalyseJavaTask.allcharactere+"个");
//                // 字符个数总和
//
//                System.out.println("注释总和："+gu.AnalyseJavaTask.comment+"个，其中，单行注释 "+gu.AnalyseJavaTask.singalLineComment+
//                        " 个，多行注释 "+gu.AnalyseJavaTask.multiLineComment+" 个");
//                System.out.println("注释的字符个数："+gu.AnalyseJavaTask.sumOfComment.length());
//                System.out.println(gu.KeyWordStatus.mapIntoListAndSort(gu.KeyWordStatus.keywordsMap));
                //打印关键词列表
                i = false;
            }

        }
    }

    }
