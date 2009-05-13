<?php 

class MessageManager{
	
	public static $MSG_ID = 'CURR_MSG';
	
	static function put($msg){
		SessionManager::putObject(self::MSG_ID);
	}
	
	static function pop(){
		$msg = new Message();
		$msg = SessionManager::popObject(self::MSG_ID);
		return $msg;
	}
	
	
}

?>