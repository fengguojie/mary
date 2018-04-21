<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>

<style type="text/css">
#linkPark input{
	height:28px;
}

</style>

<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>添加用户</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>用户管理</a></li>
		<li><a href="#" class="active">添加用户</a></li>
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
					<form class="form-horizontal offset3" id="user-form">
						<input type="hidden"  id="createUserId" value="${_dovey_session_secu_object_.user.id}"/>
						<div class="form-group">
						    <label for="" class="col-sm-3 control-label">机构</label>
						    <div class="col-sm-5">
							      <select id="companyId" class="chzn-select-deselect col-sm-12">
									      <option value="0">不分配机构</option>
							      <#if companys??>
								      <#list companys as company>
									      <option value="${company.id}">${company.name!}</option>
								      </#list>
								  </#if>
							      </select>
						    </div>
					   </div>
						<div class="form-group">
						    <label for="" class="col-sm-3 control-label">关联停车场</label>
						    <div id="linkPark" class="col-sm-5">
							      <select id="parkId" multiple="true" class="chzn-select-deselect col-sm-12">
							      <#if parks??>
								      <#list parks as park>
									      <option value="${park.id}">${park.name!}</option>
								      </#list>
								  </#if>
							      </select>
						    </div>
					   </div>
						<div class="form-group">	
							<label for="name" class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入用户名" id="name" name="name"
									type="text"/>
							</div>
									    <label for="name" class="error">*</label>
						</div>
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
						<div class="form-group">
							<label for="realname" class="col-sm-3 control-label">真实姓名</label>
							<div class="col-sm-5">
								<input id="realname" name="realname" class="form-control" type="text" placeholder="请输入真实姓名"></input>
							</div>
									    <label for="realname" class="error">*</label>
						</div>
						<div class="form-group">
							<label for="tel" class="col-sm-3 control-label">联系电话</label>
							<div class="col-sm-5">
								<input id="tel" name="tel" class="form-control" type="text" placeholder="请输入联系电话"></input>
							</div>
									    <label for="tel" class="error">*</label>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label">电子邮箱</label>
							<div class="col-sm-5">
								<input id="email" name="email" class="form-control" type="text" placeholder="请输入电子邮箱帐号"></input>
							</div>
									    <label for="email" class="error">*</label>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label">分配角色</label>
							<div class="col-sm-5">
								<a class="btn btn-primary" data-toggle="modal"
									data-target="#role-list">点击选择</a>
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

<div class="modal fade" id="role-list" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h4 id="addModalLabel" align="center">关联角色</h4>
			</div>
			<div class="modal-body">
				<table id="roleDatatable" data-toggle="table" data-height="300"
					<@SecuValidate resource="*">
					data-url="${request.contextPath!}/system/user/getRoles"
					</@SecuValidate>
					data-url="${request.contextPath!}/system/user/getRolesByCreateUserId?id=${_dovey_session_secu_object_.user.id}"
					>
					<thead>
						<tr>
							<th data-field="" data-checkbox="true"></th>
							<th data-field="name" data-align="center" data-sortable="true">名称</th>
							<th data-field="description" data-align="center"
								data-sortable="true">描述</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="modal-footer">
				<a href="javascript:;" class="btn btn-info" data-dismiss="modal"
					id="roleCancel" aria-hidden="true">取消</a> <a href="javascript:;"
					data-dismiss="modal" class="btn btn-danger">确定</a>
			</div>
		</div>
	</div>
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
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});

		//返回列表页面
		$('#btn-back').click(function() {
			$("#main-content").zrPageRefresh(href);
		});

		//表单验证
		$("#user-form").validate(
				{
					event : "keyup" || "blur",
					rules : {
						name : {
							required : true,
							remote: "${request.contextPath!}/system/user/checkName",
							rangelength : [ 2, 20 ]
						},
						password:{
							required : true,
							rangelength : [6 , 20]
						},
						repassword:{
							required : true,
							equalTo:"#password"
						},
						tel : {
							required : true,
							isMobile : true
						},
						realname : {
							required : true,
							rangelength : [ 2, 20 ]
						},
						email : {
							required : true,
							email:true 
						}
					},
					messages : {
						name : {
							required : "请输入用户名！",
							remote : "用户名已存在",
							rangelength : "用户名必须介于2到20个字符之间！",
						},
						password : {
							required : "请输入密码！",
							rangelength : "密码必须介于6到20个字符之间！",
						},
						repassword : {
							required : "请再次输入密码！",
							equalTo : "两次输入密码必须相同！",
						},
						tel : {
							required : "请输入手机号！",
							isMobile : "手机号不正确！",
						},
						realname : {
							required : "请输入真实姓名！",
							rangelength : "姓名必须介于2到20个字符之间！",
						},
						email : {
							required : "请输入邮箱帐号！",
							email : "邮箱帐号不正确！",
						},
					},

					submitHandler : function() {
						var user = {
							name : $("#name").val(),
							password :$("#password").val(),
							tel : $("#tel").val(),
							realname : $("#realname").val(),
							email : $('#email').val(),
							createUserId : $("#createUserId").val(),
							companyId:$("#companyId").val(),
							parkIds:$("#parkId").val(),
							roles:$('#roleDatatable').bootstrapTable("getSelections"),
						};
						param = "user=" + JSON.stringify(user);
						$.post('${request.contextPath!}/system/user/save',
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
