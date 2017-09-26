<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.action.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Short Codes</title>
<!-- for-mobile-apps -->
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

<link href='http://fonts.googleapis.com/css?family=Poiret+One' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lato:400,100,100italic,300,300italic,400italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
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
	<style>
		input[type="button"],input[type="submit"], button{
			text-decoration:none;
			border: none;
			background:lightblue;
			color:black;
			cursor: pointer;
			padding: 10px 30px 10px 30px;
			font-size:8px;
			font-family: "Arial","Microsoft YaHei","é»ä½","å®ä½",sans-serif;

			border-radius:5px;

		}
		input[type="submit"]{
			display:block;
			width:200px;

			margin-left:300px;
		}
		td{

			color: black;

			font:  12px/1.5 Tahoma,Helvetica,Arial,'å®ä½',sans-serif;
			font-weight:bold;
			font-size: 18px;

		}
		select{
			padding: 5px 10px 5px 10px;
			font-size: 18px;
		}
		tr{
			width:800px;
			display:block ;
			margin-top: 10px;
			padding: 5px;
			border-bottom: 1px solid gray;
		}

		td{
			width: 540px;
		}
		table{
			display: flex;
			border-collapse: collapse;
			background-color: whitesmoke;
			align-items: center;
			justify-content: center;





		}
		body{
			background-color: ghostwhite;
		}



		.quanxuanh{
			padding: 0;
			width: 65px;
			height: 35px;

			position: absolute;
			top:407px;
			left:935px;

		}
		.fanxuanh{
			padding: 0;
			width: 65px;
			height: 35px;
			position: absolute;
			top:407px;
			left:1013px;
		}




		input[type=checkbox]{
			width: 21px;
			height: 21px;
			background: #ddd;


			border-radius: 100%;
			position: relative;
			-webkit-box-shadow: 0px 1px 3px rgba(0,0,0,0.5);
			-moz-box-shadow: 0px 1px 3px rgba(0,0,0,0.5);
			box-shadow: 0px 1px 3px rgba(0,0,0,0.5);
		}
		input[type=checkbox] label{
			display: block;
			width: 30px;
			height: 30px;
			border-radius: 100px;
            color:black;
			-webkit-transition: all .5s ease;
			-moz-transition: all .5s ease;
			-o-transition: all .5s ease;
			-ms-transition: all .5s ease;
			transition: all .5s ease;
			cursor: pointer;
			position: absolute;
			top: 5px;
			left: 5px;
			z-index: 1;

			background: #333;

			-webkit-box-shadow:inset 0px 1px 3px rgba(0,0,0,0.5);
			-moz-box-shadow:inset 0px 1px 3px rgba(0,0,0,0.5);
			box-shadow:inset 0px 1px 3px rgba(0,0,0,0.5);
		}
		input[type=checkbox]:checked + label {
			background: yellow;
		}
		.last{
			border: none;
		}
		.last td{
			width:111px;
		}
		.tdLabel{
			width:100px;
		}
		tr:first-child{
			border-bottom:none;
		}
		.fanxuann{
			padding: 0;
			width: 65px;
			height: 35px;
			position: absolute;
			top:498px;
			left:1013px;

		}
		.quanxuann{
			padding: 0;
			width: 65px;
			height: 35px;
			position: absolute;
			top:498px;
			left:935px;

		}
		.jiance{
			width:106px;

		}
		.file {
			position: relative;
			display: inline-block;
			background: #D0EEFF;
			border: 1px solid #99D3F5;
			border-radius: 4px;
			padding: 8px 12px;
			overflow: hidden;
			color: #1E88C7;
			text-decoration: none;
			text-indent: 0;
			line-height: 20px;
		}
		.file input {
			position: absolute;
			font-size: 100px;
			right: 0;
			top: 0;
			opacity: 0;
		}
		.file:hover {
			background: #AADFFD;
			border-color: #78C3F3;
			color: #004974;
			text-decoration: none;
		}
		.index-banner{
			height: 150px;
			overflow: hidden;
			background: url(http://rr.mep.gov.cn/rsmsreq/apply/images/2.jpg) no-repeat center top;
			position: relative;
			z-index: 2;
			background-size: cover;
		}
		.index-logo {
			width: 1100px;
			margin: 0 auto;
		}
		.index-logo p {
			color: #046eb8;
			line-height: 150px;
			font-size: 32px;
			padding-left: 83px;
		}
		img {
			vertical-align: middle;
		}
		.index-info {
			height: 60px;
			border-bottom: 1px solid #f7f7f9;

			box-shadow: 0px 2px 4px #d4dcdf;
			position: relative;
			z-index: 2;
		}
		.index-info .index-infodiv {
			width: 1100px;
			line-height: 43px;
			text-align: right;
			margin: 0 auto;
			position: relative;
		}
		.top-table {

			
			float:right;
			width:200px;

		}
		.top-table tbody{



		}
		.index-infodiv:after{
			content:'';
			display:block;
			clear:both;

		}
		form{


			box-shadow: 10px 10px 5px #888888;
			height:400px;

		}
		form>table{
		}
.last{
		
			
		}



	</style>
	<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript">
	
	function ajaxFileUpload()
    {
        /*$("#loading")
        .ajaxStart(function(){
            $(this).show();
        })//开始上传文件时显示一个图片
        .ajaxComplete(function(){
            $(this).hide();
        });//文件上传完成将图片隐藏起来
        */
        $.ajaxFileUpload
        (
            {
                url:'fileUploadAction.action',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
                    alert(data.message);//从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量
                    
                    if(typeof(data.error) != 'undefined')
                    {
                        if(data.error != '')
                        {
                            alert(data.error);
                        }else
                        {
                            alert(data.message);
                        }
                    }
                },
                error: function (data, status, e)//服务器响应失败处理函数
                {
                    alert(e);
                }
            }
        )
        return false;
    }
		
		
		function selectAllAge() {  
            var ages = document.getElementsByName("ageList");  
              
            for (var i=0; i < ages.length; i++) {  
                var age = ages[i];  
                age.checked = true;  
            }             
        }
		function inverseAge() {  
            var ages = document.getElementsByName("ageList");  
              
            for (var i=0; i < ages.length; i++) {  
                var age = ages[i];  
                  
                if(age.checked) {  
                    age.checked = false;  
                }else {  
                    age.checked = true;  
                }  
            }  
        } 
		function selectAllRadio() {  
            var radios = document.getElementsByName("radioList");  
              
            for (var i=0; i < radios.length; i++) {  
                var radio = radios[i];  
                radio.checked = true;  
            }             
        }
		function inverseRadio() {  
            var radios = document.getElementsByName("radioList");  
              
            for (var i=0; i < radios.length; i++) {  
                var radio = radios[i];  
                  
                if(radio.checked) {  
                    radio.checked = false;  
                }else {  
                    radio.checked = true;  
                }  
            }  
        } 
	</script>
