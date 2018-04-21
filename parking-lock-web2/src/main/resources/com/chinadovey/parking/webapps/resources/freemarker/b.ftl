  
 <style type="text/css">
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,button,textarea,p,blockquote,th,td { margin:0; padding:0; }
body { background:#fff; color:#555; font-size:14px; font-family: Verdana, Arial, Helvetica, sans-serif; }
td,th,caption { font-size:14px; }
h1, h2, h3, h4, h5, h6 { font-weight:normal; font-size:100%; }
address, caption, cite, code, dfn, em, strong, th, var { font-style:normal; font-weight:normal;}
a { color:#555; text-decoration:none; }
a:hover { text-decoration:underline; }
img { border:none; }
ol,ul,li { list-style:none; }
input, textarea, select, button { font:14px Verdana,Helvetica,Arial,sans-serif; }
table { border-collapse:collapse; }
html {overflow-y: scroll;}
/* css common */
.clearfix:after {content: "."; display: block; height:0; clear:both; visibility: hidden;}
.clearfix { *zoom:1; }
</style>
    <body>  
    <embed id="svg" src="/parking-carlock/statics/svg/B.svg"  height="720px"  width="855px"	
    type="image/svg+xml"  
    pluginspage="http://www.adobe.com/svg/viewer/install/" />  
    <div>
		x:<input type="text"    id="x" name='x'   >
		y:<input type="text"    id="y" name='y'   >
		name:<input type="text"  id="name" name='name'   >
		 <button type="button"  onClick="save()">保存</button>
	</div>	
    </body>  
    
	<script type="text/javascript"> 
window.onload=function(){
setTimeout("",10000);	
var element = document.getElementById("svg");
var svg = element.getSVGDocument();
	svg.addEventListener("click", function (param) { 

	//alert(param.layerX+"--"+param.layerY); console.log(param);
	 document.getElementById("x").value=param.layerX;  
     document.getElementById("y").value=param.layerY;  
	});
}
 function save(){
		var parkMap = {
							xAxis : document.getElementById("x").value,
							yAxis : document.getElementById("y").value,
							name : document.getElementById("name").value,
							pic : 'B'
						};
						param = "parkMap=" + JSON.stringify(parkMap);
 		var xhr = new XMLHttpRequest();

		xhr.open("POST", "${request.contextPath!}/map/map/save", true);
		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		xhr.onreadystatechange = function(){
		    var XMLHttpReq = xhr;
		    if (XMLHttpReq.readyState == 4) {
		        if (XMLHttpReq.status == 200) {
		            var text = XMLHttpReq.responseText;
		
		            console.log(text);
		        }
		    }
		};
		xhr.send(param);	
 }



</script>
  