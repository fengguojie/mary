<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<section class="content-header">
	<h1>停车场收费规则</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>基础设置</a></li>
		<li><a href="#" class="active">停车场管理</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
                            <form class="form-horizontal" id="chargeRule-form">
                            <div class="box box box-primary">
                                <div class="box-header">
		                            <input id="parkId" name="parkId" value="${parkId!}" type="hidden"/>
		                            <input id="id" name="id" value="${chargeRule.id!}" type="hidden"/>
                                    <h3 class="box-title">停车场收费规则修改</h3>
                                    <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                                </div><!-- /.box-header -->
                                <div class="box-body">
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">收费类型</label>
									    <div class="col-sm-5">
									      <select id="type" class="col-sm-12 chzn-select-deselect">
												<option value='1' <#if chargeRule.type??><#if chargeRule.type == 1>selected</#if><#else>selected</#if>>按停车时长收费</option>
												<option value='2' <#if chargeRule.type??><#if chargeRule.type == 2>selected</#if></#if>>按停车次数收费</option>									      
									      </select>
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">价格</label>
									    <div class="col-sm-5">
											<input class="span1"  id="price" name="price" type="text" value="${chargeRule.price!'2'}" placeholder="元"/>
									    	<span style="font-size: 14px;">元/</span>
											<input class="span1" id="span" name="span" type="text" value="${chargeRule.span!'30'}" placeholder="分钟"/>
											<span style="font-size: 14px;" id="unit">分钟</span>
									    </div>
									    <label for="span" class="error">*</label>
									  </div>
									    									  
									   <div class="form-group">
									    <label for="" class="col-sm-3 control-label">停留时长</label>
									    <div class="col-sm-4">
									      <input type="text" value="${chargeRule.retention!'15'}" class="form-control" id="retention" name="retention" placeholder="请输入缴费后允许停留时长">
									    </div>
									    <label for="" class="col-sm-1 control-label">分钟</label>
									    <label for="retention" class="error">*</label>
									   </div>

									   <div class="form-group">
									    <label for="" class="col-sm-3 control-label">详细信息</label>
									    <div class="col-sm-5">
											<textarea id="content" name="content" class="col-sm-12" rows="4" placeholder="请输入详细收费信息">${chargeRule.content!}</textarea>
									    </div>
									    <label for="address" class="error">*</label>
									  </div>
									  
                                </div><!-- /.box-body -->
                                <div class="box-footer text-center">
                                       <button id="btn-edit" type="submit" class="btn btn-primary" href="javascript:;">&nbsp;修改&nbsp;</button>
                                       <a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
                                </div>
                            </div><!-- /.box -->
						</form>
        </section>
		
		<!-- right col -->
	</div>
</section>

<!-- /.content -->
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/chosen/chosen.jquery.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/jquery.validate.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/validate_expand.js"/>
<script type="text/javascript">

	$(function(){
	
		var href = '${request.contextPath!}/baseInfo/park/list';
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});
		//返回列表页面
	    $('#btn-back').click(function(){
				$("#main-content").zrPageRefresh(href);
		});
		
		var type = $("#type").val();
			if(type == 1){
				$("#unit").text("分钟");
				$("#span").val("30");
				$("#span").show();
			}else{
				$("#span").val("1");
				$("#span").hide();
				$("#unit").text("次");
			}
		
		$('#type').change(function(){
			var type = $(this).val();
			if(type == 1){
				$("#unit").text("分钟");
				$("#span").val("30");
				$("#span").show();
			}else{
				$("#span").val("1");
				$("#span").hide();
				$("#unit").text("次");
			}
		});
		
		//表单验证
		$("#chargeRule-form").validate({
	        	event:"keyup" || "blur",
	            rules: {
	                price: {required:true,number:true},
	                span:{required:true,digits:true},
	                retention:{required:true,digits:true},
	                content: {required: true,}
	            },
	            messages: {
	                price: {
	                	required: "请输入价格！",
	                	number:"必须为数字",
	                },
	                span: {
	                    required: "请输入计费单位！",
	                    digits:"计费单位必须为数字",
	                },
	                retention: {
	                    required: "请输入停留时长！",
	                    digits:"停留时长必须为整数",
	                },
	                content: {
	                	required:"请输入详细收费信息！",
	                },
	            },
	            
	           submitHandler: function() { 
			       	var chargeRule={
			       	    id:$('#id').val(),
			       		type:$("#type").val(),
			       		price:$("#price").val(),
			       		span:$("#span").val(),
			       		retention:$("#retention").val(),
			       		type:$("#type").val(),
			       		parkId:$("#parkId").val(),
			       		content:$("#content").val()
			       	};
			       	$.post('${request.contextPath!}/baseInfo/park/updateChargeRule',
								"chargeRule="+ JSON.stringify(chargeRule),
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
 
