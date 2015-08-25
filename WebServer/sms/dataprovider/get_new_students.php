<?php
$response = array();
if(isset($_POST['sid']))
{
	session_id($_POST['sid']);
	session_start();
	if(isset($_SESSION['user']))
	{
		if(isset($_POST['sversion']))
			{
				require_once 'db_config.php';
				$db = new PDO("mysql:dbname=test_sms;host=localhost", DB_USER, DB_PASSWORD);
				
				$stmt = $db->prepare("SELECT id_changed FROM student_version WHERE :sversion < version");
				$stmt->bindValue(":sversion",$_POST['sversion'],PDO::PARAM_INT);
				$stmt->execute();
				$changed = $stmt->fetchAll(PDO::FETCH_ASSOC);
				if(count($changed) > 0)
				{
					$query = "";
					$i = 0;
					foreach($changed as $cchanged)
					{
						if(strlen($query) == 0)
							$query = "SELECT * FROM test_stdns WHERE id_number=:cchanged0";
						else
							$query = $query." or id_number=:cchanged".strval($i);
						$i++;
					}
					$statement = $db->prepare($query);
					$i = 0;
					foreach($changed as $cchanged)
					{
						
						$statement->bindValue(":cchanged".$i,$cchanged['id_changed'],PDO::PARAM_INT);
						$i = $i+1;
					}
					$statement->execute();
					$rows = $statement->fetchAll(PDO::FETCH_ASSOC);
					if(count($rows) > 0)
					{
						$response['data']=array();
						$response['success'] = 1;	
						$response['flag']=0;
						$statement = $db->prepare('SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = "test_sms" AND   TABLE_NAME   = "student_version"');
						$statement->execute();
						$res = $statement->fetchAll(PDO::FETCH_ASSOC);
						$response['sversion'] = $res[0]['AUTO_INCREMENT']-1;
						array_push($response['data'],$rows);	
					}
					else
					{
						$response['success'] = 1;
						$response['flag']=3;
						$response['data']= "DB error";
					}
				}
				else
				{
					$response['success'] = 1;
					$response['flag']=1;
					$response['data']= "no data";
				}	
			}else{
					$response['success'] = 0;
					$response['flag']=0;
					$response['data'] = "sversion";
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
	
echo json_encode($response);
?>