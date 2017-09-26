<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.action.*"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员登陆成功</title>
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
					<li><a class="active">管理员登录成功</a></li>
					<li><a href="index.jsp">首页</a></li>
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
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<link rel="stylesheet" href="css/flexslider.css" />
<script type="text/javascript" src="js/jquery.flexslider.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style>
	
			.wrap {
            width: 100%;
            height: 303px;
            padding: -20px;                  
            opacity: 0.8;
            background: linear-gradient(to bottom right,#50a3a2,#53e3a6);
            background: -webkit-linear-gradient(to bottom right,#50a3a2,#53e3a6);
        }
        #Layer2 {
				position:absolute;
				left:435px;
				top:388px;
				width:773px;
				height:23px;
				z-index:2;
			}
			.lableTipClass {
        	position: absolute;
			top:187px;
			left:15px;
			color:#FFFFFF;
			font-size: 14px;
        }
</style>
<script type="text/javascript">
	window.onload = function(){
		if(!("${loginStatus}"=="登陆成功"))
			window.location.href="managerLogin.jsp"; 
	}
	function f_DL(){
		//s="fileUploadAction!download.action?filePath="+"localhost:8080/fileUploadandDownload/file/log4j-core-2.7.jar";
	    window.location.href="downloadAction?user=admin";
	   // var target=document.getElementById(myModal);
	    
	}
	function firm(){
    	if(confirm("请在导入前会【覆盖】原有数据，建议先【下载备份】原始文件文件，确定要导入这个excel文件吗？")){
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
                url:'fileUploadAction.action',//用于文件上传的服务器端请求地址
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
</script>
<body>
	<img src="images/qqq15.jpg" alt="Smiley face"  style="max-width:100%;">				
<div class="wrap">	
<div class="row clearfix" id="Layer2">
		<div class="col-md-6 column">
			<div class="form-group">
			    <div class="input-group">
			        <input name="upfile" id="upfile" value="请选择要上传的文件" type="text" class="form-control">
			        <span id="scan" style="position: relative"  onclick="file.click()" class="btn btn-primary input-group-addon">浏览</span>
			        <input type="file" id="file" name="file" style="display:none" onchange="changevalue()">
			    </div>
			</div>
			<button onclick="firm()" type="button" class="btn btn-default btn-block btn-success">上传全部原始数据</button>
			<button onclick="f_DL()" type="button" class="btn btn-default btn-block btn-success">下载全部原始数据</button>
		</div>
		<div class="lableTipClass">
		<p>
			提示  ：  若使用原始数据评估，则不需要“下载”或“上传”数据
		</p>
	</div>
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