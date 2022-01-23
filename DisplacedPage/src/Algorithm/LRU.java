package Algorithm;

/**
 * @author JasonGu
 * @date 2021/12/9 22:17
 */

import DataStructure.SinglePage;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 最近最少使用算法
 *Least Recently Used策略：置换内存中最长时间未被引用的页
 */
public class LRU extends algorithm {
    public ArrayList<SinglePage> LRUlist;
    private Vector<Integer> vectotList;
    /**
     * 指针
     */
    private int hand=0;

    /**
     * @param i     内存页框数
     * @param pages 页地址流，存着SinglePage
     */
    public LRU(int i, ArrayList<SinglePage> pages) {
        this.num = i;
        LRUlist = new ArrayList<>(i);
        vectotList = new Vector<>(400);
        // 内存列表
        for (int x = 0; x < pages.size(); x++)
        // 访问页地址流里的第x位存的页号
        {
            // 如果此时内存中的页框没用完
            int a = 0,b=0;
            boolean A = true,B = false;
            while (x>=1&&a < LRUlist.size()) {
                // compare(pages.get(x), fIFOlist.get(j)) 对比页地址流第x位的页号和内存中第j页的页号是否相同
                if (compare(pages.get(x), LRUlist.get(a))) {
                    // 如果相同，不需要置换
                    VisitOperate(pages.get(x).getLocations());
                    A = false;
                    break;
                }
                // 否则，看内存的下一个页框
                else {a++;}
            }
            if (LRUlist.size()<i&&A) {
                // 向内存中添加页
                VisitOperate(pages.get(x).getLocations());
                LRUlist.add(pages.get(x));
                p++;
            }
            if(LRUlist.size()<=i-1)
            {
                B = true;
            }
            else B = false;
            //页框用完后
            int j = 0;
            //j 内存中第j个页框

            //页框用完后，要再存入新页的话，首先要遍历内存里是否存在此页
            while (j < LRUlist.size()&& !B) {
                // compare(pages.get(x), fIFOlist.get(j)) 对比页地址流第x位的页号和内存中第j页的页号是否相同
                if (compare(pages.get(x), LRUlist.get(j))) {
                    // 如果相同，不需要置换
                    VisitOperate(pages.get(x).getLocations());
                    break;
                }
                // 否则，看内存的下一个页框
                else {
                    j++;
                }
            }
            // 遍历完后，发现没有相同的，需要置换
            if (j == LRUlist.size()) {
                // 将页地址流的第x位的页号set进指针所指的页框
                int removedPagenum;
                try
                {
                    removedPagenum = vectotList.remove(0);
                }catch (ArrayIndexOutOfBoundsException e)
                {
                    removedPagenum = -1;
                }


                for(SinglePage page:LRUlist){
                    if(removedPagenum==-1||removedPagenum==page.getLocations()){
                        break;
                    }
                    hand++;
                }
                LRUlist.set(hand,pages.get(x));
                hand = 0; // hand重新初始化
                p++;q++;
            }
        }
    }

    /**
     * 建一个栈，每次访问某一页时将其页号记录进栈顶，若栈中存在同样的页号，则将此页号删除再进行前面所说的操作
     * @param pagenum 页号
     */
    private void VisitOperate(int pagenum)
    {
        if(vectotList.size()==0){
            vectotList.add(pagenum);
        }
        else {
            vectotList.remove(Integer.valueOf(pagenum));
            vectotList.addElement(Integer.valueOf(pagenum));
        }
    }
}
