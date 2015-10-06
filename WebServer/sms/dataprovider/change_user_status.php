<?php
$response = array();
if(isset($_POST['sid']))
{
	session_id($_POST['sid']);
	session_start();
	if(isset($_SESSION['user']))
	{
		
		if(isset($_POST['id']) &&  isset($_POST['status']))
		{
			require_once 'db_config.php';
			$db = new PDO("mysql:dbname=" . DB_NAME . ";host=" . DB_SERVER, DB_USER, DB_PASSWORD);

			$stmt = $db->prepare('UPDATE users set status= :status where id = :id');
			$stmt->bindValue(":x2",$_POST['achievements'],PDO::PARAM_STR);
			$stmt->bindValue(":x3",$_POST['behaviour_history'],PDO::PARAM_STR);
			$stmt->execute();
			$response['success'] = 1;	
			$response['flag']=0;	

			$stmt = $db->prepare("insert into complaint_version(id_changed) values(:cid );");
			$stmt->bindValue(":cid",$_POST['id_number']);
			$stmt->execute();			
		}
		else{
			$response['success'] = 0;
				$response['flag']=0;
				$response['data']= "param error";
		}
	}else{
		$response['success'] = 0;
		$response['flag']=2;
		$response['data'] = "not logged in";
			
	}
	
}else
{
	$response['success'] = 0;
	$response['flag']=0;
	$response['data'] = "sid";
}
	

?>