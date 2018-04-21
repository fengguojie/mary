<!-- Content Header (Page header) -->
<style type="text/css">
label.error{
	color:red;
}
</style>
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<section class="content-header">
	<h1>车锁管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>车锁管理</a></li>
		<li><a href="#" class="active"></a></li>
	</ol>
</section>
<section class="content">
	<div class="row">
         <section class="col-lg-12 connectedSortable">
            <form class="form-horizontal" id="company-form">
	            <div class="box box box-primary">
	                <div class="box-header">
	                    <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
	                </div>
	                <div class="box-body">
						<input id="id" name="id" value="${carLock.id}" hidden/>
						<div class="form-group">
						    <label class="col-sm-3 control-label">网关编号</label>
						    <div class="col-sm-5">
						    	<input type="text" value="${carLock.gatewayNo!}" class="form-control" id="gatewayNo" placeholder="" disabled>
						    </div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-3 control-label">车锁编号</label>
						    <div class="col-sm-5">
						    	<input type="text" value="${carLock.slaveId!}" class="form-control" id="slaveId" placeholder="" disabled>
						    </div>
						</div>
						<div class="form-group">
						    <label for="" class="col-sm-3 control-label">串口编号</label>
						    <div class="col-sm-5">
						    	<select id="serial" class="chzn-select-deselect col-sm-12">
							    	<#if serialA??>
									      <option value="${serialA}" >${serialA!}</option>
								  	</#if>
								  	<#if serialB??>
									      <option value="${serialB}" >${serialB!}</option>
								  	</#if>
								  	
						        </select>
						    </div>
						    <label for="serial" class="error" id="show">*</label>
						</div>
	                </div>
	                <div class="box-footer text-center">
	                       <button id="btn-edit" type="submit" class="btn btn-primary" href="javascript:;">&nbsp;配置&nbsp;</button>
	                       <a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
	                </div>
	            </div>
			</form>
        </section>
	</div>
</section>

<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/chosen/chosen.jquery.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/jquery.validate.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/validate_expand.js"/>

<script type="text/javascript">
	$(function(){
		var page=${colentpage!};
		var href = '${request.contextPath!}/baseInfo/carLock/Pagelist?page='+page;
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});
		//返回列表页面
	    $('#btn-back').click(function(){
			$("#main-content").zrPageRefresh(href);
		});
		//表单验证
		$("#company-form").validate({
	           event:"keyup" || "blur",
	            
	           submitHandler: function() { 
		       		var slaveId = $("#slaveId").val();
		       		var gatewayNo = $("#gatewayNo").val();
			       	var serial = $("#serial").val();
			       	if(serial == null || serial == undefined){
			       		$('#show').html('先配置串口编号');
			       		$('#show').css('display','block');
			       	}else{
			       		var param = "slaveId="+ slaveId+"&serial="+serial +"&gatewayNo="+gatewayNo;
		       			$.post('${request.contextPath!}/baseInfo/carLock/config',param,
							function(data){
								if (data.result=="SUCCESS") {
								       $("#failure-info").hide();
								       $("#success-info").show();
								       setTimeout(function(){$("#main-content").zrPageRefresh(href);},3000);
								}
								else{
									 $("#success-info").hide();
								     $("#failure-info").show();
								     setTimeout(function(){$("#failure-info").hide();},3000);
								}
							});		
			       	}
			       	
	            } 
	        });
	      });
</script>
 
