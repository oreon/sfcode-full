<?php

?>
<html>
<head>

<script type="text/javascript" src="js/jquery.js"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>

<script>
$().ready(function(){
	$("#registerForm").validate({
		rules: {
		retypepassword: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			}
		}
	});

	$("#password").blur(function() {
		$("#retypepassword").valid();
	});
	
	
	
});

</script>



</head>
<body>
<style>
table.formTable {
	background-color: #dff;
	color: #000;
}

#registerForm {
	width: 500px;
}

#registerForm label {
	width: 250px;
}

#registerForm label.error {
	color: red;
	margin-left: 100px;
}

#registerForm input.submit {
	margin-left: 253px;
}
</style>






<form id="registerForm" action="sponsorcontroller_class.php"
	method="post">
<TABLE class="formTable" border="1">
	<thead>
		<tr>
			<td colspan="2">Registration</td>
		</tr>
	</thead>

	<TR>
		<TD>First Name</TD>
		<td><input name="firstname" class="required" minlength="2" /></TD>
	</TR>
	<TR>
		<TD>Last Name</TD>
		<td><input name="lastname" class="required" minlength="2" /></TD>
	</TR>
	<tr>
		<td>Email</TD>
		<td><input name="email" class="required email" /></td>
	</tr>
	<tr>
		<td>password</TD>
		<td><input type="password" id="password" name="password" class="required" minlength="5" /></td>
	</tr>
	<tr>
		<td>Retype password</TD>
		<td><input  id="retypepassword" type="password" name="retypepassword" class="required"
			minlength="5" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="save me"></td>
	</tr>

</TABLE>
</form>


</body>
</html>
