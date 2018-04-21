<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>车位管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>基础设置</a></li>
		<li><a href="#" class="active">车位管理</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
                            <form class="form-horizontal">
                            <div class="box box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">车位详情</h3>
                                </div><!-- /.box-header -->
                                <div class="box-body">
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">车位编号</label>
									    <div class="col-sm-5">
									      <input type="text" value="${carSpace.code!}" class="form-control" id="" placeholder="" disabled>
									    </div>
									  </div>
									  <#if carSpace.type??>
										  <div class="form-group">
										    <label for="" class="col-sm-3 control-label">类型</label>
										    <div class="col-sm-5">
										      <input type="text" value="${(1==carSpace.type!)?string('公共','个人')}" class="form-control" id="" placeholder="" disabled >
										    </div>
										  </div>
									  </#if>
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">状态</label>
									    <div class="col-sm-5">
									      <input type="text" value="${(1==carSpace.isEmpty!)?string('空','不空')}" class="form-control" id="" placeholder="" disabled >
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">停车场</label>
									    <div class="col-sm-5">
									      <input type="text" value="${(carSpace.park.name)!}" class="form-control" id="" placeholder="" disabled >
									    </div>
									  </div>
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">车位锁编号</label>
									    <div class="col-sm-5">
									      <input type="text" value="${(carSpace.slaveId)!}" class="form-control" id="" placeholder="" disabled >
									    </div>
									  </div>
									  <!--
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">物权人</label>
									    <div class="col-sm-5">
									      <input type="text" value="${realName!(carSpace.park.linkman)!'无'}" class="form-control" id="" placeholder="" disabled >
									    </div>
									  </div>
									  -->
									  <div class="form-group">
									    <label for="" class="col-sm-3 control-label">延时</label>
									    <div class="col-sm-5">
									      <input type="text"  class="form-control" id="delay" name='delay' value="${carSpace.delay}s" placeholder="" disabled>
									    </div>
									    <label for="延时" class="error">*</label>
									  </div>
									   <div class="form-group">
									    <label for="" class="col-sm-3 control-label">备注</label>
									    <div class="col-sm-5">
									    	<textarea rows="2" class="form-control" disabled>${carSpace.remark!}</textarea>
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
				var href = '${request.contextPath!}/baseInfo/carSpace/Pagelist?page='+page;
			    $("#main-content").zrPageRefresh(href);
		});
	});

</script>
 
