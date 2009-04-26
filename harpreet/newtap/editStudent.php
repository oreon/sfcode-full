<head>
<script type="text/javascript" src="js/jquery.js"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>

<script>
$().ready(function(){
	$("#studentForm").validate();
	
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
<form id="studentForm" action="studentcontroller_class.php" method="get">
First Name <input name="firstName" class="required" minlength="3" />
Last Name <input name="lastName" class="required" minlength="3" />
Gender
<select name="Gender">
<option value="male">Male</option>
<option value="female">Female</option>
</select>



<input type="submit">
</form> 
</div>


</body>