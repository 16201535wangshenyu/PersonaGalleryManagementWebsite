<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/12/11
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link href="/css/UpdatePass.css"  type="text/css" rel="stylesheet">

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
                <li class="nav-item"><a href="/PersonalPage/PersonalIndex.jsp" class="item-link button">图片宝库</a>
                </li>
                <li class="nav-item"><a href="/PersonalPage/AddImage.jsp" class="item-link button ">增加图片</a>
                </li>
                <li class="nav-item"><a href="" class="item-link button active">修改密码</a>
                </li>
                <li class="nav-item"><a href="../index.jsp" class="item-link button">退出登录</a>
                </li>
            </ul>
        </nav>
    </div>

    <div class="grid">

        <div class="form-div">
            <div class="login-form" id="form1">

                <div class="head-info">
                    <label class="lbl-1" onclick="clickBlue()" name="blue"> </label>
                    <label class="lbl-2" onclick="clickPink()" name="yellow"> </label>
                    <label class="lbl-3" onclick="clickYellow()" name="pink"> </label>
                </div>

                <div class="avtar">
                    <img src="/images/HeadImg.jpg" />
                </div>
                <form method="post" action="/UpdateUserPass">


                    <input type="text" name="Oldpassword" value="原密码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '原密码';}">
                    <input type="text" name="Newpassword" value="新密码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '新密码';}">
                    <input type="text" name="Repassword" value="确认密码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '确认密码';}">
                    <div class="msgIfo">

                            <c:if test="${not empty msg}">
                                ${msg}
                                ${msg=null}
                            </c:if>


                    </div>
                    <input type="submit" value="修     改" >
                </form>



            </div>

        </div>

    </div>
    </div>
</div>



</body>
<script>
    var formBack=document.getElementById("form1");
    function clickYellow() {
        formBack.style.backgroundColor="#f1c85f";

    }
    function clickPink() {

        formBack.style.backgroundColor="#ff2775";
    }
    function clickBlue() {
        formBack.style.backgroundColor="#6756ea";
    }



</script>

</html>
