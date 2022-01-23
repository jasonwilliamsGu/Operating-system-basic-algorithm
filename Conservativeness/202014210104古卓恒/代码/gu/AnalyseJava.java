package gu;

import java.util.ArrayList;

/**
 * @author JasonGu
 * @date 2021/11/10 8:37
 */

/**
 *线程池建立，多线程操作
 */

public class AnalyseJava
{
    public AnalyseJava(ArrayList<String> list)
    {
        new TaskPool(list);
    }
}