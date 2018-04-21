
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>管理员</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>系统管理</a></li>
		<li><a href="#" class="active">管理员</a></li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<section class="col-lg-12 connectedSortable">
			<div class="box box box-primary">
				<div class="box-header">
					<h3 class="box-title">密码修改</h3>
					<#include
					"/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<form class="form-horizontal offset3" id="user-form">
						<input type="hidden" id="id" value="${id!_dovey_session_secu_object_.user.id}"/>
						<#if type != 1>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label" >原始密码</label>
							<div class="col-sm-5">
								<input id="oldPassword" name="oldPassword" class="form-control"
									type="password" placeholder="请输入原始密码"/>
							</div>
									    <label for="oldPassword" class="error">*</label>
						</div>
					    </#if>
						<div class="form-group">
							<label for="password" class="col-sm-3 control-label" >密码</label>
							<div class="col-sm-5">
								<input id="password" name="password" class="form-control"
									type="password" placeholder="请输入密码"/>
							</div>
									    <label for="password" class="error">*</label>
						</div>
						<div class="form-group">
							<label for="repassword" class="col-sm-3 control-label">确认密码</label>
							<div class="col-sm-5">
								<input id="repassword" name="repassword" class="form-control"
									type="password" placeholder="请再次输入密码"/>
							</div>
									    <label for="repassword" class="error">*</label>
						</div>

						<div class="box-footer text-center">
							<button id="btn-add" type="submit" class="btn btn-primary"
								href="javascript:;">&nbsp;修改&nbsp;</button>
							<#if type == 1>
							<a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
							</#if>
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
	$(function() {

		var href = '${request.contextPath!}/system/user/list';

		//返回列表页面
		$('#btn-back').click(function() {
			$("#main-content").zrPageRefresh(href);
		});

		//表单验证
		$("#user-form").validate(
				{
					event : "keyup" || "blur",
					rules : {
						oldPassword : {
							required : true,
							remote: {
								url:"${request.contextPath!}/system/user/checkPassword",
								data:{
									oldPassword:function() {
										return $("#oldPassword").val()
									},
									id:function() {
										return $("#id").val()
									}
								},		
							},
							rangelength : [ 6, 20 ]
						},
						password:{
							required : true,
							rangelength : [6 , 20]
						},
						repassword:{
							required : true,
							equalTo:"#password"
						},
					},
					messages : {
						oldPassword : {
							required : "请输入原始密码！",
							remote : "原始密码不正确",
							rangelength : "密码必须介于6到20个字符之间！",
						},
						password : {
							required : "请输入密码！",
							rangelength : "密码必须介于6到20个字符之间！",
						},
						repassword : {
							required : "请再次输入密码！",
							equalTo : "两次输入密码必须相同！",
						},
					},

					submitHandler : function() {
						var user = {
							id : $("#id").val(),
							password :$("#password").val(),
						};
						$.post('${request.contextPath!}/system/user/alterPassword',
								user, function(data) {
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
