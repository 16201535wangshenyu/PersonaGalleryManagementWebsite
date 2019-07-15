import dao.ImageDao;
import javaBean.Image;

import java.util.ArrayList;

public class test2 {
    public static void main(String[] args) {
        ImageDao iag=new ImageDao();
       /* ArrayList<Image> imageList=iag.LoadImgAllByPages(1,"O");
        int i=0;
     for(Image a:imageList){
         i++;
         System.out.println(a.getId()+"   "+a.getFileName()+"  "+a.getFileClass());
     }
        System.out.println(i);
       *//* System.out.println("totalPage:"+iag.getTotalPages("A"));*//**/
    }
}
