<!-- Content Header (Page header) -->
<script type="text/javascript">
<!--
	//-->
	$(function() {
		$("#success-info").hide();
		$("#failure-info").hide();
		$("#wait-info").hide();
	});
</script>
<style>
.chart-info, .chart-info .increase, .chart-info .decrease {
    display: inline-block;
}
.chart-info .increase {
    background: #ff6c60;
    width: 10px;
    height: 10px;
}

.chart-info .decrease {
    background: #f2f2f2;
    width: 10px;
    height: 10px;
}
</style> 
<section class="content-header">
	<h1>数据同步</h1>
	<ol class="breadcrumb">
		<li><a href="#"><i class="fa fa-dashboard"></i>系统管理</a></li>
		<li><a href="#" class="active">数据同步</a></li>
	</ol>
</section>

<!-- Main content -->
<section class="content">
	<!-- row -->
   <div class="row">                        
        <div class="col-md-12">
             <div id="success-info"  class="alert alert-success alert-dismissable">
                <i class="fa fa-check"></i>
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
              	<p id="success">更新成功</p>
             </div>
	         <div id="failure-info" class="alert alert-danger alert-dismissable">
	             <i class="fa fa-ban"></i>
	             <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	        	<p id="failure">系统异常</p>
			</div>
			<section class="col-lg-12 connectedSortable">
                    <div class="btn-group">
                          <button type="button" id="mysqlToMongodb" class="btn btn-default">mysql同步到mongodb</button>
                          <button type="button" id="mongodbToMysql" class="btn btn-default">mongodb同步到mysql</button>
					</div>

              </section>
              <div class="row">
                                  <div class="col-lg-3 col-sm-6 text-center">
                                      <div class="easy-pie-chart">
                                          <div class="percentage" data-percent="100"><font size="20px" color="#BDE432">35423</font>元</div>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-sm-6 text-center">
                                      <div class="percentage" data-percent="75"><font size="20px" color="#198543">75</font>%</div>
                                  </div>
                                  <div class="col-lg-3 col-sm-6 text-center">
                                      <div class="easy-pie-chart">
                                          <div class="percentage" data-percent="35"><font size="20px" color="#675BCA">35</font>%</div>
                                      </div>
                                  </div>
                                  <div class="col-lg-3 col-sm-6 text-center">
                                      <div class="easy-pie-chart">
                                          <div class="percentage" data-percent="100"><font size="20px" color="#f6B8A2">213</font>元</div>
                                      </div>
                                  </div>
                              </div>
                              <hr>
              <div class="row">
                    <div class="col-lg-3 col-sm-6 text-center">
						 <div id="container" style="height:400px"></div>
					</div>
                    <div class="col-lg-3 col-sm-6 text-center">
						 <div id="container1" style="height:400px"></div>
					</div>
                    <div class="col-lg-3 col-sm-6 text-center">
                    	<h2>收入统计</h2>
					 	<canvas id="canvas" height="350" width="350"></></canvas>
					</div>
				</div>
   		</div><!-- /.col -->
   </div><!-- /.row -->
</section>

            
<!-- /.content -->
<#include "/com/chinadovey/parking/webapps/resources/freemarker/include/dlg-info.ftl">
 	
 <script type="text/javascript" src="${request.contextPath!}/statics/js/ajaxfileupload.js"></script>
 <script type="text/javascript" src="${request.contextPath!}/statics/js/filestyle.min.js"></script>
 <script type="text/javascript">
 $(document).ready(function () {
 	initPieChart(".percentage");
 	data();
  	$("#mysqlToMongodb").click(function () {
	  	$.post('${request.contextPath!}/system/dataSync/mysqlToMongodb',function(data){
  			$("#failure-info").hide();
   		    $("#success-info").hide();
	  		if(data.result){
	  			$("#success").html("更新成功,用时" + data.time + "毫秒");
	  			$("#failure-info").hide();
				$("#success-info").show();
	  		}else{
	  			$("#failure").html(data.error);
    		    $("#success-info").hide();
		        $("#failure-info").show();
	  		}
		});
	});
  	$("#mongodbToMysql").click(function () {
	  	$.post('${request.contextPath!}/system/dataSync/mongodbToMysql',function(data){
  			$("#failure-info").hide();
   		    $("#success-info").hide();
	  		if(data.result){
	  			$("#success").html("更新成功,用时" + data.time + "毫秒");
	  			$("#failure-info").hide();
				$("#success-info").show();
	  		}else{
	  			$("#failure").html(data.error);
    		    $("#success-info").hide();
		        $("#failure-info").show();
	  		}
		});
	});
});
function data(){
var hj = {
    //Boolean - Whether we should show a stroke on each segment
    segmentShowStroke : true,



    //String - The colour of each segment stroke
    segmentStrokeColor : "#fff",

    //Number - The width of each segment stroke
    segmentStrokeWidth : 2,

    //Number - The percentage of the chart that we cut out of the middle
    percentageInnerCutout : 50, // This is 0 for Pie charts

    //Number - Amount of animation steps
    animationSteps : 100,

    //String - Animation easing effect
    animationEasing : "easeOutBounce",

    //Boolean - Whether we animate the rotation of the Doughnut
    animateRotate : true,

    //Boolean - Whether we animate scaling the Doughnut from the centre
    animateScale : false,
	text:"fgfhjkfghjk",
    //String - A legend template
    legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"

}
	var pieData = [
				{
					value: 331,
					color:"#F38630",
					highlight: "#B452CD",
					label: "瑞安国际大酒店"
				},
				{
					value : 530,
					color : "#C0FF3E",
					highlight: "#00F5FF",
					label: "瑞安万松停车场"
				},
				{
					value : 106,
					color : "#69D2E7",
					highlight: "#40E0D0",
					label: "创业大厦停车场"
				},
				{
					value : 251,
					color : "#FF5A5E",
					highlight: "#9B30FF",
					label: "创业大厦停车场"
				}
			];

	var myPie = new Chart($("#canvas")[0].getContext("2d")).Pie(pieData,hj);
	// Build the chart
	var markName = '瑞安国际大酒店';
        $('#container').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '收入统计'
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        distance: -115,
		                 formatter: function() { 
						   //始终只显示所选择的版块数据
							if (this.point.name != markName) {
								return "";
							} else {
								return Highcharts.numberFormat(this.y, 0, ',') + "元";
							}
						}
                    },
                     point: {
						 events: {
                              mouseOver: function () {
                              //获得当前版块的名称
                        	        markName = this.name;
                                    //动态设置当前序列的dataLabels为开启状态
                                    this.series.chart.series[this.series.index].update({
                                        dataLabels: {
                                            enabled: true,
                                            style: {
                                                 color:this.color
                                            }
                                       }
                                    });
                               }
                            }
                      },
                    innerSize:'50%',
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: '收入统计',
                data: [
                    ['瑞安国际大酒店', 1345.0],
                    ['瑞安万松停车场', 526.8],
                    ['瑞安隆山停车场', 658.5],
                    ['创业大厦停车场', 826.2],
                    ['创新大厦停车场', 560.7]
                ]
            }]
        });
        $('#container1').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: '收入统计'
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
		                 formatter: function() { 
						    return  Highcharts.numberFormat(this.y, 0, ',') + '元';
						}
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: '收入统计',
                data: [
                    ['瑞安国际大酒店', 1345.0],
                    ['瑞安万松停车场', 526.8],
                    ['瑞安隆山停车场', 658.5],
                    ['创业大厦停车场', 826.2],
                    ['创新大厦停车场', 560.7]
                ]
            }]
        });
}
 </script>
 
