<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>平台设置</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>平台中心</a></li>
		<li><a href="#" class="active">平台设置</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
                            <form class="form-horizontal">
                            <div class="box box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">参数设置</h3>
                                     <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                                </div><!-- /.box-header -->
                                <div class="box-body">
									<input type="hidden" value="${parkId!4}" name="parkId" id="parkId"/>
									<@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeEatTime">
                                  	<div class="form-group">
							      		<label class="control-label col-sm-3">默认用餐时长&nbsp;</label>
										<div class="col-sm-4">
											<input type="text" value="${eatTime!120}" class="form-control" id="eatTime" name="eatTime" placeholder="分钟"  >
										</div>
										<div class="col-sm-1">
								      		<label class="control-label">&nbsp;分钟</label>
										</div>
										<a id="eat-time" class="btn btn-primary" href="javascript:;">&nbsp;修改&nbsp;</a>
									  </div>
									</@SecuValidate>
									  <@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeEatPromptTime">
									  <div class="form-group">
								      		<label class="control-label col-sm-3">用餐声音提醒间隔时间&nbsp;</label>
										<div class="col-sm-4">
											<input type="text" value="${eatPromptTime!30}" class="form-control" id="eatPromptTime" name="eatPromptTime" >
										</div>
										<div class="col-sm-1">
								      		<label class="control-label">&nbsp;秒</label>
										</div>
										<a id="eat-prompt-time" class="btn btn-primary" href="javascript:;">&nbsp;修改&nbsp;</a>
									  </div>
									</@SecuValidate>
									<@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeQuitTime">
									  <div class="form-group">
								      		<label class="control-label col-sm-3">默认退房时间&nbsp;</label>
										<div class="col-sm-4">
											<input type="text" value="${quitRoomTime!12}" class="form-control" id="quitTime" name="quitTime" placeholder="小时"  >
										</div>
										<div class="col-sm-1">
								      		<label class="control-label">&nbsp;点</label>
										</div>
										<a id="quit-time" class="btn btn-primary" href="javascript:;">&nbsp;修改&nbsp;</a>
									  </div>
									</@SecuValidate>
									  <@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeStayPromptTime">
									  <div class="form-group">
								      		<label class="control-label col-sm-3">住宿声音提醒间隔时间&nbsp;</label>
										<div class="col-sm-4">
											<input type="text" value="${stayPromptTime!30}" class="form-control" id="stayPromptTime" name="stayPromptTime" >
										</div>
										<div class="col-sm-1">
								      		<label class="control-label">&nbsp;秒</label>
										</div>
										<a id="stay-prompt-time" class="btn btn-primary" href="javascript:;">&nbsp;修改&nbsp;</a>
									  </div>
									</@SecuValidate>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
						</form>
                 </section>
		
		<!-- right col -->
	</div>
</section>
<!-- /.content -->

<script type="text/javascript">
	var parkId = $("#parkId").val();
	$(function(){
		<@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeEatTime">
									  
		 $('#eat-time').click(function(){
	    	var eatTime = $('#eatTime').val();
	    	updateEatTime(eatTime);
	    });
	    </@SecuValidate>
	    <@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeQuitTime">
									  
	     $('#quit-time').click(function(){
	    	var quitTime = $('#quitTime').val();
	    	updateQuitTime(quitTime);
	    });
	    	</@SecuValidate>
	    	
	   <@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeEatPromptTime">
	     $('#eat-prompt-time').click(function(){
	    	var eatPromptTime = $('#eatPromptTime').val();
	    	updateEatPromptTime(eatPromptTime);
	    });
	   </@SecuValidate>
	    	
	<@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeStayPromptTime">
	     $('#stay-prompt-time').click(function(){
	    	var stayPromptTime = $('#stayPromptTime').val();
	    	updateStayPromptTime(stayPromptTime);
	    });
	   </@SecuValidate>
	});
	
	<@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeEatTime">
 	  function updateEatTime(eatTime){
	    		$.post('${request.contextPath!}/baseInfo/code/updateEatTime','eatTime='+eatTime + "&parkId=" + parkId,function(e){
					if(e.result=='SUCCESS'){
						$("#failure-info").hide();
						$("#success-info").show();
						//删除成功之后刷新表格
						setTimeout(function(){$("#success-info").hide();},3000);
					}else{
						$("#failure-info").show();
					    $("#success-info").hide();	
					    setTimeout(function(){$("#failure-info").hide();},3000);
					}
	    	
	    	});
	    }
    </@SecuValidate>
    <@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeQuitTime">
	 function updateQuitTime(quitTime){
    		$.post('${request.contextPath!}/baseInfo/code/updateQuitTime','quitTime='+quitTime + "&parkId=" + parkId,function(e){
				if(e.result=='SUCCESS'){
					$("#failure-info").hide();
					$("#success-info").show();
					setTimeout(function(){$("#success-info").hide();},3000);
				}else{
					$("#failure-info").show();
				    $("#success-info").hide();	
				    setTimeout(function(){$("#failure-info").hide();},3000);
				}
    	
    	});
    }
    </@SecuValidate>
    
    
    //用餐提醒时间间隔
   <@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeEatPromptTime">
	 function updateEatPromptTime(eatPromptTime){
    		$.post('${request.contextPath!}/baseInfo/code/updateEatPromptTime','eatPromptTime='+eatPromptTime + "&parkId=" + parkId,function(e){
				if(e.result=='SUCCESS'){
					$("#failure-info").hide();
					$("#success-info").show();
					setTimeout(function(){$("#success-info").hide();},3000);
				}else{
					$("#failure-info").show();
				    $("#success-info").hide();	
				    setTimeout(function(){$("#failure-info").hide();},3000);
				}
    	
    	});
    }
    </@SecuValidate>
    
    //住宿提醒时间间隔
    <@SecuValidate resource="com.chinadovey.parking.acl.BaseCodeStayPromptTime">
	 function updateStayPromptTime(stayPromptTime){
    		$.post('${request.contextPath!}/baseInfo/code/updateStayPromptTime','stayPromptTime='+stayPromptTime + "&parkId=" + parkId,function(e){
				if(e.result=='SUCCESS'){
					$("#failure-info").hide();
					$("#success-info").show();
					setTimeout(function(){$("#success-info").hide();},3000);
				}else{
					$("#failure-info").show();
				    $("#success-info").hide();	
				    setTimeout(function(){$("#failure-info").hide();},3000);
				}
    	
    	});
    }
    </@SecuValidate>
</script>
 
