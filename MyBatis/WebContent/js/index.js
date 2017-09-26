function ajaxFileUpload() {
			//弹一个不能让用户点击取消的对话框直至上传结束         bootstrap模态框			
			var excelFile = $('#fileToUpload').val();  
	        if(excelFile == '') {  
	            alert('请选择Excel文件..');  
	            return false;  
	        }  
	        if(excelFile.lastIndexOf('.') > 0) {  
	            var ext = excelFile.substr(excelFile.lastIndexOf('.')+1);  
	            if(!((ext == 'xlsx')||(ext == 'xls'))){  
	                alert('请选择Excel类型文件');  
	                return false  
	            }  
	        }  
				$.ajaxFileUpload({//有点像构造函数吧,alert()外层必须用一个函数包裹
					url : 'upload', //用于文件上传的服务器端请求地址
					secureuri : false,
					fileElementId : 'fileToUpload',
					dataType : 'json',//以下两个data都得是json格式 
					data : {year : $("#finaceYearId").val(),month : $("#finaceMonthId").val()},//这个data是发送给服务器的数据,
					success : function(data) {//这个data是服务器返回的数据
						if(data.code==1)
							alert('上传成功');
						else{
							alert(data.msg);
						}
					},
					error : function() {
						//如果action没有错误的话，这个一般是网络连接错误
					},
					complete:function() {
						//上传完成时，让模态框变成可取消状态
					}
				})
	
				return false;
}
function connectSQL(){
			testForm.action = "connectSQL!index.action";
			//testForm.target = "frame3"; //这会弹出新界面
			testForm.submit();
}