</head>
<body>
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
				<h1><a  href="index.html">Clinical<span> Lab </span></a></h1>
			</div>
				<!-- top-nav -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="index.html">Home</a></li>
					<li><a href="about.html">About</a></li>
					<li><a href="codes.jsp" class="active">Short Codes</a></li>
					<li><a href="treatments.html">Treatments</a></li>
					<li><a href="contact.html">Contact</a></li>
				</ul>	
				<div class="clearfix"> </div>	
			</div>
		</nav>
	</div>
</div>
<!-- header -->
<!-- banner -->
<div class="banner_w3ls page_head">
	
</div>
<!-- //banner -->

<div class="index-info " style="font-family: 'å¾®è½¯éé»',MicrosoftYaHei,Arial,Helvetica,sans-serif;">

	<div class="index-infodiv">
		<table class="top-table">
		 <tr class="last">
			<td>导入数据</td>

			<td>
				<a href="javascript:;" class="file">选择文件
					<input type="file" id="file" name="file" />
				</a>

			</td>

			<td>
				<input type="button" value="上传 " onclick="return ajaxFileUpload();">
			</td>

		</tr></table>

	</div>
</div>


<form action="getE" name="getAgeForm" method="post">
	<table>



		<s:checkboxlist list="{'U236','226Ra','238U','232Th'}" id="radioList" name="radioList" label="自然核素" > </s:checkboxlist>
		<button class="quanxuanh" onclick="selectAllRadio();">全选核素 </button>
		<button class="fanxuanh"  onclick="inverseRadio();">反宣核素 </button>





		<s:checkboxlist list="{'U237','90Sr','131I','137Cs','239Pu'}" id="radioList" name="radioList" label="人工核素 "> </s:checkboxlist>






		<s:checkboxlist list="{'2-7','8-12','13-19(M)','13-19(F)','20-50(M)','20-50(F)','51-65(M)','51-65(F)','>65(M)','>65(F)'}" id="ageList" name="ageList" label="年龄"> </s:checkboxlist>
		<button class="quanxuann" onclick="selectAllAge();">全选年龄</button>
		<button class="fanxuann" onclick="inverseAge();">反选年龄</button>



		<tr>


			<td class="xianshi">
				<input type="submit" value="查看结果">

			</td>

		</tr>








	</table>
</form>
<div class="last"></div>
<!-- //typography -->
<!-- contact -->

<!-- smooth scrolling -->
	<script type="text/javascript">
		$(document).ready(function() {
		/*
			var defaults = {
			containerID: 'toTop', // fading element id
			containerHoverID: 'toTopHover', // fading element hover id
			scrollSpeed: 1200,
			easingType: 'linear' 
			};
		*/								
		$().UItoTop({ easingType: 'easeOutQuart' });
		});
	</script>
	<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
<!-- //smooth scrolling -->
<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
</body>
</html>