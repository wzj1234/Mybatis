<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery.js"></script>
<title>查看结果</title>
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
					<li><a class="active">评估结果</a></li>
					<li><a href="index.jsp">首页</a></li>
					<li><a href="chooseElement.jsp">评估条件设定</a></li>
					<li><a href="NorUpDownLoad.jsp">更新数据</a></li>
					<li><a href="managerLogin.jsp">管理员登录</a></li>
					<li><a href="contactUs.jsp">联系我们</a></li>
				</ul>	
				<div class="clearfix"> </div>	
			</div>
		</nav>
	</div>
</div>
<!-- header -->
</head>
<style>
        .city{
            display: none;;
        }
        .city_first {
            display: block;
        }
        .downAskResClass{position:relative; width:260px; height:30px; left:23px; top:8px} 
        .downConclusionClass{position:relative; width:260px; height:30px;  left:323px; top:-22px} 
        .downAllResClass{ position:relative; width:150px; left:23px; top:64px} 
        .checkBoxClass{ position:relative;left:25px; } 
        .checkElements {
            width: 98%;
            color: #68b068;   
            font:  15px/1.5 Tahoma,Helvetica,Arial,'å®ä½',sans-serif;
        }
        input[type=checkbox]{
			width: 0px;
			height: 0px;
		}
    </style>
