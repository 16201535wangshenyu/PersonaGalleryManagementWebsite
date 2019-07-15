<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/12/9
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <link href="/css/PersonalIndex.css"  type="text/css" rel="stylesheet">
    <meta charset="utf-8">

</head>

<body>
<div >
    <div class="sidebar">

        <div class="msgIfoDiv">

            <c:if test="${not empty username}">
                <div class="welUser">
                    欢迎&nbsp;&nbsp;&nbsp;${username}!

                </div>

            </c:if>
        </div>

        <nav class="main-nav">
            <ul class="main-nav-ul">
                <li class="nav-item"><a href="" class="nav-item-link button active">图片宝库</a>
                </li>
                <li class="nav-item"><a href="/PersonalPage/AddImage.jsp" class="nav-item-link button">增加图片</a>
                </li>
                <li class="nav-item"><a href="/PersonalPage/Update Pass.jsp" class="nav-item-link button">修改密码</a>
                </li>
                <li class="nav-item"><a href="../index.jsp" class="nav-item-link button">退出登录</a>
                </li>
            </ul>
        </nav>
    </div>
        <div class="grid">
            <div class="div2" >
                <ul id="nav">
                    <li><a href="/loadImage?fileClass=O" class="borderSpec">全部图片</a></li>
                    <li><a href="/loadImage?fileClass=B">可爱图片</a> </li>

                    <li><a href="/loadImage?fileClass=C">心情图片</a></li>

                    <li><a href="/loadImage?fileClass=D">伤感图片</a> </li>


                    <li><a href="/loadImage?fileClass=E">励志图片</a></li>
                    <li><a href="/loadImage?fileClass=H">梦幻图片</a></li>
                    <li><a href="/loadImage?fileClass=A">唯美图片</a></li>
                    <li><a href="/loadImage?fileClass=F">中秋图片</a></li>
                    <li><a href="/loadImage?fileClass=G">晚安图片</a></li>
                    <!--<li><a href="">招生就业</a></li>
                    <li><a href="">招生就业</a></li>-->
                </ul>
            </div>
            <c:if test="${empty imageList}">
                <div class="NoPicMsg">
                   <h1>暂无图片</h1>

                </div>

            </c:if>
            <c:if test="${not empty imageList}">


            <c:forEach items="${imageList}" var="image" varStatus="">
                <div class="grid-item">
                    <img src="img/${image.getFileName()}" alt="Image">
                    <div class="delet">
                        <a href="/deleteImage?id=${image.getId()}&&fileName=${image.getFileName()}" >删除</a>
                    </div>
                </div>
            </c:forEach>
            </c:if>

            <form name="f1" method="POST" action="/loadImage" style="margin:0px;width:89%;position: absolute;bottom:-50px;">
                <table border="0" align="center" style="background-color:#CC6733;width:80%;margin:0px;">
                    <tr>
                        <%int currentPage=(int)request.getSession().getAttribute("currentPage");
                          int totalpages=(int)request.getSession().getAttribute("totalpages");

                        %>
                        <td>第${currentPage}页 &nbsp;&nbsp;&nbsp;&nbsp;共${totalpages}页
                            &nbsp;&nbsp;&nbsp;&nbsp; <a href="/loadImage?currentPage=1">首页</a>
                        </td>
                        <td><a href="/loadImage?currentPage=<%=(currentPage<=1)?currentPage:(currentPage-1)%>">上一页</a></td>
                        <td><a href="/loadImage?currentPage=<%=(currentPage>=totalpages)?totalpages:(currentPage+1)%>"> 下一页</a></td>
                        <td><a href="/loadImage?currentPage=<%=totalpages%>">最后一页</a></td>
                        <td>转到第:
                            <input type="text" name="currentPage" size="8">页
                            <input type="submit" value="GO">
                        </td>
                    </tr>
                </table>
            </form>
        </div>


</div>



</body>
</html>