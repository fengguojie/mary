<style type="text/css">
	label.error{
		color:red;
	}
</style>
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<section class="content-header">
	<h1>网关管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>基础设置</a></li>
		<li><a href="#" class="active">网关管理</a></li>
	</ol>
</section>
<section class="content">
	<div class="row">
         <section class="col-lg-12 connectedSortable">
             <form class="form-horizontal" id="company-form">
                <div class="box box box-primary">
                    <div class="box-header">
                        <h3 class="box-title">配置串口</h3>
                        <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                    </div>
                    <div class="box-body">
						<input id="id" name="id" value="${gateway.id}" hidden/>
						<input id="gatewayNo" name="gatewayNo" value="${gateway.gatewayNo}" hidden/>
						<div class="form-group">
							<label for="" class="col-sm-3 control-label">串口编号A</label>
						    <div class="col-sm-5">
						    	<input type="text" value="00000001" class="form-control" id="seriala" name="seriala" disabled >
						    </div>
						    <label for="gatewayNo" class="error">*</label>
						</div>
						<div class="form-group">
						    <label for="" class="col-sm-3 control-label">无线模块A</label>
						    <div class="col-sm-5">
						    	<input type="text"  class="form-control" id="wirea" name="wirea" value="${gateway.wirea!}" placeholder="请填写4位16进制的数" >
						    </div>
						    <label for="gatewayNo" class="error">*</label>
						</div>
						<div class="form-group">
						    <label for="" class="col-sm-3 control-label">信道A</label>
						    <div class="col-sm-5">
						    	<input type="text"  class="form-control" id="channela" name='channela' value="${gateway.channela!}" placeholder="请填写2位16进制的数" >
						    </div>
						    <label for="gatewayName" class="error">*</label>
						</div>
						<div class="form-group">
						    <label for="" class="col-sm-3 control-label">串口编号B</label>
						    <div class="col-sm-5">
						    	<input type="text"  value="00000002" class="form-control" id="serialb" name='serialb' disabled >
						    </div>
						    <label for="gatewayName" class="error">*</label>
						</div>
						<div class="form-group">
						    <label for="" class="col-sm-3 control-label">无线模块B</label>
						    <div class="col-sm-5">
						    	<input type="text"  class="form-control" id="wireb" name="wireb" value="${gateway.wireb!}" placeholder="请填写4位16进制的数" >
						    </div>
						    <label for="gatewayNo" class="error">*</label>
						</div>
						<div class="form-group">
						    <label for="" class="col-sm-3 control-label">信道B</label>
						    <div class="col-sm-5">
						    	<input type="text"  class="form-control" id="channelb" name='channelb' value="${gateway.channelb!}" placeholder="请填写2位16进制的数" >
						    </div>
						    <label for="gatewayName" class="error">*</label>
						</div>
                    </div>
                    <div class="box-footer text-center">
                    	<button id="btn-edit" type="submit" class="btn btn-primary" href="javascript:;">&nbsp;配置&nbsp;</button>
                        <a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
                    </div>
                </div>
			</form>
    	</section>
		<div class="modal fade" id="map_container_modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
      				<div class="modal-body" style="padding: 0; max-height: 999px; max-width: 999px;">
						<div id="map_container" style="width: 800px; height: 475px; margin: 0;"></div>
					</div>
    			</div>
  			</div>
		</div>
	</div>
</section>

<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/chosen/chosen.jquery.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/jquery.validate.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/validate_expand.js"/>

<script type="text/javascript">
	$(function(){
		var page=${colentpage!};
		var href = '${request.contextPath!}/baseInfo/gateway/Pagelist?page='+page;
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});
		//返回列表页面
	    $('#btn-back').click(function(){
				$("#main-content").zrPageRefresh(href);
		});
		//表单验证
		$("#company-form").validate({
        	event:"keyup" || "blur",
            rules: {
                seriala: {required:true,rangelength:[8,8]},
                wirea: {required:true,rangelength:[4,4]},
                channela: {required: true,rangelength:[2,2]},
                serialb: {required:true,rangelength:[8,8]},
                wireb: {required:true,rangelength:[4,4]},
                channelb: {required: true,rangelength:[2,2]},
                
			},
           	messages: {
                seriala: {
                    required: "请输入！",
                    rangelength:"必须是8位十六进制数字",
                },
                wirea: {
                	required: "请输入！",
                    rangelength:"必须是4位十六进制数字",
                },
                channela: {
                    required: "请输入！",
                    rangelength:"必须是2位十六进制数字",
                },
                serialb: {
                   required: "请输入！",
                    rangelength:"必须是8位十六进制数字",
                },
                wireb: {
                	required: "请输入！",
                    rangelength:"必须是4位十六进制数字",
                },
                channelb: {
                    required: "请输入！",
                    rangelength:"必须是2位十六进制数字",
                },
               
            },
           	submitHandler: function() { 
	       	   	var gateway={
		       	    id:$('#id').val(),
		       	    gatewayNo:$('#gatewayNo').val(),
		       	    seriala:$("#seriala").val(),
		       		wirea:$("#wirea").val(),
		       		channela:$("#channela").val(),
		       		serialb:$("#serialb").val(),
		       		wireb:$("#wireb").val(),
		       		channelb:$("#channelb").val(),
	       		};
	       		
       			$("#wait-info").show();
       			$.post('${request.contextPath!}/baseInfo/gateway/saveconfig',"gateway="+ JSON.stringify(gateway),
					function(data){
						if (data.result=="SUCCESS") {
					       	$("#failure-info").hide();
					       	alert(data.msg);
					       	setTimeout(function(){$("#main-content").zrPageRefresh(href);},3000);
						}
						else{
							 $("#success-info").hide();
						     $("#failure-info").show();
						     setTimeout(function(){$("#failure-info").hide();},3000);
						}
						$("#wait-info").hide();
					});		
            } 
	    });
	    
	});
	      
</script>
 
