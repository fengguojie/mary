
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
                <form class="form-horizontal"  id="company-form">
                <div class="box box box-primary">
                    <div class="box-header">
                    	<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                    </div>
                    <div class="box-body">
	                  	<div class="form-group">
						    <label class="col-sm-3 control-label">车场名称</label>
						    <div class="col-sm-5">
							    <select id="parkId" class="chzn-select-deselect col-sm-12">
								    <#if parks??>
									    <#list parks as park>
											<option value="${park.id}">${park.name!}</option>
									    </#list>
									</#if>
							    </select>
						    </div>
					   	</div>
						<div class="form-group">
						    <label class="col-sm-3 control-label">网关编号</label>
						    <div class="col-sm-5">
							    <select id="gatewayNo" class="chzn-select-deselect col-sm-12">
							    	<#if gateways??>
								        <#list gateways as gateway>
									    	<option value="${gateway.gatewayNo}">${gateway.gatewayNo!}</option>
								        </#list>
							        </#if>
							    </select>
						    </div>
						 </div>
						 <div class="form-group">
						     <label class="col-sm-3 control-label">车锁编号</label>
						     <div class="col-sm-5">
						     	<input type="text" class="form-control" id="slaveId" name='slaveId' placeholder="请输入12位16进制的数字" >
						     </div>
						     <label for="slaveId" class="error">*</label>
						 </div>
						 
						<div class="form-group">
						    <label class="col-sm-3 control-label">车位编号</label>
						    <div class="col-sm-5">
							    <select id="spaceNo" class="chzn-select-deselect col-sm-12">
							    	<option value="0">不分配车位</option>
							    	<#if spaceNoList??>
								        <#list spaceNoList as spaceNo>
									    	<option value="${spaceNo}">${spaceNo!}</option>
								        </#list>
							        </#if>
							    </select>
						    </div>
						</div>
						
						<div class="form-group">
						     <label class="col-sm-3 control-label">延时</label>
						     <div class="col-sm-5">
						     	<input type="text" class="form-control" id="delay" name='delay' placeholder="请输入车位控制时的延时时长" >
						     </div>
						     <label for="delay" class="error">*</label>
						 </div>
                      </div>
                      <div class="box-footer text-center">
                      	   <button id="btn-add" type="submit" class="btn btn-primary" href="javascript:;">&nbsp;添加&nbsp;</button>
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
		var href = '${request.contextPath!}/baseInfo/carLock/list';
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});
		//返回列表页面
	    $('#btn-back').click(function(){
			$("#main-content").zrPageRefresh(href);
		});
		
		$('#parkId').change(function(){
			var parkId = $('#parkId').val();
			$.post('${request.contextPath!}/baseInfo/carLock/getCarLock?parkId='+parkId,
				function(data){
					$("#gatewayNo").html("");
	  				for(var i=0; i< data.gateways.length; i++){
	  					var gatewayNo = data.gateways[i].gatewayNo;
	  					$("#gatewayNo").append("<option value='"+gatewayNo+"'>"+gatewayNo+"</option>");
	  					$('#gatewayNo').chosen().trigger("liszt:updated");
	  				}
				});
		});
		
		
		$('#companyId').change(function(){
		    $("#dasId").html("");
		    $("#dasId").html("");
		    $.post('${request.contextPath!}/baseInfo/carLock/getGatewayByCompanyId',"companyNo="+ $("#companyNo").val(),
				function(data){
		            $(data).each(function () {
		    	    	$("#dasId").append($("<option/>").text(this.gatewayName).attr("value",this.dasId));
		        	});
	        		$('#dasId').chosen().trigger("liszt:updated");
		    	});	
		});	
		//表单验证
		$("#company-form").validate({
	    	event:"keyup" || "blur",
	        rules: {
	            slaveId:{
	                 required:true,
	                 remote: "${request.contextPath!}/baseInfo/carLock/checkSlaveId",
	                 rangelength:[12,12]
	            },
	        },
            messages: {
                slaveId: {
                	required: "请输入编号！",
                    remote : "编号已存在",
                	rangelength:"编号必须是12位十六进制数字",
                },
            },
	        submitHandler: function() { 
	        	var parkId = $('#parkId').val();
	        	var gatewayNo = $('#gatewayNo').val();
	        	var slaveId = $("#slaveId").val();
	        	var spaceNo = $('#spaceNo').val();
	        	var delay = $('#delay').val();
	        	
	        	var param = 'parkId='+parkId +"&gatewayNo="+gatewayNo +"&slaveId="+slaveId +"&spaceNo="+spaceNo +"&delay="+delay;
		       	$.post('${request.contextPath!}/baseInfo/carLock/save',param,
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
	      });
	      
	 });

</script>
 
