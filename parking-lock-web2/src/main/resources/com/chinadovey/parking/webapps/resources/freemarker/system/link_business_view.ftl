<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>用户管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>用户中心</a></li>
		<li><a href="#" class="active">用户管理</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
             		 <form class="form-horizontal" id="company-form">
                            <div class="box box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">关联商家</h3>
                                   <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                                </div><!-- /.box-header -->
                                <input type="hidden" id="id" value="${(user.id)!}"/>
                                <div class="box-body">
                                	<div class="form-group">
									    <label for="" class="col-sm-3 control-label">用户名</label>
									    <div class="col-sm-5">
									      <input type="text" value="${(user.name)!}" class="form-control" id="name" name="name" placeholder="请输入微信名称" disabled>
									    </div>
									  </div>
	  								<div class="form-group">
									    <label for="" class="col-sm-3 control-label">商家</label>
									    <div class="col-sm-5">
									      <select id="businessIds" class="chzn-select-deselect col-sm-12"  multiple="multiple">
									      <#if businesses??>
										      <#list businesses as business>
											      <option value="${business.id}" selected="selected">${business.name!}</option>
										      </#list>
										   </#if>
									      <#if allBusinesses??>
										     <#list allBusinesses as business>
										          <option value="${business.id}">${business.name!}</option>
									         </#list>
									      </#if>
									      </select>
									    </div>
									</div>
                                </div>
                                <div class="box-footer text-center">
                                       <a id="btn-link" class="btn btn-primary" href="javascript:;">&nbsp;关联选中的商家&nbsp;</a>
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
		var href = '${request.contextPath!}/system/user/list';
		//返回列表页面
	    $('#btn-back').click(function(){
				$("#main-content").zrPageRefresh(href);
		});
		
		$('#btn-link').click(function(){
			var businessIds = $("#businessIds").val();
			var param = '';
			if(businessIds == null){
				param = "id=" + $('#id').val();			
			}else{
				param = "businessIds="+ businessIds +"&id=" + $('#id').val();			
			}	   
	       	$.post('${request.contextPath!}/system/user/linkBusinessById',
						param,
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
 
