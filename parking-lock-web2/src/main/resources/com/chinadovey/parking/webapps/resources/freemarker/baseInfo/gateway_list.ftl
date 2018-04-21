<link rel="stylesheet" href="${request.contextPath!}/statics/css/bootstrap-table/bootstrap-table.css"/>
<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>网关管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>网关管理</a></li>
		<li><a href="#" class="active"></a></li>
	</ol>
</section>
<section class="content">
	<div class="row">
    	<section class="col-lg-12 connectedSortable">
            <div class="box box box-primary">
                <div class="box-header">
                    <#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
                </div>
                <div class="box-body">
                   <p>
					  <button type="button" class="btn btn-success add">&nbsp;添加&nbsp;</button>
					</p>
                    <table id="myDatatable" data-toggle="table" data-url="${request.contextPath!}/baseInfo/gateway/getList"
						data-side-pagination="server" data-show-refresh="true" data-show-toggle="true" data-show-columns="true"
						data-pagination="true" data-search="true" data-field-text="网关编号、网关名称搜索" data-page-list="[10, 25, 50, 100, 200]">
					<thead>
						<tr>
							<th data-field="parkId" data-align="left" data-formatter="parkingFormatter" data-sortable="true">车场名称</th>
							<th data-field="gatewayNo" data-align="left" data-sortable="true">网关编号</th>
							<th data-field="gatewayName" data-align="left" data-sortable="true">网关名称</th>
							<th data-field="gatewayStatus" data-align="left" data-formatter="statusFormatter" data-sortable="true">设备状态</th>
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
<script type="text/javascript">
 	var id;
 	$(function () {
	 	//跳转到添加页面
	 	$('.add').click(function(){
			var href = '${request.contextPath!}/baseInfo/gateway/add';
		    $("#main-content").zrPageRefresh(href);
		});
		//删除
		$('#modalConfirm').click(function(){
			deleteRecord(id);
		});
		
 	});
 	
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
	 * 机构格式
	 * @param value
	 * @param row
	 * @param index
	 * @returns
	 */
	function companyFormatter(value, row, index) {
		 var companyNo = row.companyNo;
		 var name;
		 <#if companys??>
			<#list companys as company>
				if(companyNo == ${company.companyNo!}){
					name = "${company.name!'暂无机构信息'}";
				}
			</#list>
		</#if>
		return [name].join('');
	}
	/**
	 * 设备状态
	 *
	 * @param value
	 * @param row
	 * @param index
	 * @returns
	 */
	function statusFormatter(value, row, index) {
       if(value == 1){
       		return ['离线'].join('');
       }else if(value == 0){
       		return ['在线'].join('');
       } 
	}
	
	/**
	 * 显示表格中操作（查看、修改、删除）字样
	 * @param value
	 * @param row
	 * @param index
	 * @returns
	 */
	function operateFormatter(value, row, index) {
          var detail = '<@SecuValidate resource="com.chinadovey.parking.acl.BaseGatewayRank"><a class="detail" href="javascript:;">查看&nbsp;</a></@SecuValidate>';
          var edit = '<@SecuValidate resource="com.chinadovey.parking.acl.BaseGatewayRank"><a class="edit" href="javascript:;">修改&nbsp;</a></@SecuValidate>';
          var gconfig = '<@SecuValidate resource="com.chinadovey.parking.acl.BaseGatewayRank"><a class="gconfig" href="javascript:;">串口配置&nbsp;</a></@SecuValidate>';
          var del = '<@SecuValidate resource="com.chinadovey.parking.acl.BaseGatewayRank"><a class="delete" href="#model">删除</a></@SecuValidate>';
		  return [detail,edit,gconfig,del].join('');
	}
	/**
	 * 操作
	 * 
	 */
	window.operateEvents = {
		//点击查看详情
        'click .detail': function (e, value, row, index) {
			var href = '${request.contextPath!}/baseInfo/gateway/detail?id='+row.id+'&page='+colentpage;
			if (href != "" && href != "#") {
				$("#main-content").zrPageRefresh(href);
			}
			return false;
        },    
        //点击修改
        'click .edit': function (e, value, row, index) {
			var href = '${request.contextPath!}/baseInfo/gateway/edit?id='+row.id+'&page='+colentpage;
			if (href != "" && href != "#") {
				$("#main-content").zrPageRefresh(href);
			}
			return false;
        },
        //串口配置
        'click .gconfig': function (e, value, row, index) {
			var href = '${request.contextPath!}/baseInfo/gateway/config?id='+row.id+'&page='+colentpage;
			if (href != "" && href != "#") {
				$("#main-content").zrPageRefresh(href);
			}
			return false;
        },
        //点击删除
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
		$.post('${request.contextPath!}/baseInfo/gateway/delete','id='+id,
			function(e){
				if(e.result=='SUCCESS'){
				    $('#myModal').modal('hide');
					$("#failure-info").hide();
					$("#success-info").show();
					//删除成功之后刷新表格
					$('#myDatatable').bootstrapTable('refresh',{url: '${request.contextPath!}/baseInfo/gateway/getList'});
					setTimeout(function(){$("#success-info").hide();},3000);
				}else{
					$("#failure-info").show();
				    $("#success-info").hide();	
				    setTimeout(function(){$("#failure-info").hide();},3000);
				}
			});
	}
    
 </script>
 
