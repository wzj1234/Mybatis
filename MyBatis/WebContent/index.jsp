<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.action.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RASFRI</title>
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
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<style>
		.wrap {
            width: 100%;
            height: 540px;
            padding: -20px;                  
            opacity: 0.8;
            background: linear-gradient(to bottom right,#50a3a2,#53e3a6);
            background: -webkit-linear-gradient(to bottom right,#50a3a2,#53e3a6);
        }
    li.css a {
      border-radius: 0;
    }
    .comDiv{
	  position: relative;
      width:96%;
      height:440px;
	  top:30px;
	  left:25px;
	  border-style:solid; 
	  border-width:1px;
	  border-color:#ffffff;
	}
	.imageDiv{
	  position: relative;
	  top:20px;
	  left:20px;
	}
	.proProDiv{
	  position: relative;
      width:36%;
      left:63%;
      top:-85%;
      height:300px;
	  border-style:solid; 
	  border-width:1px;
	  border-color:#ffffff;
	}
	.buttonDiv{
	  position: relative;
      width:36%;
      left:63%;
      top:-77%;
	}
	.flex-caption {
      width: 100%;
      padding-left: 40%;
      padding-top: 8%;
      left: 0;
      bottom: 0;
      background: rgba(0,0,0,0);
      color: #ffffff;
      text-shadow: 0 -1px 0 rgba(0,0,0,0.6);
      font:  20px/1.5 Tahoma,Helvetica,Arial,'å®ä½',sans-serif;
      line-height: 18px;
    }
    .flex-caption3 {
     padding-left: 3%;
     padding-top: 1%;
      color: #ffffff;
      text-shadow: 0 -1px 0 rgba(0,0,0,0.3);
      font:  23px/1.5 Tahoma,Helvetica,Arial,'å®ä½',sans-serif;
      line-height: 18px;
    }
    .flex-caption2 {
     padding-left: 5%;
     padding-right: 3%;
     padding-top: 8%;
      color: #ffffff;
      text-shadow: 0 -1px 0 rgba(0,0,0,0.3);
      font:  17px/1.5 Tahoma,Helvetica,Arial,'å®ä½',sans-serif;
      line-height: 18px;
    }
</style>
<script type="text/javascript">

	function Download_shuoming(){
	    window.location.href="downloadManual";
	}
</script>
<body>	

<div class="wrap">
	<h1 class="flex-caption3">Risk Assessment System for Foodborne Radionuclide Intake</h1>
  <div class="comDiv">
	<div class="imageDiv">
		<img src="images/qqq13.jpg" style="height:400px;width:60%"/>
	</div>
	<div class="proProDiv">
		<p class="flex-caption">
			项目介绍
			<p class="flex-caption2">
			&emsp;&emsp;项目名称“食品安全未知成份鉴定平台构建及不明危害物质检定评估技术体系研究与应用”。
			<br>
			&emsp;&emsp;本软件用于计算居民食入放射性核素所致的待积有效剂量当量，并进行放射性核素的内照射剂量评估。便于对居民主要食品中主要放射性核素进行风险监测，提高食品放射性剂量的常规监测及应急监测能力，是对食品中铀、钍、钋-210、锶-90、铯-137 等主要放射性核素的内照射评估体系。
			</p>
		</p>
	</div>
	<div class="buttonDiv">
		<button onclick="Download_shuoming()" type="button" class="btn btn-default btn-block btn-success" >用户使用说明</button>
	<!-- <button type="button" class="btn btn-default btn-block btn-success" data-toggle="modal" data-target="#myModal" >用户使用说明</button> -->
	</div>
  </div>
</div>
</body>
</html>