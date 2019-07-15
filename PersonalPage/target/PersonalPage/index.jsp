
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <link href="/css/index.css"  type="text/css" rel="stylesheet">


</head>
<body>

<div class="main">
    <h1>Personal Web Site</h1>
    <div class="login-form" id="form1">

        <div class="head-info">
            <div class="registBt">
                <a href="regist.jsp" >没有账号？点击注册</a>
            </div>
            <label class="lbl-1" onclick="clickBlue()" name="blue"> </label>
            <label class="lbl-2" onclick="clickPink()" name="yellow"> </label>
            <label class="lbl-3" onclick="clickYellow()" name="pink"> </label>
        </div>

        <div class="avtar">
            <img src="images/HeadImg.jpg" />
        </div>
        <form method="post" action="/userLogin">
                <input type="text" name="username"class="text" value="Username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Username';}" >

                <input type="password" name="password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}">

            <div class="msgIfo">

                    <c:if test="${not empty msg}">
                        ${msg}
                        ${msg=null}
                    </c:if>


            </div>
                 <input type="submit" value="Login" >
        </form>



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