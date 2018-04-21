<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>添加机构</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>在线视频学习管理</a></li>
		<li><a href="#" class="active">笔记管理系统</a></li>
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
					<form class="form-horizontal offset3" id="note-form">
						
						
						<div class="form-group">	
							<label for="privilege" class="col-sm-3 control-label">笔记标题</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入笔记标题" id="title" name="title"
									type="text"/>
							</div>
						</div>
						<div class="form-group">
							<label for="linkmen" class="col-sm-3 control-label">笔记内容</label>
							<div class="col-sm-5">
								<input id="content" name="content" class="form-control"
									type="text" placeholder="请输入笔记内容"/>
							</div>
							<label for="linkmen" class="error">*</label>
						</div>
						<div class="form-group">	
							<label for="privilege" class="col-sm-3 control-label">用户姓名</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入用户姓名" id="username" name="username"
									type="text"/>
							</div>
						</div>
						<div class="form-group">	
							<label for="privilege" class="col-sm-3 control-label">视频id</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入视频id" id="videoId" name="videoId"
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

		var href = '${request.contextPath!}/test/note/list';
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});

		//返回列表页面
		$('#btn-back').click(function() {
			$("#main-content").zrPageRefresh(href);
		});

		//表单验证
		$("#note-form").validate(
				{
					event : "keyup" || "blur",
					rules : {
						
						title : {
							required : true,
							rangelength : [ 2, 20 ]
						},
						content:{
							required : true,
							rangelength : [ 2, 500 ]
						},
						username:{
							required : true,
							rangelength : [ 2, 20 ]
						},
						videoId:{
							required : true,
							rangelength : [ 2, 20 ]
						},
						
					},
					messages : {
						
						title : {
							required : "请输入名称！",
							rangelength : "名称必须介于2到20个字符之间！"
						},
						content : {
							required : "请输入年龄！",
							rangelength : "名称必须介于2到500个字符之间！"
						},
						
						username : {
							required : "请输入名称！",
							rangelength : "名称必须介于2到20个字符之间！"
						},
						videoId : {
							required : "请输入年龄！",
							rangelength : "名称必须介于2到20个字符之间！"
						},
					},

					submitHandler : function() {
						var note = {
							title : $("#title").val(),
							content : $("#content").val(),
							username : $("#username").val(),
							videoId : $("#videoId").val(),
						
						};
						
						param = "note=" + JSON.stringify(note)+"&name="+"mary";
						alert(param);
						alert(JSON.stringify(note));
						
						$.post('${request.contextPath!}/test/note/save',
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
