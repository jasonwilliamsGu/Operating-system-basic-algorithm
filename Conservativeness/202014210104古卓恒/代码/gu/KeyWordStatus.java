package gu;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author JasonGu
 * @date 2021/11/12 10:46
 */

/**
 * 统计关键词使用情况
 */
public class KeyWordStatus {
    public static final String[] KEYWORDS = {
            "abstract", "assert", "boolean", "break", "byte", "case", "catch",
            "char", "class", "const", "continue", "default", "do", "double", "else",
            "enum", "extends", "final", "finally", "float", "for", "goto", "if",
            "implements", "import", "instanceof", "int", "interface", "long", "native",
            "new", "package", "private", "protected", "public", "return", "short",
            "static", "strictfp", "super", "switch", "synchronized", "this", "throw",
            "throws", "transient", "try", "void", "volatile", "while"};

    private String[] content = null;

    public static Map<String,Integer> keywordsMap=InitializeMap();
    /**
     * 将 content 分片好
     * @param filename
     */
    private void setContent(String filename)
    {
        content = CleanJavaFIle(filename).split(" ");
    }

    /**
     * 将文件清洗为筛选过的字符串
     * @param filename
     * @return content
     */
    private String CleanJavaFIle(String filename)
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


        Pattern pattern= Pattern.compile("((?<!:)\\/\\/.*)|(\\/\\*(\\s|.)*?\\*\\/)|(\"(\\s|.)*?\")|\\W");
//        Pattern pattern= Pattern.compile("((?<!:)\\/\\/.*)|(\\/\\*(\\s|.)*?\\*\\/)|(\"(.*?)\")");
        // 也对，但上面那个更好
        // 第三个正则表达式是去除所有字符串直接量
        // 第四个//W与任何非单词字符匹配。与"[^A-Za-z0-9_]"等效。
//        java.util.regex.Pattern pattern=Pattern.compile("(//[^\n]*)|((/[*]([*@]|[\n]|\\w|\\d|\\s|[^\\x00-\\xff])+[*]/))");
        Matcher matcher=pattern.matcher(content);
        boolean isFIndTarget = matcher.find();
       content = matcher.replaceAll(" ");
//       System.out.println(content);
      // content 为去除掉注释后、去掉字符串直接量后、去掉非单词字符后的content
        return content;
    }

    private static Map<String,Integer> InitializeMap()
    {
        Map<String,Integer> keywordsMap = new HashMap<>(50);
        for(String keyword:KEYWORDS)
        {
            keywordsMap.put(keyword,0);
        }

        return keywordsMap;
    }

    private void CountNumber()
    {
        for(String str:content)
        {
            for(String keyword:KEYWORDS)
            {
                if(keyword.equals(str))
                {
                    keywordsMap.put(str,keywordsMap.get(str)+1);
                }
            }
        }
    }
    public KeyWordStatus(String filename)
    {
        setContent(filename);
        // 此时，得到了处理好的字符串数组content
        CountNumber();
        mapIntoListAndSort(keywordsMap);

    }
    public static void Reset()
    {
        keywordsMap=InitializeMap();
    }

    /**
     *
     * @param keywords map
     * @return map转化为List数组并排序
     */
    public static List<Map.Entry<String,Integer>> mapIntoListAndSort(Map<String, Integer> keywords)
    {
        //  将map.entrySet()转换为list
        List<Map.Entry<String,Integer>> list= new ArrayList<>(keywords.entrySet());
        //  优先按值降序排序，值相同按键的字典序排序
        list.sort((o1, o2) -> {
            if (o2.getValue().equals(o1.getValue()))
            {
                return String.CASE_INSENSITIVE_ORDER.compare(o1.getKey(), o2.getKey());
            }
            return o2.getValue() - o1.getValue();
        });
        return list;
    }

//    public static void main(String[] args) {
//        new gu.KeyWordStatus("F:\\javaProjects\\Conservativeness\\src\\gu.Main.java");
//        System.out.println(gu.KeyWordStatus.keywordsMap);
//    }
    /**
     *
     * @return 把关键字使用情况的结果存进文件中
     */
    public static ArrayList<String> writeIntoFile(){
//        File file = new File(fileName);
        ArrayList<String> returnList = new ArrayList<>();
        Map<String, Integer> map = keywordsMap;
//        if(file.isFile()){
//            map = KeySelectUtil.fileCountKeyWords(fileName);
//        }else{
//            map = KeySelectUtil.dirCountKeyWords(fileName);
//        }
        List<Map.Entry<String, Integer>> list = null;
        if (map != null) {
            list = mapIntoListAndSort(map);
        }
        if (list != null) {
            for (Map.Entry<String, Integer> stringIntegerEntry : list) {
                if(stringIntegerEntry.getValue()>0){
                    returnList.add("["+String.format("%-15s",stringIntegerEntry.getKey())+
                            "\t="+String.format("%5d",stringIntegerEntry.getValue())+"]");
                }
            }
        }
        return returnList;
    }

}
