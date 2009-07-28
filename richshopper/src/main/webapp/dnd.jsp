<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">

<title>Rico</title>
<% 
int rows = 5;
int cols = 4;
%>
<script src="js/src/rico.js" type="text/javascript"></script>
<script type='text/javascript'>
Rico.loadModule('Corner','DragAndDrop');
Rico.onLoad( function() {
   Rico.Corner.round('explanation');
   dndMgr.registerDraggable( new Rico.Draggable('test-rico-dnd','dragme') );
   var rows = <%=rows%>;
   var cols = <%=cols%>;
   
   for( var i = 1; i < rows; i++){
	for (var j = 1; j < cols; j++)	 {
		var divName = 'droponme' + i + '-' + j;
   		dndMgr.registerDropZone( new Rico.Dropzone(divName) );
   		var dropZone = document.getElementById(divName);
   		//if( i < 2 && j < 2 )
   		//alert("adding event " + divName);

   		dropZone.addEventListener('dblclick',function (e) {
			 var target = e.target;
			 if(e.target.innerHtml != null)
				 return;
			 
			 var newdivid = 'Appt:'+target.id;
			 //var divName = 'droponme' + i + '-' + j;
			 //alert('here' +  target.innerHTML); 
	  		 var newdiv = document.createElement('div');
	   	   	 newdiv.setAttribute('id', newdivid );
	   	  	 newdiv.innerHTML =newdivid;
	   	   	 
	   	  	 newdiv.setAttribute('class', 'draggable');
	   	  	 newdiv.style.position = "absolute";
	   	  	 //dndMgr.drag(newdiv);
	   	  	 //alert(target.offsetLeft + ' - ' + target.style.top);
	   	  	 newdiv.style.left = getPosition(target.id,'left');
	   	     newdiv.style.top = getPosition(target.id,'top');
	   	  	 
	   	  	 document.body.appendChild(newdiv);
	   	  	 dndMgr.registerDraggable( new Rico.Draggable('test-rico-dnd', newdivid) );
		},
		true);
	}
   }


});

function getPosition(divName,leftOrTop){
	obj = document.getElementById(divName);
    var topValue= 0,leftValue= 0;
    while(obj){
	leftValue+= obj.offsetLeft;
	topValue+= obj.offsetTop;
	obj= obj.offsetParent;
    }
    if(leftOrTop=='left')
    finalvalue = leftValue;
    else
        finalvalue = topValue;
    
    return finalvalue;
}

function getLeftOffset(obj) {
	var x = obj.offsetLeft;
	while (obj = obj.offsetParent) 
		x += obj.offsetLeft;
	return x;
}

function getTopOffset(obj) {
	var y = obj.offsetLeft;
	while (obj = obj.offsetParent) 
		y += obj.offsetTop;
	return y;
}


</script>

<style type="text/css">
body,p {
	font-family: Trebuchet MS, Arial, Helvetica, sans-serif;
}

h1 {
	font-size: 16pt;
}

div.title {
	font-family: Arial;
	font-size: 10px;
	background-color: #797979;
	color: #ffffff;
	width: 200px;
	margin: 1px;
}

div.box {
	font-family: Arial;
	font-size: 14px;
	width: 100px;
	height: 40px;
	text-align: center;
	border-bottom: 1px solid #6b6b6b;
	border-right: 1px solid #6b6b6b;
}

div.draggable{
	font-family: Arial;
	font-size: 14px;
	color: #FFFFFF;
	height: 40px;
	text-align: center;
	border-bottom: 1px solid #6b6b6b;
	border-right: 1px solid #6b6b6b;
	background-color: rgb(41, 82, 163);
	 
}

div.panel {
	width: 200px;
	height: 80px;
	padding: 2px;
	border: 1px solid #5b5b5b;
}

div.explanation {
	font-family: Arial;
	font-size: 12px;
	width: 600px;
	background-color: #cdd7b5;
	border: 1px solid black;
}

div.simpleDropPanel {
	width: 100%;
	height: 60px;
	
	
}

.tg-time {
border-bottom:1px solid #DDDDDD;
padding-right:2px;
width: 70PX;
}

.wk-dayname {
color:#112ABB;
font-weight:normal;
margin-left:4px;
padding:2px 4px;
white-space:nowrap;
}

.wk-weektop {
background-color:#C3D9FF;
font-size:11px;
line-height:14px;
overflow:hidden;
table-layout:fixed;
width:100%;

}

.tg-dualmarker {
border-left:3px double #DDDDDD;
border-bottom:1px dotted #DDDDDD;

padding :0px;
height:1em;
line-height:1em;
margin-bottom:0em;
background-color:#FFFFFF;
}


</style>
</head>

<body>


<div class="box" style="background: #f7a673" id="dragme">Drag Me</div>


<p>


<table class="wk-weektop"  cellspacing="0" cellpadding="0">
	<%
	out.println( "<tr class='wk-dayname'><th width='70px' height='50'></th>");
	for(int j = 1; j < cols; j++){
		int date = 16+ j;
		out.println( "<th>"+  date + "  </th>");
	}
	out.println( "</tr>");

	for(int i = 1; i < rows; i++){
		out.println("<tr>");
		int time = i + 7 ;
		out.println("<td class='tg-time'>" +   time + " :00 </td>");
		for(int j = 1; j < cols; j++){
	%>		

	<td class="tg-dualmarker">
	<div id="droponme<%= i%>-<%= j %>" class="simpleDropPanel"
		></div>
	</td>

	<% 
		}
		out.println( "</tr>");
	}
	%>

</table>


<div id="explanation" class="explanation">hi there this should have
rounded corners lets see if it actually works. We can acooplish a lto
<p>this has to be sorry state of affairs wow it works now - fantastic
bombastic</p>
</div>

</body>