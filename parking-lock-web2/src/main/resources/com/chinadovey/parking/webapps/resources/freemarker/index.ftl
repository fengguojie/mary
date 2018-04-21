<style type="text/css">
.box-block{
	height: 120px;padding: 20px;padding-left: 5%;border-radius: 2px;
	color:#fff;
}
.box-block-img{
	float:left;margin-left: 8%;
}
.box-block-message{
	font-size:20px;
}
.box-block-number{
	font-size:30px;
}
.font-init{
	font-family: "Arial","Microsoft YaHei","黑体","宋体",sans-serif;
}
.box-block-uint{
	font-size:10px;
}
.bg-drak-gray{
	background: #B0B0B0;
}
.bg-lilac{
	background: #9A68E3;
}
.bg-pale-blue{
	background: #4BC0E3;
}
.bg-light-green{
	background: #40DC7B;
}
.box-row{
	margin-left:8px;margin-right:8px;
}
.box.box-solid.box-success > .box-header{
	background-color: #1BE9B6;
}
.box.box-solid.box-info > .box-header{
	background-color: #4BC0E3;
}
.box.box-solid.box-warning > .box-header{
	background-color: #9A68E2;
}


</style>

<!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                                              停车场概况
                    </h1>
                </section>
                
                <section class="content">
							<div class="box-body">		 			
               					<div class="row box-row">
			                        <div class="col-lg-4">
			                         <div class="box box-solid box-success text-center">
                                <div class="box-header">
                                    <h3><img src="${request.contextPath!}/statics/img/web/gateway_all_icon.png" width="40px" height="40px">&nbsp;网关总数</h3>
                                </div>
                                <div class="box-body" style="margin-left:12%;margin-top:5%;">
                                	<div class="percentage text-center" id="incomePie" width="80%"  height="90%" data-percent="100">
                                		 <font size="8" color="#198543" style="line-height: 10px;" class="money">${parkCount!0}</font><font size="4">个</font>
                                	</div>
                                </div>
                                         <div style="margin-top:10px;">
                                	<table class="table table-bordered text-center" style="height:30px;padding:0px;">
                                		<tr>
                                			<td><font size="5">全部车位：</font><font size="6" color="#ff0000"><value class="money">${CountTotal!0}</value></font><font size='4' style="margin-top:20px;">个</font></td>
                                			
                                		</tr>
                                	</table>
                                </div>
                            </div><!-- /.box -->
                        </div>
                        
                           	<div class="col-sm-4">
                            <!-- Primary box -->
                            <div class="box box-solid box-info text-center">
                                <div class="box-header">
                                    <h3><img src="${request.contextPath!}/statics/img/web/gateway_online_icon.png" width="40px" height="40px">&nbsp;在线网关</h3>
                                </div>
                                <div class="box-body"  style="margin-left:12%;margin-top:5%;">
                                	<div class="percentage text-center" id="turnoverPie" data-percent="100">
                                	  <font size="20px" color="#198543"><value class="money">${onLineTotal!0}</value></font>个
                                	</div>
                                </div><!-- /.box-body -->
                                <div style="margin-top:10px;">
                                	<table class="table table-bordered text-center" style="height:30px;padding:0px;">
                                		
                                		<tr>
                                			<td><font size="5">有车车位：</font><font size="6" color="#ff0000"><value class="money">${TotalonLine!0}</value></font><font size='4' style="margin-top:20px;">个</font></td>
                                		</tr>
                                	</table>
                               </div>
                            </div><!-- /.box -->
                        </div>
                        
                        <div class="col-sm-4">
                            <!-- Primary box -->
                            <div class="box box-solid box-warning text-center">
                                <div class="box-header bgc-lilac" >
                                    <h3><img src="${request.contextPath!}/statics/img/web/gateway_offline_icon.png" width="40px" height="40px">&nbsp;离线网关</h3>
                                </div>
                                <div class="box-body"  style="margin-left:12%;margin-top:5%;">
                                	<div class="percentage text-center" id="utilizationPie" data-percent="100"><font size="20px" color="#198543"><value class="money">${offLineTotal!0}</value></font>个</div>
                                </div><!-- /.box-body -->
                                <div style="margin-top:10px;">
                                	<table class="table table-bordered text-center" style="height:30px;padding:0px;">
                                		<tr>
                                			<td><font size="5">无车车位：</font><font size="6" color="#ff0000"><value class="money">${TotaloffLine!0}</value></font><font size='4' style="margin-top:20px;">个</font></td>
                                		</tr>
                                	</table>
                               </div>
                            </div><!-- /.box -->
                        </div>
                    </div>
                        
			                  
		       </section>
                

		   
		       <section class="content">
                <div class="box-body">		 			
                   					<div class="row box-row">
				                        <div class="col-lg-3">
				                           <div class="bg-pale-blue box-block">
				                         		<div class="box-block-img">
				                         			<img src="${request.contextPath!}/statics/img/web/carlock_all_icon.png"  align="absmiddle">
				                         		</div>
				                         		<div>
				                         			<div class="box-block-message">车锁总数</div>
				                         			<div style="margin-top:10px;">
				                         				&nbsp;<font class="money box-block-number">${LockTotal!0}</font><font class="box-block-uint">(个)</font>
				                         			</div>
				                         		</div>
			                         		</div>
			                         	</div>
				                        <div class="col-lg-3">
				                           <div class="bg-light-green box-block">
				                         		<div class="box-block-img">
				                         			<img src="${request.contextPath!}/statics/img/web/carlock_normal_icon.png"  align="absmiddle">
				                         		</div>
				                         		<div>
				                         			<div class="box-block-message">正常车锁</div>
				                         			<div style="margin-top:10px;">
				                         				&nbsp;<font class="money box-block-number">${LockonLine!0}</font><font class="box-block-uint">(个)</font>
				                         			</div>
				                         		</div>
			                         		</div>
			                         	</div>
					                  <div class="col-lg-3">
					                  		<div class="bg-drak-gray box-block">
				                         		<div class="box-block-img">
				                         			<img src="${request.contextPath!}/statics/img/web/electric_low_icon.png"  align="absmiddle">
				                         		</div>
				                         		<div>
				                         			<div class="box-block-message">电量过低</div>
				                         			<div style="margin-top:10px;">
				                         				&nbsp;<font class="money box-block-number">${elect!0}</font><font class="box-block-uint">(个)</font>
				                         			</div>
				                         		</div>
			                         		</div>
			                         	</div>
			                         	<div class="col-lg-3">
					                  		<div class="bg-lilac box-block">
				                         		<div class="box-block-img">
				                         			<img src="${request.contextPath!}/statics/img/web/carlock_offline_icon.png"  align="absmiddle">
				                         		</div>
				                         		<div>
				                         			<div class="box-block-message">离线车锁</div>
				                         			<div style="margin-top:10px;">
				                         				&nbsp;<font class="money box-block-number">${LockoffLine!0}</font><font class="box-block-uint">(个)</font>
				                         			</div>
				                         		</div>
			                         		</div>
			                         	</div>
					              </div>
		       </section><!-- /.content -->
