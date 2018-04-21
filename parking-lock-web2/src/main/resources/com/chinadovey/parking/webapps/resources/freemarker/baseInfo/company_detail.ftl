<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<style type="text/css">
label.error{
	color:red;
}
</style>
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>运营机构</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>管理中心</a></li>
		<li><a href="#" class="active">运营机构</a></li>
	</ol>
</section>
<!-- Main content -->
<section class="content">
	<div class="row">
		<section class="col-lg-12 connectedSortable">
			<div class="box box box-primary">
				<div class="box-header">
					<h3 class="box-title">查看运营机构详情</h3>
					<#include
					"/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<form class="form-horizontal offset3" id="discount-form">
						
						<div class="form-group">	
							<label for="name" class="col-sm-3 control-label">机构名称</label>
							<div class="col-sm-5">
								<input class="form-control"  id="name" name="name"
									value="${company.name!}" type="text" disabled/>
							</div>
						</div>
						<div class="form-group">	
							<label for="companyNo" class="col-sm-3 control-label">机构编码</label>
							<div class="col-sm-5">
								<input class="form-control"  id="companyNo" name="companyNo"
									value="${company.companyNo!}" type="text" disabled/>
							</div>
						</div>
						<div class="form-group">	
							<label for="name" class="col-sm-3 control-label">机构类型</label>
							<div class="col-sm-5">
								<select type="text" id="type" name="type"  class="form-control" disabled>
										<option value="1" <#if company.type==1>selected</#if>>区域</option>
										<option value="2" <#if company.type==2>selected</#if>>公司</option>
										<option value="3" <#if company.type==3>selected</#if>>政府</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="remark" class="col-sm-3 control-label">法人名称</label>
							<div class="col-sm-5">
								<input id="legalPerson" name="legalPerson" class="form-control" value="${company.legalPerson!}"
									type="text"  disabled/>
							</div>
						</div>
						<div class="form-group">
							<label for="legalPersonContacts" class="col-sm-3 control-label">法人联系方式</label>
							<div class="col-sm-5">
								<input id="legalPersonContacts" name="legalPersonContacts" class="form-control" value="${company.legalPersonContacts!}"
									type="text"  disabled/>
							</div>
						</div>
						
						<div class="form-group">	
							<label for="contactNumber" class="col-sm-3 control-label">联系电话</label>
							<div class="col-sm-5">
								<input class="form-control"  id="contactNumber" name="contactNumber" value="${company.contactNumber!}"
									type="text" disabled/>
							</div>
						</div>
						<div class="form-group">
							<label for="linkmen" class="col-sm-3 control-label">联系人</label>
							<div class="col-sm-5">
								<input id="linkmen" name="linkmen" class="form-control" value="${company.linkmen!}"
									type="text"  disabled/>
							</div>
						</div>
						<div class="form-group">
							<label for="moblie" class="col-sm-3 control-label">手机号码</label>
							<div class="col-sm-5">
								<input id="moblie" name="moblie" class="form-control" value="${company.moblie!}"
									type="text"  disabled/>
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="col-sm-3 control-label" >联系邮箱</label>
							<div class="col-sm-5">
								<input id="email" name="email" class="form-control" value="${company.email!}"
									type="text"  disabled/>
							</div>
						</div>
						<div class="form-group">
							<label for="remark" class="col-sm-3 control-label">备注</label>
							<div class="col-sm-5">
								<input id="remark" name="remark" class="form-control" value="${company.remark!}"
									type="text"  disabled/>
							</div>
						</div>
						
						
						<div class="box-footer text-center">
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
function timeFormatter(value, row, index) {
    var date = dateFormat(new Date(value), 1);
    return [date].join('');
}

	$(function() {
		
		var type = $('#mode').val();
		if(type==1){
			name = "全免";
		}else if(type==2){
			name = "免时间";
		}else if(type==3){
			name = "免金额";
		}else if(type==4){
			name = "折扣率";
		}
		 $('#mode').val(name);
		var page=${colentpage!}
		var href = '${request.contextPath!}/baseInfo/company/Pagelist?page='+page;
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});

		//返回列表页面
		$('#btn-back').click(function() {
			$("#main-content").zrPageRefresh(href);
		});

	});
</script>
