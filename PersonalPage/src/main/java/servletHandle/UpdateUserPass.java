package servletHandle;

import dao.Userdao;
import javaBean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateUserPass")
public class UpdateUserPass extends HttpServlet {
    private String Oldpassword="";
    private String NewPassword="";
    private String Repassword="";
    private Userdao udao=new Userdao();
    private String username="";
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        Oldpassword=request.getParameter("Oldpassword");
        NewPassword=request.getParameter("Newpassword");
        Repassword=request.getParameter("Repassword");
        if(Oldpassword.trim().equals("")||NewPassword.trim().equals("")||Repassword.trim().equals("")){
            Oldpassword="原密码";
            NewPassword="新密码";
            Repassword="确认密码";
        }
        System.out.println("Oldpassword"+Oldpassword+"NewPassword"+NewPassword+"Repassword"+Repassword);
        username=request.getSession().getAttribute("username")+"";
        if(Oldpassword.trim().equals("原密码")||NewPassword.trim().equals("新密码")||Repassword.trim().equals("确认密码")){
            request.setAttribute("msg","所有选项均不能为空！");
            request.getRequestDispatcher("/PersonalPage/Update Pass.jsp").forward(request,response);
        }else{
            System.out.println(username);
            User u=udao.UserLoadByUserName(username);
            if(u.getPassword().equals(Oldpassword)){
                if(!NewPassword.equals(Repassword)){
                    System.out.println("两次密码输入不匹配！");
                    request.setAttribute("msg","两次密码输入不匹配！");
                    request.getRequestDispatcher("/PersonalPage/Update Pass.jsp").forward(request,response);

                }else{
                    if(udao.UpdaUserPass(username,NewPassword)) {
                        response.getWriter().print("<h1>修改成功！3秒之后自动回到登录页面</h1>");
                        response.setHeader("refresh", "3;url=/index.jsp");
                    }else{
                        response.getWriter().print("<h1>修改失败！3秒之后自动回到修改页面</h1>");
                        response.setHeader("refresh", "3;url=/PersonalPage/Update Pass.jsp");
                    }
                }


            }else{
                System.out.println("原密码输入错误！");
                request.setAttribute("msg","原密码输入错误！");
                request.getRequestDispatcher("/PersonalPage/Update Pass.jsp").forward(request,response);
            }

        }
    }
}
