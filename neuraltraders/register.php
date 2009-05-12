<?php
?>
<html>
<head>

<script type="text/javascript" src="/js/jquery.js"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>

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






<form id="registerForm" action="controller/usercontroller_class.php"
	method="post">
	<input type="hidden" name="action" value="save" />
<TABLE class="formTable2" border="0" width="70%">
	<thead>
		<tr style="background-color: navy; color: #fff;">
			<td colspan="2"><b>Registration</b></td>
		</tr>
	</thead>

	<TR>
		<TD>First Name</TD>
		<td><input name="firstName" class="required" minlength="2" /></TD>
	</TR>
	<TR>
		<TD>Last Name</TD>
		<td><input name="lastName" class="required" minlength="2" /></TD>
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
		<td>phone</TD>
		<td><input name="phone" class="required" minlength="10" /></td>
	</tr>
	<tr>
		<td colspan="2"><input type="submit" value="Register"></td>
	</tr>

</TABLE>
</form>


</body>
</html>

