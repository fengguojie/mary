<!-- Content Header (Page header) -->
<section class="content-header">
	<h1>停车场管理</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>基础设置</a></li>
		<li><a href="#" class="active">停车场管理</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<div class="row">
             <section class="col-lg-12 connectedSortable">
                            <form class="form-horizontal">
                            <div class="box box box-primary">
                                <div class="box-header">
                                    <h3 class="box-title">停车场详情</h3>
                                </div><!-- /.box-header -->
                                <div class="row">
							        <div class="col-md-3">
							        
							         <div>
								   		 <label for="" class="col-sm-5 control-label">车场图片</label>
											<div class="form-group">
								             	
								                   	<div class="col-sm-12">
														<img id="upload" alt="" style="width: 100%; height: 200px"  
								    						src="${request.contextPath!}/system/${park.picture!'b.png'}/picture">  
								                	</div>
								           </div>
								    </div> 
								    </div> 
							        <div class="col-md-6">
											        <div class="box-body">
				                               			<input type="hidden" value="b.png" name="picture" id="picture"/>
				                               	     
													  <div class="form-group">
													    <label for="" class="col-sm-3 control-label">名称</label>
													    <div class="col-sm-8">
													      <input type="text"  class="form-control" id="name" name='name'  value="${park.name!}" disabled>
													    </div>
													  </div>
													  <div class="form-group">
													    <label for="parkType" class="col-sm-3 control-label">车位类型</label>
													    <div class="col-sm-8">
													     <select type="text"  name="parkType"  class="form-control" disabled>
															<option value="1"<#if (park.parkType!0)==1>selected</#if>>地上</option>
															<option value="2"<#if (park.parkType!0)==2>selected</#if>>地下</option>
															<option value="3"<#if (park.parkType!0)==3>selected</#if>>路边</option>
														 </select>
													    </div>
													  </div>
													   <div class="form-group">
													    <label for="" class="col-sm-3 control-label">车位数量</label>
													    <div class="col-sm-8">
													     <input type="text"  class="form-control"  id="total" name='total'  value="${park.total!}" disabled>
													    </div>
													  </div>
													   <div class="form-group">
													    <label for="type" class="col-sm-3 control-label">在线类型</label>
													    <div class="col-sm-8">
													     <select type="text"  name="type"  class="form-control" disabled>
													     	<option value="0"<#if 0==park.type>selected</#if>>不是</option>
															<option value="1"<#if 1==park.type>selected</#if>>只允许查看数据</option>
															<option value="2"<#if 2==park.type>selected</#if>>在线缴费不可预约</option>
															<option value="3"<#if 3==park.type>selected</#if>>在线缴费可预约</option>
														 </select>
													    </div>
													  </div>
													 <div class="form-group">
														<label  class="col-sm-3 control-label">地理位置</label>
													    <div class="col-sm-8">
										
															<div id="map_container" style="width: 100%; height: 300px; margin: 0;"></div>
														</div>
													</div>
													
													<div class="form-group">
													    <label for="" class="col-sm-3 control-label">地址</label>
													    <div class="col-sm-8">
													    	<input type="text"  class="form-control" id="address" name='address' " value="${park.address!}" disabled>
													    </div>
													    <label for="address" class="error">*</label>
													  </div>
													
													   <div class="form-group">
													    <label for="" class="col-sm-3 control-label">联系人</label>
													    <div class="col-sm-8">
													      <input type="text"  class="form-control" id="linkman" name='linkman'  value="${park.linkman!}" disabled>
													    </div>
													  </div>
													  
													   <div class="form-group">
													    <label for="" class="col-sm-3 control-label">联系电话</label>
													    <div class="col-sm-8">
													      <input type="text"  class="form-control" id="tel" name='tel'  value="${park.tel!}"disabled>
													    </div>
													  </div>
													  
													   <div class="form-group">
													    <label for="" class="col-sm-3 control-label">邮箱</label>
													    <div class="col-sm-8">
													      <input type="text"  class="form-control" id="email" name='email' value="${park.email!}"disabled>
													    </div>
													  </div>
													  <div class="form-group">
													    <label for="" class="col-sm-3 control-label">营业时间</label>
													    <div class="col-sm-8">
													   	   <div class="row">
													   	   	<div class="col-sm-5">
														    	<div class="input-group bootstrap-timepicker timepicker">
															    	<input type="text"  class="form-control"  id="openingTime" name='openingTime'  value="${park.openingTime!0}" disabled>
															    	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
														    	</div>
														    </div>
														    <div class="col-sm-2">
														    	<span class="input-group-addon">至</span>
														    </div>
														    <div class="col-sm-5">
														    	<div class="input-group bootstrap-timepicker timepicker">
															    	<input type="text"  class="form-control"  id="closingTime" name='closingTime'  value="${park.closingTime!24}" disabled>
															    	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
														    	</div>
														    </div>
													   
													      </div>
													   </div>
													  </div>
													  <#--
													  <div class="form-group">
													    <label for="" class="col-sm-3 control-label">是否免费</label>
													    <div class="col-sm-8">
													   	   		 <select type="text"  name="isFree"  class="form-control" disabled>
																	<option value="1"<#if 1==park.isFree!0>selected</#if>>免费</option>
																	<option value="2"<#if 2==park.isFree!0>selected</#if>>收费</option>
																 </select>
													      </div>
													  </div>
													  <div class="rule" >
													  <div class="form-group" >
													    <label for="" class="col-sm-3 control-label">收费规则</label>
													    <div class="col-sm-8">
														    	 <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">查看收费规则</button>
													      </div>
													  </div>
													  </div>
													  -->
													  <div class="form-group">
													    <label for="companyId" class="col-sm-3 control-label">所属机构</label>
													    <div class="col-sm-8">
													   		 <input type="text"  class="form-control"  id="companyId" name='companyId'  value="${park.company.name!}" disabled>
													      </div>
													  </div>
				                                </div>
				                                <div class="box-footer text-center">
				                                       <a id="btn-back" class="btn btn-primary" href="javascript:;">&nbsp;返回&nbsp;</a>
				                                </div>
				                            </div><!-- /.box -->
				                            
				      	<div class="col-md-2">
							        
						</div>
							       
						       </div>
   
                       </div>        
						</form>
        </section>
		
		<!-- right col -->
	</div>
	

