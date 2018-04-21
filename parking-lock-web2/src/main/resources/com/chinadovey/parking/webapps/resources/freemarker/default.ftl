<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>智慧停车服务云平台</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- bootstrap 3.0.2 -->
<link href="${request.contextPath!}/statics/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${request.contextPath!}/statics/css/cropper.css" rel="stylesheet" type="text/css" />
<!-- font Awesome -->
<link href="${request.contextPath!}/statics/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- Ionicons -->
<link href="${request.contextPath!}/statics/css/ionicons.min.css" rel="stylesheet" type="text/css" />
<!-- Morris chart -->
<link href="${request.contextPath!}/statics/css/morris/morris.css" rel="stylesheet" type="text/css" />
<!-- jvectormap -->
<link href="${request.contextPath!}/statics/css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet"
	type="text/css" />
<!-- fullCalendar -->
<link href="${request.contextPath!}/statics/css/fullcalendar/fullcalendar.css" rel="stylesheet"
	type="text/css" />
<!-- Daterange picker -->
<link href="${request.contextPath!}/statics/css/daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" type="text/css" />
<link href="${request.contextPath!}/statics/css/bootstrap-timepicker/bootstrap-datetimepicker.min.css"
	rel="stylesheet" type="text/css" />
<link type="text/css" href="${request.contextPath!}/statics/css/bootstrap-timepicker.min.css" rel="stylesheet" />
<!-- bootstrap wysihtml5 - text editor -->
<link href="${request.contextPath!}/statics/css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"
	rel="stylesheet" type="text/css" />
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<!-- Theme style -->
<link href="${request.contextPath!}/statics/css/AdminLTE.css" rel="stylesheet" type="text/css" />
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<link href="${request.contextPath!}/statics/font-awesome/css/font-awesome.css" rel="stylesheet"/>
<link href="${request.contextPath!}/statics/css/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet"/>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        <!-- Content Header (Page header) -->
<link rel="Shortcut Icon" href="${request.contextPath!}/statics/img/icon.png">
<style type="text/css">
label.error{
	color:red;
}
.navbar-nav > .user-menu > .dropdown-menu > li.user-header {
    height: 110px;
    padding: 10px;
    background: #3E9AFB;
    text-align: center;
}
</style>
</head>
<body class="skin-blue fixed">
	<!-- header logo: style can be found in header.less -->
	<header class="header">
		<a href="javascript:;" class="logo">     <!-- Add the class icon to your logo image or logo icon to add the margining -->
				车位锁管控平台
		</a>
		<!-- Header Navbar: style can be found in header.less -->
		<nav class="navbar navbar-static-top" role="navigation">
			<!-- Sidebar toggle button-->
			<a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas"
				role="button"> <span class="sr-only">Toggle navigation</span> <span
				class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
			</a>
			<div class="navbar-right">
				<ul class="nav navbar-nav">
				<!-- Notifications: style can be found in dropdown.less -->
					<li class="dropdown user user-menu"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"> <i
							class="glyphicon glyphicon-user"></i> <span>${_dovey_session_secu_object_.user.realname!} <i
								class="caret"></i></span>
					</a>
						<ul class="dropdown-menu">
							<!-- User image -->
							<li class="user-header"> 
								<p>
								<input type="hidden" value="${_dovey_session_secu_object_.user.id!}" id="admin_id"/>
								<input type="hidden" value="${_dovey_session_secu_object_.user.picture!}" id="admin_picture"/>
 									${_dovey_session_secu_object_.user.email!}
								</p></li>
							<!-- Menu Body -->
							<li class="user-body">
							</li>
							<!-- Menu Footer-->
							<li class="user-footer">
								<div class="pull-left">
									<a id="switch" class="btn btn-default btn-flat">切换用户</a>
								</div>
								<div class="pull-right">
									<a id="logout" class="btn btn-default btn-flat">退出系统</a>
								</div>
							</li>
						</ul></li>
				</ul>
			</div>
		</nav>
	</header>
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="left-side sidebar-offcanvas">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image">
					</div>
					<div class="pull-left info">
						<a href="#"></a>
						<p>你好，${_dovey_session_secu_object_.user.realname!}</p>
					</div>
				</div>
				<!-- /.search form -->
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu">
				<@SecuValidate resource="com.chinadovey.parking.acl.PlatformOverviewRank">
				<li class="active"><a id="_default" href="javascript:;"> <i
					class="fa fa-dashboard"></i> <span>平台概览</span>
				</a></li>
				</@SecuValidate>
					${_dovey_session_secu_ui_menu_}
				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Right side column. Contains the navbar and content of the page -->
		<aside id="main-content" class="right-side"></aside>
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->
    