<script type="text/javascript" src="echarts-2.2.7/build/dist/echarts.js"></script>   
<script src="js/jquery.min.js"></script>
<script type="text/JavaScript" src="js/jquery.js"></script>
<script>
//s:debug
      //echarts start
   	 require.config({
               paths: {
                   echarts: 'echarts-2.2.7/build/dist'
               }
           });
           
           // 使用
           require(
               [
                   'echarts',
                   'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
               ],
               function (ec) {
                   // 基于准备好的dom，初始化echarts图表
                   var array = new Array();//食物类型  
                   array=["粮谷类","豆类","蔬菜类","乳制品","饮料及水","茶叶及相关制品","酒类","水果类","蛋类","肉类","水产类","糖类"];
                   /*
                   <c:forEach items="${foodTypeList}" var="item" varStatus="status" >  
                       array.push("${item}");
                   </c:forEach> 
                    */                      				
    			 
                   //图一start
                   var diva = ["diva1","diva2","diva3","diva4","diva5","diva6","diva7","diva8","diva9"];
                   var array1 = new Array();//存grapgone的纵坐标值 contaver ！！！！！！！！array1和array2要互换
                   var array2 = new Array();//存grapgone的lkj ！！！！！！！！array1和array2要互换，坐标纵轴长度取array2的最大值
                   var ele;
                   var radioName;
                   var allResult;
                   <c:forEach items="${graphOne}" var="itemSum" varStatus="statusa" > 
    				<c:forEach items="${itemSum.graphOneModel}" var="item" varStatus="status" >  
        				for (var i=0;i<array.length;i++)
        				{
        					if("${item.foodType}" == array[i])
        						{
        							array1[i] = "${item.contaver}";
        							array1[i] = parseFloat(array1[i]).toExponential(3);
        							array2[i] = "${item.LKJ}";
        							//array2[i] = parseFloat(array2[i]).toExponential(0);
        							radioName = "${item.radio}";
        						}
        				}
    				</c:forEach>
    				
                    ele=document.getElementById("diva${statusa.count}");//这里第9个不出结果
                    ele.style.display= "block";
                    var myChart = ec.init(ele); 
                    var ifExceedSum = "${itemSum.graphOneHead.ifExceedSum}";
                    ifExceedSum = parseFloat(ifExceedSum).toExponential(3);
                    var ifExceedSumString;
                    if(ifExceedSum<1){
                    	ifExceedSumString = " 实际受照剂量为："+ifExceedSum+" "+"${itemSum.graphOneHead.unit}"+" , 未超出国家规定的剂量限值";
                    }else{
                         ifExceedSumString = " 实际受照剂量为："+ifExceedSum+" "+"${itemSum.graphOneHead.unit}"+" , 超出国家规定的剂量限值";
                    }
                    var option = {
                 		   title : {
                 		        text: radioName,
                 		        subtext: ifExceedSumString+'\n  '+"${itemSum.graphOneHead.unit}",
                 		        textStyle:{
                 		        	fontSize: 18,
                 		            fontWeight: 'bolder',
                 		            color: '#89cff0',
                 		            fontStyle:'normal'
                 		        },
                 		   subtextStyle:{
                 			   color: 'gray'
                 		   }
                 		    },
                 		   tooltip: {
                            show: true,
                            formatter: "{b}<br/> {c}   ${itemSum.graphOneHead.unit}"
                        	},
                        
                        legend: {
                     	   data: [
                                {
                                    name:'检测结果',
                                    textStyle:{fontWeight:'bold', color:'#89cff0'}
                                },
                                {
                                    name:'剂量限值',
                                    icon: 'images/redline.png',
                                    iconSize: '60%',
                                    inactiveColor:'#ff0000',
                                    textStyle:{fontWeight:'bold', color:'#ff0000'},
                                    color:'#ff0000'
                                }
                                
                            ],
                            selected: {
                                // 选中'系列1'
                                '剂量限值': true,
                                '检测结果': true
                            },
                            selectedMode:false
                        },
                        toolbox: {
                            show : true,
                            feature : {
                                //dataView : {show: true, readOnly: false},
                                saveAsImage : {show: true}
                            }
                        },
                        xAxis : [
                            {
                         	   name:  "类型",
                         	   type : 'category',
                                data : array,
                                axisLabel:{  
                                    interval:0,  
                                    rotate:30,//倾斜度 -90 至 90 默认为0  
                                    margin:2,  
                                    textStyle:{  
                                        color:"#666666"  
                                    }  
                                },    
                            }
                        ],
                        yAxis : [
                            {
                                type : 'value'
                            }
                        ],
                        series : [
                            {
                         	   "name":'检测结果',
                         	   "type":"bar",
                                "data":array1,
                                itemStyle: {  
                                    normal: {  
                                        color: '#89cff0'
                                    }  
                                }
                            ,
                            markPoint:{
                            	symbolSize: '90%',  
                         	   itemStyle: {  
                                    normal: {  
                                        color:'#ff0000',  
                                        label: {  
                                            show: false
                                        }  
                                    }  
                                     },
                                     symbol:'rect',
                                  data : [
                                         {name: '剂量限值：粮谷类', value: array2[0], xAxis: 0, yAxis: array2[0]},      
                                         {name: '剂量限值：豆类', value: array2[1], xAxis: 1, yAxis: array2[1]},
                                         {name: '剂量限值：蔬菜类', value: array2[2], xAxis: 2, yAxis: array2[2]},
                                         {name: '剂量限值：乳制品', value: array2[3], xAxis: 3, yAxis: array2[3]},
                                         {name: '剂量限值：饮料及水', value: array2[4], xAxis: 4, yAxis: array2[4]},
                                         {name: '剂量限值：茶叶及相关制品', value: array2[5], xAxis: 5, yAxis: array2[5]},
                                         {name: '剂量限值：酒类', value: array2[6], xAxis: 6, yAxis: array2[6]},
                                         {name: '剂量限值：水果类', value: array2[7], xAxis: 7, yAxis: array2[7]},
                                         {name: '剂量限值：蛋类', value: array2[8], xAxis: 8, yAxis: array2[8]},
                                         {name: '剂量限值：肉类', value: array2[9], xAxis: 9, yAxis: array2[9]},
                                         {name: '剂量限值：水产类', value: array2[10], xAxis: 10, yAxis: array2[10]},
                                         {name: '剂量限值：糖类', value: array2[11], xAxis: 11, yAxis: array2[11]}

                                     ]
                            } 
                                
                            }
                            
                        ]
                        
                    };
                    myChart.setOption(option); 
    			   </c:forEach>                  
                   //图一end
                   
                   //图二start
                   var divb = ["divb1","divb2","divb3","divb4","divb5","divb6","divb7","divb8","divb9","divb10","divb11","divb12","divb13","divb14","divb15","divb16","divb17","divb18","divb19","divb20","divb21","divb22","divb23","divb24","divb25","divb26","divb27","divb28","divb29","divb30"];
                   var arraySVT = new Array();//存grapgtwo的纵坐标值 SVT
                   var radioName;
                   var eleb;
                   <c:forEach items="${graphTwo}" var="itemSumb" varStatus="statusb" >
                   //alert("${statusb.count}");
    				<c:forEach items="${itemSumb.SVTModel}" var="itemb" varStatus="statusc" >  
        				for (var i=0;i<array.length;i++)
        				{
        					if("${itemb.foodType}" == array[i])
        						{
        							arraySVT[i] = "${itemb.SVT}";
        							arraySVT[i] = parseFloat(arraySVT[i]).toExponential(3);
        							radioName = "${itemb.radio}"+"   "+"${itemb.age}"+"岁   ";
        						}
        				}
    				</c:forEach>
    				eleb=document.getElementById("divb${statusb.count}");
                    eleb.style.display= "block";
                   var myChartx = ec.init(eleb);
                   var Sv = "${itemSumb.graphTwoHead.SVTRA}";
                   Sv = parseFloat(Sv).toExponential(3);
                   var optionx = {
                		   title : {
               		        text: radioName,
               		        subtext: " 实际受照有效剂量为："+Sv+" Sv"+'\n  Sv',
               		        
               		        textStyle:{
               		        	fontSize: 18,
               		            fontWeight: 'bolder',
               		            color: '#B7FF4A',
               		            fontStyle:'normal'
               		        },
               		   subtextStyle:{
               			   color: 'gray'
               		   }
               		    },
               		 	tooltip: {
                         show: true,
                         formatter: "{b}<br/> {c}   Sv"
                     	},
                       legend: {
                           data:[
                        	   {name:'有效剂量',
                               textStyle:{fontWeight:'bold', color:'#B7FF4A'}
                        	   }
                        	   ],
                           selected: {
                               '有效剂量': true
                           },
                           selectedMode:false
                       },
                       toolbox: {
                           show : true,
                           feature : {
                               //dataView : {show: true, readOnly: false},
                               saveAsImage : {show: true}
                           }
                       },
                       xAxis : [
                           {
                        	   name:  "类型",
                        	   type : 'category',
                               data : array,
                               axisLabel:{  
                                   interval:0,  
                                   rotate:30,//倾斜度 -90 至 90 默认为0  
                                   margin:2,  
                                   textStyle:{  
                                       color:"#666666"  
                                   }  
                               }
                           }
                       ],
                       yAxis : [
                           {
                               type : 'value'
                           }
                       ],
                       series : [
                           {
                        	   "name":'有效剂量',
                        	   "type":"bar",
                               "data":arraySVT,
                               itemStyle: {  
                                   normal: {  
                                       color: '#B7FF4A'
                                   }  
                               }
                           }
                           
                           
                       ]
                   };
                   myChartx.setOption(optionx);
   				</c:forEach>
                 //图二end
               }
           );
   	//echarts end
    </script>
