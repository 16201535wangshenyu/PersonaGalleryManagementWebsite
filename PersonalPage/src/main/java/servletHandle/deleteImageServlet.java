package servletHandle;

import dao.ImageDao;
import javaBean.Image;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/deleteImage")
public class deleteImageServlet extends HttpServlet {
    private ImageDao imageDao;
    private Image image;
    private String FileId="";
    private String Filename="";
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        imageDao=new ImageDao();
        FileId=request.getParameter("id");
        Filename=request.getParameter("fileName");

        Boolean result=imageDao.deleteImageByID(FileId);
        if (result){
            java.io.File iof = new java.io.File("main\\webapp\\PersonalPage\\img/"+Filename);
            System.out.println(iof);
            //删除文件
            iof.delete();

            response.sendRedirect("/loadImage");
        }else {
            /*String msg = "删除失败";
            request.getSession().setAttribute("deletefail_msg",msg);
            response.sendRedirect("/user/uploading.jsp");*/
        }

    }
}
