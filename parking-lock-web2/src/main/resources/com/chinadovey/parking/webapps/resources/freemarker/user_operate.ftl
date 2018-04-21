<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>智慧停车服务云平台</title>
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<link href="${request.contextPath!}/statics/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${request.contextPath!}/statics/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${request.contextPath!}/statics/css/ionicons.min.css" rel="stylesheet" type="text/css" />
<link href="${request.contextPath!}/statics/css/morris/morris.css" rel="stylesheet" type="text/css" />
<link rel="Shortcut Icon" href="${request.contextPath!}/statics/img/icon.png">
<style type="text/css">
         html{
         height:100%;
        }
          body {
             	background-image: url('${request.contextPath!}/statics/img/login_bg.jpg');
	            background-size:cover;
          }
          
          #globale{
          		margin-top:60px;
          		text-align:center;
				
          }
          .logo{
              padding-right:400px;
          }
          #content{
          	margin-top:60px;
          	margin-left:auto ;
		    margin-right:auto;
            background-color:#fff;
            width:700px;
          }
          
          #content-left{
            background-color:#6cb2fb;
            width:300px;
            height:320px;
            padding:26px;
            float:left;
          }
          
          #code-text{
			margin-top:250px;
			margin-left:20px;
			color:#fff;
			font-size:14px;          
          }

          #content-right{
            height:320px;
          }
                
          #code-pic{
             float:left;
          }
          
          #login-title{
            padding-top:24px;
            padding-left:0px;
          	font-size:18px;
          	font-family:"微软雅黑";
          	
          }
          
          .form-box {
				width: 360px;
				margin-top:10%;
			    margin-left:45%;
           }
           
           #mobile{
	            margin-left:30px;
	            margin-top:24px;
	            background:url(${request.contextPath!}/statics/img/user.png) no-repeat; 
	            background-position:5px 9px;                
	            border: 1px solid #999;                
	            padding: 2px 2px 2px 30px;
           }
           
           #spaceNo{
            margin-left:30px;
            margin-top:30px;
            background:url(${request.contextPath!}/statics/img/pwd.png) no-repeat; 
            background-position:5px 9px;                
            border: 1px solid #999;                
            padding: 2px 2px 2px 30px;
           }
          
          
          .mytxt {
		    color:#333;
		    width:340px;
		    height:40px;
		    line-height:30px;
		    font-family:"Microsoft YaHei",Tahoma,Verdana,SimSun;
		    font-style:normal;
		    font-variant:normal;
		    font-size-adjust:none;
		    font-stretch:normal;
		    font-weight:normal;
		    margin-top:0px;
		    margin-bottom:0px;
		    margin-left:0px;
		    padding-top:4px;
		    padding-right:4px;
		    padding-bottom:4px;
		    padding-left:4px;
		    font-size:14px;
		    outline-width:medium;
		    outline-style:none;
		    outline-color:invert;
		    border-top-left-radius:3px;
		    border-top-right-radius:3px;
		    border-bottom-left-radius:3px;
		    border-bottom-right-radius:3px;
		    text-shadow:0px 1px 2px #fff;
		    background-attachment:scroll;
		    background-repeat:repeat-x;
		    background-position-x:left;
		    background-position-y:top;
		    background-size:auto;
		    background-origin:padding-box;
		    background-clip:border-box;
		    background-color:rgb(255,255,255);
		    margin-right:8px;
		    border-top-color:#ccc;
		    border-right-color:#ccc;
		    border-bottom-color:#ccc;
		    border-left-color:#ccc;
		    border-top-width:1px;
		    border-right-width:1px;
		    border-bottom-width:1px;
		    border-left-width:1px;
		    border-top-style:solid;
		    border-right-style:solid;
		    border-bottom-style:solid;
		    border-left-style:solid;
		    }
		    
		    .mytxt:focus {
			     border: 1px solid #fafafa;
			     -webkit-box-shadow: 0px 0px 6px #007eff;
			     -moz-box-shadow: 0px 0px 5px #007eff;
			     box-shadow: 0px 0px 5px #007eff;   
             }
             
             .login-btn{
                  margin-top:30px;
				  margin-left:30px;	
			      width:340px;
			      height:46px; 
			      border:none;
				  padding:0 2px 4px 0;
				  background:url(${request.contextPath!}/statics/img/login-btn-bg.png) no-repeat 0 0px;font:16px/35px "微软雅黑"; 
				  color:#fff; 
				  cursor:pointer;
			      border-radius:4px;
                 -webkit-border-radius: 4px
            }
              .operate-btn{
                  margin-top:0px;
				  margin-left:30px;	
			      width:340px;
			      height:72px; 
			      border:none;
				  padding:0 2px 4px 0;
				  background:url(${request.contextPath!}/statics/img/operate-btn-bg.png) no-repeat 0 0px;font:16px/35px "微软雅黑"; 
				  color:#fff; 
				  cursor:pointer;
			      border-radius:4px;
                 -webkit-border-radius: 4px
            }
            .forget-password{
				padding-right:250px;
				margin-top:10px;
            }
            #login-msg{
				padding-right:160px;
				color:red;
				font-size:14px;            	
            }
            .operate {
               padding-bottom:50px;
            }
        </style>
