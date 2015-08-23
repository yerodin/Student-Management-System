<?php
if(isset($_POST['sid']))
{
	session_id($_POST['sid']);
	session_start();
	if(isset($_SESSION['user']))
	{
		
		if(isset($_POST['academic_status']) &&  isset($_POST['achievements']) && isset($_POST['behaviour_history']) &&  isset($_POST['block'])&&  isset($_POST['cell_phone']) &&  isset($_POST['chancellorite_history']) &&  
		isset($_POST['community_group']) &&  isset($_POST['co_curricular'])&&  isset($_POST['day_joined'])&&  isset($_POST['dob'])&&  isset($_POST['faculty'])&&  isset($_POST['father_first_name'])&&  
		isset($_POST['father_last_name'])&&  isset($_POST['father_phone'])&&  isset($_POST['first_name'])&&  isset($_POST['hall_history'])&&  isset($_POST['home_address1'])&&  isset($_POST['home_address2'])&&  
		isset($_POST['home_city'])&&  isset($_POST['home_province'])&&  isset($_POST['id_number'])&&  isset($_POST['last_name'])&&  isset($_POST['middle_name'])&&  isset($_POST['mother_first_name'])&&  
		isset($_POST['mother_last_name'])&&  isset($_POST['mother_phone'])&&  isset($_POST['nationality'])&&  isset($_POST['image'])&&  isset($_POST['previous_secondary_school'])&&  isset($_POST['reason_residing'])&&  
		isset($_POST['resident_country'])&&  isset($_POST['room'])&&  isset($_POST['tertiary_level'])&&  isset($_POST['id'])&&  isset($_POST['will_participate']))
		{
			require_once 'db_config.php';
			$db = new PDO("mysql:dbname=test_sms;host=localhost", DB_USER, DB_PASSWORD);

			$stmt = $db->prepare('UPDATE test_stdns setacademic_status=:x1,achievements,=:x2behaviour_history=:x3, block=:x4, cell_phone=:x5,chancellorite_history=:x6,community_group=:x7,co_curricular=:x8,'.'
			day_joined=:x9,dob=:x10,faculty=:x11,father_first_name=:x12,father_last_name=:x13,father_phone=:x14,first_name=:x15,hall_history=:x16,home_address1=:x17,home_address2=:x18,home_city=:x19,home_province=:x20,id_number=:x21,last_name=:x22,middle_name=:x23,mother_first_name=:x24,mother_last_name=:x25,'.
			'mother_phone=:x26,nationality=:x27,previous_secondary_school=:x28,reason_residing=:x29,resident_country=:x30,room=:x31,tertiary_level=:x32,will_participate=:x33,participation_level=:x34,email=:x35,image=:x36 where id_number = :x37');
			$stmt->bindValue(":x1",$_POST['academic_status'],PDO::PARAM_INT);
			$stmt->bindValue(":x2",$_POST['achievements'],PDO::PARAM_STR);
			$stmt->bindValue(":x3",$_POST['behaviour_history'],PDO::PARAM_STR);
			$stmt->bindValue(":x4",$_POST['block'],PDO::PARAM_INT);
			$stmt->bindValue(":x5",$_POST['cell_phone'],PDO::PARAM_STR);
			$stmt->bindValue(":x6",$_POST['chancellorite_history'],PDO::PARAM_STR);
			$stmt->bindValue(":x7",$_POST['community_group'],PDO::PARAM_STR);
			$stmt->bindValue(":x8",$_POST['co_curricular'],PDO::PARAM_STR);
			$stmt->bindValue(":x9",$_POST['day_joined'],PDO::PARAM_STR);
			$stmt->bindValue(":x10",$_POST['dob'],PDO::PARAM_STR);
			$stmt->bindValue(":x11",$_POST['faculty'],PDO::PARAM_INT);
			$stmt->bindValue(":x12",$_POST['father_first_name'],PDO::PARAM_STR);
			$stmt->bindValue(":x13",$_POST['father_last_name'],PDO::PARAM_STR);
			$stmt->bindValue(":x14",$_POST['father_phone'],PDO::PARAM_STR);
			$stmt->bindValue(":x15",$_POST['first_name'],PDO::PARAM_STR);
			$stmt->bindValue(":x16",$_POST['hall_history'],PDO::PARAM_STR);
			$stmt->bindValue(":x17",$_POST['home_address1'],PDO::PARAM_STR);
			$stmt->bindValue(":x18",$_POST['home_address2'],PDO::PARAM_STR);
			$stmt->bindValue(":x19",$_POST['home_city'],PDO::PARAM_STR);
			$stmt->bindValue(":x20",$_POST['home_province'],PDO::PARAM_STR);
			$stmt->bindValue(":x21",$_POST['id_number'],PDO::PARAM_STR);
			$stmt->bindValue(":x22",$_POST['last_name'],PDO::PARAM_STR);
			$stmt->bindValue(":x23",$_POST['middle_name'],PDO::PARAM_STR);
			$stmt->bindValue(":x24",$_POST['mother_first_name'],PDO::PARAM_STR);
			$stmt->bindValue(":x25",$_POST['mother_last_name'],PDO::PARAM_STR);
			$stmt->bindValue(":x26",$_POST['mother_phone'],PDO::PARAM_STR);
			$stmt->bindValue(":x27",$_POST['nationality'],PDO::PARAM_INT);
			$stmt->bindValue(":x28",$_POST['previous_secondary_school'],PDO::PARAM_STR);
			$stmt->bindValue(":x29",$_POST['reason_residing'],PDO::PARAM_STR);
			$stmt->bindValue(":x30",$_POST['resident_country'],PDO::PARAM_INT);
			$stmt->bindValue(":x31",$_POST['room'],PDO::PARAM_INT);
			$stmt->bindValue(":x32",$_POST['tertiary_level'],PDO::PARAM_INT);
			$stmt->bindValue(":x33",$_POST['will_participate'],PDO::PARAM_INT);
			$stmt->bindValue(":x34",$_POST['participation_level'],PDO::PARAM_INT);
			$stmt->bindValue(":x35",$_POST['email'],PDO::PARAM_STR);
			$stmt->bindValue(":x36",$_POST['picture'],PDO::PARAM_INT);
			$stmt->bindValue(":x37",$_POST['id'],PDO::PARAM_STR);
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