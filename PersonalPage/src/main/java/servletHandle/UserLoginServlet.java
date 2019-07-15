package servletHandle;

import dao.Userdao;
import javaBean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet {
    private User user;
    private Userdao userDao;
    private String username="";
    private String password="";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user=new User();
        userDao=new Userdao();
        if(request.getParameter("username")!=null)
        username=request.getParameter("username");
        if(request.getParameter("password")!=null)
        password=request.getParameter("password");
        /*username="16201535";
        password="16201535";*/
        if(username.equals("Username")||password.equals("Password")){

            request.setAttribute("msg","用户名密码不能为空！");
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }else{
            System.out.println("username"+username);
            user=userDao.UserLoadByUserName(username);
            if(user!=null&&password.equals(user.getPassword())){
                request.getSession().setAttribute("username",username);
                response.sendRedirect("/loadImage");
            }else{
                request.setAttribute("msg","用户名密码不匹配！");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }

        }



    }
}
