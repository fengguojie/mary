<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>道闸日志</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>控制中心</a></li>
		<li><a href="#" class="active">道闸日志</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
                            <div class="box box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">道闸日志</h3>
                                   <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                                </div><!-- /.box-header -->
                                  <#if parks??>
										<#if (parks?size > 1)>
                                <div class="col-sm-1"><label class="col-sm-10 control-label">车场</lable></div>
                                	<select id="parkId" class="chzn-select-deselect col-sm-2">
								         <option value="0">全部停车场</option>
												<#list parks as park>
												  	<option value="${park.id}" <#if (park.id==Session.openGateRecord_parkId!0)>selected='selected'</#if>>${park.name!}</option>
												</#list>
									 </select>

									 <#else>
										<#list parks as park>
											<input type="hidden" id="parkId" value="${park.id!}"/>
										</#list>
									  	</#if>
								  	</#if>
                                <div class="box-body"> 
                                 <div id="custom-toolbar">
									    <div class="form-inline" role="form">
									        <div class="form-group">
									            <div class="input-group">
									                <label class="col-sm-4 control-label">开始时间</label>
									                <div class="col-sm-8">
														<input class="form-control datetime" name="start" id="startDate" type="text" value="${Session.openGateRecord_start!}" data-date-format="yyyy-mm-dd" placeholder="开始时间">
													</div>
										      	</div>
									      	</div>
									        <div class="form-group">
									            <div class="input-group">
									                <label class="col-sm-4 control-label">结束时间</label>
									                <div class="col-sm-8">
									               		<input class="form-control datetime" name="end" id="endDate" type="text" value="${Session.openGateRecord_end!}" data-date-format="yyyy-mm-dd" placeholder="结束时间" >
													</div>
										      	</div>
										    </div>
										      	 <button id="query" type="submit" class="btn btn-default">查询</button>
										    
									    </div>
									</div>
                                    <table id="myDatatable" data-toggle="table" data-url=""
										data-side-pagination="server" data-show-refresh="true" data-show-toggle="true" data-show-columns="true"
										data-pagination="true" data-toolbar="#custom-toolbar" 
										data-sort-name="operateTime" data-sort-order="desc"
										data-page-list="[10, 25, 50, 100, 200]">
									<thead>
										<tr>
											<th data-field="parkId" data-align="left" data-formatter="parkFormatter" data-sortable="true">车场名称</th>
											<th data-field="channelName" data-align="left" data-sortable="true">通道名称</th>
											<th data-field="operateEvent" data-align="left" data-formatter="channelFormatter" data-sortable="true">操作类型</th>
										<th data-field="operateResul" data-align="left" data-formatter="channelResultFormatter" data-sortable="true">操作结果</th>
											<th data-field="operateTime" data-align="left" data-formatter="floodgatesDateFormatter" data-sortable="true">操作时间</th>
												<th data-field="operator" data-align="left" data-sortable="true">操作人</th>
											
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
   var now = new Date();
		//如果日期选择的是年月日格式
function GetDateStr(AddDayCount) {
	var dd = new Date();
	dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
	var y = dd.getFullYear();
	var m = (dd.getMonth()+1)>=10?(dd.getMonth()+1):"0"+(dd.getMonth()+1);//获取当前月份的日期
	var d = dd.getDate()>=10?""+dd.getDate():"0"+dd.getDate();
	var h = dd.getHours();
	var f = dd.getMinutes();
	return y+"-"+m+"-"+d+" "+h+":"+f;
} 
  $(".datetime").datetimepicker({
 		language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
});

