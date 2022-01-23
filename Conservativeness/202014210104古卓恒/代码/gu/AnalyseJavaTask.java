package gu;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author JasonGu
 * @date 2021/11/18 0:14
 */

public class AnalyseJavaTask implements Runnable {
    // 静态变量，通过类调用
    public  static int allcharactere=0;
    // 文件信息
    public static int singalLineComment=0,multiLineComment=0,comment=0;
    // 注释信息 comment 注释个数
    public static StringBuilder sumOfComment =new StringBuilder();
    // 存储所有注释

    String filename = "";
    // 可变变量

    @Override
    public void run()
    {
        StringNum(filename);
        CommentStatus(filename);
        new KeyWordStatus(filename);
    }
    /**
     * 初始化文件名
     * @param filename 文件名
     */
    public AnalyseJavaTask(String filename)
    {
        this.filename = filename;
    }

    public static void Reset()
    {
        allcharactere=0;
        singalLineComment=multiLineComment=comment=0;
        sumOfComment =new StringBuilder();
    }
    /**
     * 统计java源程序字符个数
     * @param filename 文件名
     */
    private  void StringNum(String filename)
    {
        File file = new File(filename);
//        new gu.KeyWordStatus(filename);
        //
        if(!file.exists()) {
            System.out.println("The file is Null");
            System.exit(0);
        }
        try {
            Scanner cin = new Scanner(file);
            int charactere=0;

            // 逐行遍历
            while(cin.hasNext()) {
                String line = cin.nextLine();
                charactere+=line.length();
            }
            allcharactere+=charactere;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 统计注释相关
     * @param filename 文件名
     */
    private void CommentStatus(String filename)
    {


        // 全文件查找注释
        StringBuilder sb=new StringBuilder();
        try (LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filename)))
        {
            String line = null;

            while ((line = lineNumberReader.readLine()) != null)
            {
                sb.append(line+"\n");
                // \n is necessary
            }
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        String content=sb.toString();

        // (//[^\n]*):双斜杠注释
        // ((/[*]([*@]|[\n]|\\w|\\d|\\s|[^\\x00-\\xff])+[*]/)):斜杠星注释
        // /[*]:Start /*
        // [*@]:allow * @
        // [\n]:allow new line
        // \\w|\\d|\\s:allow word,digit,space
        // [^\\x00-\\xff]:allow double bytes characters
        // +:([*@]|[\n]|\\w|\\d|\\s|[^\\x00-\\xff]) repeat at least once
        // [*]/:end */
        Pattern pattern= Pattern.compile("((?<!:)\\/\\/.*)|(\\/\\*(\\s|.)*?\\*\\/)");
//        java.util.regex.Pattern pattern=Pattern.compile("(//[^\n]*)|((/[*]([*@]|[\n]|\\w|\\d|\\s|[^\\x00-\\xff])+[*]/))");
        Matcher matcher=pattern.matcher(content);
        boolean isfindTarget=matcher.find();
        while(isfindTarget) {
            if(matcher.group(1)!=null) {
                singalLineComment++;
                comment++;
                sumOfComment.append(matcher.group(1)+"\n");
//                System.out.println("单行注释：" + ":" + matcher.group(1)+"\n");

            }else if(matcher.group(2)!=null) {
                multiLineComment++;
                comment++;
                sumOfComment.append(matcher.group(2)+"\n");
//                System.out.println("多行注释：\n" + ":" + matcher.group(2)+"\n");
            }

            isfindTarget=matcher.find();

        }

    }
}
