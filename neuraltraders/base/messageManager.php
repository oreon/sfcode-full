<?php 

class MessageManager{
	
	public static $MSG_ID = 'CURR_MSG';
	
	static function put($msg){
		SessionManager::putObject(MessageManager::MSG_ID);
	}
	
	static function pop(){
		$msg = new Message();
		$msg = SessionManager::getObject(MessageManager::MSG_ID);
		return $msg;
	}
	
	
}

?>