function channelResultFormatter(value , row , index){
	var channelResult =  row.operateResul;
	if(channelResult==0){
		return '成功';
	}else{
		return '失败';
	}
}
function channelFormatter(value , row , index){
	var operateEvent =  row.operateEvent;
	if(operateEvent==1){
		return '开闸';
	}else{
		return '异常';
	}
}
  $(function () {
		//启用下拉菜单
		$('.chzn-select-deselect').chosen({});
		var parkId = $('#parkId').val();
  		var start = $('#startDate').val();
  		var end = $('#endDate').val();
  		if(start==null||start==""){
  			start = GetDateStr(-30);
  			$('#startDate').val(start);
  			end = GetDateStr(0);
  			$('#endDate').val(end);
  		}
  		var param = "start=" + start + "&end=" + end+ "&parkId=" + parkId;
  		$('#myDatatable').bootstrapTable('refresh',{
                 url: '${request.contextPath!}/signoCarLog/getList?'+param,
			});
		
		$('#parkId').change(function(){
		 param = "start=" + $('#startDate').val() + "&end=" + $('#endDate').val() + "&parkId=" + $('#parkId').val();
			$('#myDatatable').bootstrapTable('refresh',{
                 url: "${request.contextPath!}/signoCarLog/getList?"+param,
			});
		});
		
  		$('#query').click(function(){
	  		var parkId = $('#parkId').val();
  			var start = $('#startDate').val();
  			var end = $('#endDate').val();
  			var param = "start=" + start + "&end=" + end+ "&parkId=" + parkId;
			//判断结束日期是否大于开始日期
			var couldSearch = compareCalendar(start,end);
			if(couldSearch){
				$('#myDatatable').bootstrapTable('refresh',{
					url: "${request.contextPath!}/signoCarLog/getList?"+param
				});
			}else{
				alert("结束时间要大于开始时间！");
			}	
  		});
  		$('#export').click(function(){
  			$("#export").attr("disabled",true);
	  		var parkId = $('#parkId').val();
	  		var start = $('#startDate').val();
	  		var end = $('#endDate').val();
	  		var param = "start=" + start + "&end=" + end + "&parkId=" + parkId;
	  		//判断结束日期是否大于开始日期
			var couldSearch =compareCalendar(start,end);
			if(couldSearch){
		  		$.post(
		  			"${request.contextPath!}/statistics/openGateRecord/export",param,
		  			function(data){
		  				if(data.result){
		  					if(1 == data.type){
		  						alert("数据量过多,本次只导出一部分数据");
		  					}
		  					location.href="${request.contextPath!}/system/download?path="+"excels/statistics"+ "&fileName="+data.fileName+"&type="+"xls";
		  				}else{
		  					alert(data.error);
		  				}
				  			$("#export").attr("disabled",false);
		  			}
		  		);
	  		}
	  	});
  		
  });
  
/**
 * 总价
 * @param value
 * @param row
 * @param index
 * @returns
 */
function priceFormatter(value, row, index) {
       return [value,' 元'].join('');
   }
/**
 * 车牌号
 * @param value
 * @param row
 * @param index
 * @returns
 */
function carNoFormatter(value, row, index) {
	var data = row.carInOutRecord.carNo;
	return [data].join('');	
 }
/**
 * 时间
 * @param value
 * @param row
 * @param index
 * @returns
 */
function floodgatesDateFormatter(value, row, index) {
      var date =dateFormat(new Date(value),1);
	  return [date].join('');
}
/**
 * 数量格式
 * @param value
 * @param row
 * @param index
 * @returns
 */
function parkFormatter(value, row, index) {
	 var data = value;
	 var parkId = row.parkId;
	 var parkName;
	 <#if parks??>
		<#list parks as park>
			if(parkId == ${park.id!}){
				parkName = "${park.name!'暂无停车场信息'}";
			}
		</#list>
	</#if>
	  return [parkName].join('');
}
function statusFormatter(value,row,index){
	if(value == 0) return ['<font color="bule">未过期</font>'].join('');
	else if(value == 1)return ['<font color="DarkBlue">已过期</font>'].join('');
	else if(value == 2)return ['<font color="Gold">已取消</font>'].join('');
	else if(value == 3)return ['<font color="springgreen">已付款</font>'].join('');
	else if(value == 4)return ['<font color="blue">未提交订单</font>'].join('');
	else return ['<font color="red">异常</font>'].join('');
}

/**
 * 显示表格中操作（查看、修改、删除）字样
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operateFormatter(value, row, index) {
       return [
          <@SecuValidate resource="com.chinadovey.parking.acl.StatisticsCarChargeRecordRank">
           '<a class="detail" href="javascript:;">查看&nbsp;</a>',
          </@SecuValidate>
       ].join('');
   }
   
 
window.operateEvents = {
        'click .detail': function (e, value, row, index) {
				var href = '${request.contextPath!}/statistics/openGateRecord/detail?id='+row.id+'&page='+colentpage;
				if (href != "" && href != "#") {
					$("#main-content").zrPageRefresh(href);
				}
				return false;
        },    
        
    };
    
 </script>
 
