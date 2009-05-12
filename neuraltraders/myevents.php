<?php
include_once 'base/model/entity.php';
include_once 'model/event.php';

$event = new Event();

$userid = 3;
$arr = $event->getUserEvents($userid);

foreach ($arr as $obj){
	//$event = (Event) $obj;
	print "<table border='1'>"	;
	print "<tr><td><b> $obj->name,  </b></td></tr>" ;
	print "<tr><td><b> $obj->description </td></tr>" ;
	print "</table>";
	print "<form action='controller/eventRegistrationController.php'>";
	print "<input type='hidden' name='action' value='cancel' />";
	print "<input type='hidden' name='eventid' value='$obj->id' />";
	print "<input type='hidden' name='userid' value='4' />";
	print"<input type='submit' value='cancel' />";
	print"</form><hr/>";
}

?>
