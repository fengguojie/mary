<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>用户管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>用户管理</a></li>
		<li><a href="#" class="active">用户12管理</a></li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<section class="col-lg-12 connectedSortable">
			<div class="box box box-primary">
				<div class="box-header">
					<div style=" position: relative;
    							 left: 26%;
  								 background-color: #F56954;
   								 width: 41%;
					"><span id="info" style="position: relative;left: 40%;"></span></div>
					<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
					<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/dlg-info-resetPassword.ftl">
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<@SecuValidate resource="com.chinadovey.parking.acl.SecuUserManagerCret">
						<p>
							<button type="button" class="btn btn-success add">&nbsp;添加&nbsp;</button>
							<button type="button" class="btn btn-danger resetPassword">&nbsp;初始化密码&nbsp;</button>
						</p>
				    </@SecuValidate>
					<table id="myDatatable" data-toggle="table"
						data-url="${request.contextPath!}/system/user/getList" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" 
						data-side-pagination="server" data-pagination="true" data-search="true" data-field-text="按照用户名搜索" 
						data-page-list="[10, 25, 50, 100, 200]">
						<thead>
							<tr>
								<th data-checkbox="true"></th>
								<th data-field="name" data-align="center" data-sortable="true">用户名</th>
								<th data-field="roleName" data-align="left" data-sortable="true">角色</th>
								<th data-field="comName" data-align="left" data-sortable="true">所属机构</th>
								<th data-field="parkName" data-align="left" data-sortable="true">所属车场</th>
								<th data-field="tel" data-align="left" data-sortable="true">联系电话</th>
								<th data-field="email" data-align="left" data-sortable="true">电子邮件</th>
								<th data-field="operate" data-formatter="operateFormatter"
									data-events="operateEvents" data-align="left">操作</th>
							</tr>
						</thead>
					</table>
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</section>
		<!-- right col -->
	</div>
</section>
	<script type="text/javascript">
		var Newpage=${Newpage!'1'}
		var colentpage=Newpage;
	</script>
	<!-- /.content -->
	<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/dlg-info.ftl">
	<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
	
	<script type="text/javascript">
		var id;
		
		$(function() {
			$('#myDatatable').bootstrapTable({
		    	url : '${request.contextPath!}/system/user/getList',
			});
		
			//跳转到添加页面
			$('.add').click(function() {
				var href = '${request.contextPath!}/system/user/add';
				$("#main-content").zrPageRefresh(href);
			});
			/**
			 * 初始化密码(可多选)
		 	*/
		 	var ids='';
			$('.resetPassword').click(function() {
				 var num =  $('#myDatatable').bootstrapTable("getSelections").length;
				    if(num == 0){
				    	$("#info").text("请至少选择一条记录");
				        $("#info").show();
				        setTimeout(function() {
							$("#info").hide();
						}, 1000);
						return;
					}
					
					var  che= $('#myDatatable').bootstrapTable("getSelections");
			  
			  for(var i=0;i<che.length;i++){
				  if(che.length>1){
					  ids+=che[i].id+',';  
				  }else{
					  ids+=che[i].id;  
				  }
					
			  }
			
				$('#modalLabel2').text("初始化密码确认");
				$('#modalText2').html("初始密码为:123456  确定要初始化密码吗？");
				$('#modalCancel2').show();
				$('#modalConfirm2').show();
				$('#myModal2').modal('show');
		    
			//初始化
			$('#modalConfirm2').click(function() {
				 resetPassword(ids);
			});
			
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
		 <@SecuValidate resource="com.chinadovey.parking.acl.SecuUserManagerView">'<a class="detail" href="javascript:;">查看&nbsp;</a>',</@SecuValidate>
					<@SecuValidate resource="com.chinadovey.parking.acl.SecuUserManagerLinkPark">'<a class="linkPark" href="javascript:;">关联停车场&nbsp;</a>',</@SecuValidate>
					<@SecuValidate resource="com.chinadovey.parking.acl.SecuUserManagerRemovePark">'<a class="removePark" href="javascript:;">取消关联停车场&nbsp;</a>',</@SecuValidate>
					<@SecuValidate resource="com.chinadovey.parking.acl.SecuUserManagerPasswordEdit">'<a class="alter" href="javascript:;">修改密码&nbsp;</a>',</@SecuValidate>
		 */
		function operateFormatter(value, row, index) {
			return [ 
					<@SecuValidate resource="com.chinadovey.parking.acl.SecuUserManagerEdit">'<a class="edit" href="javascript:;">修改&nbsp;</a>',</@SecuValidate>
					<@SecuValidate resource="com.chinadovey.parking.acl.SecuUserManagerDele">'<a class="delete" href="#model">删除&nbsp;</a>',</@SecuValidate>].join('');
		}
	
		window.operateEvents = {
			'click .detail' : function(e, value, row, index) {
				var href = '${request.contextPath!}/system/user/detail?id='
						+ row.id+'&page='+colentpage;
				if (href != "" && href != "#") {
					$("#main-content").zrPageRefresh(href);
				}
				return false;
			},
	
			'click .edit' : function(e, value, row, index) {
				var href = '${request.contextPath!}/system/user/edit?id='
						+ row.id+'&page='+colentpage;
				if (href != "" && href != "#") {
					$("#main-content").zrPageRefresh(href);
				}
				return false;
			},
			
			'click .linkPark' : function(e, value, row, index) {
				var href = '${request.contextPath!}/system/user/linkPark?id='
						+ row.id;
				if (href != "" && href != "#") {
					$("#main-content").zrPageRefresh(href);
				}
				return false;
			},
			
			'click .linkBusiness' : function(e, value, row, index) {
				var href = '${request.contextPath!}/system/user/linkBusiness?id='
						+ row.id;
				if (href != "" && href != "#") {
					$("#main-content").zrPageRefresh(href);
				}
				return false;
			},
			
			'click .removePark' : function(e, value, row, index) {
				var href = '${request.contextPath!}/system/user/removePark?id='
						+ row.id;
				if (href != "" && href != "#") {
					$("#main-content").zrPageRefresh(href);
				}
				return false;
			},
	
			'click .alter' : function(e, value, row, index) {
				var href = '${request.contextPath!}/system/user/alterPasswordView?id='
						+ row.id + '&type=1';
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
			$.post('${request.contextPath!}/system/user/delete','id=' + id,
				function(e) {
					if (e.result == 'SUCCESS') {
						$('#myModal').modal('hide');
						$("#failure-info").hide();
						$("#success-info").show();
						//删除成功之后刷新表格
						$('#myDatatable')
								.bootstrapTable(
										'refresh',
										{
											url : '${request.contextPath!}/system/user/getList'
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
		* 初始化密码
		*/
		function resetPassword(ids) {
		$.post('${request.contextPath!}/system/user/resetPassword','ids=' + ids,function(e) {
				if (e.result == 'SUCCESS') {
					$('#myModal2').modal('hide');
					$("#failure-info").hide();
					$("#success-info").show();
					//成功之后刷新表格
					$('#myDatatable')
							.bootstrapTable(
									'refresh',
									{
										url : '${request.contextPath!}/system/user/getList'
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
		
</script>
