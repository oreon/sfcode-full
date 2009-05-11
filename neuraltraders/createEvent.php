
<head>
<script type="text/javascript" src="js/jquery.js"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>

<script>
$().ready(function(){
	$("#eventForm").validate();
});
</script>

	
</head>
<?php
?>

<body>

<style>
.formDiv{
background-color:#ddd;
}
</style>
<div class="formDiv">
<form id="eventForm" action="controller/eventController.php" method="get">
	<input type="hidden" name="action" value="save" />

<table class="formTable" border="1">
 <tr><td>Name</td><td><input name="name" class="required" minlength="3" /></td>
<td>Date</td><td> <input name="date" class="required" minlength="3" /></td></tr>
<tr><td>Time</td><td> <input name="time" class="required" minlength="3" /></td>
<td>Price</td><td><input name="price" class="required" minlength="1" /></td></tr>
<tr><td>Venue</td><td> <input name="venue" class="required" minlength="10" /></td>
<td>Max Seats</td><td> <input name="maxseat" class="required" minlength="1" /></td></tr>
<tr><td colspan="4"> Description </td></tr>
<tr><td colspan="4"> <textarea name="description"  cols="50"/> </textarea></td></tr>

<tr><td><input type="submit" value="Submit"></td></tr>
</table>
</form> 
</div>


</body>
