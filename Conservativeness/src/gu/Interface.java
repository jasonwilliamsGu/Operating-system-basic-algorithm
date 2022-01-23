package gu;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author JasonGu
 * @date 2021/11/17 20:44
 */

public class Interface {
    public Interface()
    {
        System.out.println("-------MENU-------");
        System.out.println("1.分析目录或源程序文件");
        System.out.println("2.查看已有的分析结果");
        System.out.println("0.退出");
        System.out.println("------------------");
    }
    public static int getNum()
    {
        int i = 3;
        while((i!=1)&(i!=2)&(i!=0))
        {
            System.out.print("请选择：");
            Scanner sc = new Scanner(System.in);
            try{
                i = sc.nextInt();
            }catch (InputMismatchException e)
            {
                System.out.println("数字输入错误，请重新输入");
                continue;
            }
            if((i!=1)&(i!=2)&(i!=0))
                System.out.println("数字输入错误，请重新输入");
        }
        return i;
    }
}
