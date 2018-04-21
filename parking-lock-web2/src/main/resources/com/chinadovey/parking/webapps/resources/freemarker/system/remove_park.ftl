<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>关联停车场</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>基础设置</a></li>
		<li><a href="#" class="active">关联停车场</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
                            <div class="box box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">停车场记录</h3>
                                   <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                                </div><!-- /.box-header -->
                                <input type="hidden" id="id" value="${id!}"/>
                                <div class="box-body">
                                    <table id="myDatatable" data-toggle="table" data-url="${request.contextPath!}/baseInfo/park/getListByUserId?userId=${id!}"
										data-show-refresh="true" data-show-toggle="true" data-show-columns="true"
										data-side-pagination="server" data-pagination="true" data-search="true"  
										data-page-list="[10, 25, 50, 100, 200]">
									<thead>
										<tr>
											<th data-checkbox="true"></th>
											<th data-field="code" data-align="center" data-sortable="true">编码</th>
											<th data-field="name" data-align="center" data-sortable="true">名称</th>
											<th data-field="linkman" data-align="center" data-sortable="true">联系人</th>
										</tr>
									</thead>
								</table>
                                </div><!-- /.box-body -->
                                <div class="box-footer text-center">
                                       <button id="btn-link" type="submit" class="btn btn-primary" href="javascript:;">&nbsp;取消关联选中的停车场&nbsp;</button>
                                       <a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
                                </div>
                            </div><!-- /.box -->
        </section>
		
		<!-- right col -->
	</div>
</section>
<!-- /.content -->
<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/dlg-info.ftl">

 <script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
 <script type="text/javascript">
	$(function(){
	
		var href = '${request.contextPath!}/system/user/list';
		//返回列表页面
	    $('#btn-back').click(function(){
				$("#main-content").zrPageRefresh(href);
		});
		
		$('#btn-link').click(function(){
	       	var parks = $('#myDatatable').bootstrapTable("getSelections");
	   
	       	$.post('${request.contextPath!}/system/user/removeParkById',
						"parks="+ JSON.stringify(parks) +"&id=" + $('#id').val(),
						function(data){
							if (data.result=="SUCCESS") {
							       $("#failure-info").hide();
							       $("#success-info").show();
							       setTimeout(function(){$("#main-content").zrPageRefresh(href);},3000);
							}else{
								 $("#success-info").hide();
							     $("#failure-info").show();
							     setTimeout(function(){$("#failure-info").hide();},3000);
							}
						});		
		      });
	     });
		      
 </script>
 
