package Algorithm;

import DataStructure.SinglePage;

import java.util.ArrayList;

/**
 * @author JasonGu
 * @date 2021/12/10 9:08
 */

public abstract class algorithm {
    /**
     * 页框填满之前和之后的总缺页次数
     */
    public int p=0;
    /**
     * 页框填满之后的总缺页次数
     */
    public int q=0;
    /**
     * 页框数
     */
    public int num=0;
    public ArrayList<SinglePage> pages;
    /**
     *
     * p 页框填满之前和之后的总缺页次数
     * @param i 页地址流大小
     * @return 缺页率
     */
    public double calculate(int i)
    {
        return p/Double.valueOf(i);
    }

    /**
     *
     * @param i 页地址流大小
     * @return 命中率
     */
    public double calculate2(int i)
    {
        return 1-(double)q/i;
    }
    public static boolean compare(SinglePage page1, SinglePage page2){
        if (page1.compareTo(page2)==0) {
            return true;
        }
        else {return false;}
    }
}
