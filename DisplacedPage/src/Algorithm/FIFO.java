package Algorithm;

/**
 * @author JasonGu
 * @date 2021/12/9 22:17
 * 先进先出算法
 * FIFO策略把分配给进程的页框视为一个缓冲区，并按循环方式移动页
 * 隐含逻辑：置换驻留在内存时间最长的页
 * 写另外两个算法只用这两个方法够用了
 * fIFOlist.set(hand,pages.get(x));将页地址流的第x位的页号set进指针所指的位
 * compare(pages.get(x), fIFOlist.get(j));对比页地址流第x位的页号和内存中第j页的页号是否相同
 */

import DataStructure.SinglePage;

import java.util.ArrayList;

public class FIFO extends algorithm {
    public ArrayList<SinglePage> fIFOlist;
    /**
     * 指针
     */
    private int hand=0;

    /**
     *
     * @param i 内存页框数
     * @param pages 页地址流，存着SinglePage
     */
    public FIFO(int i,ArrayList<SinglePage> pages) {
        this.num = i;
        fIFOlist = new ArrayList<>(i);
        // 内存列表
        for (int x = 0; x < pages.size(); x++)
        // 访问页地址流里的第x位存的页号
        {
            // 如果此时内存中的页框没用完
            int a = 0;
            boolean A = true,B = false;
            while (x>=1&&a < fIFOlist.size()) {
                // compare(pages.get(x), fIFOlist.get(j)) 对比页地址流第x位的页号和内存中第j页的页号是否相同
                if (compare(pages.get(x), fIFOlist.get(a))) {
                    // 如果相同，不需要置换
                    A = false;
                    break;
                }
                // 否则，看内存的下一个页框
                else {a++;}
            }
                if (fIFOlist.size()<i&&A) {
                    // 向内存中添加页
                    fIFOlist.add(pages.get(x));
                    p++;
                }
                if(fIFOlist.size()<=i-1)
                {
                    B = true;
                }else B = false;
                //页框用完后
                int j = 0;
                //j 内存中第j个页框

                //页框用完后，要再存入新页的话，首先要遍历内存里是否存在此页
                while (j < fIFOlist.size()&& !B) {
                    // compare(pages.get(x), fIFOlist.get(j)) 对比页地址流第x位的页号和内存中第j页的页号是否相同
                    if (compare(pages.get(x), fIFOlist.get(j))) {
                        // 如果相同，不需要置换
                        break;
                    }
                    // 否则，看内存的下一个页框
                    else {
                        j++;
                    }
                }
                // 遍历完后，发现没有相同的，需要置换
                if (j == fIFOlist.size()) {
                    // 将页地址流的第x位的页号set进指针所指的位
                    fIFOlist.set(hand, pages.get(x));
                    // 指针移位
                    hand = (hand + 1) % fIFOlist.size();
                    p++;q++;
                }
            }
        }
    }
