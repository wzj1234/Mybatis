<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.action.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户上传下载数据</title>
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
					<li><a href="NorUpDownLoad.jsp" class="active">更新数据</a></li>
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
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<link rel="stylesheet" href="css/flexslider.css" />
<script type="text/javascript" src="js/jquery.flexslider.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>

<style>
			html{
  			width:100%;
  			height:100%;
  			overflow-x:hidden;
			}
			.wrap {
            width: 100%;
            height: 730px;
            padding: -20px;                  
            opacity: 0.8;
            background: #5ab8a9;
        }
        .input-group {
        	position: relative;
        	width:630px;
			top:30px;
			left:255px;
        }
        .uploadButtonDiv{
        	position: relative;
        	top:-25px;
        	left:915px;
        }
        .downloadButtonDiv{
        	position: relative;
        	top:-58px;
        	left:1025px;
        }
        .lableTipClass {
        	position: relative;
			top:-48px;
			left:257px;
			color:#FFFFFF;
			font-size: 14px;
        }
        .wrapp {
        	position: relative;
			top:0px;
            color: #FFFFFF;   
            font:  20px/1.5 Tahoma,Helvetica,Arial,'??????',sans-serif;
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
		input[type=checkbox]:checked + label {
			color:#FFF937;
		}
		.quanxuanh{
			padding: 0;
			width: 77px;
			height: 35px;
			position: relative;
			top:-60px;
			left:1015px;
		}
		.fanxuanh{
			padding: 0;
			width: 77px;
			height: 35px;
			position: relative;
			top:-60px;
			left:1036px;
		}
		.natureHesu{
			position: relative;
			top:0px;
			left:200px;
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
			left:200px;
			height: 23px;
		}
		.nianlingDiv{
			position: relative;
			top:0px;
			left:200px;
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
			width: 77px;
			height: 35px;
			position: relative;
			top:-90px;
			left:835px;
		}
		.quanxuann{
			padding: 0;
			width: 77px;
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
			top:-38px;
			left:430px;
			text-shadow: 0 -1px 0 rgba(0,0,0,0.3);
		}
</style>
<script type="text/javascript">

	function f_DL(){
		//s="fileUploadAction!download.action?filePath="+"localhost:8080/fileUploadandDownload/file/log4j-core-2.7.jar";
	    window.location.href="downloadAction?user=user";
	   // var target=document.getElementById(myModal);
	}
	function firm(){
    	if(confirm("请确保此次上传数据的格式遵循【标准格式】")){
			var filePath = document.getElementById("file").value;
			if(filePath.length < 1)
			{
			  alert("请选择要上传的excel文件");
			  return false ;
			}
			else
			{
			  var fileLx = filePath.toString().substring(filePath.toString().lastIndexOf(".")+1) ;
			  if(fileLx == "xlsx" || fileLx == "xls")
			  {
				  ajaxFileUpload();
			  }
			  else
			  {
				   alert("只支持excel文件的上传！");
				   return false ;
			  }
			}
    		
    	}
    	else{
    	}
    }
	function ajaxFileUpload()
    {	 $('#myModal').modal({backdrop: 'static', keyboard: false});	
        $.ajaxFileUpload
        (
            {
                url:'userfileUploadAction.action',//用于文件上传的服务器端请求地址
                secureuri:false,//一般设置为false
                fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
                dataType: 'json',//返回值类型 一般设置为json
                success: function (data, status)  //服务器成功响应处理函数
                {
  				  
                    //alert(data.message);//这是弹出上传文件路径；从服务器返回的json中取出message中的数据,其中message为在struts2中action中定义的成员变量
                    //document.getElementById("upfile").value="请选择要上传的文件";
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
                },
            	complete:function() {
            		$('#closeButton').click();
            	}
            }
        )
        return false;
    }
	function changevalue(){
		var path=document.getElementById("file").value;
		path=path.substring(path.lastIndexOf("\\")+1,path.length);
		document.getElementById("upfile").value=path;
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
	function qwas() {  
        alert(document.getElementById("upfile").value);
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
        	window.location.href="NorUpDownLoad.jsp";     
        	return false;
        }
    } 
</script>
<body>
<div class="wrap" style="background-color:#5ab8a9"  >	
		<div>
			<br>
			    <div class="input-group">
			        <input name="upfile" id="upfile" value="请选择要上传的文件" type="text" class="form-control">
			        <span id="scan" style="position: relative"  onclick="file.click()" class="btn btn-primary input-group-addon">浏览</span>
			        <input type="file" id="file" name="file" style="display:none" onchange="changevalue()">
			    </div>
			<!-- 			<button onclick="f_DL()" type="button" class="btn btn-block btn-success">下载标准格式</button> -->
			<div class="uploadButtonDiv">
			<button onclick="firm()" type="button" class="btn btn-default btn-success" >上传新数据</button>
			</div>
			<div class="downloadButtonDiv" >
			<button onclick="f_DL()" type="button" class="btn btn-default btn-success">下载标准格式</button>
			</div>
			<div class="lableTipClass" style="width:100%">
			提示  ：  1  若使用原始数据评估，则进入“评估条件设定”模块选择相应条件进行评估
			<br>
			 &emsp; &emsp; &emsp;2 若使用新数据评估，评估步骤：下载标准格式 > 按照标准格式编写数据 > 上传新数据 > 选择评估条件 > 依据当前上传数据评估    
			 <br>
			 &emsp; &emsp; &emsp;3 如需使用软件默认消费量数据，请不要在消费量数据的工作表中录入任何内容
			</div>
			</div>
<div class="wrapp">
<form action="getENorUser" name="getAgeForm" method="post">
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
				<input type="submit" onClick="return validateCheckBox()" class="showResult btn btn-default btn-success" value="依据当前上传数据评估">
				</div>
	</form>
	</div>
</div>	
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				上传中，请稍候...
			</div>
                <button id="closeButton" type="button" class="btn btn-info" data-dismiss="modal"  style="display:none">关闭</button>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</body>
</html>