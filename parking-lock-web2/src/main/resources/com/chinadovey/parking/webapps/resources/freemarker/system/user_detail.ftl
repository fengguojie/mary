<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<style type="text/css">
label.error{
	color:red;
}
</style>
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
					<h3 class="box-title">管理员详情</h3>
					<#include
					"/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<form class="form-horizontal offset3" id="user-form">
					    <div class="form-group">
						    <label for="" class="col-sm-3 control-label">所属机构</label>
						    <div class="col-sm-5">
							    <select id="companyId" class="chzn-select-deselect col-sm-12" >
									      <option value="0">不分配机构</option>
							    	<#if companys??>
								      <#list companys as company>
								          <#if user.companyId ??>
									      <option value="${company.id}" <#if user.companyId == company.id>selected="selected"</#if>>${company.name!}</option>
								          </#if>
								      </#list>
								  	</#if>
							    </select>
						    </div>
						 </div>
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-5">
								<input class="form-control" value="${user.name!}" id="name" name="name"
									type="text" disabled/>
							</div>
						</div>
						<div class="form-group">
							<label for="realname" class="col-sm-3 control-label">真实姓名</label>
							<div class="col-sm-5">
								<input id="realname" name="realname" value="${user.realname!}" class="form-control" type="text" disabled></input>
							</div>
						</div>
						<div class="form-group">
							<label for="tel" class="col-sm-3 control-label">联系电话</label>
							<div class="col-sm-5">
								<input id="tel" name="tel" class="form-control" value="${user.tel!}" type="text" disabled></input>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label">电子邮件</label>
							<div class="col-sm-5">
								<input id="email" name="email" class="form-control" value="${user.email!}" type="text" disabled></input>
							</div>
						</div>
						<@SecuValidate resource="com.chinadovey.parking.acl.SecuRoleManagerView">
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label">角色</label>
							<div class="col-sm-5">
								<table id="roleDatatable" data-toggle="table" data-height="300"
								data-url="${request.contextPath!}/system/user/getUserRoles?id=${user.id}">
									<thead>
										<tr>
											<th data-field="name" data-align="center" data-sortable="true">名称</th>
											<th data-field="description" data-align="center"
												data-sortable="true">描述</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
						<div class="box-footer text-center">
							<a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
						</div>
						</@SecuValidate>
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
var page=${colentpage!}
		var href = '${request.contextPath!}/system/user/Pagelist?page='+page;
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});

		//返回列表页面
		$('#btn-back').click(function() {
			$("#main-content").zrPageRefresh(href);
		});

	});
</script>
