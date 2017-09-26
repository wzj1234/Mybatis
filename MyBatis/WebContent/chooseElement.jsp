<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.action.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>评估条件设定界面</title>
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
					<li><a href="chooseElement.jsp" class="active">评估条件设定</a></li>
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
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery.js"></script>
<style>
		html{
  			width:100%;
  			overflow-x:hidden;
			}
		.wrap {
            width: 100%;
            height: 100%;
            padding: -80px; 
            color: #FFFFFF;   
            font:  20px/1.5 Tahoma,Helvetica,Arial,'å®ä½',sans-serif;
            opacity: 0.8;
            background: linear-gradient(to bottom right,#50a3a2,#53e3a6);
            background: -webkit-linear-gradient(to bottom right,#50a3a2,#53e3a6);
        }

		select{
			padding: 5px 10px 5px 10px;
			font-size: 18px;
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
            color:green;
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
			color:#FFF937;
		}
		.last{
			border: none;
		}
		.last td{
			width:111px;
		}
		.tdLabel{
			width:100px;
			font-size: 24px;
			color:#FFFFFF;
			font-weight:bold;
			text-shadow: 0 -1px 0 rgba(1.3,1.3,1.3,1.3);			
		}
		tr:first-child{
			border-bottom:none;
		}
		.quanxuanh{
			padding: 0;
			width: 65px;
			height: 35px;
			position: relative;
			top:-60px;
			left:915px;
		}
		.fanxuanh{
			padding: 0;
			width: 65px;
			height: 35px;
			position: relative;
			top:-60px;
			left:936px;
		}
		.natureHesu{
			position: relative;
			top:0px;
			left:100px;
			height: 23px;
		}
		.chooseMoreLableClass{
		font: 13px/1 Tahoma,Helvetica,Arial,"\5b8b\4f53",sans-serif;
		position: relative;
			top:0px;
			left:30px;
		}
		.chooseMoreLableAgeClass{
		font: 13px/1 Tahoma,Helvetica,Arial,"\5b8b\4f53",sans-serif;
		position: relative;
			top:0px;
			left:30px;
		}
		.rengongHesu{
			position: relative;
			top:0px;
			left:100px;
			height: 23px;
		}
		.nianlingDiv{
			position: relative;
			top:0px;
			left:100px;
		}
		.nianlingLable{
			position: relative;
			top:0px;
			left:0px;
			height: 37px;
		}
		.nianlingCheck{
			position: relative;
			top:-40px;
			left:149px;
		}
		.fanxuann{
			padding: 0;
			width: 65px;
			height: 35px;
			position: relative;
			top:-90px;
			left:835px;
		}
		.quanxuann{
			padding: 0;
			width: 65px;
			height: 35px;
			position: relative;
			top:-90px;
			left:815px;
		}
	.showResult{
			padding: 0;
			width: 350px;
			height: 35px;
			position: relative;
			top:-48px;
			left:430px;
		}
	</style>
<script type="text/javascript">
	
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
		function validateCheckBox() {  
			var radios = document.getElementsByName("radioList");  
            var checkid = 0;
            for (var i=0; i < radios.length; i++) {  
                var radio = radios[i];  
                  
                if(radio.checked) {  
                	checkid++; 
                }  
            }              
            if(checkid==0){
            	alert("评估条件不能为空！");
            	window.location.href="chooseElement.jsp";     
            	return false;
            }
        } 
	</script>
<body>	
<img src="images/qqq15.jpg" alt="Smiley face"  style="max-width:100%;">			
<div class="wrap">
	<br>
	
	<form action="getE" name="getAgeForm" method="post">
			<div  class="hesuDiv">
			<div class="natureHesu">
					<label>天然放射性核素：</label>	
					<s:checkboxlist list="{'210Po','226Ra','238U','232Th'}" id="radioList" name="radioList"> </s:checkboxlist>
			<br>
			<label class="chooseMoreLableClass">( 可多选 )</label>
			</div>
			<br>
			<div  class="rengongHesu">
				<label>人工放射性核素：</label>
				<s:checkboxlist list="{'89Sr','90Sr','131I','137Cs','239Pu'}" id="radioList" name="radioList"> </s:checkboxlist>				
				<br>
				<label class="chooseMoreLableClass">( 可多选 )</label>
			</div>
			<button class="quanxuanh btn btn-default btn-success"  type="button" onclick="selectAllRadio();">全选核素</button>
			<button class="fanxuanh btn btn-default btn-success"  type="button" onclick="inverseRadio();">反选核素</button>
			</div>

			<div class="nianlingDiv">
				<div class="nianlingLable">
					<label>人群年龄组别：</label>
					<br>
				<label class="chooseMoreLableAgeClass">( 可多选 )</label>
				</div>
				<div  class="nianlingCheck" style="max-width:52%;">							
				<s:checkboxlist list="{'2-7','8-12','13-17(M)','13-17(F)','18-50(M)','18-50(F)'}" id="ageList" name="ageList"> </s:checkboxlist>
				<br>
				<s:checkboxlist list="{'51-65(M)','51-65(F)','>65(M)','>65(F)'}" id="ageList" name="ageList"> </s:checkboxlist>
				</div>
				<button class="quanxuann btn btn-default btn-success"  type="button" onclick="selectAllAge();">全选人群</button>
				<button class="fanxuann btn btn-default btn-success"  type="button" onclick="inverseAge();">反选人群</button>
				</div>
				<div>
				<input type="submit" onClick="return validateCheckBox()" value=" 评&emsp;&emsp;&emsp;估    " class="showResult btn btn-default btn-success">
				</div>
	</form>
</div>
</body>
</html>