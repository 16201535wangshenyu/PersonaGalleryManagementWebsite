package servletHandle;
import dao.ImageDao;
import javaBean.Image;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;


@WebServlet( "/addImgServlet")
// 表示 对应的上传文件的配置
@MultipartConfig(location = "main\\webapp\\PersonalPage\\img")
public class AddImageServlet extends HttpServlet {

    private ImageDao imageDao = new ImageDao();

    /**
     * 如果是 post 方式请求的话，那么 认为 可以上传文件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* request.setCharacterEncoding("UTF-8");

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");*/



        Part p = request.getPart("file") ;
//        System.out.println( p.getName() ); //
//        System.out.println(p.getSize() ); //
//        System.out.println( p.getSubmittedFileName() ); //
        // 将 上传的文件 写出到磁盘
         String fileName=p.getSubmittedFileName();
         String fileClass=request.getParameter("fileClass");
         String userName=request.getSession().getAttribute("username")+"";
         if("".equals(fileName)){
             String msg = "上传文件为空！！！";
             request.getSession().setAttribute("fileName_msg",msg);

             response.sendRedirect("/PersonalPage/AddImage.jsp");

         }else {
             System.out.println("fileName:"+fileName+"fileClass:"+fileClass+"userName"+userName);
             boolean result = imageDao.insertImage(fileName,fileClass,userName);
             if (result){

                 response.sendRedirect("/loadImage");
             }
         }

        p.write( p.getSubmittedFileName() );
    }
}
