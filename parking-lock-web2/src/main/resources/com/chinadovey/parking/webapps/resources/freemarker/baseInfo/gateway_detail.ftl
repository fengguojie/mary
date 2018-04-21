<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>网关管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>网关管理</a></li>
		<li><a href="#" class="active"></a></li>
	</ol>
</section>
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
                            <form class="form-horizontal">
                            <div class="box box box-primary">
                                <div class="box-header">
                                </div><!-- /.box-header -->
                                <div class="box-body">
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">所属车场</label>
									    <div class="col-sm-5">
									      <input type="text" value="${park.name}" class="form-control" id="parkingName" placeholder="" disabled />
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">网关编号</label>
									    <div class="col-sm-5">
									      <input type="text" value="${gateway.gatewayNo!}" class="form-control" id="" placeholder="" disabled />
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">网关名称</label>
									    <div class="col-sm-5">
									      <input type="text" value="${gateway.gatewayName!}" class="form-control" id="" placeholder="" disabled />
									    </div>
									  </div>
									  
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">串口编号A</label>
									    <div class="col-sm-5">
									    	<input type="text" value="${gateway.seriala!}" class="form-control" disabled />
									    </div>
									  </div>
									   <div class="form-group">
									    <label for="" class="col-sm-3 control-label">无线模块A</label>
									    <div class="col-sm-5">
									    	<input type="text" value="${gateway.wirea!}" class="form-control" disabled />
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">信道A</label>
									    <div class="col-sm-5">
									    	<input type="text" value="${gateway.channela!}" class="form-control" disabled />
									    </div>
									  </div>
									   <div class="form-group">
									    <label for="" class="col-sm-3 control-label">串口编号B</label>
									    <div class="col-sm-5">
									    	<input type="text" value="${gateway.serialb!}" class="form-control" disabled />
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">无线模块B</label>
									    <div class="col-sm-5">
									    	<input type="text" value="${gateway.wireb!}" class="form-control" disabled />
									    </div>
									  </div>
									   <div class="form-group">
									    <label for="" class="col-sm-3 control-label">信道B</label>
									    <div class="col-sm-5">
									    	<input type="text" value="${gateway.channelb!}" class="form-control" disabled />
									    </div>
									  </div>
                                </div><!-- /.box-body -->
                                <div class="box-footer text-center">
                                       <a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
                                </div>
                            </div><!-- /.box -->
						</form>
                 </section>
		
		<!-- right col -->
	</div>
</section>
<!-- /.content -->

<script type="text/javascript">
	$(function(){
	     var companyNo = $("#companyNo").val();
		 var name;
		 <#if companys??>
			<#list companys as company>
				if(companyNo == ${company.companyNo!}){
					name = "${company.name!'暂无机构信息'}";
				}
			</#list>
		</#if>
		$("#companyNo").val(name);
	
		$('#btn-back').click(function(){
			var page=${colentpage!};
			var href = '${request.contextPath!}/baseInfo/gateway/Pagelist?page='+page;
			$("#main-content").zrPageRefresh(href);
		});
		
	});
	
</script>
 
