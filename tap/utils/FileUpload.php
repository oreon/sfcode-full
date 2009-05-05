<?php
class FileUpload{
	
	static function upload($dirPath = "", $fileField = 'imageFile'){
		$baseUploadDir = $_SERVER['DOCUMENT_ROOT']."/tap/uploads/";
		$dirPath = $baseUploadDir.$dirPath;

		if(!isset($_FILES) && isset($HTTP_POST_FILES))
			$_FILES = $HTTP_POST_FILES;

		if(!isset($_FILES[$fileField]))
			$error[$fileField] = "An image was not found.";

		echo $dirPath;
		if(!file_exists($dirPath))
			mkdir($dirPath, 0777, true);

		$imagename = basename($_FILES[$fileField]['name']);
		//echo $imagename;

		if(empty($imagename))
			$error["imagename"] = "The name of the image was not found.";

		if(empty($error))
		{
			$newimage = $dirPath."/".$imagename;
			echo $newimage;
			$result = @move_uploaded_file($_FILES[$fileField]['tmp_name'], $newimage);
			if(empty($result))
				$error["result"] = "There was an error moving the uploaded file.";
		}

		/*
		if(!is_array($error)){
			echo "<img src='$newimage' />";
		}*/
		print_r($error);
		
		return $error;

	}
}
?>