package servletHandle;

import dao.Userdao;
import javaBean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/userRegist")
public class UserRegistServlet extends HttpServlet {
    private User user;
    private Userdao userDao;
    private String username="";
    private String password="";
    private String repassword="";

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");*/
        user=null;
        userDao=new Userdao();

        username=request.getParameter("username");
        password=request.getParameter("password");
        repassword=request.getParameter("Repassword");
        if(username.equals("Username")||password.equals("Password")){

            request.setAttribute("msg","用户名密码不能为空！");
            request.getRequestDispatcher("/regist.jsp").forward(request,response);
        }else{
            if(password.equals(repassword)) {
                user = userDao.UserLoadByUserName(username);
                if (user != null) {
                    System.out.println("用户已经存在!" + user);
                    request.setAttribute("msg", "该用户已经存在！");
                    request.getRequestDispatcher("/regist.jsp").forward(request, response);
                } else {
                    System.out.println("用户已经注册!");
                    Boolean result = userDao.AddUser(username, password);
                    if (result) {
                        System.out.println("注册成功！");
                        response.getWriter().print("<h1>注册成功!，3秒之后自动回到登录页面</h1>");
                        response.setHeader("refresh", "3;url=/index.jsp");

                    } else {
                        response.getWriter().print("<h1>注册失败!，3秒之后自动回到注册页面</h1>");
                        response.setHeader("refresh", "3;url=/regist.jsp");

                    }
                }
            }
            else {
                request.setAttribute("msg", "两次密码不一致！");
                request.getRequestDispatcher("/regist.jsp").forward(request,response);
            }



        }




    }
}
