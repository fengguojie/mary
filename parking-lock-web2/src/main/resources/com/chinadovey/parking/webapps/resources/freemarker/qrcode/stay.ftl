<!-- Content Header (Page header) -->
<script type="text/javascript">
<!--
	//-->
	$(function() {
		$("#success-info").hide();
		$("#failure-info").hide();
		$("#wait-info").hide();
	});
</script>
<section class="content-header">
	<h1>住宿二维码</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>二维码管理</a></li>
		<li><a href="#" class="active">住宿二维码</a></li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<section class="col-lg-12 connectedSortable">
			<div class="box box box-primary">
				<div class="box-header">
					<h3 class="box-title">住宿二维码</h3>
					<#include
					"/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<form class="form-horizontal offset3" id="role-form">
					<input type="hidden"  id="createUserId" value="${_dovey_session_secu_object_.user.id}"/>
						<div class="form-group">
							<label for="name" class="col-sm-3 control-label">角色名称</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入角色名称" id="name"
									name="name" type="text" required />
							</div>
									    <label for="name" class="error">*</label>
						</div>
						<div class="form-group">
							<label for="description" class="col-sm-3 control-label">角色描述</label>
							<div class="col-sm-5">
								<textarea id="description" name="description" placeholder="请输入关于角色的描述" class="form-control"
									rows="3"></textarea>
							</div>
						</div>
						<div class="box-footer text-center">
							<button id="btn-detail" type="submit" class="btn btn-primary"
								href="javascript:;">&nbsp;查看&nbsp;</button>
						</div>
					</form>
		</section>

		<!-- right col -->
	</div>


</section>
 <script type="text/javascript">
 
 </script>
 
