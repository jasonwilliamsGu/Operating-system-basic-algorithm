package Algorithm;

/**
 * @author JasonGu
 * @date 2021/12/9 22:16
 * OPT 最佳置换法
 * OPT策略选择置换下次访问距当前时间最长的那些页，这种算法导致缺页中断最少
 * 它要求操作系统必须知道将来的事件，因此不可能实现，现在模拟实现
 */

import DataStructure.SinglePage;
import java.util.ArrayList;

public class OPTimal extends algorithm {
    ArrayList<SinglePage> OPTlist;

    public OPTimal(int i, ArrayList<SinglePage> pages) {
        this.num = i;
        OPTlist = new ArrayList<>(i);

        // 内存列表
        for (int x = 0; x < pages.size(); x++)
        // 访问页地址流里的第x位存的页号
        {
            // 如果此时内存中的页框没用完
            int a = 0;
            boolean A = true, B = false;
            while (x >= 1 && a < OPTlist.size()) {
                // compare(pages.get(x), fIFOlist.get(j)) 对比页地址流第x位的页号和内存中第j页的页号是否相同
                if (compare(pages.get(x), OPTlist.get(a))) {
                    // 如果相同，不需要置换
                    A = false;
                    break;
                }
                // 否则，看内存的下一个页框
                else {a++;}
            }
            if (OPTlist.size() < i && A) {
                OPTlist.add(pages.get(x));
                p++;
            }
            if (OPTlist.size() <= i - 1) {
                B = true;
            }else B = false;
            //页框用完后
            int j = 0;
            //j 内存中第j个页框

            //页框用完后，要再存入新页的话，首先要遍历内存里是否存在此页
            while (j < OPTlist.size() && !B) {
                // compare(pages.get(x), fIFOlist.get(j)) 对比页地址流第x位的页号和内存中第j页的页号是否相同
                if (compare(pages.get(x), OPTlist.get(j))) {
                    // 如果相同，不需要置换
                    break;
                }
                // 否则，看内存的下一个页框
                else {
                    j++;
                }
            }
            // 遍历完后，发现没有相同的，需要置换
            if (j == OPTlist.size()) {
                int hand = Find(OPTlist,pages,x+1);
                if(hand>=0){OPTlist.set(hand, pages.get(x));
                p++;q++;}
            }
        }
    }

    /**
     * 寻找下次访问距当前时间最长的那些页
     * @param pages 页地址流
     * @param OPTlist 内存列表
     * @param X 开始寻找的位置指针
     * @return hand 替换的页框的指针
     */
    private int Find(ArrayList<SinglePage> OPTlist,ArrayList<SinglePage> pages,int X) {
        int hand=-1,max=0;
        for(int a=0;a<OPTlist.size();a++){
            for(int x=X;x<pages.size();x++)
            {
                if(compare(OPTlist.get(a),pages.get(x))){
                    int m = x-X-1;
                    if(m>=max){
                        max = m;
                        hand = a;
                        break;
                    }
                }
            }
        }
        return hand;
    }

}
