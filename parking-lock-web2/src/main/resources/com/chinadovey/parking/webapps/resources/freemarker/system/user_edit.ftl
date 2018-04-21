<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>修改用户</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>用户管理</a></li>
		<li><a href="#" class="active">修改用户</a></li>
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
						<input type="hidden" id="id" name="id" value="${user.id}"/>
						 <div class="form-group">
							    <label for="" class="col-sm-3 control-label">机构</label>
							    <div class="col-sm-5">
							      <select id="companyId" class="chzn-select-deselect col-sm-12">
									      <option value="0">不分配机构</option>
							    	<#if companys??>
								      <#list companys as company>
									      <option value="${company.id}" <#if user.companyId == company.id>selected="selected"</#if>>${company.name!}</option>
								      </#list>
								  	</#if>
							      </select>
							    </div>
						 </div>
						 <!--
						 <#if parks??>
								      <#list parks as park>
								      	  <#if urps??>
								      		<#list urps as urp>
									     		 <option value="${park.id}" <#if park.id == urp.parkId>selected="selected"</#if> >${park.name!}</option>
								     		</#list>
								     		<#else>
								     	  		 <option value="${park.id}" >${park.name!}</option>
								     		
								     	  </#if>
								      </#list>
								  </#if>
						  -->
						 <div class="form-group">
						    <label for="" class="col-sm-3 control-label">关联停车场</label>
						    <div class="col-sm-5">
							      <select id="parkId" multiple="true" class="chzn-select-deselect col-sm-12">
							      		<#if hasparks??>
								      		<#list hasparks as h>
									     		 <option value="${h.id}" selected="selected">${h.name!}</option>
								     		</#list>
								     	</#if>
							      		<#if noparks??>
								      		<#list noparks as n>
									     		 <option value="${n.id}">${n.name!}</option>
								     		</#list>
								     	</#if>
							      </select>
						    </div>
					   </div>
						 
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入用户名" value="${user.name}" id="name" name="name"
									type="text"/>
							</div>
									    <label for="name" class="error">*</label>
						</div>
						<div class="form-group">
							<label for="realname" class="col-sm-3 control-label">真实姓名</label>
							<div class="col-sm-5">
								<input id="realname" name="realname" placeholder="请输入真实姓名" value="${user.realname!}" class="form-control" type="text"></input>
							</div>
									    <label for="realname" class="error">*</label>
						</div>
						<div class="form-group">
							<label for="tel" class="col-sm-3 control-label">联系电话</label>
							<div class="col-sm-5">
								<input id="tel" name="tel" class="form-control" type="text" placeholder="请输入联系电话" value="${user.tel}"></input>
							</div>
									    <label for="tel" class="error">*</label>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label">电子邮箱</label>
							<div class="col-sm-5">
								<input id="email" name="email" class="form-control" value="${user.email}" placeholder="请输入邮箱帐号" type="text"></input>
							</div>
									    <label for="email" class="error">*</label>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">更改角色</label>
							<div class="col-sm-5">
								<div class="table-responsive" style="height:300px;overflow:auto;">
									<table class="table table-bordered" id="role-table" data-show-refresh="true" data-show-toggle="true" data-show-columns="true">
										<tr>
											<th width="20px">#</th>
											<th>名称</th>
											<th>描述</th>
										</tr>
										<#list roles as role>
										<tr>
											<td class="abc" width="20px"><input ${ids?seq_contains(role.id)?string("checked","")} type='checkbox' name='id' value=${role.id!}></td>
											<td>${role.name!}</td>
											<td>${role.description!}</td>
										</tr>
										</#list>
									</table>
								</div>
							</div>
						</div>

						<div class="box-footer text-center">
							<button id="btn-update" type="submit" class="btn btn-primary"
								href="javascript:;">&nbsp;修改&nbsp;</button>
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
	$(function() {
	var page=${colentpage!};
	var href = '${request.contextPath!}/system/user/Pagelist?page='+page;
	// 启用下拉菜单
	$('.chzn-select-deselect').chosen({});

	// 返回列表页面
	$('#btn-back').click(function() {
		$("#main-content").zrPageRefresh(href);
	});

	// 表单验证
	$("#user-form")
			.validate(
					{
						event : "keyup" || "blur",
						rules : {
							name : {
								required : true,
								remote : {
									url : "${request.contextPath!}/system/user/checkNameById",
									data : {
										name : function() {
											return $("#name").val()
										},
										id : function() {
											return $("#id").val()
										}
									}
								},
								rangelength : [ 2, 20 ]
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
								email : true
							}
						},
						messages : {
							name : {
								required : "请输入用户名！",
								remote : "用户名已存在",
								rangelength : "用户名必须介于2到20个字符之间！",
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
							var roles = new Array();
							var role;
							var i = 0;
							$('.abc :checked').each(function(){
								role = {
									id:$(this).val()
								}
								roles[i++] =  role;
							});
							var user = {
								id : $("#id").val(),
								name : $("#name").val(),
								tel : $("#tel").val(),
								realname : $("#realname").val(),
								email : $('#email').val(),
								companyId:$("#companyId").val(),
								parkIds:$("#parkId").val(),
								roles : roles,
							};
							param = "user=" + JSON.stringify(user);

							$
									.post(
											'${request.contextPath!}/system/user/update',
											param, function(data) {
												if (data.result == "SUCCESS") {
													$("#failure-info").hide();
													$("#success-info").show();
													setTimeout(function() {
														$("#main-content")
																.zrPageRefresh(
																		href);
													}, 3000);
												} else {
													$("#success-info").hide();
													$("#failure-info").show();
													setTimeout(function() {
														$("#failure-info")
																.hide();
													}, 3000);
												}
											});

						}

					});

});

</script>
