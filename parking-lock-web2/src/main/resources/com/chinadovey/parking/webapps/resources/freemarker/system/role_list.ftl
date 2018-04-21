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
				<!-- /.box-header -->
				<div class="box-body">
					<@SecuValidate resource="com.chinadovey.parking.acl.SecuRoleManagerCret">
						<p><button type="button" class="btn btn-success add">&nbsp;添加&nbsp;</button></p>
					</@SecuValidate>
					<table id="myDatatable" data-toggle="table" data-url="${request.contextPath!}/system/role/getList"
						data-side-pagination="server" data-show-refresh="true" data-show-toggle="true" data-show-columns="true"
						data-pagination="true" data-search="true" data-field-text="根据名称搜索" data-page-list="[10, 25, 50, 100, 200]">
						<thead>
							<tr>
								<th data-field="name" data-align="left" data-sortable="true">名称</th>
								<th data-field="operate" data-formatter="operateFormatter" data-events="operateEvents"data-align="left">操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</section>
	</div>
</section>

<script type="text/javascript">
	var Newpage=${Newpage!'1'}
	var colentpage=Newpage;
</script>

<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/dlg-info.ftl">
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>

<script type="text/javascript">
	var id;
	$(function() {
		//跳转到添加页面
		$('.add').click(function() {
			var href = '${request.contextPath!}/system/role/add';
			$("#main-content").zrPageRefresh(href);
		});
		//删除
		$('#modalConfirm').click(function() {
			deleteRecord(id);
		});

	});

	/**
	 * 显示表格中操作（查看、修改、删除）字样
	 * @param value
	 * @param row
	 * @param index
	 * @returns
	 */
	function operateFormatter(value, row, index) {
		return [ <@SecuValidate resource="com.chinadovey.parking.acl.SecuRoleManagerView">'<a class="detail" href="javascript:;">查看&nbsp;</a>',</@SecuValidate>
				<@SecuValidate resource="com.chinadovey.parking.acl.SecuRoleManagerEdit">'<a class="edit" href="javascript:;">修改&nbsp;</a>',</@SecuValidate>
				<@SecuValidate resource="com.chinadovey.parking.acl.SecuRoleManagerDele">'<a class="delete" href="#model">删除</a>' </@SecuValidate>].join('');
	}

	
	window.operateEvents = {
		'click .detail' : function(e, value, row, index) {
			var href = '${request.contextPath!}/system/role/detail?id='+ row.id+'&page='+colentpage;
			if (href != "" && href != "#") {
				$("#main-content").zrPageRefresh(href);
			}
			return false;
		},

		'click .edit' : function(e, value, row, index) {
			var href = '${request.contextPath!}/system/role/edit?roleId='+ row.id+'&page='+colentpage;
			if (href != "" && href != "#") {
				$("#main-content").zrPageRefresh(href);
			}
			return false;
		},

		'click .delete' : function(e, value, row, index) {
			id = row.id;
			$('#modalLabel').text("删除确认");
			$('#modalText').text("确定要删除选择的记录吗？");
			$('#modalCancel').show();
			$('#modalConfirm').show();
			$('#myModal').modal('show');
		}

	};

	/**
	 * 删除操作(单个)
	 */
	function deleteRecord(id) {
		$.post('${request.contextPath!}/system/role/delete','id=' +id,
			function(e) {
				if (e.result == 'SUCCESS') {
					$('#myModal').modal('hide');
					$("#failure-info").hide();
					$("#success-info").show();
					//删除成功之后刷新表格
					$('#myDatatable').bootstrapTable('refresh',{
						url : '${request.contextPath!}/system/role/getList'
					});
					setTimeout(function() {
						$("#success-info").hide();
					}, 3000);
				} else {
					$("#failure-info").show();
					$("#success-info").hide();
					setTimeout(function() {
						$("#failure-info").hide();
					}, 3000);
				}
			});
	}
	
	/**
	 * 提交时间
	 * @param value
	 * @param row
	 * @param index
	 * @returns
	 */
	function createTimeFormatter(value, row, index) {
		var date =dateFormat(new Date(value),1);
		return [date].join('');
	}
	
</script>