<div id="audio"> 
<audio id="aud" src="${request.contextPath!}/statics/audio/promptTone.mp3" ></audio>
</div>
	<!-- add new calendar event modal -->
	<!-- jQuery 2.0.2 -->
	<script src="${request.contextPath!}/statics/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	<!-- jQuery UI 1.10.3 -->
	<script src="${request.contextPath!}/statics/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
	<!-- Bootstrap -->
	<script src="${request.contextPath!}/statics/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/js/cropper.js" type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/js/uploadPreview.js" type="text/javascript"></script>
	<!-- Morris.js charts -->
	<script src="${request.contextPath!}/statics/js/plugins/raphael/raphael-min.js"></script>
	<script src="${request.contextPath!}/statics/js/plugins/morris/morris.min.js" type="text/javascript"></script>
	<!-- Sparkline -->
	<script src="${request.contextPath!}/statics/js/plugins/sparkline/jquery.sparkline.min.js"
		type="text/javascript"></script>
	<!-- jvectormap -->
	<script src="${request.contextPath!}/statics/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"
		type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"
		type="text/javascript"></script>
	<!-- fullCalendar -->
	<script src="${request.contextPath!}/statics/js/plugins/fullcalendar/fullcalendar.min.js"
		type="text/javascript"></script>
	<!-- jQuery Knob Chart -->
	<script src="${request.contextPath!}/statics/js/plugins/jqueryKnob/jquery.knob.js"
		type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/js/plugins/Chart.js"
		type="text/javascript"></script>
	<!-- daterangepicker -->
	<script src="${request.contextPath!}/statics/js/plugins/daterangepicker/daterangepicker.js"
		type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/js/plugins/daterangepicker/date.js"
		type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/js/plugins/bootstrap-timepicker/bootstrap-datetimepicker.min.js"
		type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/js/plugins/bootstrap-timepicker/bootstrap-datetimepicker.zh-CN.js"
		type="text/javascript"></script>
	<!-- Bootstrap WYSIHTML5 -->
	<script src="${request.contextPath!}/statics/js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
	<!-- iCheck -->
	<script src="${request.contextPath!}/statics/js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
	<!-- Admin LTE -->
	<script src="${request.contextPath!}/statics/js/adminlte-plugins.js" type="text/javascript"></script>
	
	<script src="${request.contextPath!}/statics/js/highcharts.js" type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/js/data.js" type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/js/exporting.js" type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/js/jquery.easypiechart.min.js" type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/layer/layer.js" type="text/javascript"></script>
	<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/chosen/chosen.jquery.min.js"></script>
	
	<script type="text/javascript" src="${request.contextPath!}/statics/js/ajaxfileupload.js"></script>
 	<script type="text/javascript" src="${request.contextPath!}/statics/js/filestyle.min.js"></script>
 	<script type="text/javascript" src="${request.contextPath!}/statics/css/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
	
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=eqqM74ODvE9SwBK58L940OHR"></script>
	<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=11f97147317979cafabd0b68730dcd36"></script>
	
	<!-- Custom -->
	<script type="text/javascript">
	$(function() {
			/* Sidebar tree view */
			$(".sidebar .treeview").tree();
	    	
	    	danger();
	    	
	    	
			jQuery.fn.zrPageRefresh = function(options) {
				if (typeof options === 'string') {
					options = {
						url : options
					};
				}
				// Render options
				var settings = $.extend({
					url : "",
					onLoadStart : function(dom) {
					},
					onLoadDone : function(dom) {
					}
				}, options);
              
				
				//The overlay
				var overlay = $('<div class="overlay"></div><div class="loading-img"></div>');

				return this
						.each(function() {
							//if a source is specified
							if (settings.url === "") {
								if (console) {
									console
											.log("Please specify a URL first - zrPageRefresh()");
								}
								return;
							}
							//the dom
							var dom = $(this);
							$("body").append(overlay);
							dom.load(settings.url, function() {
								$("body").find(overlay).remove();
							});
						});
			};
				if(${guard} == 1){
				$("#main-content").zrPageRefresh("${request.contextPath!}/map/map/getMap");
				}else if(${guard} == 2){
					$("#main-content").zrPageRefresh("${request.contextPath!}/index");
				}
			$(".menu-action a").click(function() {
				var href = $(this).attr("href");
				if (href != "" && href != "#") {
					$("#main-content").zrPageRefresh("${request.contextPath!}"+href);
				}
				return false;
			});
			
			$('#dangerAll').click(function(){
	    		$("#main-content").zrPageRefresh("${request.contextPath!}/control/parkOnline/list");
	    	});
			
			$(".dropdown-menu a").click(function() {
				var href = $(this).attr("href");
				if (href != "" && href != "#") {
					$("#main-content").zrPageRefresh("${request.contextPath!}"+href);
				}
				return false;
			});
			
			$('#_default').click(function(){	
					$("#main-content").zrPageRefresh("${request.contextPath!}/index");
			});
			
			$('#logout').click(function(){
				$.get('${request.contextPath!}/login/logout',
					function(data){
						location.href="${request.contextPath!}/";
					});
			});
			
			$('#switch').click(function(){
				window.location.href="${request.contextPath!}/login/input";
			});
			
			  $('#protraitEdit').click(function(){
		      	protraitEdit();
		      });
			
			var $previews = $('.preview');
      		
			  $('#file').uploadPreview({ Img: "picture-view", Width: 100, Height: 100 ,Callback: function () {
			  		$('#picture-view').cropper('destroy');
			  		$('#picture-view').cropper({
			          build: function (e) {
			            var $clone = $(this).clone();
			
			            $clone.css({
			              display: 'block',
			              width: '100%',
			              minWidth: 0,
			              minHeight: 0,
			              maxWidth: 'none',
			              maxHeight: 'none'
			            });
			
			            $previews.css({
			              width: '100%',
			              overflow: 'hidden'
			            }).html($clone);
			          },
						aspectRatio: 1 / 1,
			          cropBoxResizable: false,
			          crop: function (e) {
			            var imageData = $(this).cropper('getImageData');
			            var previewAspectRatio = e.width / e.height;
			
			            $previews.each(function () {
			              var $preview = $(this);
			              var previewWidth = $preview.width();
			              var previewHeight = previewWidth / previewAspectRatio;
			              var imageScaledRatio = e.width / previewWidth;
			
			              $preview.height(previewHeight).find('img').css({
			                width: imageData.naturalWidth / imageScaledRatio,
			                height: imageData.naturalHeight / imageScaledRatio,
			                marginLeft: -e.x / imageScaledRatio,
			                marginTop: -e.y / imageScaledRatio
			              });
			            });
			          }
			        });
			   }});      
				setTimeout(function(){newMessage();},3000);
		});

