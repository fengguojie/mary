<style type="text/css">
	table{
        border-collapse:collapse;
        margin-bottom:10px;
        width: 100%;
    }
    table tr{
        height: 30px;
        background: white;
    }
    .name{
        color: #ffffff;
        font-weight:700;
        text-align: center;
        width:150px;
        background: #8A93B2;
    }
    .data{
        width:250px;
        display: inline-block;
       	margin-left: -210px;
    }
    .dn{
        color: #767676;
        text-align: left;
        display: inline-block;
    }
    .font{
    	color: red;
    	font-size: 20px;
    }
    
</style>
<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
 <script type="text/javascript">
 	var colentpage;
 </script>
<section class="content-header">
	<ul class="nav nav-tabs" role="tablist">
	  <li role="presentation" class="active"><a id="flag" href="#home" role="tab" data-toggle="tab" onClick="getMapData('A');">车场平面图</a></li>
	  <li role="presentation"><a id="flag2" href="#home2" role="tab" data-toggle="tab" ">明细控制</a></li>
	</ul>
</section>
<section class="content">
	<ul class="nav nav-tabs" role="tablist" id="myTab">
	  <li role="presentation" class="active"><a href="#home" role="tab" data-toggle="tab" onClick="getMapData('A');">车场一层</a></li>
	  <li role="presentation"><a href="#home" role="tab" data-toggle="tab"  onClick="getMapData('B');">车场二层</a></li>
	</ul>
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" id="home"> <!-- 车场平面图页面 -->
	    	<div id="main" class="main" style="height: 800px; cursor: default; background-color: rgb(238, 238, 238);"></div>
    	</div>
		<div class="tab-pane" id="home2"><!-- 明细控制页面 -->
		  	<section class="content">
				<div class="row">
					<section class="col-lg-12 connectedSortable">
						<div class="box box box-primary">
							<div class="box-header">
								<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
							</div>
							<div class="box-body">
								<table id="myDatatable" data-toggle="table"
									data-url="${request.contextPath!}/map/map/detailControl" data-show-refresh="true" data-show-toggle="true" data-show-columns="true"
									data-side-pagination="server" data-pagination="true" data-search="true"  
									data-page-list="[10, 25, 50, 100, 200]" data-field-text="按照车位或车锁号查询">
									<thead>
										<tr>
											<th data-checkbox="true"></th>
											<th data-field="spaceNo" data-formatter="" data-events=""data-align="left" data-sortable="true">车位号码</th>
											<th data-field="slaveId" data-align="left" data-sortable="true">车锁编号</th>
											<th data-field="carlockStatus" data-align="left" data-sortable="true">车锁状态</th>
											<th data-field="voltage" data-align="left" data-sortable="true">车锁电量</th>
											<th data-field="gatewayNo" data-align="left" data-sortable="true">网关编号</th>
											<th data-field="gatewayStatus" data-align="left" data-sortable="true">网关状态</th>
											<th data-formatter="operateFormatter" data-events="operateEvents"data-align="left">开关指定</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</section>
				</div>
			</section>
      	</div><!-- 明细控制页面 结束 -->
	</div>
	<button id="btn" type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" style="display:none"></button>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
      	 <div id="success" style="background-color:#00A65A; color:white; line-height:30px; width:100%;margin:0 auto; border-radius:3px;text-align: center;">操作成功</div>
         <div id="failure" style="background-color:#F56954; color:white; line-height:30px; width:100%;margin:0 auto; border-radius:3px;text-align: center;">操作失败</div>
         <div id="wait" style="background-color:#00A65A; color:white; line-height:30px; width:100%;margin:0 auto; border-radius:3px;text-align: center;">正在操作，请稍后...</div>
         
        <h4 class="modal-title" id="myModalLabel">车位控制</h4>
      </div>
      <div class="modal-body" style="height:230px">
      	  <table border="0" class="col-lg-11">
			    <tr>
			        <td class="bg-style">
				        <span class="" >车位编号：
				        	<font class="font-style" id="name"></font>
				        </span>
			        </td>
			        <td class="bg-style">
				         <span class="">车锁编号：
				         	<font class="font-style" id="slaveId"></font>
				         </span>
			        </td>
			    </tr>
			    <tr>
			        <td class="bg-style">
			         	<span class="">车锁状态：
			         		<font class="font-style" id="status"></font>
			         	</span>
			        </td>
			        <td class="bg-style">
			         	<span class="">车锁控制：
			         		<button type="button" id="lockController" class="btn btn-primary" onClick="control()">下降</button>
			         	</span>
			        </td>
			    </tr>
	 	  </table>
	 	  <div style="width: 100%;height: 1px;background: red;float: left;margin-bottom: 20px;"></div>
	 	  <table border="0" class="col-lg-11">
			    <tr>
			        <td class="bg-style">
				        <span class="" >网关编号：
				        	<font class="font-style" id="gatewayNo"></font>
				        </span>
			        </td>
			        <td class="bg-style">
				         <span class="">配置串口：
				         	<font class="font-style" id="serial"></font>
				         </span>
			        </td>
			        <td class="bg-style">
			         	<span class="">网关状态：
			         		<font class="font" id="gatewayStatus"></font>
			         	</span>
			        </td>
			    </tr>
	 	  </table>
	 	  <div style="width: 100%;height: 1px;background: red;float: left;margin-bottom: 20px;"></div>
	 	  <table border="0" class="col-lg-11">
			    <tr>
			        <td class="bg-style">
				        <span class="" >车锁电量：
				        	<font class="font-style" id="voltage"></font>
				        </span>
			        </td>
			    </tr>
	 	  </table>
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
      </div>
    </div>
  </div>
