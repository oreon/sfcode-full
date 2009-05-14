<?php session_start(); ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>Forex Training, Currencies, Options, Commodities, Futures, Stocks
Training and Expert Advisors</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/style.css"
	media="screen" />
</head>
<body>
<div id="wrap">

<div id="top"></div>

<?php
include_once 'base/model/entity.php';
include_once 'model/user.php';
include_once 'base/messageManager.php';
include_once 'base/message.php';


include_once 'template/menu.php';
include_once 'template/header.php';
include_once 'controller/UserManager.php';

?>


<div id="content" style="height: 400px">



<div id="left"><?php 
$msg = new Message('');
$msg = MessageManager::pop();
$clr = $msg->severity == 'E' ? 'pink':'#cfc';

if($msg != null)
print "<div style='background-color:$clr;font-size:1em; margin:2px'> $msg->text  </div> ";

$node = $_GET['node'].'.php';
if(!isset($_GET['node']))
$node = "main.php";
include_once $node;

?></div>

<div id="right">
<h3>Quick Links</h3>
<ul>
	<li><a href="template.php?node=capital">Capital Management</a></li>
	<li><a href="template.php?node=fundamentals">Forex Fundamentals </a></li>
	<li><a href="template.php?node=calender">Calendar </a></li>
	<li><a href="template.php?node=alerts">Alerts </a></li>
</ul>

<?php
if( UserManager::getLoggedInUser() == null ) {
	include_once 'loginForm.php'; }
else { 
	include_once 'mylinks.php'; 
} 
?>
</div>

<div style="clear: both;"></div>

</div>

	<?php include_once 'template/footer.php'; ?></div>
</body>
</html>
