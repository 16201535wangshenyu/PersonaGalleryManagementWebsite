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

@WebServlet("/loadImage")
public class LoginImageServlet extends HttpServlet {
    private ImageDao imageDao=new ImageDao();
    private int currentPage=1;
    private int totalPages=1;
    private String fileClass="O";//要显示的菜单选项 可爱图片、....

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getSession().getAttribute("username")+"";
        if(request.getParameter("currentPage")!=null) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
            System.out.println("currentPage="+currentPage);
        }
        if(request.getParameter("fileClass")!=null)
        fileClass=request.getParameter("fileClass").trim();
        totalPages=imageDao.getTotalPages(fileClass,username);
        //防止当前页数大与总页数
        if(currentPage>=totalPages)
            currentPage=totalPages;
        if(currentPage<=1)
            currentPage=1;
        System.out.println("文件类型："+fileClass);
        ArrayList<Image> imageList = imageDao.LoadImgAllByPages(currentPage,fileClass,username) ;
        request.getSession().setAttribute("imageList" , imageList);
        request.getSession().setAttribute("currentPage",currentPage);
        request.getSession().setAttribute("totalpages",totalPages);

        request.getSession().setAttribute("username",username);
        request.getSession().setAttribute("Button1",1);
        response.sendRedirect("/PersonalPage/PersonalIndex.jsp");
    }


}