<body>
<div>
<br>
			 <!--  <button onclick="downloadAllCalResult()" type="button"  class="btn btn-default btn-block btn-success">下载所有结果</button>	--> 	 	
		 	<form action="downloadAllCalResult">
	  		 	<div class="downAllResClass" style="display:none;">
	  			   <input type="submit" value="下载所有结果"  class="btn btn-default btn-block btn-success"  />	
	  			</div>
			</form> 
		 	<div class="checkElements">
		 	<form action="downloadAskCalResult">
		 		<div class="checkBoxClass">
		 		<label>所选放射性核素：</label>
	   	  	  		<s:checkboxlist list="radioList" id="radioList" name="radioList"></s:checkboxlist>
	   	  	  	<br>
	   	  	  	<label>所选人群：&emsp;&emsp;&emsp;</label>
	     			 <s:checkboxlist list="ageList" name="ageList"></s:checkboxlist>
	  		 	</div>
	  		 	<div class="downAskResClass" >
	  			   <input type="submit" value="下 载 评 估 结 果 "  class="btn btn-default btn-block btn-success" />	
	  			</div>
			</form> 
			</div>
			<form action="downloadConclusion">
				<div  style="display:none;">
				<div class="checkBoxClass">
		 		<label>所选放射性核素：</label>
	   	  	  		<s:checkboxlist list="radioList" id="radioList" name="radioList"></s:checkboxlist>
	   	  	  	<br>
	   	  	  	<label>所选年龄段：&emsp;&emsp;</label>
	     			 <s:checkboxlist list="ageList" name="ageList"></s:checkboxlist>
	  		 	</div>
				</div>
	  			<div class="downConclusionClass">
	  			   <input type="submit" value="下 载 评 估 结 论 "  class="btn btn-default btn-block btn-success" />	
	  			</div>
			</form> 
</div>			
		<br>
	  	  <div id="diva1" style="width:630px;height:400px;display:none;float:left"></div>
	  	  <div id="diva2" style="width:630px;height:400px;display:none;float:left"></div>
	  	  <div id="diva3" style="width:630px;height:400px;display:none;float:left"></div>
	  	  <div id="diva4" style="width:630px;height:400px;display:none;float:left"></div>
	  	  <div id="diva5" style="width:630px;height:400px;display:none;float:left"></div>
	  	  <div id="diva6" style="width:630px;height:400px;display:none;float:left"></div>
	  	  <div id="diva7" style="width:630px;height:400px;display:none;float:left"></div>
	  	  <div id="diva8" style="width:630px;height:400px;display:none;float:left"></div>	
	  	  <div id="diva9" style="width:630px;height:400px;display:none;float:left"></div>
	  	  	  	  	  	    
	   	   <div id="divb1" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb2" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb3" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb4" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb5" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb6" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb7" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb8" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb9" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb10" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb11" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb12" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb13" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb14" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb15" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb16" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb17" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb18" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb19" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb20" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb21" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb22" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb23" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb24" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb25" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb26" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb27" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb28" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb29" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb30" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb31" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb32" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb33" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb34" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb35" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb36" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb37" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb38" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb39" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb40" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb41" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb42" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb43" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb44" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb45" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb46" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb47" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb48" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb49" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb50" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb51" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb52" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb53" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb54" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb55" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb56" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb57" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb58" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb59" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb60" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb61" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb62" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb63" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb64" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb65" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb66" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb67" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb68" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb69" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb70" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb71" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb72" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb73" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb74" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb75" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb76" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb77" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb78" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb79" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb80" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb81" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb82" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb83" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb84" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb85" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb86" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb87" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb88" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb89" style="width:630px;height:400px;display:none;float:left"></div>
	   	   <div id="divb90" style="width:630px;height:400px;display:none;float:left"></div>
	   	  
</body>
</html>