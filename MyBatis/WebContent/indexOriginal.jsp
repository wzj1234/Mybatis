<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.action.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
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
				<h1><a  href="index.jsp">Welcome<span> Cal<span> Lab </span></a></h1>
			</div>
				<!-- top-nav -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="index.jsp" class="active">首页</a></li>
					<li><a href="chooseElement.jsp">评估条件设定</a></li>
					<li><a href="NorUpDownLoad.jsp">更新数据</a></li>
					<li><a href="managerLogin.jsp">管理员登录</a></li>
					<li><a href="contactUs.jsp">联系我们</a></li>
				</ul>	
			</div>
		</nav>
	</div>
</div>
<!-- header -->
</head>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<link rel="stylesheet" href="css/flexslider.css" />
<script type="text/javascript" src="js/jquery.flexslider.js"></script>
<style>
		.wrap {
            width: 100%;
            height: 540px;
            padding: -20px;                  
            opacity: 0.8;
            background: linear-gradient(to bottom right,#50a3a2,#53e3a6);
            background: -webkit-linear-gradient(to bottom right,#50a3a2,#53e3a6);
        }
        .flex-caption {
      width: 100%;
      padding: 2%;
      left: 0;
      bottom: 0;
      background: rgba(0,0,0,0);
      color: #666666;
      text-shadow: 0 -1px 0 rgba(0,0,0,.1);
      font-size: 14px;
      line-height: 18px;
    }
    li.css a {
      border-radius: 0;
    }
		
</style>
<script type="text/javascript">
$(function() {
	  $('.flexslider').flexslider({
	    animation: "slide",
		slideshowSpeed: 4000, //展示时间间隔ms
    	animationSpeed: 100, //滚动时间ms
    	directionNav: false,
    	controlNav: true,
    	pauseOnHover: true,  
    	manualControlEvent:"ABC",
    	touch: true //是否支持触屏滑动
	  });
	});
</script>
<body>	

<div class="wrap">
	<div class="flexslider">   
		<ul class="slides"> 
			<li class="images">
				<img src="images/aaa.jpg" style="width:100%;height:400px"/>
				<p class="flex-caption">实验室介绍： 北京重点实验室。。。。。</p>
			</li>
			<li class="images">
				<img src="images/bbb.jpg"  style="width:100%;height:400px"/>
				<p class="flex-caption">项目介绍： 通过选择核素、年龄并依据上传数据计算含量等</p>
			</li>
		</ul>
		
</div>
</div>
</body>
</html>