</section>
<!-- /.content -->

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
	<div class="modal-content">
	<div class="modal-header">
	
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
	×
	</button>
	<from>
	<h4 class="modal-title" id="myModalLabel">
	收费规则
	</h4>
	</div>
	<div class="modal-body">
		 			 <div class="form-group">
													    <label for="name" class="col-sm-3 control-label">规则名称</label>
													    <div class="col-sm-8">
													     <input type="text"  class="form-control"  id="ruleName" name='ruleName' value="${(chargingRules.name)!}"  disabled  >
													    </div>
													    <label for="total" class="error">*</label>
													  </div>
									<div class="form-group">
													    <label for="freeTime" class="col-sm-3 control-label">免时长</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="freeTime" name='freeTime' value="${(chargingRules.freeTime)!}"   disabled  >
													       	<span class="input-group-addon">分钟</span>
													       </div>
													    </div>
													    <label for="total" class="error">*</label>
													  </div>
							        <div class="form-group">
													    <label for="chargeType" class="col-sm-3 control-label">收费类型</label>
													    <div class="col-sm-8">
													     <select type="text" id="chargeType"  name="chargeType"  class="form-control"  disabled>
															<option value="1" <#if ((chargingRules.chargeType)!0)==1>selected</#if> >次</option>
															<option value="2" <#if ((chargingRules.chargeType)!0)==2>selected</#if> >时段</option>
														</select>
													    </div>
													    <label for="total" class="error">*</label>
													  </div>
									 <div class="form-group">
													    <label for="chargeType" class="col-sm-3 control-label">类型</label>
													    <div class="col-sm-8">
													     <select type="text" id="timeType"  name="timeType"  class="form-control" onchange="timeTypeChange();" disabled>
															<option value="1" <#if ((chargingRules.timeType)!0)==1>selected</#if> >全天</option>
															<option value="2" <#if ((chargingRules.timeType)!0)==2>selected</#if> >白天,夜晚</option>
														</select>
													    </div>
													    <label for="total" class="error">*</label>
													  </div>				  
						<!-- 按时间  -->							  
						  <div class="shijian">
							        <div class="form-group">
													    <label for="cycleTime" class="col-sm-3 control-label">收费周期</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="cycleTime" name='cycleTime' value="${(chargingRules.cycleTime)!}"  disabled  >
													       	<span class="input-group-addon">分钟</span>
													       </div>
													    </div>
													    <label for="total" class="error">*</label>
													  </div>
						 <div class="daynight">			
									
									<div class="form-group">
													    <label for="" class="col-sm-3 control-label">白天时间</label>
													    <div class="col-sm-8">
													   	   <div class="row">
													   	   	<div class="col-sm-5">
														    	<div class="input-group bootstrap-timepicker timepicker">
															    	<input type="text"  class="form-control"  id="dayStartTime" name='dayStartTime' value="${(chargingRules.dayStartTime)!}"  disabled >
															    	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
														    	</div>
														    </div>
														    <div class="col-sm-2">
														    	<span class="input-group-addon">至</span>
														    </div>
														    <div class="col-sm-5">
														    	<div class="input-group bootstrap-timepicker timepicker">
															    	<input type="text"  class="form-control"  id="dayEndTime" name='dayEndTime' value="${(chargingRules.dayEndTime)!}"  disabled >
															    	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
														    	</div>
														    </div>
													   
													      </div>
													   </div>
													  </div>
									<div class="form-group">
													    <label for="dayFirstTime" class="col-sm-3 control-label">白天首时段</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="dayFirstTime" name='dayFirstTime' value="${(chargingRules.dayFirstTime)!}"  disabled  >
													       	<span class="input-group-addon">分钟</span>
													       </div>
													    </div>
													  </div>
									<div class="form-group">
													    <label for="dayFirstTimePrice" class="col-sm-3 control-label">白天首时段收费</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="dayFirstTimePrice" name='dayFirstTimePrice' value="${(chargingRules.dayFirstTimePrice)!}"  disabled  >
													       	<span class="input-group-addon">元</span>
													       </div>
													    </div>
													  </div>
									<div class="form-group">
													    <label for="dayRemainTimePrice" class="col-sm-3 control-label">剩余时段收费</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="dayRemainTimePrice" name='dayRemainTimePrice' value="${(chargingRules.dayRemainTimePrice)!}"  disabled  >
													       	<span class="input-group-addon">元</span>
													       </div>
													    </div>
													  </div>
									<div class="form-group">
													    <label for="dayMaximumAmount" class="col-sm-3 control-label">白天最高收费</label>
													    <div class="col-sm-8">
													     <input type="text"  class="form-control"  id="dayMaximumAmount" name='dayMaximumAmount' value="${(chargingRules.dayMaximumAmount)!}"  disabled  >
													    </div>
													  </div>

									<div class="form-group">
													    <label for="" class="col-sm-3 control-label">夜间时间</label>
													    <div class="col-sm-8">
													   	   <div class="row">
													   	   	<div class="col-sm-5">
													   	   		<div class="input-group bootstrap-timepicker timepicker">
															    	<input type="text"  class="form-control"  id="nightStartTime" name='nightStartTime' value="${(chargingRules.nightStartTime)!}"  disabled >
															    	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
														    	</div>
														    </div>
														    <div class="col-sm-2">
														    	<span class="input-group-addon">至</span>
														    </div>
														    <div class="col-sm-5">
														    	<div class="input-group bootstrap-timepicker timepicker">
															    	<input type="text"  class="form-control" id="nightEndTime" name='nightEndTime' value="${(chargingRules.nightEndTime)!}"  disabled >
															    	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
														    	</div>
														    </div>
													   
													      </div>
													   </div>
													  </div>
									<div class="form-group">
													    <label for="nightFirstTime" class="col-sm-3 control-label">夜间首时段</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="nightFirstTime" name='nightFirstTime' value="${(chargingRules.nightFirstTime)!}"  disabled  >
													       	<span class="input-group-addon">分钟</span>
													       </div>
													    </div>
													  </div>
									<div class="form-group">
													    <label for="nightFirstTimePrice" class="col-sm-3 control-label">夜间首时段收费</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="nightFirstTimePrice" name='nightFirstTimePrice' value="${(chargingRules.nightFirstTimePrice)!}"  disabled >
													       	<span class="input-group-addon">元</span>
													       </div>
													    </div>
													  </div>
									<div class="form-group">
													    <label for="nightRemainTimePrice" class="col-sm-3 control-label">剩余时段收费</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="nightRemainTimePrice" name='nightRemainTimePrice' value="${(chargingRules.nightRemainTimePrice)!}"  disabled  >
													       	<span class="input-group-addon">元</span>
													       </div>
													    </div>
													  </div>
									<div class="form-group">
													    <label for="nightMaximumAmount" class="col-sm-3 control-label">夜间最高收费</label>
													    <div class="col-sm-8">
													     <input type="text"  class="form-control" id="nightMaximumAmount" name='nightMaximumAmount' value="${(chargingRules.nightMaximumAmount)!}"  disabled >
													    </div>
													  </div>
							  </div>
							  <div class="quantian">
							  		
									<div class="form-group">
													    <label for="nightFirstTime" class="col-sm-3 control-label">首时段</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="dayFirstTime_quan" name='dayFirstTime_quan' value="${(chargingRules.dayFirstTime)!}"  disabled  >
													       	<span class="input-group-addon">分钟</span>
													       </div>
													    </div>
													  </div>
									<div class="form-group">
													    <label for="nightFirstTimePrice" class="col-sm-3 control-label">首时段收费</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="dayFirstTimePrice_quan" name='dayFirstTimePrice_quan' value="${(chargingRules.dayFirstTimePrice)!}"  disabled  >
													       	<span class="input-group-addon">元</span>
													       </div>
													    </div>
													  </div>
									<div class="form-group">
													    <label for="nightRemainTimePrice" class="col-sm-3 control-label">剩余时段收费</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="dayRemainTimePrice_quan" name='dayRemainTimePrice_quan' value="${(chargingRules.dayRemainTimePrice)!}"  disabled  >
													       	<span class="input-group-addon">元</span>
													       </div>
													    </div>
													  </div>
							  </div>
						</div>
				<!-- 按次  -->
						<div class="cishu">
							 <div class="quantian">
									<div class="form-group">
													    <label for="nightFirstTimePrice" class="col-sm-3 control-label">价格</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="dayTimesPrice_quan" name='dayTimesPrice_quan' value="${(chargingRules.dayTimesPrice)!}"  disabled  >
													       	<span class="input-group-addon">元/次</span>
													       </div>
													    </div>
													  </div>
							 </div>
							 <div class="daynight">
							 		<div class="form-group">
													    <label for="" class="col-sm-3 control-label">白天时间</label>
													    <div class="col-sm-8">
													   	   <div class="row">
													   	   	<div class="col-sm-5">
														    	<div class="input-group bootstrap-timepicker timepicker">
															    	<input type="text"  class="form-control"  id="dayStartTime_ci" name='dayStartTime_ci' value="${(chargingRules.dayStartTime)!}"  disabled >
															    	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
														    	</div>
														    </div>
														    <div class="col-sm-2">
														    	<span class="input-group-addon">至</span>
														    </div>
														    <div class="col-sm-5">
														    	<div class="input-group bootstrap-timepicker timepicker">
															    	<input type="text"  class="form-control"  id="dayEndTime_ci" name='dayStartTime_ci' value="${(chargingRules.dayStartTime)!}"  disabled >
															    	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
														    	</div>
														    </div>
													   
													      </div>
													   </div>
													  </div>
									<div class="form-group">
													    <label for="nightFirstTimePrice" class="col-sm-3 control-label">白天价格</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="dayTimesPrice" name='dayTimesPrice' value="${(chargingRules.dayTimesPrice)!}"  disabled  >
													       	<span class="input-group-addon">元/次</span>
													       </div>
													    </div>
													  </div>
									<div class="form-group">
													    <label for="" class="col-sm-3 control-label">夜间时间</label>
													    <div class="col-sm-8">
													   	   <div class="row">
													   	   	<div class="col-sm-5">
													   	   		<div class="input-group bootstrap-timepicker timepicker">
															    	<input type="text"  class="form-control"  id="nightStartTime_ci" name='nightStartTime_ci' value="${(chargingRules.nightStartTime)!}"  disabled >
															    	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
														    	</div>
														    </div>
														    <div class="col-sm-2">
														    	<span class="input-group-addon">至</span>
														    </div>
														    <div class="col-sm-5">
														    	<div class="input-group bootstrap-timepicker timepicker">
															    	<input type="text"  class="form-control" id="nightEndTime_ci" name='nightEndTime_ci' value="${(chargingRules.nightEndTime)!}"  disabled >
															    	<span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
														    	</div>
														    </div>
													   
													      </div>
													   </div>
													  </div>
									<div class="form-group">
													    <label for="nightFirstTimePrice" class="col-sm-3 control-label">夜间价格</label>
													    <div class="col-sm-8">
													   	   <div class="input-group">
													     	<input type="text"  class="form-control"  id="nightTimesPrice" name='nightTimesPrice' value="${(chargingRules.nightTimesPrice)!}"  disabled  >
													       	<span class="input-group-addon">元/次</span>
													       </div>
													    </div>
													  </div>				  
							 </div>
						</div>		
				
									<div class="form-group">
													    <label for="alldayMaximumAmount " class="col-sm-3 control-label">全天最高收费</label>
													    <div class="col-sm-8">
													     <input type="text"  class="form-control" id="alldayMaximumAmount" name='alldayMaximumAmount' value="${(chargingRules.alldayMaximumAmount)!}"  disabled  >
													    </div>
													    <label for="alldayMaximumAmount" class="error">*</label>
													  </div>  				  			  				  				  				  
								     
	</div>
	
	<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal" onClick="del(0);">关闭
	</button>
	<input type="hidden" class="form-control" id="del" name='del' >
	
	</from>
	</div>
	</div>
	</div>
	</div>


