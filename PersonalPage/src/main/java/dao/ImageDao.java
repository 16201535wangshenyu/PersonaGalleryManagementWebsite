package dao;

import javaBean.Image;
import untilBean.JDBCHelper;

import javax.sql.rowset.CachedRowSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * create table FilteTable(
 *  ID int auto_increment primary key,
 *  Filename char(248),
 *  FileClass char(248)
 *  );
 *  图片类别解释如下：
 *  A:唯美图片
 *  B:可爱图片
 *  C:心情图片
 *  D:伤感图片
 *  E:励志图片
 *  F:中秋图片
 *  G:晚安图片
 *  H:梦幻图片
 *  I：其他图片
 *  O:全部图片
 */

public class ImageDao {
    private String tableName=" FilteTable ";
    private Image img=null;
    /**
     * 根据文件名来加载文件
     */
    public Image LoadImageByName(String ImageName){
        Image image=null;
        String SQL="select * from FilteTable where Filename='"+ImageName+"'";
        ResultSet rs= JDBCHelper.query(SQL);
        try {
            while(rs.next()){
                image=new Image();
                image.setFileClass(rs.getString("FileClass"));
                image.setFileName(rs.getString("Filename"));
                image.setId(rs.getString("ID"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 一次性加载所有的图片,一页15条记录，
     * @return
     */
    public ArrayList<Image> LoadImgAllByClass(String fileClass,String userName){
        ResultSet rs;
        //多表组合查询将图片文件与用户关联起来
        String SQL="select * from "+tableName +",user_file where FileClass=? " +
                "and username=? and FilteTable.id=user_file.fileID ";

        if(fileClass.equals("O")) {
            SQL = "select * from " + tableName+",user_file where userName='"+userName+"'"+" and" +
                    " FilteTable.id=user_file.fileID";
            rs= JDBCHelper.query(SQL);
        }else {
            rs = JDBCHelper.query(SQL, fileClass,userName);
        }
        ArrayList<Image> imageList=new ArrayList<>();
        try {
            while(rs.next()){
                img=new Image();
                img.setId(String.valueOf(rs.getInt("ID")));
                img.setFileName(rs.getString("Filename"));
                img.setFileClass(rs.getString("FileClass"));
                imageList.add(img);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return imageList;

    }


    /**
     * 根据图片类型分页加载图片
     * @return
     */
    public ArrayList<Image> LoadImgAllByPages(int currentPage,String fileClass,String username){
        String SQL="";
        ArrayList<Image> imageList1=new ArrayList<>();

        if(fileClass.equals("O")) { //如果是全部类型
            System.out.println("我是全部类型");
            SQL = "select * from " + tableName+",user_file where username='"+username+"'"+" and" +
                    " FilteTable.id=user_file.fileID";

        }else{              //否则就是按类别加载
            System.out.println("我不是全部类型");
            SQL="select * from "+tableName +",user_file where FileClass='" +fileClass+"'"+
                    "and username='"+username+"'"+" and FilteTable.id=user_file.fileID ";
        }
        CachedRowSet rs= JDBCHelper.queryByPage(SQL,15,currentPage);

        try {
            while(rs.next()){
                img=new Image();
                img.setId(String.valueOf(rs.getInt("ID")));
                img.setFileName(rs.getString("Filename"));
                img.setFileClass(rs.getString("FileClass"));
                imageList1.add(img);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return imageList1;

    }
    /**
     * 计算某用户的某类图片记录可分为几页
     */
    public int getTotalPages(String fileClass,String username){

        ArrayList<Image> imageList=LoadImgAllByClass(fileClass,username);
        if(imageList!=null){
            if(imageList.size()%15!=0)
            return imageList.size()/15+1;
            else
                return imageList.size()/15;
        }
        return 0;

    }
    /**
     * 插入图片
     * @param imageName
     * @param imageClass
     * @return
     */
    public Boolean insertImage(String imageName,String imageClass,String username){
        String SQL="insert into "+tableName+"(Filename,FileClass) values(?,?)";
        String SQL1="insert into user_file values(?,?)";
         JDBCHelper.execute(SQL,imageName,imageClass);
         Image image= LoadImageByName(imageName);

        JDBCHelper.execute(SQL1,image.getId(),username);
         return true;
    }

    /**
     * 根据ID号来删除图片
     * @param ImagID
     * @return
     */
    public Boolean deleteImageByID(String ImagID){
        String SQL="delete from "+tableName+" where ID="+ImagID;

        return JDBCHelper.execute(SQL);

    }
}
