package gu;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author JasonGu
 * @date 2021/11/17 21:10
 */

public class AboutText {
    public AboutText() {
        ReadText();
    }

    public AboutText(File file, ArrayList<String> list) {
        SaveText(file, list);
    }

    private void ReadText() {
        String dirName = "data";
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String[] texts = dir.list();
        if (texts.length==0) {
            System.out.println("尚未有分析文件");
            new Mainproject();
        }
        else{
            System.out.println("------------------------------------------------");
            int i = 1;
            for (String txt : texts) {
                System.out.println(i + " -- " + txt);
                i++;
            }
            System.out.println("------------------------------------------------");
            System.out.print("请输入要查看的文件编号：");
            Scanner sc = new Scanner(System.in);
            int x = 0;
            while (x<=0||x>texts.length){
                try{

                    x = sc.nextInt();
                }catch (InputMismatchException e)
                {
                    System.out.println("数字输入错误，请重新输入");
                    continue;
                }
                if(x<=0||x>texts.length){
                    System.out.println("数字输入错误，请重新输入");}
            }
            String txt_name = dir.getAbsolutePath()+"\\"+texts[x-1];
            int z = OpenTxt(txt_name);
            if(z==1)
            {
                new Mainproject();
            }
            else if (z==2)
            {
                ReadText();
            }
            else if(z==0)
            {
                System.exit(1119);
            }
        }

    }

    private int OpenTxt(String txt_name)
    {
        try {
            Runtime.getRuntime().exec("rundll32 url.dll FileProtocolHandler "  +  txt_name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String txt = txt_name.substring(txt_name.lastIndexOf("\\")+1);
//        ControlWindowsConsole.console("/k","cls");
        System.out.println("文件："+txt+"已打开\n");
        System.out.println("""
                ------------------
                1.返回上一层
                2.查看其他文件
                0.退出
                ------------------""");
        return Interface.getNum();
    }
    private void SaveText(File file, ArrayList<String> list) {
        String dirName = "data\\";
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdir();
        }

        String filename = file.getPath().replaceAll("\\\\", "_").replaceAll(":", "_");
        String textName = "data\\" + filename + "_result.txt";
        if (list.size() > 1) {
//            Date date = new Date();
//            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

            try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(textName))) {
                String[] strings = {
                        "------------------------------------------------",
                        "分析目录\t\t: " + file.getAbsolutePath(),
                        "Java源程序文件个数\t:" + String.format("%15d",list.size()),
                        "源程序中字符总个数\t:" + String.format("%15d",AnalyseJavaTask.allcharactere),
                        "注释总个数\t:" + String.format("%15d",AnalyseJavaTask.comment),
                        "   1)单行注释个数\t:" + String.format("%15d",AnalyseJavaTask.singalLineComment),
                        "   2)多行注释个数\t:" + String.format("%15d",AnalyseJavaTask.multiLineComment),
                        "注释总的字符数\t:" + String.format("%15d",AnalyseJavaTask.sumOfComment.length()),
                        "关键字的使用情况如下:"
                };
                for (int i = 0; i < strings.length; i++) {
                    bufferedWriter.write(strings[i]);
                    bufferedWriter.newLine();
                    if (i == 1 || i == 7) {
                        bufferedWriter.newLine();
                    }
                }
                ArrayList<String> keyword_list = KeyWordStatus.writeIntoFile();
                for (String s : keyword_list) {
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                }
                bufferedWriter.write("------------------------------------------------");
//                ControlWindowsConsole.console("/k","cls");
                System.out.println("文件分析结束，分析结果存放在文件[" + textName + "]中");
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        if (list.size() == 1) {
            try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(textName))) {
                String[] strings = {
                        "------------------------------------------------",
                        "分析文件\t\t:" + file.getAbsolutePath(),
//                        "Java源程序文件个数\t:" + list.size(),
                        "源程序中字符总个数\t:" + String.format("%15d",AnalyseJavaTask.allcharactere),
                        "注释总个数\t:" + String.format("%15d",AnalyseJavaTask.comment),
                        "   1)单行注释个数\t:" + String.format("%15d",AnalyseJavaTask.singalLineComment),
                        "   2)多行注释个数\t:" + String.format("%15d",AnalyseJavaTask.multiLineComment),
                        "注释总的字符数\t:" + String.format("%15d",AnalyseJavaTask.sumOfComment.length()),
                        "关键字的使用情况如下:"
                };
                for (int i = 0; i < strings.length; i++) {
                    bufferedWriter.write(strings[i]);
                    bufferedWriter.newLine();
                    if (i == 1 || i == 7) {
                        bufferedWriter.newLine();
                    }
                }
                ArrayList<String> keyword_list = KeyWordStatus.writeIntoFile();
                for (String s : keyword_list) {
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                }
                bufferedWriter.write("------------------------------------------------");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("文件分析结束，分析结果存放在文件[" + textName + "中");
            }
//            ControlWindowsConsole.console("/k","cls");
            System.out.println("文件分析结束，分析结果存放在文件[" + textName + "中");
        }


    }
}

