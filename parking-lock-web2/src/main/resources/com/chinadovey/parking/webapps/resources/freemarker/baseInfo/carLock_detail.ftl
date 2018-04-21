<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>车位锁管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>基础设置</a></li>
		<li><a href="#" class="active">车位锁管理</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
                            <form class="form-horizontal">
                            <div class="box box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">车位锁详情</h3>
                                </div><!-- /.box-header -->
                                <div class="box-body">
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">所属机构</label>
									    <div class="col-sm-5">
									      <input type="text" value="${(carLock.company.name)!'未分配'}" class="form-control" id="" placeholder="" disabled>
									    </div>
									  </div>
									   <div class="form-group">
									    <label for="" class="col-sm-3 control-label">网关编号</label>
									    <div class="col-sm-5">
									      <input type="text" value="${carLock.dasId!}" class="form-control" id="" placeholder="" disabled>
									    </div>
									  </div>
									   <div class="form-group">
									    <label for="" class="col-sm-3 control-label">车位锁编号</label>
									    <div class="col-sm-5">
									      <input type="text" value="${carLock.slaveId!}" class="form-control" id="" placeholder="" disabled>
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">配置状态</label>
									    <div class="col-sm-5">
									      <input type="text" value="${(carLock.configStatus==1)?string('已配置','未配置')}" class="form-control" id="" placeholder="" disabled >
									    </div>
									   </div>
									   <div class="form-group">
									    <label for="" class="col-sm-3 control-label">开关状态</label>
									    <div class="col-sm-5">
									      <input type="text" value="${(carLock.switchStatus==1)?string('开','关')}" class="form-control" id="" placeholder="" disabled >
									    </div>
									  </div>
									   <div class="form-group">
									    <label for="" class="col-sm-3 control-label">运行状态</label>
									    <div class="col-sm-5">
									      <input type="text" value="${(carLock.runStatus==1)?string('正常','故障')}" class="form-control" id="" placeholder="" disabled >
									    </div>
									  </div>
									   <div class="form-group">
									    <label for="" class="col-sm-3 control-label">电压</label>
									    <div class="col-sm-5">
									      <input type="text" value="${carLock.voltage!'0'} V" class="form-control" id="" placeholder="" disabled >
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">备注</label>
									    <div class="col-sm-5">
									    	<textarea rows="2" class="form-control" disabled>${carLock.remark!}</textarea>
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
	var page=${colentpage!};
		$('#btn-back').click(function(){
				var href = '${request.contextPath!}/baseInfo/carLock/Pagelist?page='+page;
			    $("#main-content").zrPageRefresh(href);
		});
	});

</script>
 
