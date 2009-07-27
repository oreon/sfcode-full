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
	width: 100px;
	height: 40px;
	text-align: center;
	border-bottom: 1px solid #6b6b6b;
	border-right: 1px solid #6b6b6b;
	background-color: #dda;
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
	width: 100px;
	height: 60px;
	padding: 2px;
	border: 1px solid #dde;
}
</style>
</head>

<body>


<div class="box" style="background: #f7a673" id="dragme">Drag Me</div>


<p>


<table style="margin-bottom: 8px;" border="0" cellspacing="1"
	cellpadding="0">
	<%
	out.println( "<tr><th></th>");
	for(int j = 1; j < cols; j++){
		int date = 16+ j;
		out.println( "<th>"+  date + "  </th>");
	}
	out.println( "</tr>");

	for(int i = 1; i < rows; i++){
		out.println("<tr>");
		int time = i + 7 ;
		out.println("<td>" +   time + " :00 </td>");
		for(int j = 1; j < cols; j++){
	%>		

	<td>
	<div id="droponme<%= i%>-<%= j %>" class="simpleDropPanel"
		style="background: #ffd773"></div>
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