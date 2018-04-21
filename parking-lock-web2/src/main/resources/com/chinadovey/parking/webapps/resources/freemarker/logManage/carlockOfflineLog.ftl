<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>车锁离线日志</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>操作日志</a></li>
		<li><a href="#" class="active">车锁离线日志</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
                            <div class="box box box-primary">
                                <div class="box-header">
                                   <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                                </div><!-- /.box-header -->
                                <div class="box-body">
                                 <div id="custom-toolbar">
									    <div class="form-inline" role="form">
									        <div class="form-group">
									            <div class="input-group">
									                <label class="col-sm-4 control-label">开始日期</label>
									                <div class="col-sm-8">
														<input class="form-control date" name="start" id="startDate"  data-date-format="yyyy-mm-dd" type="text" placeholder="开始时间">
													</div>
										      	</div>
									      	</div>
									        <div class="form-group">
									            <div class="input-group">
									                <label class="col-sm-4 control-label">结束日期</label>
									                <div class="col-sm-8">
									               		<input class="form-control date" name="end" id="endDate" data-date-format="yyyy-mm-dd" type="text" placeholder="结束时间">
													</div>
										      	</div>
										    </div>
										      	 <button id="query" type="submit" class="btn btn-default">查询</button>
									    </div>
									</div>                                
                                    <table id="myDatatable" data-toggle="table"
									data-side-pagination="server" data-show-refresh="true" data-show-toggle="true" data-show-columns="true"
									data-pagination="true" data-search="true" 
									data-sort-name="time" data-sort-order="desc"  data-field-text="根据车锁编号搜索"
									data-page-list="[10, 25, 50, 100, 200]">
									<thead>
										<tr>
											<th data-field="state" data-checkbox="true"></th>
											<th data-field="slaveid" data-align="left" data-sortable="true">车位锁编号</th>
											
											<th data-field="status" data-align="left" data-sortable="true" data-formatter="operateStatusFormatter">事件</th>
											<th data-field="time" data-align="left" data-sortable="true" data-formatter="operateTimeFormatter">发生时间</th>
										</tr>
									</thead>
								</table>
                                </div><!-- /.box-body -->
                            </div><!-- /.box -->
        </section>
		
		<!-- right col -->
	</div>
</section>
<script type="text/javascript">
var Newpage=${Newpage!'1'}
var colentpage=Newpage;
</script>
<!-- /.content -->
<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/dlg-info.ftl">
 <script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
 <script type="text/javascript">
 var id;
 $(".date").datetimepicker({
 		language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
});
 $(function () {
 
   $.ajaxSetup({   
              async : true  
   }); 
   });
   
  	$('#query').click(function(){
  		var start = $('#startDate').val();
  		var end = $('#endDate').val();
  		var param = "start=" + start + "&end=" + end;
  		//判断结束日期是否大于开始日期
		var couldSearch =compareDate();
		if(couldSearch){
			$('#myDatatable').bootstrapTable('refresh',{
				url: "${request.contextPath!}/logManage/operateLog/getCarlockList?"+param
			});
		}else{
			alert("结束时间要大于开始时间！");
		}	
  	});
  		
   	function compareDate(){
		//开始时间
		var date1 = $('#startDate').val();
		//结束时间
		var date2 = $('#endDate').val();
		
		var startDate = new Date(date1.replace("/-/g","/")); 
	 	var endDate = new Date(date2.replace("/-/g","/"));
		
		 //设置时间段
		 if(startDate.valueOf()<=endDate.valueOf()){
		 	return true;
		 }else{
		 	return false;
		 }		
		
	} 
 
   
/**
 * 操作状态
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operateStatusFormatter(value, row, index) {
	if(value==5){
       return ['离线'].join('');
       }
       else{
       return ['在线'].join('');
       }
   }

    var date=new Date();
	date=date.getFullYear()+'-'+(date.getMonth()+1)+'-'+(date.getDate()-1);
	document.getElementById("startDate").value=date; 
	
	var d=new Date();
	d=d.getFullYear()+'-'+(d.getMonth()+1)+'-'+(d.getDate());  
    document.getElementById("endDate").value=d;
    
    drawPicture();
    
    function drawPicture() {
			var startDate = $("#startDate").val(); //获得日期的值
			var endDate = $("#endDate").val();
			//判断结束日期是否大于开始日期
			if(compareDate()){
				
				$('#myDatatable').bootstrapTable('refresh',{
				url:'${request.contextPath!}/logManage/operateLog/getCarlockList?start='+startDate+'&end='+endDate
			});
				
			}
		}
    
 </script>
 
