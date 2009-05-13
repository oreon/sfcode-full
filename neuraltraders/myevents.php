<?php
include_once 'base/model/entity.php';
include_once 'model/event.php';

$event = new Event();

$userid = UserManager::getLoggedInUser()->id;
$arr = $event->getUserEvents($userid);

if(count($arr) == 0)
	print "You have no upcoming events";
else {
	print "<br>Following are your upcoming events";
	
	foreach ($arr as $obj){
		//$event = (Event) $obj;
		print $obj->renderEvent();
		print "<form action='controller/eventRegistrationController.php'>";
		print "<input type='hidden' name='action' value='cancel' />";
		print "<input type='hidden' name='eventid' value='$obj->id' />";
		print "<input type='hidden' name='userid' value='$userid' />";
		print"<input type='submit' value='cancel' />";
		print"</form><hr/>";
	}
}
?>
