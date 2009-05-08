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
<select name="gender">
<option value="m">Male</option>
<option value="f">Female</option>
</select>
DOB<input name="dob"/>

<input type="submit">
</form> 
</div>


</body>