var messageTotal = 0;
var messagetime = 60;
	<@SecuValidate resource="com.chinadovey.parking.acl.MessageStayCall">
		messagetime = ${stayPromptTime!60};
	</@SecuValidate>
	<@SecuValidate resource="com.chinadovey.parking.acl.MessageEatCall">
		messagetime = ${eatPromptTime!60};
	</@SecuValidate>
	<@SecuValidate resource="com.chinadovey.parking.acl.MessageStayCall">
	<@SecuValidate resource="com.chinadovey.parking.acl.MessageEatCall">
		messagetime = ${eatPromptTime!60};
	</@SecuValidate>
	</@SecuValidate>
var audio = $('#aud')[0];
var messageStay = 0;
var messageEat = 0;
function newMessage(){
	<@SecuValidate resource="com.chinadovey.parking.acl.MessageStayCall">
	$.post("${request.contextPath!}/account/stayRecord/countRecently",function(data){
		var stayTmp = messageStay;
		messageStay = data;
		messageTotal = messageStay;
		$("#stayMessage").text(messageStay);
		$("#totalMessages").text(messageTotal);
		$("#totalMessage").text(messageTotal);
		if($("#messageId").val()=="stay"){
			if(messageStay > 0 && messageStay > stayTmp){
				$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/account/stayRecord/getList'});
			}
		}else if($("#messageId").val() == "zlstay"){
			if(messageStay > 0 && messageStay > stayTmp){
				$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/account/stayRecord/getList'});
			}
		}
	});
	</@SecuValidate>
	<@SecuValidate resource="com.chinadovey.parking.acl.MessageEatCall">
	$.post("${request.contextPath!}/account/eatRecord/countRecently",function(data){
		var eatTmp = messageEat;
		messageEat = data;
		messageTotal = messageEat;
		$("#eatMessage").text(messageEat);
		$("#totalMessages").text(messageTotal);
		$("#totalMessage").text(messageTotal);
		if($("#messageId").val()=="eat"){
			if(messageEat > 0 && messageEat > eatTmp){
				$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/account/eatRecord/getList'});
			}
		}	
	});
	</@SecuValidate>
			setTimeout(function(){
				messageTotal = ($("#stayMessage").text()*1 + $("#eatMessage").text()*1);
				$("#totalMessages").text(messageTotal);
				$("#totalMessage").text(messageTotal);
				if(messageTotal > 20){
					$("#totalLabel").attr("class","label label-danger");
				}else if(messageTotal > 10){
					$("#totalLabel").attr("class","label label-warning");
				}
				if(messageTotal > 0){
					$("#audio").html('<audio id="aud" src="${request.contextPath!}/statics/audio/promptTone.mp3" autoplay="true" playcount="1" ></audio>');
				}
			}, 1000);
	setTimeout(function(){
		$("#audio").html("");
		newMessage();
	},messagetime* 3600000);
	
}


