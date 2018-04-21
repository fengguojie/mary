<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>添加机构</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>车位管理</a></li>
		<li><a href="#" class="active">机构管理</a></li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<section class="col-lg-12 connectedSortable">
			<div class="box box box-primary">
				<div class="box-header">
					<#include
					"/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<form class="form-horizontal offset3" id="company-form">
						<div class="form-group">	
							<label for="number" class="col-sm-3 control-label">机构名称</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入机构名称" id="name" name="name"
									type="text"/>
							</div>
									    <label for="name" class="error">*</label>
						</div>
						
						<div class="form-group">	
							<label for="privilege" class="col-sm-3 control-label">联系电话</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入联系电话" id="contactNumber" name="contactNumber"
									type="text"/>
							</div>
						</div>
						<div class="form-group">
							<label for="linkmen" class="col-sm-3 control-label">联系人</label>
							<div class="col-sm-5">
								<input id="linkmen" name="linkmen" class="form-control"
									type="text" placeholder="请输入联系人"/>
							</div>
							<label for="linkmen" class="error">*</label>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label" >联系邮箱</label>
							<div class="col-sm-5">
								<input id="email" name="email" class="form-control"
									type="text" placeholder="请输入联系邮箱"/>
							</div>
									    <label for="email" class="error">*</label>
						</div>
						<div class="form-group">
							<label for="remark" class="col-sm-3 control-label">备注</label>
							<div class="col-sm-5">
								<input id="remark" name="remark" class="form-control"
									type="text" placeholder="请输入备注"/>
							</div>
						</div>
						<div class="box-footer text-center">
							<button id="btn-add" type="submit" class="btn btn-primary"
								href="javascript:;">&nbsp;添加&nbsp;</button>
							<a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
						</div>
					</form>
		</section>

		<!-- right col -->
	</div>



</section>
<!-- /.content -->
<script type="text/javascript"
	src="${request.contextPath!}/statics/js/plugins/chosen/chosen.jquery.min.js" />
<script type="text/javascript"
	src="${request.contextPath!}/statics/js/jquery.validate.min.js" />
<script type="text/javascript"
	src="${request.contextPath!}/statics/js/validate_expand.js" />
<script type="text/javascript"
	src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript"
	src="${request.contextPath!}/statics/js/md5.js"></script>

<script type="text/javascript">
$(".date").datetimepicker({
    language: 'zh-CN',
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 0
});

	$(function() {

		var href = '${request.contextPath!}/baseInfo/company/list';
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});

		//返回列表页面
		$('#btn-back').click(function() {
			$("#main-content").zrPageRefresh(href);
		});

		//表单验证
		$("#company-form").validate(
				{
					event : "keyup" || "blur",
					rules : {
						
						name : {
							required : true,
							remote: "${request.contextPath!}/baseInfo/company/checkName",
							rangelength : [ 2, 20 ]
						},
						linkmen:{
							required : true,
							rangelength : [ 2, 20 ]
						},
						moblie:{
							required : true,
							isMobile : true
						},
						email:{
							required : true,
							email:true 
						}
					},
					messages : {
						
						name : {
							required : "请输入名称！",
							remote: "机构名称已存在！",
							rangelength : "名称必须介于2到20个字符之间！"
						},
						linkmen : {
							required : "请输入联系人！",
							rangelength : "联系人必须介于2到20个字符之间！"
						},
						moblie : {
							required : "请输入手机号！",
							isMobile : "手机号不正确！",
						},
						email : {
							required : "请输入备注！",
							email : "邮箱帐号不正确！"
						}
						
					},

					submitHandler : function() {
						var company = {
							linkmen : $("#linkmen").val(),
							name : $("#name").val(),
							contactNumber :$("#contactNumber").val(),
							email :$("#email").val(),
							remark : $("#remark").val()
						
						};
						param = "company=" + JSON.stringify(company);
						$.post('${request.contextPath!}/baseInfo/company/save',
								param, function(data) {
			
									if (data.result == "SUCCESS") {
										$("#failure-info").hide();
										$("#success-info").show();
										setTimeout(function() {
											$("#main-content").zrPageRefresh(
													href);
										}, 3000);
									} else {
										$("#success-info").hide();
										$("#failure-info").show();
										setTimeout(function() {
											$("#failure-info").hide();
										}, 3000);
									}
								});

					}

				});

	});
</script>
