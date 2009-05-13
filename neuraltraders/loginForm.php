
<h3>Log In</h3>
<form action="controller/usercontroller_class.php"  method="post">
	<input type = hidden name="action" value="authenticate" />
	<table>
		<tr><td> UserName/Email<br/> <input type="text" name="userName" /></td></tr>
		<tr><td> Password<br/> <input type="password" name="password" /></td></tr>
		<tr><td colspan="2"> <input type="submit" name="login" value="Login" /></td></tr>
</table>
<a href="template.php?node=forgotPassword" > Forgot Password </a> 

</form>

