<!DOCTYPE>
<html class="bg-black">
    <head>
        <meta charset="UTF-8">
        
        <title>智慧停车服务云平台</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
        <!-- bootstrap 3.0.2 -->
        <link href="${request.contextPath!}/statics/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="${request.contextPath!}/statics/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="${request.contextPath!}/statics/css/AdminLTE.css" rel="stylesheet" type="text/css" />
        
		<link rel="Shortcut Icon" href="${request.contextPath!}/statics/img/icon.png">
        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
        
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
            padding-left:330px;
          	font-size:18px;
          	font-family:"微软雅黑";
          	
          }
          
          
          .form-box {
				width: 360px;
				margin-top:10%;
			    margin-left:45%;
           }
           
           #username{
            margin-left:30px;
            margin-top:24px;
            background:url(${request.contextPath!}/statics/img/user.png) no-repeat; 
            background-position:5px 9px;                
            border: 1px solid #999;                
            padding: 2px 2px 2px 30px;
           }
           
           #password{
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
            
            .forget-password{
				padding-right:250px;
				margin-top:10px;
            }
            
            #login-msg{
				padding-right:160px;
				color:red;
				font-size:14px;            	
            }
        </style>
        
    </head>
    <body>
    <div id="globale">
	    <div class="logo">
	        <img src="${request.contextPath!}/statics/img/logo.png">
	    </div>
	    <div id="content">
	       <div id="content-left">
		       <div id="code-pic">
	              <img src="${request.contextPath!}/statics/img/code.png" >
		       </div>
		       <div id="code-text">扫一扫，下载PP管家手机客户端！</div>
		    </div>
		    <div id="content-right">
                 <div id="login-box">
                      <form id="login-form">
                         <div id="login-title">用户登录</div>
                         <div id="login-msg"></div>
                           <input type="text" name="username" id="username" class="mytxt" placeholder="用户名"/><br/>
                           <input type="password" id="password" name="password" class="mytxt" placeholder="密码"/><br/>
                           <div class="forget-password"><a href="http://parking.zhrhq.com/about.html">忘记密码</a></div>
                            <button type="button" id="login-btn" class="login-btn" >登录</button> 
                      </form>
                 </div>		    
		    </div>
	    </div>
    <div>
    </body>
</html>
	<script src="${request.contextPath!}/statics/js/jquery-1.8.3.min.js" type="text/javascript"></script>
        <script src="${request.contextPath!}/statics/js/bootstrap.min.js" type="text/javascript"></script>  
        <script type="text/javascript" src="${request.contextPath!}/statics/js/jquery.form.min.js"></script>
    	<script type="text/javascript" src="${request.contextPath!}/statics/js/md5.js"></script>
    	<script type="text/javascript" src="${request.contextPath!}/statics/js/jquery.validate.min.js"></script>
		<script>
		$(function(){
		 var username;
		 var password;
		    $('#login-btn').click(function(){
		        username = $('#username').val();
		        password = $('#password').val();
		        if(username==''||password==''){
                    $("#login-btn").attr("disabled",true);
		        	$("#login-msg").text("用户和密码不能为空！");
                }else{
					    $('#login-btn').text("登录中……");
		        	  	$.post('${request.contextPath!}/login/dologin',
								"username=" + username +"&password=" + password,
								function(data){
									if (data.status) {
										location.href="${request.contextPath!}/default";
									}else{
                                        $("#login-btn").removeAttr("disabled");
                                        $('#login-btn').text("登录");
           								$("#login-msg").text(data.message);
									}
								});	
		            }
		    
		       });
		      
    		}); 
    	document.onkeydown = function(event){
    	    if(event.which == 13){
                $('#login-btn').click();
			}
		}
    	</script>      