function danger(){
     $.post("${request.contextPath!}/control/parkOnline/getOutList",function(data){
          var outlineCount = data.outLineList.length;
          $('#outLineCount').text(outlineCount);
          $('#outLineTitle').text("当前有"+outlineCount+"个停车场掉线！");
          var htmlText = "";
          for(var i=0 ; i<data.outLineList.length; i++){
                 htmlText += '<a href="#"><i class="ion ion-ios7-people info"></i>'+data.outLineList[i].name+'</a>'
          }
          $('#outLineParks').html(htmlText);
          setTimeout(function(){
		   $("#audio").html("");
		danger();
	},messagetime* 3600000);
          
     });

}

function initPieChart(chart,size,color){
	if(size == null){
		size = 350;
	}
	if(color == null){
		color = randomColor();
	}
	$(chart).easyPieChart({
		animate: 1000,
        size: size,
        lineWidth:17,
        scaleColor:false,
        trackColor:'#F0F0F0',
		trackWidth: 12,
        barColor:color
	});
}
function initPieCharts(charts,size){
	if(size == null){
		size = 350;
	}
	$(charts).each(function() {
		initPieChart(this,size);
	});
}

function randomColor(){
	return '#'+(Math.random()*0xffffff<<0).toString(16);
}


/**
 * 格式化日期
 * @param dateTime
 * @param flag
 * @returns {String}
 */
function dateFormat(dateTime , flag){
	var year = dateTime.getFullYear();
	var month = formatDateElement(dateTime.getMonth()+1);
	var date = formatDateElement(dateTime.getDate());
	var hour = formatDateElement(dateTime.getHours());
	var minute = formatDateElement(dateTime.getMinutes());
	var second = formatDateElement(dateTime.getSeconds());
	if(flag == 1){
		return year + '-' + month + '-' + date + ' ' + hour + ':' + minute + ':' + second;
	}else if(flag == 2){
		return hour + ":" + minute + ":" + second;
	}else if(flag == 3){
	    return year + "年" + month + "月";
	}else if(flag == 4){
	    return year + "年" + month + "月" + date + "日";
	}else if(flag == 5){
	    return year + "年" + month + "月" + date + "日   " + hour +"时";
	}else if(flag == 6){
	    return year + "年";
	}else{
		return year + '-' + month + '-' + date;
	}
}

/**
 * 日期格式中小于10的前面加0
 * @param element
 */
function formatDateElement(element){
	if(element < 10){
		return '0'+element;
	}else{
		return element;
	}
}

//判断日期，时间大小 
function compareTime(startDate, endDate) {  
	 if (startDate.length > 0 && endDate.length > 0) {  
	    var startDateTemp = startDate.split(" ");  
	    var endDateTemp = endDate.split(" ");  
	
	    var arrStartDate = startDateTemp[0].split("-");  
	    var arrEndDate = endDateTemp[0].split("-");  
	
	    var arrStartTime = startDateTemp[1].split(":");  
	    var arrEndTime = endDateTemp[1].split(":");  
		for(var i = arrStartTime.length;i<3;i++){
				arrStartTime[i] = 0;
				arrEndTime[i] = 0;
		}
		
		var allStartDate = new Date(arrStartDate[0], arrStartDate[1], arrStartDate[2], arrStartTime[0], arrStartTime[1], arrStartTime[2]);  
		var allEndDate = new Date(arrEndDate[0], arrEndDate[1], arrEndDate[2], arrEndTime[0], arrEndTime[1], arrEndTime[2]);  

		if (allStartDate.getTime() >= allEndDate.getTime()) {  
		        return false;  
		} else {  
		    return true;  
	    }  
	} else {  
    	return false;  
    }  
}  

  
  	//比较日前大小 
