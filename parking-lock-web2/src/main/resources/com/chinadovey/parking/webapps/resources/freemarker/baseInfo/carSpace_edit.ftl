<!-- Content Header (Page header) -->
<style type="text/css">
label.error{
	color:red;
}
</style>
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<section class="content-header">
	<h1>车位管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>车位管理</a></li>
		<li><a href="#" class="active">车位管理</a></li>
	</ol>
</section>
<section class="content">
	<div class="row">
         <section class="col-lg-12 connectedSortable">
         	<form class="form-horizontal" id="park-form">
                <div class="box box box-primary">
                    <div class="box-header">
                        <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                    </div>
                    <div class="box-body">
                    	<div class="form-group">
							<label for="" class="col-sm-3 control-label">车场名称</label>
						    <div class="col-sm-5">
						    	<select id="parkId" class="chzn-select-deselect col-sm-12">
						    		<option value="${park.id}">${park.name!}</option>
						    		<#if parks??>
									     <#list parks as park>
										     <option value="${park.id}">${park.name!}</option>
									     </#list>
									 </#if>
						      	</select>
						    </div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-3 control-label">车锁编号</label>
						    <div class="col-sm-5">
						    	<select id="slaveId" class="chzn-select-deselect col-sm-12">
						    		<#if carSpace.slaveId == "0">
						    			<option value="0">不分配车位锁</option>
						    		<#else>
						      			<option value="${carSpace.slaveId}">${carSpace.slaveId!''}</option>
						      			<option value="0">不分配车位锁</option>
						    		</#if>
						      		<#if carLocks??>
							        	<#list carLocks as carLock>
								        	<option value="${carLock.slaveId}">${carLock.slaveId!''}</option>
							      		</#list>
						      		</#if>
						      	</select>
						    </div>
						</div>
				    	<div class="form-group">
				    		<label class="col-sm-3 control-label">车位编号</label>
				    		<div class="col-sm-5">
				      			<input type="text" value="${carSpace.spaceNo}" class="form-control" id="spaceNo" name="spaceNo" placeholder="请输入车位编号" >
				    		</div>
				    		<label for="spaceNo" class="error">*</label>
				  		</div>
				  		<div class="form-group">
							<label for="" class="col-sm-3 control-label">车位名称</label>
						    <div class="col-sm-5">
						    	<input type="text" value="${carSpace.spaceName}" class="form-control" id="spaceName" name='spaceName' placeholder="请输入车位名称" >
						    </div>
						    <label for="spaceName" class="error">*</label>
						</div>
                    </div>
                    <div class="box-footer text-center">
                    	<button id="btn-edit" type="submit" class="btn btn-primary" href="javascript:;">&nbsp;修改&nbsp;</button>
                        <a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
                    </div>
                </div>
			</form>
    	</section>
	</div>
</section>

<input id="id" value="${carSpace.id}" hidden/>

<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/chosen/chosen.jquery.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/jquery.validate.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/validate_expand.js"/>

<script type="text/javascript">
	$(function(){
		var page=${colentpage!};
		var href = '${request.contextPath!}/baseInfo/carSpace/Pagelist?page='+page;
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});
		//返回列表页面
	    $('#btn-back').click(function(){
			$("#main-content").zrPageRefresh(href);
		});
		
		$('#parkId').change(function(){
			var parkId = $('#parkId').val();
			$.post('${request.contextPath!}/baseInfo/carSpace/getCarLock?parkId='+parkId,
				function(data){
					$("#slaveId").html("");
					$("#slaveId").append("<option value='0'>"+"不分配车位锁"+"</option>");
	  				for(var i=0; i< data.carLocks.length; i++){
	  					var slaveId = data.carLocks[i].slaveId;
	  					$("#slaveId").append("<option value='"+slaveId+"'>"+slaveId+"</option>");
	  					$('#slaveId').chosen().trigger("liszt:updated");
	  				}
				});
		});
				
		//表单验证
		$("#park-form").validate({
	        	event:"keyup" || "blur",
	            rules: {
	                spaceNo:{required:true,remote: {
	                	url:"${request.contextPath!}/baseInfo/carSpace/checkCodeById",
	                	data:{
	                		spaceNo() {
	           					return $("#spaceNo").val()
	           				},
							id:function() {
								return $("#id").val()
							}
						}
					},rangelength:[2,20]},
	             
	            },
	            messages: {
	                spaceNo: {
	                	required: "请输入车位编号！",
	                	remote:"编号已存在",
	                	rangelength:"编号必须介于1到20个数字之间",
	                },
	            },
	            submitHandler: function() { 
		       	    var carSpace={
			       	    id:$('#id').val(),
			       		spaceNo:$("#spaceNo").val(),
			       		slaveId:$('#slaveId').val(),
			       		parkId:$("#parkId").val(),
			       		spaceName:$("#spaceName").val(),
		       		};
	       			$.post('${request.contextPath!}/baseInfo/carSpace/update',"carSpace="+ JSON.stringify(carSpace),
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
 
