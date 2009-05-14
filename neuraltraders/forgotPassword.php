<?php
?>
<script type="text/javascript" src="/js/jquery.js"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script>
$().ready(function(){
	$("#forgotPasswordForm").validate();
});
</script>

<form id="forgotPasswordForm" action="controller/usercontroller_class.php"
	method="post">
	<input type="hidden" name="action" value="mailPassword" />
<table>
<thead>
		<tr style="background-color: navy; color: #fff;">
			<td colspan="2"><b>Please enter your email address</b></td>
		</tr>
	</thead>
<tr><td>Email</td><td><input name="email" class="required email" /></td>
	</tr>
<tr>
		<td> <input type="submit" value="Send me my credentials"/></td>
	</tr>

</table>"
</form>