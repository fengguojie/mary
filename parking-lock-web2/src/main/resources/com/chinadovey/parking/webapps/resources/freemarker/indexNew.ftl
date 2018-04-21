<link href="${request.contextPath!}/statics/js/plugins/chosen/chosen.css" rel="stylesheet"/>
<section class="content-header">
	<h1>车场平面图</h1>
</section>

<!-- Main content -->
<section class="content">
<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/message.ftl">
	<ul class="nav nav-tabs" role="tablist" id="myTab">
	  <li role="presentation" class="active"><a href="#home" role="tab" data-toggle="tab">车场一层</a><a href="${request.contextPath!}/map/map/a">入口</a></li>
	  <li role="presentation"><a href="#profile" role="tab" data-toggle="tab">车场二层</a><a href="${request.contextPath!}/map/map/b" role="tab" data-toggle="tab">入口</a></li>
	</ul>
	<div class="tab-content">
	  <div role="tabpanel" class="tab-pane active" id="home">
      	<div id="main" class="main" style="height: 900px; cursor: default; background-color: rgb(238, 238, 238);"></div>
      </div>
	  <div role="tabpanel" class="tab-pane" id="profile">
		 二层
	  </div>
	</div>
	


	
	
<button id="btn" type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" style="display:none">
</button>

<!-- Modal -->
<div class="modal" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">车锁详情</h4>
      </div>
      <div class="modal-body" style="height:150px">
         <input type="hidden" type="text"  class="form-control"  id="slaveId" name='slaveId'   />
         <div class="form-group">
			<label for="name" class="col-sm-3 control-label">X</label>
				<div class="col-sm-8">
				<input type="text"  class="form-control"  id="x" name='x' disabled  >
				</div>
													    
		 </div>
		 <div class="form-group">
			<label for="name" class="col-sm-3 control-label">Y</label>
				<div class="col-sm-8">
				<input type="text"  class="form-control"  id="y" name='y' disabled  >
				</div>
													    
		 </div>
		 <div class="form-group">
			<label for="name" class="col-sm-3 control-label">车位号</label>
				<div class="col-sm-8">
				<input type="text"  class="form-control"  id="name" name='name'   >
				</div>
													    
		 </div>


      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onClick="save()">保存</button>
        <button type="button" id="close" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

</section>
<!-- /.content -->
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/chosen/chosen.jquery.min.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/tab.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/modal.js"/>
<script type="text/javascript" src="${request.contextPath!}/statics/js/plugins/ECharts/dist/echarts.js"/>

 <script type="text/javascript">
 $(function(){
   $('#myTab a:first').tab('show')
   
	$.post('${request.contextPath!}/map/map/getDate',
							function(data){
								if (data.result) {
									 var map = data.data;
									 var zbs = eval("(" + map.zbs + ")");
									 mapDate(zbs,map.names);
								}else{
								
								}
							});
	setTimeout("test();", "1000");							
});
 
 function mapDate(zbs,names){
 	console.log(zbs);
 	console.log(names);
		
     require.config({
            paths: {
               'echarts': './statics/js/plugins/ECharts/dist',
            }
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
						            url: "./statics/svg/A.svg",
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
						        formatter: '{b}'
						    },
						    
						    series : [
						        {
						            name: '车位',
						            type: 'map',
						            mapType: 'baiduBuilding',
						            roam:true,
						            itemStyle:{
						                normal:{label:{show:true}},
						                emphasis:{label:{show:true}}
						            },
						            data: [],
						            geoCoord:zbs,
						            markPoint : {
						                symbolSize : 3,
						                data : names
						            },
						           
						        },
						       
						    ]
						};
						 // 为echarts对象加载数据 
						myChart.setOption(option);
						var ecConfig = require('echarts/config');
						myChart.on(ecConfig.EVENT.CLICK, eConsole);
						function eConsole(param) {
						
						}
	            }
	 );
 }
 function dialog(param){
 		$('#x').val("开");
 		$('#y').val("开");
 		$('#btn').click();
 }
 
function save(){
		var parkMap = {
							xAxis : $("#x").val(),
							yAxis : $("#y").val(),
							name : $("#name").val(),
							pic : 'A'
						};
						param = "parkMap=" + JSON.stringify(parkMap);
 		$.post('${request.contextPath!}/map/map/save',param,
							function(data){
								if (data.result) {
								   $("#failure-info").hide();
							       $("#success-info").show();
							       setTimeout(function(){$("#main-content").zrPageRefresh(href);},3000);
							       $('#name').val("");
								}else{
									 $("#success-info").hide();
								     $("#failure-info").show();
								     setTimeout(function(){$("#failure-info").hide();},3000);
								}
							});	
 }

  function windowTocanvas(canvas, x, y) {  
                var bbox = canvas.getBoundingClientRect();  
                return {  
                    x: x - bbox.left * (canvas.width / bbox.width),   
                    y: y - bbox.top * (canvas.height / bbox.height)  
                };  
  
            }  
  function test(){  
             var _canvas = $("canvas.zr-element");  
              console.log(_canvas);
           
            var canvas2 = _canvas[2];
            
           
            canvas2.onclick=function(event){  
                   console.log(event);
                var loc=windowTocanvas(canvas2,event.clientX,event.clientY);  
                var x=parseInt(loc.x);  
                var y=parseInt(loc.y);  
                //alert(x+"--"+y);
                //$('#x').val(x);
 				//$('#y').val(y);
 				$('#btn').click();
            }   
        }  
 </script>
