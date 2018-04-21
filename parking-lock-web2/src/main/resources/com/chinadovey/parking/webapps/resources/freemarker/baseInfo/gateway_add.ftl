
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<section class="content-header">
	<h1>网关管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>网关管理</a></li>
		<li><a href="#" class="active"></a></li>
	</ol>
</section>
<section class="content">
	<div class="row">
	     <section class="col-lg-12 connectedSortable">
	        <form class="form-horizontal"  id="gateway-form">
	            <div class="box box box-primary">
	                <div class="box-header">
	                	<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
	                </div><!-- /.box-header -->
	                <div class="box-body">
						  <div class="form-group">
						    <label for="" class="col-sm-3 control-label">网关编号</label>
						    <div class="col-sm-5">
						      <input type="text"  class="form-control" id="gatewayNo" name="gatewayNo" placeholder="请填写8位16进制的数" >
						    </div>
						    <label for="gatewayNo" class="error">*</label>
						  </div>
						  <div class="form-group">
						    <label for="" class="col-sm-3 control-label">网关名称</label>
						    <div class="col-sm-5">
						      <input type="text"  class="form-control" id="gatewayName" name='gatewayName' placeholder="请填写网关名称" >
						    </div>
						    <label for="gatewayName" class="error">*</label>
						  </div>
						  <div class="form-group">
							    <label class="col-sm-3 control-label">所属车场</label>
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
						   
						  <!--
							  <div class="form-group">
								    <label for="" class="col-sm-3 control-label">机构</label>
								    <div class="col-sm-5">
									      <select id="companyNo" class="chzn-select-deselect col-sm-12">
											      <option value="0">不分配机构</option>
									      <#if companys??>
										      <#list companys as company>
											      <option value="${company.companyNo}">${company.name!}</option>
										      </#list>
										  </#if>
									      </select>
								    </div>
							   </div>
						   -->
						   
						   <div class="form-group">
						    <label for="" class="col-sm-3 control-label">备注</label>
						    <div class="col-sm-5">
						    	<textarea rows="2" class="form-control" name="remark" id="remark" ></textarea>
						    </div>
						  </div>
	                </div><!-- /.box-body -->
	                <div class="box-footer text-center">
	            		<button id="btn-add" type="submit" class="btn btn-primary" href="javascript:;">&nbsp;添加&nbsp;</button>
	                    <a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
	                </div>
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
<script type="text/javascript" src="${request.contextPath!}/statics/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${request.contextPath!}/statics/js/filestyle.min.js"></script>
<script type="text/javascript" src="${request.contextPath!}/statics/js/jquery.cityselect.js"></script>
<script type="text/javascript" src="${request.contextPath!}/statics/js/bootstrap-timepicker.js"></script>

<script type="text/javascript">
	$(function(){
		var href = '${request.contextPath!}/baseInfo/gateway/list';
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});
		//返回列表页面
	    $('#btn-back').click(function(){
			$("#main-content").zrPageRefresh(href);
		});
		//表单验证
		$("#gateway-form").validate({
        	event:"keyup" || "blur",
            rules: {
                gatewayNo:{
	                required:true,
	                remote: "${request.contextPath!}/baseInfo/gateway/checkDasId",
	                rangelength:[8,8]
	            },
	        },
            messages: {
                gatewayNo: {
                	required: "请输入编号！",
                    remote : "网关编号已存在",
                	rangelength:"编号必须是8位十六进制数字",
                },
           },
           submitHandler: function() { 
	       		var gatewayNo = $("#gatewayNo").val();
	       		var gatewayName = $("#gatewayName").val();
	       		var parkId = $('#parkId').val();
	       		var remark = $("#remark").val();
		       	
		       	var param = 'gatewayNo='+gatewayNo +'&gatewayName='+gatewayName +'&parkId='+parkId +'&remark='+remark;
		       	$.post('${request.contextPath!}/baseInfo/gateway/save',param,
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
 