function compareDate(checkStartDate, checkEndDate) {     
    var arys1= new Array();     
    var arys2= new Array();     
	if(checkStartDate != null && checkEndDate != null) {     
	    arys1=checkStartDate.split('-');     
	    arys2=checkEndDate.split('-');     
	    for(var i = arys1.length;i<3;i++){
				arys1[i] = 0;
				arys2[i] = 0;
		}
	    var sdate=new Date(arys1[0],parseInt(arys1[1]-1),arys1[2]);     
	    var edate=new Date(arys2[0],parseInt(arys2[1]-1),arys2[2]);     
		if(sdate > edate) {     
		    return false;        
		}  else {  
		    return true;     
		}  
    }     
}    

//比较日期，时间大小 
function compareCalendar(startDate, endDate) {  
	if (startDate.indexOf(" ") != -1 && endDate.indexOf(" ") != -1 ) {  
	    //包含时间，日期 
	   return compareTime(startDate, endDate);              
	} else {  
	    //不包含时间，只包含日期 
	   return compareDate(startDate, endDate);  
    }  
}   

/**
 * 停车场格式
 * @param value
 * @param row
 * @param index
 * @returns
 */
function parkFormatter(value, row, index) {
	  var data;
	  if(row.park == null){
	  	data = "暂时没有查询到停车场信息";
	  }else{
	  	data = row.park.name;
	  }
	  return [data].join('');
}


		 function protraitEdit() {
				var protraitprotraitView = $("#protraitView img");
				var cropper = $('#picture-view').cropper('getCroppedCanvas');
				var data = cropper.toDataURL();
				data=data.split(',')[1];
				
	        	var fileName = $("#file").val();
	        	var ldot = fileName.lastIndexOf(".");
  				var type = fileName.substring(ldot + 1);
			    $.ajaxFileUpload({
			      url: "${request.contextPath!}/system/upload",
			      secureuri:false,
			      fileElementId:'file',//file标签的id  
			      data:{
			      	data:data,
			      	type:type,
			      	path:"picture"
			      },//一同上传的数据  
			      dataType: 'application/json',//返回数据的类型  
			       success : function(data, status) {  
		                //把图片替换  
				       	data = data.substring(data.indexOf("{"),data.indexOf("}") + 1 );  
				       	var obj = jQuery.parseJSON(data);
				       	$("#admin_picture").val(obj.fileName);
			 		    $("#failure-info").hide();
			 		    var user = {
			 		    	id : $("#admin_id").val(),
							picture : "cut" + $("#admin_picture").val().substring(0,16)+".png"
						};
						var param = "user=" + JSON.stringify(user);
						$.post('${request.contextPath!}/system/user/update',param,function(){
							$("#protrait").attr("src","${request.contextPath!}/system/cut" + obj.fileName.substring(0,16) + ".png" + "/picture");
							$("#protrait1").attr("src","${request.contextPath!}/system/cut" + obj.fileName.substring(0,16) + ".png" + "/picture");
						});
						$('#file').attr('disabled',"true");
						$("#protrait-edit").modal('hide');
			   		 },
			    	error : function(data,status,e) {
						$("#success-info").hide();
			 		    $("#failure-info").show();
			     	 }
			    });
			  }
	function checkCarNo(carNo){
		var re= /^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$/;
		if(carNo.search(re)==-1){
	         return false;
	    }
    return true;
}

 function pieChart(element,title,seriesName,data){
		  Highcharts.setOptions({  
		       colors: [ randomColor(),randomColor(),randomColor(),randomColor(),randomColor(),randomColor(),randomColor(),randomColor()]  
		}); 
		for(var index in data){
			var obj = data[index];
		  	if(0==obj[0]){
		 		delete obj;
		  	}
		}
	    $(element).highcharts({
	        title: {
	            text: title
	        },
	        chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y}（元）</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false,
		               //  formatter: function() { 
						//    return  Highcharts.numberFormat(this.y, 0, ',') + '元';
						//}
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: seriesName,
               	data: data
            }]
	    });
}
	</script>
</body>
</html>