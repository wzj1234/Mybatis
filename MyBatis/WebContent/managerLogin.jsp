<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.action.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登录</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<!-- //js -->
<!--animate-->
<link href="css/animate.css" rel="stylesheet" type="text/css" media="all">
<script src="js/wow.min.js"></script>
	<script>
		 new WOW().init();
	</script>
<!--//end-animate-->
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
</script>
<!-- start-smoth-scrolling -->
<script src="js/responsiveslides.min.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-form.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript">

	window.onload = function(){
		if(("${loginStatus}"=="账号不存在")||("${loginStatus}"=="密码错误"))
    		alert("${loginStatus}"+"，请重新登录！");		
	}
	</script>
	<style>
  .banner_w3ls {
    min-height: 162px;
    }
   .wrap {
            width: 100%;
            height: 378px;
            padding: 40px;    
            opacity: 0.8;
            background: linear-gradient(to bottom right,#50a3a2,#53e3a6);
            background: -webkit-linear-gradient(to bottom right,#50a3a2,#53e3a6);
        }
        .container1 {
            width: 60%;
            margin: 0 auto;
        }
        .container1 h1 {
            text-align: center;
            color: #FFFFFF;
            font-weight: 500;
        }
        .container1 input {
            width: 320px;
            display: block;
            height: 36px;
            border: 0;
            outline: 0;
            padding: 6px 10px;
            line-height: 24px;
            margin: 10px auto;
            -webkit-transition: all 0s ease-in 0.1ms;
            -moz-transition: all 0s ease-in 0.1ms;
            transition: all 0s ease-in 0.1ms;
        }
        .container1 input[type="text"] , .container input[type="password"]  {
            background-color: #FFFFFF;
            font-size: 16px;
            color: #50a3a2;
        }
	</style>
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
				<h1><a  href="index.jsp">Welcome<span> RASFRI </span></a></h1>
			</div>
				<!-- top-nav -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="index.jsp">首页</a></li>
					<li><a href="chooseElement.jsp">评估条件设定</a></li>
					<li><a href="NorUpDownLoad.jsp">更新数据</a></li>
					<li><a href="managerLogin.jsp" class="active">管理员登录</a></li>
					<li><a href="contactUs.jsp">联系我们</a></li>
				</ul>	
				<div class="clearfix"> </div>	
			</div>
		</nav>
	</div>
</div>
<!-- header -->
</head>	
<body>	  
	<img src="images/qqq15.jpg" alt="Smiley face"  style="max-width:100%;">				
	<div class="wrap">
    <div class="container1">
            <h1>Welcome</h1>
	   <div id="reloadDiv">
	   <form action="manage" id="submitRequest" name="manageForm" method="post">
		<input class="tx1" type="text" name="managerName" placeholder="账号">
		<input class="tx1" type="password" name="managerPSW" placeholder="密码">
	     <input class="btn btn-default btn-block btn-success" type="submit" value="登录" style="width:324px">
	   </form>
	    </div>
     
    </div>
	</div>
	
	
</body>
</html>