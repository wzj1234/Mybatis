<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.action.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<!-- header -->
<div class="header_w3l">
	<div class="container">
		<nav class="navbar navbar-default">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<h1><a  href="index.jsp">Clinical<span> Lab </span></a></h1>
			</div>
				<!-- top-nav -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="index.jsp">首页</a></li>
					<li><a href="about.html">简介</a></li>
					<li><a href="codes.jsp">Short Codes</a></li>
					<li><a href="managerLogin.jsp" class="active">管理员登录</a></li>
					<li><a href="contact.html">联系我们</a></li>
				</ul>	
				<div class="clearfix"> </div>	
			</div>
		</nav>
	</div>
</div>
<!-- header -->
</head>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript">

	window.onload = function(){
		if(("${loginStatus}"=="账号不存在")||("${loginStatus}"=="密码错误"))
    		alert("${loginStatus}"+"，请重新登录！");		
	}
	//局部刷新start
	$(document).ready(function() {
        var options = {   
            //需要刷新的区域id 
            target:'#reloadDiv',    
        };  
        /*
        //绑定FORM提交事件  
        $('#submitRequest').submit(function() {  
            alert("00");
            $(this).ajaxSubmit(options);  
            alert("ahah");
            alert("${ifSuccess}");
            return false;   
        }); 
        */
    }); 
	//局部刷新end
	
	</script>
<body>	
	<div id="reloadDiv">
	<form action="manage" id="submitRequest" name="manageForm" method="post">
		用户名: <input type="text" name="managerName"><br>
		    密码: <input type="password" name="managerPSW">
	<input type="submit" value="登录">
	</form>
	</div>
	
</body>
</html>