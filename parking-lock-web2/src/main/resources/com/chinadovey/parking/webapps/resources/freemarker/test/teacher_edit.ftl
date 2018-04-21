<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>修改机构</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>人力資源管理</a></li>
		<li><a href="#" class="active">教師管理</a></li>
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
					<form class="form-horizontal offset3" id="teacher-form">
						<input type="hidden" id="teacherId" name="teacherId" value="${teacher.teacherId}"/>
							<div class="form-group">	
							<label for="name" class="col-sm-3 control-label">老师名称</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入老师名称" id="teacherName" name="teacherName"
									value="${teacher.teacherName!}" type="text" />
							</div>
									    <label for="name" class="error">*</label>
						</div>
						
						<div class="form-group">	
							<label for="contactNumber" class="col-sm-3 control-label">年龄</label>
							<div class="col-sm-5">
								<input class="form-control" placeholder="请输入年龄" id="teacherAge" name="teacherAge" value="${teacher.teacherAge!}"
									type="text" />
							</div>
						</div>
						<div class="box-footer text-center">
							<button id="btn-add" type="submit" class="btn btn-primary"
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
        $('#change').text(teacherName);
		var href = '${request.contextPath!}/test/teacher/list';
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});

		//返回列表页面
		$('#btn-back').click(function() {
			$("#main-content").zrPageRefresh(href);
		});

		//表单验证
		$("#teacher-form").validate(
				{
					event : "keyup" || "blur",
					rules : {
						teacherName : {
							required : true,
							rangelength : [ 2, 20 ]
						},
						teacherId:{
							required : true,
							rangelength : [ 2, 20 ]
						},
					},
					messages : {
						teacherName : {
							required : "请输入名称！",
							rangelength : "名称必须介于2到20个字符之间！"
						},
						teacherAge : {
							required : "请输入年齡！",
							rangelength : "联系人必须介于2到20个字符之间！"
						},
					},

					submitHandler : function() {
						var teacher = {
							teacherId : $("#teacherId").val(),
							teacherName : $("#teacherName").val(),
							teacherAge :$("#teacherAge").val(),
						
						};
						param = "teacher=" + JSON.stringify(teacher);
						$.post('${request.contextPath!}/test/teacher/update',
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
