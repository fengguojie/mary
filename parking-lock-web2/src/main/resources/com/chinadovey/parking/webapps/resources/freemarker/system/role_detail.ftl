<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>角色管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>用户管理</a></li>
		<li><a href="#" class="active">角色管理</a></li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<section class="col-lg-12 connectedSortable">
			<div class="box box box-primary">
				<div class="box-header">
					<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<form class="form-horizontal offset3" id="role-form">
						<input type="hidden" id="id" name="id" value="${role.id}"/>
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">角色名称</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="2-25个字符（中文、英文、数字）" id="name"
									name="name" type="text" value="${role.name!}" disabled />
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">角色描述</label>
							<div class="col-sm-5">
								<textarea id="description" name="description" class="form-control"
									rows="3" disabled>${role.description}</textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">角色权限</label>
							<div class="col-sm-5" id="auths">
								<table data-toggle="table" id="auth-table" data-height="400" data-search="true" data-field-text="根据名称搜索"
									data-url="${request.contextPath!}/system/role/getRoleAuthorityList?id=${role.id}">	
									<thead>
										<tr>
											<th data-field="name" data-align="left" data-sortable="true">名称</th>
											<th data-field="description" data-align="left"  data-sortable="true">描述</th>
										</tr>

									</thead>
								</table>
							</div>
						</div>
						<div class="box-footer text-center">
							<a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
						</div>
					</form>
				</div>
			</div>		
		</section>

		<!-- right col -->
	</div>


</section>
<!-- /.content -->
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/chosen/chosen.jquery.min.js" />
<script type="text/javascript" src="${request.contextPath!}/statics/js/jquery.validate.min.js" />
<script type="text/javascript" src="${request.contextPath!}/statics/js/validate_expand.js" />
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
	
<script type="text/javascript">
	$(function() {
		var page = ${colentpage!}
		var href = '${request.contextPath!}/system/role/Pagelist?page='+page;
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});

		//返回列表页面
		$('#btn-back').click(function() {
			$("#main-content").zrPageRefresh(href);
		});
	});
	
</script>

