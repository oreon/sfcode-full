
<head>
<script type="text/javascript" src="js/jquery.js"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>

<script>
$().ready(function(){
	$("#gradeForm").validate();
	
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
<form id="gradeForm" action="gradecontroller_class.php" method="get">
Grade Name <input name="gradename" class="required" minlength="1" />
<input type="submit">
</form> 
</div>


</body>
<?php
?>