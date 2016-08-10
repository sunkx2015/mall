<!DOCTYPE html>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="JS/JQuery/jquery.mobile-1.4.5.css" />
    <script type="text/javascript" src="JS/JQuery/jquery-2.1.js"></script>
    <script type="text/javascript" src="JS/JQuery/jquery.mobile-1.4.5.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#login").click(function () {
                var userid = $("#userid").val();
                var password = $("#password").val();
                $.ajax({
                    type: "POST",
                    url: "login.do",
                    data:{
                    	userId:userid,
                    	password:password
                    },
                    dataType: 'json',
                    success: function (result) {
                        if (result == "true") {
                            location.href = "home.html";
                        } else {
                            location.href = "index.jsp#dialog";
                        }
                    },
                    error: function () {
                        location.href = "index.jsp#dialog";
                    }
                });
            });
        })
    </script>
</head>
<body>
	<div data-role="page">
		<div data-role="header" data-theme="c">
           	网上超市
        </div>
        <!-- /header -->
        <div role="main" class="ui-content">
            <h3>登录</h3>
            <label for="userid">用户名</label>
            <input type="text" id="userid" name="userid">
            <label for="password">密码</label>
            <input type="password" name="password" id="password">
            <input type="button" id="login" value="登录" role="button" />
        </div>
        <!-- /content -->
        <!-- /page -->
    	<div data-role="dialog" id="dialog">
	        <div data-role="header" data-theme="d">
	            <h1>提示</h1>
	        </div>
	        <div data-role="content" data-theme="c">
	            <h1>错误</h1>
	            <p>用户名或密码错误</p>
	            <a href="index.jsp" data-role="button" data-rel="back" data-theme="c">确定</a>
	        </div>
	    </div>
	</div>
</body>
</html>
