<?php
if(isset($_POST['sid']))
{
	session_id($_POST['sid']);
	session_start();
	if(isset($_SESSION['user']))
	{
		if(isset($_POST['id']) && isset($_POST['name']))
		{
			$filename="../attached/".$_GET['id']."/".$_GET['name'];
			$fileData=file_get_contents('php://input');
			if(unlink($filename))
			{
				$response['success'] = 1;
				$response['flag']=0;
			}else
			{
				$response['success'] = 0;
				$response['flag']=3;
				$response['data']="Delete failed.";
			}
			
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