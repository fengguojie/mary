<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<section class="content-header">
	<h1>添加车场</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>车位管理</a></li>
		<li><a href="#" class="active">车场管理</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
             
                            <form class="form-horizontal"  id="park-form">
                 
    
            
                            <div class="box box box-primary">
                                <div class="box-header">
                                <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                                </div><!-- /.box-header -->
                               <div class="row">
							        
							        <div class="col-md-6">
											  <div class="box-body">
				                               		<input type="hidden" value="b.png" name="picture" id="picture"/>
				                               	    <div class="form-group">
													    <label for="companyId" class="col-sm-3 control-label">所属机构</label>
													    <div class="col-sm-8">
													   	   	<select type="text" id="companyId"  name="companyId"  class="form-control" >
																	<#list companys as c>
																		<option value="${c.id}" >${c.name}</option>
																	</#list>
															</select>
													      </div>
													    <label for="companyId" class="error">*</label>
													  </div>
													  
													  <div class="form-group">
													    <label for="" class="col-sm-3 control-label">名称</label>
													    <div class="col-sm-8">
													      <input type="text"  class="form-control" id="name" name='name' placeholder="请输入停车场名称"  >
													    </div>
													    <label for="name" class="error">*</label>
													  </div>
													  <!-- <div class="form-group">
													    <label for="" class="col-sm-3 control-label">车位数量</label>
													    <div class="col-sm-8">
													     <input type="text"  class="form-control"  id="total" name='total' placeholder="停车场车位数量"  >
													    </div>
													    <label for="total" class="error">*</label>
													  </div> -->
													
													   <div class="form-group">
													    <label for="" class="col-sm-3 control-label">联系人</label>
													    <div class="col-sm-8">
													      <input type="text"  class="form-control" id="linkman" name='linkman' placeholder="请输入联系人姓名"  >
													    </div>
													    <label for="linkman" class="error">*</label>
													  </div>
													  
													   <div class="form-group">
													    <label for="" class="col-sm-3 control-label">联系电话</label>
													    <div class="col-sm-8">
													      <input type="text"  class="form-control" id="tel" name='tel' placeholder="请输入联系人电话" >
													    </div>
													    <label for="tel" class="error">*</label>
													  </div>
													  
				                                </div>
				                                <div class="box-footer text-center">
				                                		<button id="btn-add" type="submit" class="btn btn-primary" href="javascript:;">&nbsp;添加&nbsp;</button>
				                                       <a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
				                                </div>
								</div>
							       
						    </div>
   
                       </div>        
						</form>
						
        </section>
		<!-- right col -->
		
	

</section>
<!-- /.content -->


<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/chosen/chosen.jquery.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/jquery.validate.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/validate_expand.js"/>
 <script type="text/javascript" src="${request.contextPath!}/statics/js/ajaxfileupload.js"></script>
 <script type="text/javascript" src="${request.contextPath!}/statics/js/filestyle.min.js"></script>
<script type="text/javascript" src="${request.contextPath!}/statics/js/jquery.cityselect.js"></script>
<script type="text/javascript" src="${request.contextPath!}/statics/js/bootstrap-timepicker.js"></script>
<script type="text/javascript">

$(function(){
		
	   // 设置同步
        $.ajaxSetup({
            async: true
        });
        
		var href = '${request.contextPath!}/baseInfo/park/list';
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});
		//返回列表页面
	    $('#btn-back').click(function(){
				$("#main-content").zrPageRefresh(href);
		});
		
		//表单验证
		$("#park-form").validate({
	        	event:"keyup" || "blur",
	            rules: {
	                name: {required:true,rangelength:[2,20]},
	                total: {required:true,digits:true},
	                linkman: {required: true,rangelength:[2,20]},
	                tel: {required: true,isTel:true},
	                email: {required: true,email: true},
	                address: {required: true,}
	             
	            },
	            messages: {
	                name: {
	                    required: "请输入停车场名称！",
	                    rangelength:"名称必须介于2到20个字符之间",
	                },
	                total: {
	                	required:"请输入停车场车位数量！",
	                    digits:"必须为数字",
	                },
	                linkman: {
	                    required: "请输入联系人名称！",
	                    rangelength:"名称必须介于2到20个字符之间",
	                },
	                tel: {
	                    required: "请输电话号码！",
	                },
	                email: { 
	                	required: "请输入邮箱！",
	                	email:"请输入正确的邮件格式！"
	                },
	                address:{
	                	required: "请输入联系地址！"
	                },
	            },
	            
	           submitHandler: function() { 
	       	var park={
	       		companyId:$("select[name=companyId] option:selected").val(),
	       		name:$("#name").val(),
	       		linkman:$("#linkman").val(),
	       		tel:$("#tel").val(),
	       		//total:$("#total").val(),
	       		//type:$("#type").val(),
	       		//email:$("#email").val(),
	       		//address:getLocation() + $("#address").val(),
	       		//latitude:$("#latitude").val(),
	       		//longitude:$("#longitude").val(),
	       		//picture:$("#picture").val(),
	       		//type:$("select[name=type] option:selected").val(),
	       		//parkType:$("select[name=parkType] option:selected").val(),
	       		//isFree:$("select[name=isFree] option:selected").val(),
	       		//closingTime:$("#closingTime").val(),
	       		//openingTime:$("#openingTime").val(),
	       		//chargingRulesId:$("#chargingRuleId").val(),
	       		
	       	};
	       	$.post('${request.contextPath!}/baseInfo/park/save',
						"park="+ JSON.stringify(park),
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
 
