package gu;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author JasonGu
 * @date 2021/11/9 23:03
 *
 */

/**
 *读入目录，提取java文件到list，
 * 最终得到所有java文件地址
 */
public class AnalyseFile {
    public File file;
    public AnalyseFile(ArrayList<String> list){
        // 初始化操作，清空上次所有静态变量
        Reset();
        file = readFileName();
        findFileList(file,list);
        // list 为存储了java文件绝对地址的list
//        for(String str:list)
//        {
//            System.out.println(str);
//        }
//        System.out.println("总结：共"+list.size()+"个java文件");
    }

    /**
     * 读取文件名，并转为File对象
     * @return file
     */
    private File readFileName()
    {
        boolean i = false;
        File file = null;

        // i 信号量
        while (!i){
            System.out.print("请输入文件名(输入0退出程序,输入1返回上一层)：");
            Scanner sc = new Scanner(System.in);
            String filename = sc.next();
            if(filename.equals("0"))    System.exit(1118);
            if(filename.equals("1"))    new Mainproject();
            file = new File(filename);
            i = file.exists();
            if(!i)
            {
                System.out.println("文件名错误，请重新输入！");

            }
        }
        return file;
    }
    /**
     * 读取目录下的所有文件
     *
     * @param dir
     *            目录
     * @param fileNames
     *            保存文件名的集合
     */
    static void findFileList(File dir, ArrayList<String> fileNames)
    {
        // 判断是否存在目录
        if (!dir.exists() || !dir.isDirectory())
        {

            if(dir.isFile())
            {
                int index = dir.getName().lastIndexOf(".");

                {
                    String sub = dir.getName().substring(index+1);
                    if(sub.equals("java"))
                    {fileNames.add(dir.getAbsolutePath());}

                }
            }
            return;
        }

        String[] files = dir.list();
        // 读取目录下的所有目录文件信息
        for (String s : files)
        {
            // 循环，添加文件名或回调自身
            File file = new File(dir, s);
            if (file.isFile())
            {
                // 如果文件
                int index = file.getName().lastIndexOf(".");
                if (index == -1)
                {
                    break;
                }
                else
                {
                    String sub = file.getName().substring(index + 1);
                    if (sub.equals("java"))
                    {
                        fileNames.add(dir + "\\" + file.getName());
                        // 添加文件全路径名
                    }
                }
            }
            else {
                // 如果是目录
                findFileList(file, fileNames);
                // 回调自身继续查询
            }
        }
    }
    private void Reset()
    {
        AnalyseJavaTask.Reset();
        KeyWordStatus.Reset();
    }
}
