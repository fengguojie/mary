<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<section class="content-header">
	<h1>车锁管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>车锁管理</a></li>
	</ol>
</section>
<section class="content">
	<div class="row">
         <section class="col-lg-12 connectedSortable">
            <div class="box box box-primary">
            	<div style="background-color: red; width: 40%;position: relative;left: 27%;;">
            	<span id="infoUp" style="position: relative;left: 40%;color: white;"></span></div>
                <div class="box-header">
                   <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                </div>
                <div class="box-body">
                	<@SecuValidate resource="com.chinadovey.parking.acl.BaseCarCret">
                   		<p>
                       		<button type="button" class="btn btn-success add">&nbsp;添加&nbsp;</button>&nbsp;&nbsp;&nbsp;&nbsp;
							<input style="float: right;left: -81%;position: relative;top: 6px;" id="file" name="file" type="file" ></input>
    						<button id="inform" type="button" class="btn btn-danger" style="position: relative;left: 11%;top: 0px;">批量导入</button>
    						<button id="config" type="button" class="btn btn-danger" 
    							style="position: relative;left: 11%;top: 0px;background-color: #0099FF;border-color: #0099FF;">批量配置</button> 
    						<!--<button id="download" type="button" class="btn btn-danger" 
    							style="position: relative;left: 11%;top: 0px;">下载批量导入模板</button> -->
						</p>
					</@SecuValidate>		
                    <table id="myDatatable" data-toggle="table" data-url="${request.contextPath!}/baseInfo/carLock/getList" data-side-pagination="server" 
                    	data-show-refresh="true" data-show-toggle="true" data-show-columns="true"
						data-pagination="true" data-search="true"  data-field-text="根据车锁编号搜索" data-page-list="[10, 25, 50, 100, 200]">
					<thead>
						<tr>
							<th data-field="parkId" data-align="left" data-formatter="parkingFormatter" data-sortable="true">车场名称</th>
							<th data-field="slaveId" data-align="left" data-sortable="true">车锁编号</th>
							<th data-field="gatewayNo" data-align="left" data-sortable="true">网关编号</th>
							<th data-field="spaceName" data-align="left" data-formatter="" data-sortable="true">车位名称</th>
							<th data-field="isauto" data-align="left" data-formatter="isautoFormatter" data-sortable="true">关闭类型</th>
							<th data-field="runStatus" data-align="left" data-formatter="runStatusFormatter" data-sortable="true">车锁电量</th>
							<th data-field="delay" data-align="left" data-sortable="true">延时</th>
							<th data-field="configStatus" data-align="left" data-formatter="configFormatter" data-sortable="true">配置状态</th>
							<th data-formatter="operateFormatter" data-events="operateEvents"data-align="left">操作</th>
						</tr>
					</thead>
				</table>
                </div>
            </div>
        </section>
	</div>
</section>
<script type="text/javascript">
	var Newpage=${Newpage!'1'}
	var colentpage=Newpage;
</script>
<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/dlg-info.ftl">

