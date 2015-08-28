<?php
$response = array();
if(isset($_POST['sid']))
{
	session_id($_POST['sid']);
	session_start();
	if(isset($_SESSION['user']))
	{
		if(isset($_POST['id']) && isset($_POST['name']))
		{
			$filename="../attached/".$_POST['id']."/".$_POST['name'];
			passthru('cat'.$filename);	
			$response['success'] = 1;	
			$response['flag']=0;
		}else{
		$response['success'] = 0;
		$response['flag']=0;
		$response['data'] = "id or name";
		}
						
	}else{
		$response['success'] = 0;
		$response['flag']=2;
		$response['data'] = "not logged in";
	}
}
else{
	$response['success'] = 0;
	$response['flag']=0;
	$response['data'] = "sid";
}
	echo json_encode($response);
	die();
?>