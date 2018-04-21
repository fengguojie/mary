<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>车锁控制</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>设备中心</a></li>
		<li><a href="#" class="active">车锁控制</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- Left col -->
		<!-- right col (We are only adding the ID to make the widgets sortable)-->
		<section class="col-lg-12 connectedSortable">
                            <div class="box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title"></h3>
                                    <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                                </div>
                                <p>
			   						 <!--
			   						 <button id="opens" type="submit" class="btn btn-default">批量打开</button>
			   						 <button id="closes" type="submit" class="btn btn-default">批量关闭</button>
			   						 -->
			   						 <@SecuValidate resource="com.chinadovey.parking.acl.CarlockBatchOperate">
			   						 <button id="allDown" type="submit" class="btn btn-danger">全部下降</button>
			   						 <button id="allUp" type="submit" class="btn btn-success">全部上升</button>
			   						 </@SecuValidate>
								</p>
                                  <div class="box-body">
                                    <table id="myDatatable" data-toggle="table" data-url="${request.contextPath!}/baseInfo/operator/getList"
										data-side-pagination="server" data-show-refresh="true" data-show-toggle="true" data-show-columns="true"
										data-pagination="true" data-search="true"  data-field-text="请输入车锁编号"
										data-page-list="[10, 25, 50, 100, 200]">
										<thead>
											<tr>
												<th data-checkbox="true"></th>
												<th data-field="slaveId" data-align="center" data-sortable="true">车锁编号</th>
											
												<th data-field="voltage" data-align="center" data-formatter="voltageFormatter" data-sortable="true">电压</th>
												<th data-field="runStatus" data-align="center" data-formatter="runFormatter" data-sortable="true">状态</th>
												<th data-field="carStatus" data-align="center" data-formatter="carFormatter" data-sortable="true">是否有车</th>
												<@SecuValidate resource="com.chinadovey.parking.acl.OperateCarSpaceStatus">
													<th data-formatter="operateFormatter" data-events="operateEvents"data-align="center">开关动作</th>
												</@SecuValidate>
											</tr>
										</thead>
									</table>
                                </div><!-- /.box-body -->
                            </div>
                  </section>
	</div>
	
</section>

<!-- /.content -->
<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/dlg-operate-info.ftl">
<script type="text/javascript">
	var Newpage=${Newpage!'1'}
	var colentpage=Newpage;
</script>
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
<!--<script src="js/AdminLTE/app.js" type="text/javascript"></script>-->     
        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!--  <script src="js/AdminLTE/dashboard.js" type="text/javascript"></script>--> 
 <script type="text/javascript">
 var equiId;
 var btnId;
 $(function () {
      $('#allUp').click(function(){
        flag = "allup";
		$('#myModalText').text("确定要让车位锁全部上升吗？");
		$('#modalCancel').show();
		$('#modalConfirm').show();
		$('#myModal').modal('show');
	  });
	 
	 $('#allDown').click(function(){
	    flag = "alldown";
		$('#myModalText').text("确定要让车位锁全部下降吗？");
		$('#modalCancel').show();
		$('#modalConfirm').show();
		$('#myModal').modal('show');
     });
	 
	 $('#modalConfirm').click(function(){
		 if(flag == "allup"){
		     allup();
		 }else{
		     alldown();
		 }
	 });
	 
	 $('#opens').click(function(){
       	var carlocks = $('#myDatatable').bootstrapTable("getSelections");
        $("#wait-info").show();
       	$.post('${request.contextPath!}/baseInfo/operator/opens',
					"carlocks="+ JSON.stringify(carlocks),
					function(data){
						if (data.result=="SUCCESS") {
						       $("#wait-info").hide();
						       $("#failure-info").hide();
						       $("#success-info").show();
						       //setTimeout(function(){$("#main-content").zrPageRefresh(href);},3000);
						       $('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/operator/getList'});
				               setTimeout(function(){$("#success-info").hide();},3000);
						}else{
						     $("#wait-info").hide();
							 $("#success-info").hide();
						     $("#failure-info").show();
						     setTimeout(function(){$("#failure-info").hide();},3000);
						}
					});		
	 });
	 
	 $('#closes').click(function(){
       	var carlocks = $('#myDatatable').bootstrapTable("getSelections");
        $("#wait-info").show();
       	$.post('${request.contextPath!}/baseInfo/operator/closes',
					"carlocks="+ JSON.stringify(carlocks),
					function(data){
						if (data.result=="SUCCESS") {
						       $("#wait-info").hide();
						       $("#failure-info").hide();
						       $("#success-info").show();
						       //setTimeout(function(){$("#main-content").zrPageRefresh(href);},3000);
						       $('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/operator/getList'});
				               setTimeout(function(){$("#success-info").hide();},3000);
						}else{
						     $("#wait-info").hide();
							 $("#success-info").hide();
						     $("#failure-info").show();
						     setTimeout(function(){$("#failure-info").hide();},3000);
						}
					});		
	 });
	});
	
	

