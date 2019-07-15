import dao.ImageDao;
import dao.Userdao;
import javaBean.Image;
import untilBean.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class test4 {
    public static void main(String[] args) {
        Userdao ud=new Userdao();
        ImageDao id=new ImageDao();
        ArrayList<Image> a=new ArrayList<>();
        String SQL="select * from FilteTable";
        ResultSet resultSet=JDBCHelper.query(SQL);
        try {
            while (resultSet.next()){
                Image aa=new Image();
                aa.setId(resultSet.getString("id"));
                aa.setFileName(resultSet.getString("filename"));
                aa.setFileClass(resultSet.getString("FileClass"));
                a.add(aa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*for(Image aaa:a) {
            ud.insertUser_File("16201535", aaa.getId());

        }*/
        ResultSet rs=JDBCHelper.query("select * from user_file");
        int i=0;
        try {

            while (rs.next()) {
                i++;
                System.out.println(rs.getString("fileID") + ":" + rs.getString("username"));
            }
            System.out.println(i);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
