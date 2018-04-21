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
      <li id="index-tab" class="nav-current"><a href="${request.contextPath!}/web/index">首页</a></li>
      <li id="cooperation-tab"><a href="${request.contextPath!}/web/cooperation">PP协作</a></li>
      <li id="about-tab"><a href="${request.contextPath!}/web/about">关于我们</a></li>
    </ul>
  </div>
</div>
<div id="doc2">
  <div id="hd">
    <div class="bd-wrap">
      <div class="bd">
        <div class="slogan"></div>
        <div id="download-btn-container"> 
           <div class="download-left">
	                      	   <div id="download_android_div" class="download_android">
	                      	   		<a id="download_android_link" href="${request.contextPath!}/android/version/download" class="dowload-button download_android_bg" hidefocus="true" target="_blank">
	                      	   		</a>
	                       	   </div>
	                       	   <div id="download_iphone_div" class="download_iphone">
	                       	   		<a href="${request.contextPath!}/android/version/download" class="dowload-button download_iphone_bg" target="_blank" hidefocus="true">
	                      	   		</a>
	                       	   </div>
	                      </div>
	                      <div class="dowload-right" align="center">
	                           <img class="dowload-wechat" src="${request.contextPath!}/statics/img/web/wechat_download.png"><br>
	                           <font>扫一扫下载</font>
	                      </div>
        </div>
      </div>
    </div>
  </div>
  <div id="bd" class="clearfix">
    <ul class="intros">
      <li> <a class="" href="#search">
        <div> <img src="${request.contextPath!}/statics/img/web/find_park.png" alt="一分钟快速认识有道云协作"> 
         <span class="intro-desc">
                          搜索周边停车场
          </span> </div>
        </a></li>
        <li> <a class="" href="#subscribe">
        <div> <img src="${request.contextPath!}/statics/img/web/subscribe.png" alt="一分钟快速认识有道云协作"> 
         <span class="intro-desc">
                          预约车位
          </span> </div>
        </a></li>
        <li> <a class="" href="#mobile_pay">
        <div> <img src="${request.contextPath!}/statics/img/web/pay_title.png" alt="一分钟快速认识有道云协作"> 
         <span class="intro-desc">
                   手机支付
          </span> </div>
        </a></li>
        <li> <a class="" href="#navegation">
        <div> <img src="${request.contextPath!}/statics/img/web/navegation.png" alt="一分钟快速认识有道云协作"> 
         <span class="intro-desc">
                   目的地导航
          </span> </div>
        </a>
        </li>
         <li> <a class="" href="#control">
        <div> <img src="${request.contextPath!}/statics/img/web/control.png" alt="一分钟快速认识有道云协作"> 
         <span class="intro-desc">
                   车位锁控制
          </span> </div>
        </a>
        </li>
    </ul>
  </div>
  <div class="screen-line"></div>
     <div class="screen" id="search">
			<div class="left-block s4-right">
				<div class="s4-image">
					<img id="screen4Image" data-animate="0" src="${request.contextPath!}/statics/img/web/search_park_bg.png" style="right: -100px; opacity: 0; display: none;">
				</div>
			</div>
			<div class="right-block s4-left">
				<div class="s4-title">
					<img src="${request.contextPath!}/statics/img/web/search_park_title.png" id="s4TitleImage" style="top: -50px; opacity: 0;">
				</div>
				<div class="s4-list" id="s4List" style="position: absolute; top: 400px; opacity: 0;">
					<div class="s4-item">
						帮助车主选择合适的停车场
					</div>
					<div class="s4-item">
						根据当前位置自动搜索周边停车场
					</div>
					<div class="s4-item">
						显示空位数量、价格、停车场距离
					</div>
				</div>
			</div>
		</div>
		<div class="screen-line"></div>
		
		<div class="screen" id="subscribe">
			<div class="left-block s5-left" id="find_car_block">
				<div class="s5-image">
					<img id="screen5Image1" data-animate="0" src="${request.contextPath!}/statics/img/web/subscribe_bg.png" style="left: -80px; opacity: 0; display: none;">
					<!--<img id="screen5Image2" data-animate="0" src="${request.contextPath!}/statics/img/web/subscribe_bg.png" style="left: 0px; opacity: 0; display: none;">
					<img id="screen5Image3" data-animate="0" src="${request.contextPath!}/statics/img/web/subscribe_bg.png" style="left: 140px; opacity: 0; display: none;">-->
				</div>
			</div>
			<div class="right-block s5-right">
				<div class="s5-title">
					<img src="${request.contextPath!}/statics/img/web/subscribe_title.png" id="s5TitleImage" style="top: -20px; right: 40px; opacity: 0;">
				</div>
				<div class="s5-desc" id="s5Description" style="position: absolute; top: 360px; left: 40px; opacity: 0;">
					<div class="s5-item">
						选择停车时间段，就可以完成预约
					</div>
					<div class="s5-item">
						不用选车位，系统帮您选择最优的车位
					</div>
					<div class="s5-item">
						方便快捷，在最短时间内预约
					</div>
				</div>
			</div>
		</div>
		<div class="screen-line"></div>
		<div class="screen" id="mobile_pay">
			<div class="left-block s6-right">
				<div class="s6-image">
					<img id="screen6Image" data-animate="0" src="${request.contextPath!}/statics/img/web/mobile_pay_bg.png" style="right: -100px; opacity: 0; display: none;">
				</div>
			</div>
			<div class="right-block s6-left">
				<div class="s6-title">
					<img src="${request.contextPath!}/statics/img/web/mobile_pay_title.png" id="s6TitleImage" style="top: -50px; opacity: 0;">
				</div>
				<div class="s6-list" id="s6List" style="position: absolute; top: 400px; opacity: 0;">
					<div class="s6-item">
						不用登记，不用人缴费，用手机就可以完成支付
					</div>
					<div class="s6-item">
						省时省心
					</div>
				</div>
			</div>
		</div>
		<div class="screen-line"></div>
		<div class="screen" id="navegation">
			<div class="left-block s7-left" id="find_car_block">
				<div class="s7-image">
					<img id="screen7Image1" data-animate="0" src="${request.contextPath!}/statics/img/web/navegation_bg.png" style="left: -80px; opacity: 0; display: none;">
					<!--<img id="screen7image2" data-animate="0" src="${request.contextPath!}/statics/img/web/subscribe_bg.png" style="left: 0px; opacity: 0; display: none;">
					<img id="screen7Image3" data-animate="0" src="${request.contextPath!}/statics/img/web/subscribe_bg.png" style="left: 140px; opacity: 0; display: none;">-->
				</div>
			</div>
			<div class="right-block s7-right">
				<div class="s7-title">
					<img src="${request.contextPath!}/statics/img/web/navegation_title.png" id="s7TitleImage" style="top: -20px; right: 40px; opacity: 0;">
				</div>
				<div class="s7-desc" id="s7Description" style="position: absolute; top: 360px; left: 40px; opacity: 0;">
					<div class="s7-item">
						预约了，不用找不到停车场发愁
					</div>
					<div class="s7-item">
						系统为您导航
					</div>
				</div>
			</div>
		</div>
		<div class="screen-line"></div>
		<div class="screen" id="control">
			<div class="left-block s8-right">
				<div class="s8-image">
					<img id="screen8Image" data-animate="0" src="${request.contextPath!}/statics/img/web/control_bg.png" style="right: -100px; opacity: 0; display: none;">
				</div>
			</div>
			<div class="right-block s8-left">
				<div class="s8-title">
					<img src="${request.contextPath!}/statics/img/web/control_title.png" id="s8TitleImage" style="top: -50px; opacity: 0;">
				</div>
				<div class="s8-list" id="s8List" style="position: absolute; top: 400px; opacity: 0;">
					<div class="s8-item">
						预约了，车位被人占了怎么办？
					</div>
					<div class="s8-item">
						智能车位锁，只有你的手机可以控制哦！
					</div>
					<div class="s8-item">
						我的车位，我做主！
					</div>
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