<script src="${request.contextPath!}/statics/js/AdminLTE/app.js" type="text/javascript"></script>    
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="${request.contextPath!}/statics/js/AdminLTE/dashboard.js" type="text/javascript"></script>

<script type="text/javascript:;">
    	$(function(){
        	    		//启用下拉菜单
				$('.chzn-select-deselect').chosen({});
				initPieChart("#incomePie",$("#incomePie").width() * 0.8,"#1BE9B6");
				initPieChart("#turnoverPie",$("#turnoverPie").width() * 0.8,"#4BC0E3");
				initPieChart("#utilizationPie",$("#utilizationPie").width() * 0.8,"#9A68E2");
				$("#incomePie").append('<div class="font-init" style="line-height: 10%;font-size:24px; color:#1BE9B6; margin-bottom: 162px;margin-top: -30%;"></div>');
				
				$("#turnoverPie").append('<div class="font-init" style="line-height: 10%;font-size:24px; color:#1BE9B6; margin-bottom: 162px;margin-top: -30%;"></div>');
				$("#utilizationPie").append('<div class="font-init" style="line-height: 10%;font-size:24px; color:#1BE9B6; margin-bottom: 162px;margin-top: -30%;"></div>');
				
				
				$('#parkId').change(function(){
					$("#main-content").zrPageRefresh('${request.contextPath!}/indexLocal?parkId='+$(this).val());
				});
	           $('img').error(function () {
		      		$(this).attr('src', '${request.contextPath!}/system/b.png/picture');
		    	});
			   	$(".indexLocal").click(function(){
						var parkId = $(this).attr("data-id");
						$("#main-content").zrPageRefresh('${request.contextPath!}/indexLocalByParkId?parkId=' + parkId); 
				});
			
        	});

</script>
        