<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>微信配置</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>配置中心</a></li>
		<li><a href="#" class="active">微信配置</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
             		 <form class="form-horizontal" id="company-form">
                            <div class="box box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">关联停车场</h3>
                                   <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                                </div><!-- /.box-header -->
                                <input type="hidden" id="id" value="${(wechatConfigure.id)!}"/>
                                <div class="box-body">
                                	<div class="form-group">
									    <label for="" class="col-sm-3 control-label">微信名称</label>
									    <div class="col-sm-5">
									      <input type="text" value="${(wechatConfigure.name)!}" class="form-control" id="name" name="name" placeholder="请输入微信名称" disabled>
									    </div>
									  </div>
	  								<div class="form-group">
									    <label for="" class="col-sm-3 control-label">停车场</label>
									    <div class="col-sm-5">
									      <select id="parkIds" class="chzn-select-deselect col-sm-12"  multiple="multiple">
									      <#if parks??>
										      <#list parks as park>
											      <option value="${park.id}" selected="selected">${park.name!}</option>
										      </#list>
										   </#if>
									      <#if allParks??>
										     <#list allParks as park>
										          <option value="${park.id}">${park.name!}</option>
									         </#list>
									      </#if>
									      </select>
									    </div>
									</div>
                                </div>
                                <div class="box-footer text-center">
                                       <a id="btn-link" class="btn btn-primary" href="javascript:;">&nbsp;关联选中的停车场&nbsp;</a>
                                       <a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
                                </div>
                            </div><!-- /.box -->
                         </form>
        </section>
		
		<!-- right col -->
	</div>
</section>
<!-- /.content -->
<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/dlg-info.ftl">

 <script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
 <script type="text/javascript">
	$(function(){
			//启用下拉菜单
		$('.chzn-select-deselect').chosen({});
		var href = '${request.contextPath!}/baseInfo/wechatConfigure/list';
		//返回列表页面
	    $('#btn-back').click(function(){
				$("#main-content").zrPageRefresh(href);
		});
		
		$('#btn-link').click(function(){
			var parkIds = $("#parkIds").val();	   
	       	$.post('${request.contextPath!}/baseInfo/wechatConfigure/linkParkById',
						"parkIds="+ parkIds +"&id=" + $('#id').val(),
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
 
