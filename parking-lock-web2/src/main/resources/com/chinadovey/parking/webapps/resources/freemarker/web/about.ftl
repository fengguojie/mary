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
      <li id="cooperation-tab"><a href="${request.contextPath!}/web/cooperation">PP协作</a></li>
      <li id="about-tab" class="nav-current"><a href="${request.contextPath!}/web/about">关于我们</a></li>
    </ul>
  </div>
</div>
<div id="contact">
	<div class="contact-detail">
            <div class="contact-detail-desc">
              <div class="contact-label">合作热线：</div>
              <div class="contact-content">010-82556823</div>
            </div>
            <div class="contact-detail-desc">
              <div class="contact-label">客服热线：</div>
              <div class="contact-content">010-82556823</div>
            </div>
            <div class="contact-detail-desc">
              <div class="contact-label">官方邮箱：</div>
              <div class="contact-content">service@zhrhq.com</div>
            </div>
            <div class="contact-detail-desc">
              <div class="contact-label">邮政编码：</div>
              <div class="contact-content">100094</div>
            </div>
            <div class="contact-detail-desc">
               <div class="contact-label">公司地址：</div>
               <div class="contact-content" id="company_address_detail">北京市海淀区上地信息路2号国际创业园2号楼10A</div>
            </div>
        </div>
</div>
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
<div class="dialog-mask"></div>
<div id="cboxOverlay" style="display: none;"></div>
<div id="colorbox" class="" role="dialog" tabindex="-1" style="display: none;">
  <div id="cboxWrapper">
    <div>
      <div id="cboxTopLeft" style="float: left;"></div>
      <div id="cboxTopCenter" style="float: left;"></div>
      <div id="cboxTopRight" style="float: left;"></div>
    </div>
    <div style="clear: left;">
      <div id="cboxMiddleLeft" style="float: left;"></div>
      <div id="cboxContent" style="float: left;">
        <div id="cboxTitle" style="float: left;"></div>
        <div id="cboxCurrent" style="float: left;"></div>
        <button type="button" id="cboxPrevious"></button>
        <button type="button" id="cboxNext"></button>
        <button id="cboxSlideshow"></button>
        <div id="cboxLoadingOverlay" style="float: left;"></div>
        <div id="cboxLoadingGraphic" style="float: left;"></div>
      </div>
      <div id="cboxMiddleRight" style="float: left;"></div>
    </div>
    <div style="clear: left;">
      <div id="cboxBottomLeft" style="float: left;"></div>
      <div id="cboxBottomCenter" style="float: left;"></div>
      <div id="cboxBottomRight" style="float: left;"></div>
    </div>
  </div>
  <div style="position: absolute; width: 9999px; visibility: hidden; display: none; max-width: none;"></div>
</div>
</body>
</html>