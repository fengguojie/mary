<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>控制日志</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>设备中心</a></li>
		<li><a href="#" class="active">控制日志</a></li>
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
									        <div class="form-group">
									            <div style="width:120%;" class="input-group">
									                <label class="col-sm-4 control-label">操作途径</label>
									                <div class="col-sm-8">
									               		
									                	<select class="form-control" id="type">
									                		<option value='1'>平台控制</option>
									                		<option value='2'>车牌识别</option>
									                	</select>
									               		
													</div>
										      	</div>
										    </div>
									         	 <button style="position: relative;margin-left:98%;top: -39px;" id="query" type="submit" class="btn btn-default">查询</button>
									    </div>
									</div>                                
                                    <table id="myDatatable" data-toggle="table" 
									data-side-pagination="server" data-show-refresh="true" data-show-toggle="true" data-show-columns="true"
									data-pagination="true" data-search="true" data-toolbar="#custom-toolbar"
									data-sort-name="operateTime" data-sort-order="desc"  data-field-text="按车位锁编号搜索"
									data-page-list="[10, 25, 50, 100, 200]">
									<thead>
										<tr>
											<th data-field="state" data-checkbox="true"></th>
											<th data-field="slaveId" data-align="left" data-sortable="true">车位锁编号</th>
											<th data-field="spaceName" data-align="left" data-sortable="true">车位名称</th>
											<th data-field="operateStatus" data-align="left" data-sortable="true" data-formatter="operateStatusFormatter">操作状态</th>
											<th data-field="operateResult" data-align="left" data-sortable="true" data-formatter="operateResultFormatter">操作结果</th>
											<th data-field="operateType" data-align="left" data-sortable="true" data-formatter="operateTypeFormatter">操作途径</th>
											<th data-field="userName" data-align="left" data-sortable="true">操作人</th>
											<th data-field="operateTime" data-align="left" data-sortable="true" data-formatter="operateTimeFormatter">操作时间</th>
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
   
  	$('#query').click(function(){
  		var start = $('#startDate').val();
  		var end = $('#endDate').val();
  		var type = $('#type').val();
  		var param = "start=" + start + "&end=" + end +"&type=" + type;
  		//判断结束日期是否大于开始日期
		var couldSearch =compareDate();
		if(couldSearch){
			$('#myDatatable').bootstrapTable('refresh',{
				url: "${request.contextPath!}/logManage/operateLog/getList?"+param
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
 
 });
	
	
/**
 * 操作时间
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operateTimeFormatter(value, row, index) {
       return [dateFormat(new Date(value),1)].join('');
   }
   
/**
 * 操作状态
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operateStatusFormatter(value, row, index) {
	if(value==1){
       return ['下降'].join('');
       }
       else{
       return ['上升'].join('');
       }
   }
/**
 * 操作途径
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operateTypeFormatter(value, row, index) {
	if(value==1){
       return ['平台控制'].join('');
       }
       else{
       return ['车牌识别'].join('');
       }
   }
   
/**
 * 操作结果
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operateResultFormatter(value, row, index) {
	if(value==1 || value==0){
       return ['成功'].join('');
       }
       else{
       return ['失败'].join('');
       }
   }
   

/**
 * 操作人角色
 * @param value
 * @param row
 * @param index
 * @returns
 */
function userTypeFormatter(value, row, index) {
       if(value==1) return ['管理员'].join('');
       else  return ['车主'].join('');
}



	var date=new Date();
	date=date.getFullYear()+'-'+(date.getMonth()+1)+'-'+(date.getDate()-1);
	document.getElementById("startDate").value=date; 
	
	var d=new Date();
	d=d.getFullYear()+'-'+(d.getMonth()+1)+'-'+(d.getDate());  
    document.getElementById("endDate").value=d;
    
    drawPictrue();
    
    function drawPictrue(){
    	
    	var start = $("#startDate").val();
    	var end = $("#endDate").val();
    	
    	$('#myDatatable').bootstrapTable('refresh',{
				url: "${request.contextPath!}/logManage/operateLog/getList?start="+start+"&end="+end
			});
    }
    
 </script>
 
