<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>角色管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>用户管理</a></li>
		<li><a href="#" class="active">角色管理</a></li>
	</ol>
</section>
<section class="content">
	<div class="row">
		<section class="col-lg-12 connectedSortable">
			<div class="box box box-primary">
				<div class="box-header">
					<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
				</div>
				<div class="box-body">
					<form class="form-horizontal offset3" id="role-form">
						<input type="hidden" value="${roleIds!''}" id="roleIds" />
						<input type="hidden" id="createUserId" value="${_dovey_session_secu_object_.user.id}"/>
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">角色名称</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入角色名称" id="name" name="name" type="text" required />
							</div>
							<label for="name" class="error">*</label>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">角色描述</label>
							<div class="col-sm-5">
								<textarea id="description" name="description" placeholder="请输入关于角色的描述" class="form-control" rows="3"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">角色权限</label>
							<div class="col-sm-5" id="auths">
								<table data-toggle="table" id="auth-table" data-height="500" data-url="${request.contextPath!}/system/role/getAddRoleAuthorityList">						
									<thead>
										<tr>
											<th data-checkbox="true" ></th>
											<th data-field="name" data-halign="left" data-align="left">名称</th>
											<th data-field="description" data-halign="left" data-align="left">描述</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<div class="box-footer text-center">
							<button id="btn-add" type="submit" class="btn btn-primary" href="javascript:;">&nbsp;添加&nbsp;</button>
							<a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
						</div>
					</form>
				</div>
			</div>
		</section>
	</div>
</section>

<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/chosen/chosen.jquery.min.js" />
<script type="text/javascript" src="${request.contextPath!}/statics/js/jquery.validate.min.js" />
<script type="text/javascript" src="${request.contextPath!}/statics/js/validate_expand.js" />
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
	
<script type="text/javascript">
	$(function() {
		var href = '${request.contextPath!}/system/role/list';
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});
		//返回列表页面
		$('#btn-back').click(function() {
			$("#main-content").zrPageRefresh(href);
		});
		//表单验证
		$("#role-form").validate({
			event : "keyup" || "blur",
			rules : {
				name : {
					required : true,
					remote: "${request.contextPath!}/system/role/checkName",
					rangelength : [ 2, 20 ]
				},
			},
			messages : {
				name : {
					required : "请输入角色名称！",
					remote : "角色名称已存在",
					rangelength : "角色名称必须介于2到20个字符之间！",
				},
			},

			submitHandler : function() {
				var role = {
					name : $("#name").val(),
					description : $("#description").val(),
					createUserId : $("#createUserId").val(),
					authoritys:$("#auth-table").bootstrapTable("getSelections"),
				};
				param = "role=" + JSON.stringify(role);
				$.post('${request.contextPath!}/system/role/save',param, 
					function(data) {
						if (data.result == "SUCCESS") {
							$("#failure-info").hide();
							$("#success-info").show();
							setTimeout(function() {
								$("#main-content").zrPageRefresh(href);
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

