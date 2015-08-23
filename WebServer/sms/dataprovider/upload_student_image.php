<?php
if(isset($_GET['id']))
{
	$filename="../images/".$_GET['id']."jpg";
	$fileData=file_get_contents('php://input');
	$fhandle=fopen($filename, 'wb');
	fwrite($fhandle, $fileData);
	fclose($fhandle);
	$response['success'] = 1;
	$response['flag']=0;
}else
{
	$response['success'] = 0;
	$response['flag']=0;
	$response['data'] = "id";
}
  
?>