function allup(){
    $('#myModal').modal('hide');
	$("#wait-info").show();
   	$.post('${request.contextPath!}/baseInfo/operator/allUp',
			function(data){
				if (data.result=="SUCCESS") {
				       $("#wait-info").hide();
				       $("#failure-info").hide();
				       alert(data.msg);
				       //$("#success-info").show();
				       //setTimeout(function(){$("#main-content").zrPageRefresh(href);},3000);
				       $('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/operator/getList'});
		               //setTimeout(function(){$("#success-info").hide();},3000);
				}else{
				     $("#wait-info").hide();
					 $("#success-info").hide();
				     $("#failure-info").show();
				     setTimeout(function(){$("#failure-info").hide();},3000);
				}
	});	
}
function alldown(){
    $('#myModal').modal('hide');
    $("#wait-info").show();
	$.post('${request.contextPath!}/baseInfo/operator/allDown',
			function(data){
				if (data.result=="SUCCESS") {
				       $("#wait-info").hide();
				       $("#failure-info").hide();
				       alert(data.msg);
				       //$("#success-info").show();
				       //setTimeout(function(){$("#main-content").zrPageRefresh(href);},3000);
				       $('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/operator/getList'});
		               //setTimeout(function(){$("#success-info").hide();},3000);
				}else{
				     $("#wait-info").hide();
					 $("#success-info").hide();
				     $("#failure-info").show();
				     setTimeout(function(){$("#failure-info").hide();},3000);
				}   
	});
}			    
/**
 * 开关按钮
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operateFormatter(value, row, index) {
		var btnClass;
		var btnText;
	
			if(row.switchStatus==1){
				btnClass = 'btn-success';	
				btnText = ' 上升 '	
			}else{
				btnClass = 'btn-danger';
				btnText = ' 下降 ';
			}
		
		
		return ['<button id=ctrl_'+row.slaveId+' class="btn-operate btn '+btnClass+'">&nbsp;&nbsp;'+btnText+'&nbsp;&nbsp; </button>'].join('');
   }
   
 
function voltageFormatter(value , row , index){
    if(value == null || value ==""){
       value = "0V";
    }else{
       value+="V"
    }
    return value;
	//return row.voltage+'V';
}

function runFormatter(value , row , index){
	var runStatus =  row.runStatus;
	if(runStatus==0){
		return '正常';
	}else if(runStatus==1){
		return '电量低';
	}else{
	    return '离线';
	}
}
function carFormatter(value , row , index){
	var carStatus =  row.carStatus;
	if(carStatus != null && carStatus==1){
		return '有车';
	}else{
	    return '无车';
	}
}   
   window.operateEvents = {
        'click .btn-operate': function (e, value, row, index) {
        	equiId = row.slaveId;
        	btnId = 'ctrl_'+equiId;
        	if(row.switchStatus==1){
        		operate(equiId,2);
        	}else{
        		operate(equiId,1);
        	}
        }
        
        
        
    };
   
 function operate(equiId , status){
    $("#wait-info").show();
    $('#ctrl_'+equiId).attr('disabled','disabled');
 	$.post('${request.contextPath!}/baseInfo/operator/newoperate','equiId=' + equiId + '&status='+status , function(data){
		if(data==0){
		    $("#wait-info").hide();
			$("#failure-info").hide();
			$("#success-info").show();
			$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/operator/getList'});
			setTimeout(function(){$("#success-info").hide();},5000);
		}else if(data==1){
			$("#wait-info").hide();
			$("#failure-info").hide();
			$("#success-info").show();
			$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/operator/getList'});
			setTimeout(function(){$("#success-info").hide();},5000);
		}else{
		    $("#wait-info").hide();
			$("#failure-info").show();
		    $("#success-info").hide();	
		    setTimeout(function(){$("#failure-info").hide();},5000);
		} 
		
		 $('#ctrl_'+equiId).attr('disabled',false);		
 	});
 } 
   
   
   
   
   
   
   
 function carChart(element){
	    $(element).highcharts({
	        title: {
	            text: '车辆数量统计'
	        },
	        xAxis: {
	            categories: [
	                '00',
	                '01',
	                '02',
	                '03',
	                '04',
	                '05',
	                '06',
	                '07',
	                '08',
	                '09',
	                '10',
	                '11',
	                '12',
	                '13',
	                '14',
	                '15',
	                '16',
	                '17',
	                '18',
	                '19',
	                '20',
	                '21',
	                '22',
	                '23',
	                
	            ]
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: '车位数量(个)'
	            }
	        },
	        tooltip: {
	            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	                '<td style="padding:0"><b>{point.y}（个）</b></td></tr>',
	            footerFormat: '</table>',
	            shared: true,
	            useHTML: true
	        },
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: [{
	        	name:'车位数量',
	            data: [49, 21, 16, 19,49, 71, 16, 19,49, 71, 46, 18,49, 31, 16, 29,45, 71, 66, 39,19, 41, 16, 19]

	        }]
	    });
}
 </script>
 
