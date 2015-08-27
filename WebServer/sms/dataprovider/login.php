<?php
$response = array();
session_start();
$id = session_id();
if(isset($_POST['user']) && isset($_POST['password']))
{
	$username = $_POST['user'];
	$password = sha1($username.$_POST['password']);
	require_once 'db_config.php';
	$db = new PDO("mysql:dbname=test_sms;host=localhost", DB_USER, DB_PASSWORD);
	$statement = $db->prepare("SELECT * FROM users WHERE username =:uname and password =:pw");
	$statement->bindValue(":uname",$username,PDO::PARAM_STR);
	$statement->bindValue(":pw",$password,PDO::PARAM_STR);
	$statement->execute();
	$rows = $statement->fetchAll(PDO::FETCH_ASSOC);
	if(count($rows) > 0)
	{
		$response['success'] = 1;
		$response['data'] = "Logged in success";
			$_SESSION['user'] = $username;
			$response['sid'] =$id;
	}
	else
	{
		$response['success'] = 0;
		$response['flag'] = 3;
		$response['data'] = "Login Failed.";
	}

	
}else{
	$response['success'] = 0;
	$response['flag'] = 0;
	$response['data'] = "user or password";
	
}
echo json_encode($response);
?>
