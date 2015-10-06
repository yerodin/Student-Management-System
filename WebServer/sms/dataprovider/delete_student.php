<?php
$response = array();
if(isset($_POST['sid']))
{
	session_id($_POST['sid']);
	session_start();
	if(isset($_SESSION['user']))
	{
		
		if(isset($_POST['id_number']))
		{
			
			require_once 'db_config.php';
			$db = new PDO("mysql:dbname=" . DB_NAME . ";host=" . DB_SERVER, DB_USER, DB_PASSWORD);

			$stmt = $db->prepare('delete from test_stdns where id_number=:id_number');

			$stmt->bindValue(":id_number",$_POST['id_number'],PDO::PARAM_STR);
			$stmt->execute();
			if($stmt->rowCount() > 0)
			{
				$response['success'] = 1;	
				$response['flag']=0;	
			}
			else
			{
				$response['success'] = 0;
				$response['flag']=1;
				$response['data']= "no data";
			}			
		}
		else{
			$response['success'] = 0;
				$response['flag']=0;
				$response['data']= "id_number";
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