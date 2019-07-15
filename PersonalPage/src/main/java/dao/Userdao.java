package dao;

import javaBean.User;
import untilBean.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * create table user_file(
 *   fileID int,
 *  userName char(148),
 *   primary key(fileID,userName)
 *  )
 *
 */

public class Userdao {
    private String table=" user ";
    private User user;

    /**
     * 根据用户名，加载用户
     * @param username
     * @return
     */

    public User UserLoadByUserName(String username){
        user=null;
        System.out.println("username"+username);
        String SQL=" select * from "+table+" where username='"+username+"'";
        ResultSet rs= JDBCHelper.query(SQL);

        try {
            while (rs.next()) {
                user = new User();
                user.setId(String.valueOf(rs.getInt("ID")));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
            }
            return user;

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加新的用户，注册用户
     * @param username
     * @param password
     * @return
     */
     public boolean AddUser(String username,String password){
        String SQL="insert into "+table+"(username,password) values(?,?)";


        return JDBCHelper.execute(SQL,username,password);

     }

    /**
     * 根据用户名来修改密码
     * @param username
     * @param password
     * @return
     */
     public boolean UpdaUserPass(String username,String password){
         String SQl="update "+table +"set password='"+password+"'" +" where username=?";
         return JDBCHelper.execute(SQl,username);
     }
    /**
     * 往用户-图片文件关系表中插入数据
     */
    public boolean insertUser_File(String userID,String FileId){
        String SQL="insert into user_file values(?,?)";
        return JDBCHelper.execute(SQL,FileId,userID);
    }


}
