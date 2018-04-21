<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>pp泊车，pp一下，轻松找车位</title>
<meta name="keywords" content="pp泊车、手机支付、搜索附近停车场、移动互联、物联网、云平台">
<meta name="description" content="pp泊车通过手机定位，自动搜索周边停车场、在线预约、手机支付、导航到达停车场、通过手机控制车位锁，方便、快捷、让您轻松找车位。">
<link type="text/css" rel="stylesheet" href="${request.contextPath!}/statics/css/web/index.css">
<!--[if lte IE 8]>
    <link type="text/css" rel="stylesheet" href="${request.contextPath!}/statics/css/web/glb.ie8lt.css">
    <![endif]-->
</head>
<body>
<!--[if IE 6]>
<script src="${request.contextPath!}/statics/js/web/belatedPNG-min.js"></script>
<script>
    document.write('<script type="text/javascript" id="domready" defer="defer" src="javascript:void(0)"><\/script>');
    document.getElementById("domready").onreadystatechange = function() {
        if (this.readyState == "complete") {
            var imgs = document.getElementsByTagName('img')
            for(i=0; i < imgs.length; i++) {
                if(imgs[i].src.toLowerCase().search(/\.png$/) != -1) {
                    DD_belatedPNG.fixPng(imgs[i]);
                }
            }

            var children = document.body.getElementsByTagName('*');
            for(i=0; i < children.length; i++) {
                var bg = children[i].currentStyle.backgroundImage;
                if(bg != 'none' && bg.toLowerCase().search(/\.png("|\')?\)$/) != -1) {
                    DD_belatedPNG.fixPng(children[i]);
                }
            }
        }
    }
</script>
<![endif]-->
<div id="header">
  <div id="header-warp">
    <a id="logo" style="background:url('${request.contextPath!}/statics/img/web/logo.png')"
       href="${request.contextPath!}/web/index" title="PP泊车"></a>
    <ul id="header-nav">
      <li id="index-tab"><a href="${request.contextPath!}/web/index">首页</a></li>
      <li id="cooperation-tab" class="nav-current"><a href="${request.contextPath!}/web/cooperation">PP协作</a></li>
      <li id="about-tab"><a href="${request.contextPath!}/web/about">关于我们</a></li>
    </ul>
  </div>
</div>
<div id="doc2">
  <div id="cooperation">
    <div class="bd-wrap">
      <div class="bd">
      </div>
    </div>
  </div>
  <div class="screen-line"></div>
     <div class="cooperation-content" style="">
				<div class="cooperation-title">
					<img src="${request.contextPath!}/statics/img/web/cooperation_advisory.png" id="cooperationTitleImage" style="top: -50px;">
				</div>
				<div class="cooperation-list">
					<div class="cooperation-item">
						了解我们的产品，运行模式
					</div>
					<div class="cooperation-item">
						如何加入我们，如何进行运营
					</div>
					<div class="cooperation-item">
						请咨询热线：010-82556823
					</div>
				</div>
		</div>
		<div class="screen-line"></div>
		    <div class="cooperation-content" style="">
				<div class="cooperation-title">
					<img src="${request.contextPath!}/statics/img/web/cooperation_talk.png" id="cooperationTitleImage" style="top: -50px;">
				</div>
				<div class="cooperation-list">
					<div class="cooperation-item">
						了解具体细节、相关协议
					</div>
					<div class="cooperation-item">
						与相关负责人接洽
					</div>
					<div class="cooperation-item">
						了解费用、运营条件
					</div>
				</div>
		</div>
		<div class="screen-line"></div>
		   <div class="cooperation-content" style="">
				<div class="cooperation-title">
					<img src="${request.contextPath!}/statics/img/web/cooperation_contract.png" id="cooperationTitleImage" style="top: -50px;">
				</div>
				<div class="cooperation-list">
					<div class="cooperation-item">
						根据预算、协议、双方拟定合同
					</div>
					<div class="cooperation-item">
						确认无误后、签订正式合同
					</div>
				</div>
		</div>
		<div class="screen-line"></div>
		   <div class="cooperation-content" style="">
				<div class="cooperation-title">
					<img src="${request.contextPath!}/statics/img/web/cooperation_device.png" id="cooperationTitleImage" style="top: -50px;">
				</div>
				<div class="cooperation-list">
					<div class="cooperation-item">
						安排技术人员
					</div>
					<div class="cooperation-item">
						为停车场安装智能设备
					</div>
					<div class="cooperation-item">
						安装相关软件
					</div>
				</div>
		</div>
		<div class="screen-line"></div>
		   <div class="cooperation-content" style="">
				<div class="cooperation-title">
					<img src="${request.contextPath!}/statics/img/web/cooperation_run.png" id="cooperationTitleImage" style="top: -50px;">
				</div>
				<div class="cooperation-list">
					<div class="cooperation-item">
						测试成功无误后
					</div>
					<div class="cooperation-item">
						系统即可正式运营
					</div>
				</div>
		</div>
		<div class="screen-line"></div>
		   <div class="cooperation-content" style="">
				<div class="cooperation-title">
					<img src="${request.contextPath!}/statics/img/web/cooperation_maintain.png" id="cooperationTitleImage" style="top: -50px;">
				</div>
				<div class="cooperation-list">
					<div class="cooperation-item">
						我们负责长期的维护
					</div>
					<div class="cooperation-item">
						保证设备和系统的正常运行
					</div>
				</div>
		</div>
		<div class="screen-line"></div>
		
  <div id="footer">
    <ul id="ft-follow-us">
      <li><a href="" id="weibo" target="_blank">
          <img src="${request.contextPath!}/statics/img/web/sina_default.png"></a></li>
           <li><a href="" id="weibo" target="_blank">
          <img src="${request.contextPath!}/statics/img/web/wechat_default.png"></a></li>
           <li><a href="" id="weibo" target="_blank">
          <img src="${request.contextPath!}/statics/img/web/qq_default.png"></a></li>
    </ul>
  <!--  <ul id="ft-links">
      <li><a href="http://note.youdao.com/iyoudao/" target="_blank">博客</a></li>
      ·
      <li><a href="" target="_blank">官方贴吧</a></li>
      ·
      <li><a href="" target="_blank">空间兑换</a></li>
      ·
      <li><a href="" target="_blank">会员兑换</a></li>
      ·
      <li><a href="" target="_blank">开放平台</a></li>
    </ul>
        -->
    <br><br>
    <ul id="ft-about-links">
      <li><a href="${request.contextPath!}/web/about" target="_blank">关于我们</a></li>
      |
      <li><a href="${request.contextPath!}/web/about" target="_blank">联系我们</a></li>
      |
      <li><a href="javascript:;" target="_blank">服务条款</a></li>
      |
      <li><a href="javascript:;" target="_blank">隐私协议</a></li>
      |
      <li><a href="javascript:;" target="_blank">帮助</a></li>
    </ul>
    <div id="copyright">©2015中瑞华清（北京）智能科技有限公司 京ICP备15041943号</div>
  </div>
</div>
<script type="text/javascript" src="${request.contextPath!}/statics/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request.contextPath!}/statics/js/web/animate.js"></script>
</body>
</html>