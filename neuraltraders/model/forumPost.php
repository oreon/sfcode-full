<?php
@include_once '../base/model/entity.php';
@include_once 'user.php';

class Forum extends Entity{
	var $title;
	var $text;
	var $replyToId;
	var $date;
	var $user;
	
	function __construct()
	{
		$user = new User();
	}
}


?>