</head>
<body>
      
    <div id="globale">
	    <div class="logo">
	        <img src="${request.contextPath!}/statics/img/logo.png">
	    </div>
	    <div id="content">
		    <div id="content-right">
                 <div id="login-box">
                          <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                          <div id="login-title">请输入手机号和车位号确认后控制车锁</div>
                          <div id="login-msg"></div>
                          <input type="text" name="mobile" id="mobile" class="mytxt" placeholder="手机号"/><br/>
                          <input type="text" id="spaceNo" name="spaceNo" class="mytxt" placeholder="车位号"/><br/>
                          <button type="button" id="confirm" class="login-btn" >确定</button> 
                 </div>		    
		    </div>
		    
		    <div class="operate" id='operate-content'>
		        <span style="font-size:20px;">您的车位号是<font id='mySpaceNo'></font></span><br/><br/>
		        <button type="button" id="operate" class="operate-btn" >下降车位锁</button> 
		    </div>
		    
	    </div>
    <div>
	<script src="${request.contextPath!}/statics/js/jquery-1.8.3.min.js" type="text/javascript"></script>
	<script src="${request.contextPath!}/statics/js/bootstrap.min.js" type="text/javascript"></script>
    <script type="text/javascript">
		    var slaveId;
		    var mobile;
			$(function() {
				$("#success-info").hide();
				$("#failure-info").hide();
				$("#wait-info").hide();
				$('#operate-content').hide();
				/**
			     * 验证手机和车位号
			     */	
				$('#confirm').click(function(){
				    mobile = $('#mobile').val();
				    var spaceNo = $('#spaceNo').val();
				    $.post('${request.contextPath!}/userOperate/validate','mobile='+mobile+'&spaceNo='+spaceNo , function(data){
				        if(data.result==true){
						    $('#operate-content').show();
						    $('#mySpaceNo').text(data.spaceNo);	
						    slaveId = data.slaveId;	
				        }else{
				            $("#failure-info").text('手机号或车位号不正确，请重新输入！')
				        	$("#failure-info").show();
				        	setTimeout(function(){$("#failure-info").hide();},3000);
				        }
				    });
				    
				});
				
				
				/**
				 * 操作车位锁
				 */
				$('#operate').click(function(){
					operate(slaveId,1 ,mobile);
				});

	        });
	        
function operate(equiId , status , mobile){
    $("#wait-info").show();
 	$.post('${request.contextPath!}/userOperate/newoperate','equiId=' + equiId + '&status='+status+'&mobile='+mobile , function(data){
		if(data==0){
		    $("#wait-info").hide();
			$("#failure-info").hide();
			$("#success-info").show();
			setTimeout(function(){$("#success-info").hide();},5000);
			setTimeout(function(){$("#operate-content").hide();},5000);
			setTimeout(function(){ $('#spaceNo').val("");},5000);
			setTimeout(function(){$('#mobile').val("");},5000); 
		}else if(data==1){
			$("#wait-info").hide();
			$("#failure-info").hide();
			$("#success-info").show();
			setTimeout(function(){$("#success-info").hide();},5000);
			setTimeout(function(){$("#operate-content").hide();},5000);
			setTimeout(function(){ $('#spaceNo').val("");},5000);
			setTimeout(function(){$('#mobile').val("");},5000); 
		}else{
		    $("#wait-info").hide();
			$("#failure-info").show();
		    $("#success-info").hide();	
		    setTimeout(function(){$("#failure-info").hide();},5000);
		    setTimeout(function(){$("#operate-content").hide();},5000);
		    setTimeout(function(){ $('#spaceNo').val("");},5000);
		    setTimeout(function(){$('#mobile').val("");},5000); 
		} 		
 	});
 } 
	        
	        
	        
   </script>
</body>
</html>