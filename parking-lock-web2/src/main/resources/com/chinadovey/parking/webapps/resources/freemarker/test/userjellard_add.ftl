<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>用户注册</h1>
	
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
					<form class="form-horizontal offset3" id="userJellard-form">
						
						
						<div class="form-group">	
							<label for="privilege" class="col-sm-3 control-label">姓名</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入用户姓名" id="username" name="username"
									type="text"/>
							</div>
						</div>
						<div class="form-group">
							<label for="linkmen" class="col-sm-3 control-label">密码</label>
							<div class="col-sm-5">
								<input id="password" name="password" class="form-control"
									type="text" placeholder="请输入密码"/>
							</div>
							<label for="linkmen" class="error">*</label>
						</div>
						
						<div class="form-group">	
							<label for="privilege" class="col-sm-3 control-label">时间</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入注册时间" id="add_time" name="add_time"
									type="text"/>
							</div>
						</div>
						<div class="form-group">	
							<label for="privilege" class="col-sm-3 control-label">手机号码</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入手机号码" id="phone" name="phone"
									type="text"/>
							</div>
						</div>
						<div class="form-group">	
							<label for="privilege" class="col-sm-3 control-label">用户编号</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入用户编号" id="" name="id"
									type="text"/>
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

		var href = '${request.contextPath!}/test/userJellard/list';
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});

		//返回列表页面
		$('#btn-back').click(function() {
			$("#main-content").zrPageRefresh(href);
		});

		//表单验证
		$("#userJellard-form").validate(
				{
					event : "keyup" || "blur",
					rules : {
						
						username : {
							required : true,
							rangelength : [ 2, 20 ]
						},
						password:{
							required : true,
							rangelength : [ 2, 500 ]
						},
						add_time:{
							required : true,
							rangelength : [ 2, 20 ]
						},
						phone:{
							required : true,
							rangelength : [ 2, 20 ]
						},
						id:{
							required : true,
							rangelength : [ 2, 20 ]
						},
						
					},
					messages : {
						
						username : {
							required : "请输入姓名！",
							rangelength : "名称必须介于2到20个字符之间！"
						},
						password : {
							required : "请输入密码！",
							rangelength : "名称必须介于2到500个字符之间！"
						},
						
						add_time : {
							required : "请输入注册时间！",
							rangelength : "名称必须介于2到20个字符之间！"
						},
						phone : {
							required : "请输入电话号码！",
							rangelength : "名称必须介于2到20个字符之间！"
						},
						id : {
							required : "请输入id！",
							rangelength : "名称必须介于2到20个字符之间！"
						},
					},

					submitHandler : function() {
						var userJellard = {
							username : $("#username").val(),
							password : $("#password").val(),
							add_time : $("#add_time").val(),
							phone : $("#phone").val(),
							id : $("#id").val(),
						
						};
						param = "userJellard=" + JSON.stringify(userJellard);
						$.post('${request.contextPath!}/test/userJellard/save',
								userJellard, function(data) {
			
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
