<?php 

class Message{
	var $severity;
	var $text;
	
	function __construct($text, $severity = 'E'){
		$this->text = $text;
		$this->severity = $severity;
	}
	
}

?>