package DataStructure;

/**
 * @author JasonGu
 * @date 2021/12/9 23:49
 */

import java.util.ArrayList;

/**
 * 页地址（页号）
 */

/**
 * 用户虚存中，每 K 存放10 条指令，所以那 400 条指令访问地址所对应的页地址（页号）流为：指令访问地址为[0 ，9] 的地址为第 0 页；指令访问地址为[10，19]的地址为24
 * 第1 页；……。按这种方式，把 400 条指令组织进“40页”，并将“要访问的页号序
 * 列”记录到页地址流数组中。
 */
public class Page implements Cloneable{
    public ArrayList<SinglePage> pages;

    public Page(){
        pages = new ArrayList<>();
    }

    public void setPages(ArrayList<Position> positionArrayList) {
        int n=0;
        SinglePage[]page = new SinglePage[41];
        for(int i=0;i<41;i++){
            page[i] = new SinglePage();
            page[i].setLocations(i);
        }
        while(n<positionArrayList.size()) {
            int location = positionArrayList.get(n).getLocation();
            page[location/10].addLocation(positionArrayList.get(n));
            this.pages.add(page[location/10]);
            n++;
        }
    }


    public void PrintPage1() {
        for(SinglePage page:pages) {
            page.PrintLocation();
        }
    }
    public void PrintPage2(){
        int i=0;
        System.out.println("页地址流：");
        for(SinglePage page:pages){
            System.out.print(String.format("%2d",page.getLocations())+"  ");
            i++;
            if(i==10) {System.out.println();i=0;}
        }
    }
}
