package gu;

import java.util.ArrayList;


/**
 * @author JasonGu
 * @date 2021/11/9 23:32
 */

public class Main {
    public static void main(String[] args) {
       new Mainproject();

    }
}

class Mainproject{
    public Mainproject()
    {
        new Interface();
        int i = Interface.getNum();
        while (true){
            if(i==1)
            {
                ArrayList<String> list = new ArrayList<>();
                // 保存所有java文件信息
                AnalyseFile a = new AnalyseFile(list);
                new AnalyseJava(list);
                new AboutText(a.file,list);

            }
            if(i==2)
            {
                new AboutText();
            }
            if(i==0){
                System.exit(1117);
            }
        }
    }
}
