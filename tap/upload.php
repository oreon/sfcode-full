<?
//print_r($_POST);

if($_POST["action"] == "Upload Image")
{
	unset($imagename);
	$dirPath = "images/uploads/".'x1';

	if(!isset($_FILES) && isset($HTTP_POST_FILES))
	$_FILES = $HTTP_POST_FILES;

	if(!isset($_FILES['image_file']))
	$error["image_file"] = "An image was not found.";
	
	if(!file_exists($dirPath))
		mkdir($dirPath);

	$imagename = basename($_FILES['image_file']['name']);
	//echo $imagename;

	if(empty($imagename))
	$error["imagename"] = "The name of the image was not found.";

	if(empty($error))
	{
		$newimage = $dirPath.$imagename;
		//echo $newimage;
		$result = @move_uploaded_file($_FILES['image_file']['tmp_name'], $newimage);
		if(empty($result))
		$error["result"] = "There was an error moving the uploaded file.";
	}

	if(!is_array($error)){
		echo "<img src='$newimage' />";
	}

}

?>


<form method="POST" enctype="multipart/form-data"
	name="image_upload_form" action="<?$_SERVER["PHP_SELF"];?>">

<p><input type="file" name="image_file" size="20"></p>
<p><input type="submit" value="Upload Image" name="action"></p>
</form>

<?
if(is_array($error))
{
	while(list($key, $val) = each($error))
	{
		echo $val;
		echo "<br>\n";
}
}
?>