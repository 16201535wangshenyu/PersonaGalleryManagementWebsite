<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2018/12/7
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="/css/regist.css"  type="text/css" rel="stylesheet">
    <title>注册</title>

</head>
<body>
<div class="main">
    <h1>Personal Web Site</h1>
    <div class="login-form" id="form1">

        <div class="head-info">
            <div class="registBt">
                <a href="index.jsp">已有账号？返回登陆</a>
            </div>
            <label class="lbl-1" onclick="clickBlue()" name="blue"> </label>
            <label class="lbl-2" onclick="clickPink()" name="yellow"> </label>
            <label class="lbl-3" onclick="clickYellow()" name="pink"> </label>
        </div>
        <form method="post" action="/userRegist" >
            <input type="text"  name="username" class="text" value="Username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Username';}" >

            <input type="text" name="password" class="inputPassword" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}">

            <input type="text" class="inputPassword rePass" name="Repassword"  value="Confirm Password"
                   onfocus="this.value = '';"
                   onblur="if (this.value == '') {this.value = 'Confirm Password';}">

            <div class="magIfo">
                <c:if test="${not empty msg}">
                    ${msg}
                    ${msg=null}
                </c:if>
            </div>
            <input type="submit" value="Regist" >

        </form>



    </div>

</div>

</body>
</html>
