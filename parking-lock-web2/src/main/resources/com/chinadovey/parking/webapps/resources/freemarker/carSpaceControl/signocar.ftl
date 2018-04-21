<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>道闸控制</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>设备中心</a></li>
		<li><a href="#" class="active">道闸控制</a></li>
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
                                <#if parks??>
									 <#if (parks?size > 1)>
                                <div class="col-sm-1"><label class="col-sm-12 control-label">车场</lable></div>
                                <div class="col-sm-2">
                                	<select id="parkId" class="col-sm-12 chzn-select-deselect">
								         <option value="0">全部停车场</option>
												<#list parks as park>
												  	<option value="${park.id}" <#if (park.id==Session.carInOutRecord_parkId!0)>selected='selected'</#if>>${park.name!}</option>
												</#list>
									 </select>
									  <#else>
										<#list parks as park>
											<input type="hidden" id="parkId" value="${park.id!}"/>
										</#list>
								</#if>
								</#if>
								</div>	
                                  <div class="box-body">
                                    <table id="myDatatable" data-toggle="table" data-url=""
										data-side-pagination="server" data-show-refresh="true" data-show-toggle="true" data-show-columns="true"
										data-pagination="true" 
										data-page-list="[10, 25, 50, 100, 200]">
										<thead>
											<tr>
												<th data-field="parkId" data-align="center"  data-formatter="parkFormatter">车场名称</th>
												<th data-field="channelName" data-align="center" data-sortable="true">通道名称</th>
												<th data-field="channelType" data-align="center" data-formatter="channelFormatter" data-sortable="true">通道类型</th>
												<th data-field="boxName" data-align="center"  data-sortable="true">岗亭名称</th>
												<@SecuValidate resource="com.chinadovey.parking.acl.ChannelLogRank">
													<th data-formatter="operateFormatter" data-events="operateEvents"data-align="center">操作</th>
												</@SecuValidate>
											</tr>
										</thead>
									</table>
                                </div><!-- /.box-body -->
                            </div>
                  </section>
	</div>
</div>
</section>

<!-- /.content -->
<script type="text/javascript">
var Newpage=${Newpage!'1'}
var colentpage=Newpage;
</script>
<#include
"/com/chinadovey/parking/webapps/resources/freemarker/include/signo.ftl">
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
<!--<script src="js/AdminLTE/app.js" type="text/javascript"></script>-->     
        <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!--  <script src="js/AdminLTE/dashboard.js" type="text/javascript"></script>--> 
 <script type="text/javascript">

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
function channelFormatter(value , row , index){
	var channelType =  row.channelType;
	if(channelType==0){
		return '入口';
	}if(channelType==1){
	return '出口';
	}
	if(channelType==2){
	return '正常';
	}
	else{
		return '脱机';
	}
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
				btnClass = 'btn-success';	
				btnText = ' 开闸 '	
		return ['<button id=ctrl_'+row.equiId+' class="btn-operate btn '+btnClass+'">&nbsp;&nbsp;'+btnText+'&nbsp;&nbsp; </button>'].join('');
   }

      window.operateEvents = {
        'click .btn-operate': function (e, value, row, index) {
        	equiId = row.parkId;
        	btnId = row.boxNo;
        	$('#modalLabel').text("开闸确认");
			$('#modalText').text("确定要进行开闸么？");
			$('#modalCancel').show();
			$('#modalConfirm').show();
			$('#myModal').modal('show');
        	var param='parkId=' + equiId + '&boxNo='+btnId+'&channelNo='+row.channelNo+"&id="+row.id;
        	$('#modalConfirm').click(function() {
    		$('#myModal').modal('hide');
			Record(param);
		});
        }
    };
  var equiId;
 var btnId;
 $(function () {
	 	var parkId = $('#parkId').val();
	 	var param = "parkId=" + parkId;
	 	
	 	$('#myDatatable').bootstrapTable('refresh',{
                 url: '${request.contextPath!}/signoCarControl/getList?'+param,
			});
			 $('.chzn-select-deselect').chosen({});
			 $('#parkId').change(function(){
             param ="parkId=" + $('#parkId').val();
			$('#myDatatable').bootstrapTable('refresh',{
                 url: '${request.contextPath!}/signoCarControl/getList?'+param,
			});
    });
    	
	});
		function Record(param) {
		$("#wait-info").show();
		$
				.post(
						"${request.contextPath!}/signoCarControl/Result",param,
						
						function(e) {
							if (e == true) {
								$('#myModal').modal('hide');
								$("#failure-info").hide();
								$("#wait-info").hide();
								$("#success-info").show();
								setTimeout(function() {
									$("#success-info").hide();
								}, 3000);
							} else {
							$('#myModal').modal('hide');
								$("#failure-info").show();
								$("#success-info").hide();
								$("#wait-info").hide();
								setTimeout(function() {
									$("#failure-info").hide();
								}, 3000);
							}
						});
	}

 </script>
 
