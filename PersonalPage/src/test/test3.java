import dao.Userdao;
import javaBean.User;

public class test3 {
    public static void main(String[] args) {
        Userdao ud=new Userdao();
        User u=ud.UserLoadByUserName("16201535");
        System.out.println(u.getPassword());
    }
}
