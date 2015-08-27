<?php
$response = array();
if(isset($_POST['sid']))
{
	session_id($_POST['sid']);
	session_start();
	session_destroy();
	$response['success'] = 1;
	$response['flag']=0;
}
else
{
	$response['success'] = 0;
	$response['flag']=0;
	$response['data'] = "sid";
}
echo json_encode($response);
die();

  
?>