<script type="text/javascript" src="${request.contextPath!}/statics/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${request.contextPath!}/statics/js/filestyle.min.js"></script>
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript">
	var id;
 	$(function () {
	    var isauto = $('#isauto').val();
	  	var param = "isauto=" + isauto;
		$('#myDatatable').bootstrapTable('refresh',{
			url: "${request.contextPath!}/baseInfo/carLock/getList?"+param
		});
		//跳转到添加页面
		$('.add').click(function(){
			var href = '${request.contextPath!}/baseInfo/carLock/add';
		    $("#main-content").zrPageRefresh(href);
		});
		//批量导入
		$('#inform').click(function(){
 		 	var upLoad = $('#file').val();
 			if(upLoad != "" && upLoad != undefined){
 				$("#infoUp").hide();
 				var file = $("#file");
			    var fileName = file.val();//得到文件名字及所在路径
			    var ldot = fileName.lastIndexOf(".");//得到文件类型前面有多少位
			    var type = fileName.substring(ldot + 1);//得到文件类型
				    $.ajaxFileUpload({
				   		url: "${request.contextPath!}/baseInfo/carLock/upload",
				        secureuri:false,  
				        fileElementId:'file',//file标签的id  
				        data:{type:type},//一同上传的数据  
				        dataType: 'application/json',//返回数据的类型  
				        success : function(data, status) {
				            data = data.substring(data.indexOf("{"),data.indexOf("}") + 1 );  
					       	var obj = jQuery.parseJSON(data);
					       	if(obj.code == 0){
					       		var carLockNo = obj.slaveId;
					       		alert(carLockNo+"车锁编号重复");
					       		$("#success-info").hide();
							    $("#failure-info").show();
							    setTimeout(function(){$("#failure-info").hide();},3000);
					       	}else if(obj.code == 1){
					       		var number = obj.spaceNo;
					       		alert(number+"车位编号重复");
					       		$("#success-info").hide();
							    $("#failure-info").show();
							    setTimeout(function(){$("#failure-info").hide();},3000);
					       	}else if(obj.code == 2){
					       		var spaceNo = obj.number;
					       		alert(spaceNo+"车位编号不存在");
					       		$("#success-info").hide();
							    $("#failure-info").show();
							    setTimeout(function(){$("#failure-info").hide();},3000);
					       	}
					       	alert(obj.fileName);
					       	$("#success-info").hide();
						    $("#failure-info").show();
						    setTimeout(function(){$("#failure-info").hide();},3000);
					       	$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/carLock/getList'});
				   	  },
				      error : function(data,status,e) {
					      alert("上传excel失败");
				     }
				    });
 				
 			}else{
 				$("#infoUp").text("请点击选择文件！");
 				$("#infoUp").show();
		        setTimeout(function() {
					$("#infoUp").hide();
				}, 1500);
 			}
 			
 		});
 		//批量配置
 		$('#config').click(function(){
 			var upLoad = $('#file').val();
 			if(upLoad != "" && upLoad != undefined){
 				$("#infoUp").hide();
 				var file = $("#file");
				var fileName = file.val();//得到文件名字及所在路径
			    var ldot = fileName.lastIndexOf(".");//得到文件类型前面有多少位
				var type = fileName.substring(ldot + 1);//得到文件类型
						
			    $.ajaxFileUpload({
			      url: "${request.contextPath!}/baseInfo/carLock/banchConfig",
			      secureuri:false,  
			      fileElementId:'file',//file标签的id  
			      data:{type:type},//一同上传的数据  
			      dataType: 'application/json',//返回数据的类型  
			      
			      success : function(data, status) {
			      	data = data.substring(data.indexOf("{"),data.indexOf("}") + 1 );  
			   	    var obj = jQuery.parseJSON(data);
			   	    if(obj.code == 0){
			       		var carLockNo = obj.slaveId;
			       		alert(carLockNo+"车锁编号重复");
			       		$("#success-info").hide();
					    $("#failure-info").show();
					    setTimeout(function(){$("#failure-info").hide();},3000);
			       	}else if(obj.code == 1){
			       		alert("配置失败!");
			       		$("#success-info").hide();
					    $("#failure-info").show();
					    setTimeout(function(){$("#failure-info").hide();},3000);
			       	}
			   		alert(obj.fileName);
			   		$("#success-info").hide();
				    $("#failure-info").show();
				    setTimeout(function(){$("#failure-info").hide();},3000);
			   		$('#myDatatable').bootstrapTable('refresh',
			   			{url: '${request.contextPath!}/baseInfo/carLock/getList'});
			   	  },
			      error : function(data,status,e) {
				  	alert("上传excel失败");
			      }
			      
			    });
 				
 			}else{
 				$("#infoUp").text("请点击选择文件！");
 				$("#infoUp").show();
		        setTimeout(function() {
					$("#infoUp").hide();
				}, 1500);
 			}
 		});
		
		//下载批量导入，模板
		$('#download').click(function(){
 		 	$("#download").attr("disabled",true);
	  					location.href="${request.contextPath!}/file/downloadp?path="+"excels/statistics"+ "&fileName=carlock&fileType="+"xls";
	  				$("#download").attr("disabled",false);
 			
 			});
		
	//删除
 	$('#modalConfirm').click(function(){
		deleteRecord(id);
 	});
		
 });
	
	