</div>
	<input type="hidden"   class="form-control"  id="carsData" name='carsData' value="${carsData!}"   />
	<input type="hidden"   class="form-control"  id="carsName" name='carsName'  value="${carsName!}"  />
	<input type="hidden"   class="form-control"  id="noCarsData" name='noCarsData'  value="${noCarsData!}"  />
	<input type="hidden"   class="form-control"  id="noCarsName" name='noCarsName' value="${noCarsName!}"   />

</section>
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/chosen/chosen.jquery.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${request.contextPath!}/statics/js/tab.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/ECharts/dist/echarts.js"/>

<script type="text/javascript">
	$(function() {
		$("#success").hide();
		$("#failure").hide();
		$("#wait").hide();
	});

 $("#flag2").click(function(){//点击控制明细时，隐藏车场一层、二层
	 	$("#myTab").hide();
 });
 $("#flag").click(function(){
	 	$("#myTab").show();
 });
 
 $(function(){
   $('#myTab a:first').tab('show');
   getMapData("A");
		
 });

function getMapData(pic){
	if(pic == 'A'){
		$('#myTab a:first').tab('show');
	}
	$.post('${request.contextPath!}/map/map/getMapData','pic='+pic,
		function(data){
			if (data.result) {
			
				$('#carsData').val(data.data.carsData);
				$('#carsName').val(data.data.carsName);
				$('#noCarsData').val(data.data.noCarsData);
				$('#noCarsName').val(data.data.noCarsName);
				mapDate(pic);
			}else{
				alert("网络出错1");
			}
		});	
}

 function mapDate(pic){
	var carsData = eval("(" + $('#carsData').val() + ")");
	var carsName = eval("(" + $('#carsName').val() + ")");
	var noCarsData = eval("(" + $('#noCarsData').val() + ")"); 
	var noCarsName = eval("(" + $('#noCarsName').val() + ")");
	//alert(JSON.stringify(noCarsName));

    require.config({
        paths: {'echarts': './statics/js/plugins/ECharts/dist',}
    });
	require(
		[
			'echarts',
			'echarts/chart/map' 
		],
	function (ec) {
		var myChart = ec.init(document.getElementById('main'));
        require('echarts/util/mapData/params').params.baiduBuilding = {
		    getGeoJson: function (callback) {
		        $.ajax({
		            url: "./statics/svg/"+pic+".svg",
		            dataType: 'xml',
		            success: function(xml) {
		                callback(xml)
		            }
		        });
		    }
		}
		option = {
		    backgroundColor:'#eee',
		    title : {
		        text : '',
		        textStyle: {
		            color: '#000'
		        }
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: '{b}',
		    },
		    color: ['rgba(255, 0, 0, 1)', 'rgba(0, 255, 0, 1)'],
		    legend: {
		        data: ['有车', '无车']
		    },
		    series : [
		        {
		            name: '有车',
		            type: 'map',
		            mapType: 'baiduBuilding',
		            roam:true,
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data: [],
		            geoCoord:carsData,
		            markPoint : {
		                symbolSize : 3,
		                data : carsName,
		            },
		           
		        },
		         {
		            name: '无车',
		            type: 'map',
		            mapType: 'baiduBuilding',
		            roam:true,
		            itemStyle:{
		                normal:{label:{show:true}},
		                emphasis:{label:{show:true}}
		            },
		            data: [],
		            geoCoord:noCarsData,
		            markPoint : {
		                symbolSize : 3,
		                label:{
		                	normal:{
		                		show:true,
		                		position:'top',
		                		formatter:'{b}',
		                	},
		                },
		                data : noCarsName
		            },
		           
		        },
		       
		    ]
		};
		// 为echarts对象加载数据 
		myChart.setOption(option);
		
		var ecConfig = require('echarts/config');
		myChart.on(ecConfig.EVENT.CLICK, eConsole);
		function eConsole(param) {
			if(param.name != undefined && param.name != null && param.name != ""){
			   dialog(param.name);
			}
		}
	 });

 }
 function dialog(name){
 		//alert(name);
 		$.post('${request.contextPath!}/map/map/lockDetail','name='+name,function(data){
				if (data.result) {
					var lock = data;// 原data.data
					
					$('#slaveId').html(lock.slaveId);
					$('#gatewayNo').html(lock.gatewayNo);
					$('#serial').html(lock.serial);
					$('#gatewayStatus').html(lock.gatewayStatus);
					if(lock.switchStatus==1){
						$('#status').html("平躺");//落下
						$('#lockController').html("上升");
						$('#lockController').css('background-color','red');
						$('#lockController').css('border-color','red');
					}else{
						$('#status').html("竖起");
						$('#lockController').html("下降");
						$('#lockController').css('background-color','#3c8dbc');
						$('#lockController').css('border-color','#3c8dbc');
					}
					var voltage = lock.voltage;
					if(voltage>5){
						electric = ((voltage-5.0)*0.8+0.2)*100;
					}else{
						electric = (voltage-4.0)*0.2*100;
					}
					if(electric<0){
					   electric = -electric;
					}
					$('#voltage').html(electric.toFixed(2)+"%");
					
				}else{
					alert("网络出错");
				}
		});	
 		$('#name').html(name);
 		$('#btn').click();
 }
 //对话框中，操作车位锁上升、下降 。操作之后，无论结果如何，刷新整个页面。
 function control(){
 	var lockController = $('#lockController').html();
 	var action;
 	if(lockController == '下降'){
 		action = 1;//下降
 	}else if(lockController == '上升'){
 		action = 2;
 	}
	var slaveId = $('#slaveId').html();
	$("#wait").show();
	$.post('${request.contextPath!}/baseInfo/operator/newoperate','action='+action+'&slaveId='+slaveId,
		function(data){
			if (data==0 || data==1) {
				if(action == 1){
					$('#lockController').html("上升");
					$('#status').html("平躺");
					$('#lockController').css('background-color','red');
					$('#lockController').css('border-color','red');
				}else if(action == 2){
					$('#lockController').html("下降");
					$('#status').html("竖起");
					$('#lockController').css('background-color','#3c8dbc');
					$('#lockController').css('border-color','#3c8dbc');
				}
				$("#failure").hide();
				$("#wait").hide();
				$("#success").show();
			    setTimeout(function(){$("#success").hide();},3000);
			    
			}else{
				$("#success").hide();
				$("#wait").hide();
			    $("#failure").show();
			    setTimeout(function(){$("#failure").hide();},3000);
			}
			//$('#myModal').modal('hide');
			getMapData("A");
	});	
 }


	function operateFormatter(value, row, index) {
		return [<@SecuValidate resource="com.chinadovey.parking.acl.BaseCompany">'<a class="delete" href="javascript:;">控制&nbsp;</a>'</@SecuValidate>].join('');
	}
	function operateFormatter(value, row, index) {
			var btnClass;
			var btnText;
		    if(row.carlockStatus=='平躺'){
				btnClass = 'btn-success';	
				btnText = ' 上升 '	
			}else{
				btnClass = 'btn-danger';
				btnText = ' 下降 ';
			}
			return ['<button id=ctrl_'+row.slaveId+' class="btn-operate btn '+btnClass+'">&nbsp;'+btnText+'&nbsp; </button>'].join('');
	}
	window.operateEvents = {
	        'click .btn-operate': function (e, value, row, index) {
	        	equiId = row.slaveId;
	        	btnId = 'ctrl_'+equiId;
	        	if(row.carlockStatus=='平躺'){
	        		operate(equiId,2);
	        	}else{
	        		operate(equiId,1);
	        	}
	        }
	};
	function operate(equiId , status){
	    $("#wait-info").show();
	    $('#ctrl_'+equiId).attr('disabled','disabled');
	 	$.post('${request.contextPath!}/baseInfo/operator/newoperate','slaveId=' + equiId + '&action='+status , function(data){
			if(data==0){
			    $("#wait-info").hide();
				$("#failure-info").hide();
				$("#success-info").show();
				$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/map/map/detailControl'});
				setTimeout(function(){$("#success-info").hide();},5000);
			}else if(data==1){
				$("#wait-info").hide();
				$("#failure-info").hide();
				$("#success-info").show();
				$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/map/map/detailControl'});
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
 
 
 </script>