<script type="text/javascript">
	$(function(){
	
		var type = $("select[name=isFree] option:selected").val();
		 	if(type==1){
		 		$('.rule').hide();
		 	}else{
		 		$('.rule').show();
		 	}
		var type = $("select[name=chargeType] option:selected").val();
		 	if(type==1){
		 		$('.shijian').hide();
		 		$('.cishu').show();
		 	}else{
		 		$('.shijian').show();
		 		$('.cishu').hide();
		 	}
		
		var type = $("select[name=timeType] option:selected").val();
		 	if(type==1){
		 		$('.daynight').hide();
		 		$('.quantian').show();
		 	}else{
		 		$('.daynight').show();
		 		$('.quantian').hide();
		 	}
		$('#btn-back').click(function(){
		var page=${colentpage!};
				var href = '${request.contextPath!}/baseInfo/park/Pagelist?page='+page;
			    $("#main-content").zrPageRefresh(href);
		});
		
		$('img').error(function () {
	      	$(this).attr('src', '${request.contextPath!}/system/b.png/picture');
	    });
	    
	    var address = $("#address").val().split(" ");
	    var location = "";
	    for(var i = 0;i<address.length;i++){
	    	if(address[i] != 'null'){
				 location += address[i];
	    	}
	    }
	    $("#address").val(location);
				//地图定位
		pointMap(true);
	});
/**
 * 地图定位
 */
function pointMap(flag){
	var center;
	if($('#latitude').val() == '' || $('#latitude').val() == ''){
		center = new AMap.LngLat(116.397428, 39.90923);
	}else{
		center = new AMap.LngLat(${park.longitude}, ${park.latitude});
	}
		var map = new AMap.Map('map_container', {
				resizeEnable: true,
				rotateEnable: true,
				dragEnable: true,
				zoomEnable: true,
				//设置可缩放的级别
				zooms: [3,18],
				//传入2D视图，设置中心点和缩放级别
				view: new AMap.View2D({
					center: center,
					zoom: 12
				})
			});			
	        
	    var marker = new AMap.Marker({
	        position:map.getCenter(),
	        //content:'点我定位',
			title:'拖动我定位',
		    cursor:'move',  //鼠标悬停点标记时的鼠标样式
	        raiseOnDrag:true//鼠标拖拽点标记时开启点标记离开地图的效果
	    });
	    marker.setMap(map);
	    }

</script>
 
