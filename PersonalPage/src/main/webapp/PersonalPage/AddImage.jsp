<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/12/10
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <link href="/css/AddImage.css"  type="text/css" rel="stylesheet">
    <meta charset="utf-8">


</head>

<body>
<div >
    <div class="sidebar">

        <div class="msgIfoDiv">
            <c:if test="${not empty username}">
                <div class="welUser">
                        <%--<h2 style="line-height: 100px">--%>欢迎&nbsp;&nbsp;&nbsp;${username}!<%--</h2>--%>

                </div>

            </c:if>
        </div>

        <nav class="main-nav">
            <ul class="main-nav-ul">
                <li class="nav-item"><a href="/PersonalPage/PersonalIndex.jsp" class="nav-item-link button">图片宝库</a>
                </li>
                <li class="nav-item"><a href="/PersonalPage/AddImage.jsp" class="nav-item-link button active">增加图片</a>
                </li>
                <li class="nav-item"><a href="/PersonalPage/Update Pass.jsp" class="nav-item-link button">修改密码</a>
                </li>
                <li class="nav-item"><a href="../index.jsp" class="nav-item-link button">退出登录</a>
                </li>
            </ul>
        </nav>
    </div>

        <div class="grid">
            <div class="form-div">
                <form action="/addImgServlet" method="post" enctype="multipart/form-data">

                    <div class="uploadFileDiv">

                        <label class="labelUpPic">上传图片：</label>

                                <input type="text" disabled="true"id="viewfile" onmouseout="document.getElementById('upload').style.display='none';" />
                                <label for="upload" class="uploadLabel" onmouseover="document.getElementById('upload').style.display='none';" >浏览...</label>
                                <input  name="file"  id="upload" accept=".png,.jpg,.gif" type="file" onchange="document.getElementById('viewfile').value=this.value;this.style.display='none';" />

                    </div>
                    <div class="msgSpanDiv">
                        <span class="msgSpan">
                            <c:if test="${not empty fileName_msg}">
                            ${fileName_msg}
                            ${fileName_msg=null}
                            </c:if>
                        </span>

                    </div>

                    <br><br>
                    <label>图片类别：</label> <select name="fileClass"  >
                        <option value="A" selected="selected">唯美图片</option>
                        <option value="B">可爱图片</option>
                        <option value="C">心情图片</option>
                        <option value="D">伤感图片</option>
                        <option value="E">励志图片</option>
                        <option value="H">梦幻图片</option>
                        <option value="F">中秋图片</option>
                        <option value="G">晚安图片</option>
                        <option value="I">其他类别</option>
                    </select><br><br><br><br>
                    <input type="submit"  value="点  击  上   传">

                </form>
            </div>
        </div>
</div>



</body>
</html>