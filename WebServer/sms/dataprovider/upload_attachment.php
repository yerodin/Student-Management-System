<?php
$response = array();
if(isset($_GET['sid']))
{
	session_id($_GET['sid']);
	session_start();
	if(isset($_SESSION['user']))
	{
		if(isset($_GET['id']) && isset($_GET['name']))
		{
			$filename="../attached/".$_GET['id']."/".$_GET['name'];
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
	}else
	{
		$response['success'] = 0;
		$response['flag']=2;
		$response['data'] = "Not Logged In.";
	}
}
else
	{
		$response['success'] = 0;
		$response['flag']=0;
		$response['data'] = "sid";
	}

  
?>