/**
 * 开关状态
 * @param value
 * @param row
 * @param index
 * @returns
 */
function switchFormatter(value, row, index) {
   if(value==1) return ['开'].join('');
   else if(value==2) return ['关'].join('');
}

function configFormatter(value, row, index) {
   if(value==0) return ['已配置串口A'].join('');
   else if(value==1) return ['已配置串口B'].join('');
   else if(value==2) return ['未配置'].join('');
}

function isautoFormatter(value, row, index) {
  if(value==3) return ['自动关闭'].join('');
  else if(value==4) return ['手动关闭'].join('');
}
/**
 * 车锁电量
 *
 * @param value
 * @param row
 * @param index
 * @returns
 */
function runStatusFormatter(value, row, index) {
	if(value == 0){
		return ['正常'].join('');
	}else if(value == 1){
		return ['电量低'].join('');
	}else if(value == 2){
		return ['挡臂故障'].join('');
	}else if(value == 5){
		return ['离线'].join('');
	}

}

/**
 * 车场格式
 * @param value
 * @param row
 * @param index
 * @returns
 */
function parkingFormatter(value, row, index) {
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
/**
 * 车位名称
 *
 * @param value
 * @param row
 * @param index
 * @returns
 */
function spaceNameFormatter(value, row, index) {
 	var data = value;
 	var spaceNo = row.slaveId;
 	var spaceName;
 	<#if carSpaces??>
		<#list carSpaces as car>
			if(spaceNo == ${car.slaveId!}){
				spaceName = "${car.spaceName!'暂无车位名称'}";
			}
		</#list>
	</#if>
  	return [spaceName].join('');
}
   
/**
 * 运行状态
 * @param value
 * @param row
 * @param index
 * @returns
 */
function runFormatter(value, row, index) {
   if(value==1) return ['正常'].join('');
   else if(value==2) return ['故障'].join('');
}
		    
/**
 * 显示表格中操作（查看、修改、删除）字样
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operateFormatter(value, row, index) {
      var edit = '<@SecuValidate resource="com.chinadovey.parking.acl.BaseCarEdit"> <a class="edit" href="javascript:;">修改&nbsp;</a></@SecuValidate>';
      var auto = '<@SecuValidate resource="com.chinadovey.parking.acl.BaseCarEdit"> <a class="auto" href="javascript:;">自动关&nbsp;</a></@SecuValidate>';
      var hand = '<@SecuValidate resource="com.chinadovey.parking.acl.BaseCarEdit"> <a class="hand" href="javascript:;">手动关&nbsp;</a></@SecuValidate>';
      var gconfig = '<@SecuValidate resource="com.chinadovey.parking.acl.BaseCarEdit"> <a class="gconfig" href="javascript:;">配置&nbsp;</a></@SecuValidate>';
      var del = '<@SecuValidate resource="com.chinadovey.parking.acl.BaseCarDele"> <a class="delete" href="#model">删除</a></@SecuValidate>';
      if(3 == row.isauto){
          return [edit,hand,gconfig,del].join('');
      }else if(4 == row.isauto){
          return [edit,auto,gconfig,del].join('');
      }
			
}
 
window.operateEvents = {
  	'click .edit': function (e, value, row, index) {
		var href = '${request.contextPath!}/baseInfo/carLock/edit?id='+row.id+'&page='+colentpage;
		if (href != "" && href != "#") {
			$("#main-content").zrPageRefresh(href);
		}
		return false;
    },
    
    'click .gconfig': function (e, value, row, index) {
			var href = '${request.contextPath!}/baseInfo/carLock/configView?id='+row.id+'&page='+colentpage;
			if (href != "" && href != "#") {
				$("#main-content").zrPageRefresh(href);
			}
			return false;
    },
    
    'click .auto': function (e, value, row, index) {
        $("#wait-info").show();
    	$.post('${request.contextPath!}/baseInfo/carLock/autoOrHandClose','slaveId='+row.slaveId,function(e){
		if(e.result=='SUCCESS'){
		    $('#myModal').modal('hide');
		    $("#wait-info").hide();
			$("#failure-info").hide();
			$("#success-info").show();
			$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/carLock/getList'});
			setTimeout(function(){$("#success-info").hide();},3000);
		}else{
		    $("#wait-info").hide();
			$("#failure-info").show();
		    $("#success-info").hide();	
		    setTimeout(function(){$("#failure-info").hide();},3000);
		}
	  });
    },
    
    'click .hand': function (e, value, row, index) {
        $("#wait-info").show();
    	$.post('${request.contextPath!}/baseInfo/carLock/autoOrHandClose','slaveId='+row.slaveId,function(e){
		if(e.result=='SUCCESS'){
		    $('#myModal').modal('hide');
		    $("#wait-info").hide();
			$("#failure-info").hide();
			$("#success-info").show();
			$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/carLock/getList'});
			setTimeout(function(){$("#success-info").hide();},3000);
		}else{
		    $("#wait-info").hide();
			$("#failure-info").show();
		    $("#success-info").hide();	
		    setTimeout(function(){$("#failure-info").hide();},3000);
		}
	  });
    },
    
    'click .delete': function (e, value, row, index) {
    	id = row.id;
    	$('#modalLabel').text("删除确认");
		$('#modalText').text("确定要删除选择的记录吗？");
		$('#modalCancel').show();
		$('#modalConfirm').show();
		$('#myModal').modal('show');
    }     
        
};
    
/**
 * 删除操作(单个)
 */
function deleteRecord(id){
		$.post('${request.contextPath!}/baseInfo/carLock/delete','id='+id,function(e){
		if(e.result=='SUCCESS'){
		    $('#myModal').modal('hide');
			$("#failure-info").hide();
			$("#success-info").show();
			//删除成功之后刷新表格
			$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/carLock/getList'});
			setTimeout(function(){$("#success-info").hide();},3000);
		}else{
			$("#failure-info").show();
		    $("#success-info").hide();	
		    setTimeout(function(){$("#failure-info").hide();},3000);
		}
	});
}

function upload() {
  	var file = $("#file");
    var fileName = file.val();//得到文件名字及所在路径
    var ldot = fileName.lastIndexOf(".");//得到文件类型前面有多少位
    var type = fileName.substring(ldot + 1);//得到文件类型
	    $.ajaxFileUpload({
	   		url: "${request.contextPath!}/baseInfo/carLock/upload",
	        secureuri:false,  
	        fileElementId:'file',//file标签的id  
	        data:{type:type},//一同上传的数据  
	        dataType: 'application/json',//返回数据的类型  
	        success : function(data, status) {
	            data = data.substring(data.indexOf("{"),data.indexOf("}") + 1 );  
		       	var obj = jQuery.parseJSON(data);
		       	if(obj.code == 0){
		       		var carLockNo = obj.slaveId;
		       		alert(carLockNo+"车锁编号重复");
		       	}else if(obj.code == 1){
		       		var number = obj.spaceNo;
		       		alert(number+"车位编号重复");
		       	}else if(obj.code == 2){
		       		var spaceNo = obj.number;
		       		alert(spaceNo+"车位编号不存在");
		       	}
		       	alert(obj.fileName);
		       	$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/carLock/getList'});
	   	  },
	      error : function(data,status,e) {
		      alert("上传excel失败");
	     }
	    });
	}
    
 </script>
 
