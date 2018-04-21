<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<section class="content-header">
	<h1>车位管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>车位管理</a></li>
		<li><a href="#" class="active">车位管理</a></li>
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
					  		<input style="float: right;left: -78%;position: relative;top: 6px;" id="file" name="file" type="file" ></input>
    						<button id="inform" type="button" class="btn btn-danger"  style="position: relative;left: 11%;top: 0px;">批量导入</button>
						</p>
					</@SecuValidate>
                    <table id="myDatatable" data-toggle="table" data-url="${request.contextPath!}/baseInfo/carSpace/getList"
						data-side-pagination="server" data-show-refresh="true" data-show-toggle="true" data-show-columns="true"
						data-pagination="true" data-search="true"  data-field-text="根据车位号搜索"data-page-list="[10, 25, 50, 100, 200]">
					<thead>
						<tr>
							<th data-field="parkId" data-align="left" data-formatter="parkingFormatter" data-sortable="true">车场名称</th>
							<th data-field="spaceNo" data-align="left" data-sortable="true">车位编号</th>
							<th data-field="slaveId" data-align="left" data-formatter="slaveIdFormatter" data-sortable="true">车锁编号</th>
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
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/bootstrap-table/bootstrap-table.js"></script>
<script src="${request.contextPath!}/statics/js/ajaxfileupload.js"></script>

<script type="text/javascript">

	function inform(){
		$("#infoUp").text("请点击选择文件！");
	        $("#infoUp").show();
	        setTimeout(function() {
				$("#infoUp").hide();
			}, 1500);
	};

	var id;
 	$(function () {
 		//跳转到添加页面
 		$('.add').click(function(){
			var href = '${request.contextPath!}/baseInfo/carSpace/add';
	    	$("#main-content").zrPageRefresh(href);
		});
		//删除
 		$('#modalConfirm').click(function(){
			deleteRecord(id);
 		});
 		
 		$('#inform').click(function(){
 		 	var upLoad = $('#file').val();
 			if(upLoad != "" && upLoad != undefined){
 				$("#infoUp").hide();
 				var file = $("#file");
				var fileName = file.val();//得到文件名字及所在路径
			    var ldot = fileName.lastIndexOf(".");//得到文件类型前面有多少位
				var type = fileName.substring(ldot + 1);//得到文件类型
						
			    $.ajaxFileUpload({
			      url: "${request.contextPath!}/baseInfo/carSpace/upload",
			      secureuri:false,  
			      fileElementId:'file',//file标签的id  
			      data:{type:type},//一同上传的数据  
			      dataType: 'application/json',//返回数据的类型  
			      
			      success : function(data, status) {
			      	data = data.substring(data.indexOf("{"),data.indexOf("}") + 1 );  
			   	    var obj = jQuery.parseJSON(data);
			   	    
			   		alert(obj.fileName);
			   		$('#myDatatable').bootstrapTable('refresh',
			   			{url: '${request.contextPath!}/baseInfo/carSpace/getList'});
			   	  },
			      error : function(data,status,e) {
				  	alert("上传excel失败");
				  	$("#success-info").hide();
				    $("#failure-info").show();
				    setTimeout(function(){$("#failure-info").hide();},3000);
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
 		
 	});
/**
 * 显示表格中操作（查看、修改、删除）字样
 * @param value
 * @param row
 * @param index
 * @returns
 */
function operateFormatter(value, row, index) {
   return [
     	<@SecuValidate resource="com.chinadovey.parking.acl.BaseCarEdit">'<a class="edit" href="javascript:;">修改&nbsp;</a>',</@SecuValidate>
      	<@SecuValidate resource="com.chinadovey.parking.acl.BaseCarDele">'<a class="delete" href="#model">删除</a>'</@SecuValidate>
   ].join('');
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

function slaveIdFormatter(value, row, index) {
	var slaveId = row.slaveId;
	if(value == 0){
		return ['不分配车位锁'].join('');
	}else{
		return [slaveId].join('');
	}

}  
 
window.operateEvents = {
    'click .detail': function (e, value, row, index) {
		var href = '${request.contextPath!}/baseInfo/carSpace/detail?id='+row.id +'&page='+colentpage;
		if (href != "" && href != "#") {
			$("#main-content").zrPageRefresh(href);
		}
		return false;
    },    
        
    'click .edit': function (e, value, row, index) {
		var href = '${request.contextPath!}/baseInfo/carSpace/edit?id='+row.id +'&page='+colentpage;
		if (href != "" && href != "#") {
			$("#main-content").zrPageRefresh(href);
		}
		return false;
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
    
function upload() {
	var file = $("#file");
	var fileName = file.val();//得到文件名字及所在路径
    var ldot = fileName.lastIndexOf(".");//得到文件类型前面有多少位
	var type = fileName.substring(ldot + 1);//得到文件类型
			
    $.ajaxFileUpload({
      url: "${request.contextPath!}/baseInfo/carSpace/upload",
      secureuri:false,  
      fileElementId:'file',//file标签的id  
      data:{type:type},//一同上传的数据  
      dataType: 'application/json',//返回数据的类型  
      
      success : function(data, status) {
      	data = data.substring(data.indexOf("{"),data.indexOf("}") + 1 );  
   	    var obj = jQuery.parseJSON(data);
   		alert(obj.fileName);
   		$('#myDatatable').bootstrapTable('refresh',
   			{url: '${request.contextPath!}/baseInfo/carSpace/getList'});
   	  },
      error : function(data,status,e) {
	  	alert("上传excel失败");
      }
      
    });
}

 /**
 * 删除操作(单个)
 */
function deleteRecord(id){
	$.post('${request.contextPath!}/baseInfo/carSpace/delete','id='+id,
		function(e){
			if(e.result=='SUCCESS'){
			    $('#myModal').modal('hide');
				$("#failure-info").hide();
				$("#success-info").show();
				//删除成功之后刷新表格
				$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/carSpace/getList'});
				setTimeout(function(){$("#success-info").hide();},3000);
			}else{
				$("#failure-info").show();
			    $("#success-info").hide();	
			    setTimeout(function(){$("#failure-info").hide();},3000);
			}
		});
}

    
</script>
 
