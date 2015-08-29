<?php
$response = array();
if(isset($_POST['sid']))
{
	session_id($_POST['sid']);
	session_start();
	if(isset($_SESSION['user']))
	{
		$response['data'] = "";
		$error = false;
		if(isset($_POST['academic_status']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."academic_status ";
			$error = true;
		}
		if(isset($_POST['achievements']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."achievements ";
			$error = true;
		}
		if(isset($_POST['behaviour_history']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."behaviour_history ";
			$error = true;
		}
		if(isset($_POST['block']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."block ";
			$error = true;
		}
		if(isset($_POST['cell_phone']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."cell_phone ";
			$error = true;
		}
		if(isset($_POST['family_history']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."family_history ";
			$error = true;
		}
		if(isset($_POST['community_group']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."community_group ";
			$error = true;
		}
		if(isset($_POST['co_curricular']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."co_curricular ";
			$error = true;
		}
		if(isset($_POST['day_joined']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."day_joined ";
			$error = true;
		}
		if(isset($_POST['dob']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."dob ";
			$error = true;
		}if(isset($_POST['faculty']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."faculty ";
			$error = true;
		}
		if(isset($_POST['father_first_name']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."father_first_name ";
			$error = true;
		}
		if(isset($_POST['father_last_name']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."father_last_name ";
			$error = true;
		}
		if(isset($_POST['father_phone']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."father_phone ";
			$error = true;
		}
		if(isset($_POST['first_name']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."first_name ";
			$error = true;
		}
		if(isset($_POST['hall_history']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."hall_history ";
			$error = true;
		}
		if(isset($_POST['home_address1']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."home_address1 ";
			$error = true;
		}if(isset($_POST['home_address2']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."home_address2 ";
			$error = true;
		}if(isset($_POST['home_city']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."home_city ";
			$error = true;
		}if(isset($_POST['home_province']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."home_province ";
			$error = true;
		}if(isset($_POST['id_number']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."id_number ";
			$error = true;
		}if(isset($_POST['last_name']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."last_name ";
			$error = true;
		}if(isset($_POST['middle_name']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."middle_name ";
			$error = true;
		}if(isset($_POST['mother_first_name']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."mother_first_name ";
			$error = true;
		}if(isset($_POST['mother_last_name']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."mother_last_name ";
			$error = true;
		}if(isset($_POST['mother_phone']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."mother_phone ";
			$error = true;
		}if(isset($_POST['nationality']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."nationality ";
			$error = true;
		}if(isset($_POST['previous_secondary_school']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."previous_secondary_school ";
			$error = true;
		}if(isset($_POST['reason_residing']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."reason_residing ";
			$error = true;
		}if(isset($_POST['resident_country']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."resident_country ";
			$error = true;
		}if(isset($_POST['room']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."room ";
			$error = true;
		}if(isset($_POST['tertiary_level']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."tertiary_level ";
			$error = true;
		}if(isset($_POST['will_participate']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."will_participate ";
			$error = true;
		}if(isset($_POST['participation_level']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."participation_level ";
			$error = true;
		}if(isset($_POST['email']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."email ";
			$error = true;
		}if(isset($_POST['picture']) == false){
			$response['success'] = 0;
			$response['flag']=0;
			$response['data'] =$response['data']."picture ";
			$error = true;
		}
		
		if($error == false)
		{
			
			require_once 'db_config.php';
			$db = new PDO("mysql:dbname=test_sms;host=localhost", DB_USER, DB_PASSWORD);

			$stmt = $db->prepare('INSERT INTO test_stdns(academic_status,achievements,behaviour_history, block, cell_phone,family_history,community_group,co_curricular,'.'
			day_joined,dob,faculty,father_first_name,father_last_name,father_phone,first_name,hall_history,home_address1,home_address2,home_city,home_province,id_number,last_name,middle_name,mother_first_name,mother_last_name,'.
			'mother_phone,nationality,previous_secondary_school,reason_residing,resident_country,room,tertiary_level,will_participate,participation_level,email,picture) values(:x1,:x2,:x3,:x4,:x5,:x6,:x7,:x8,:x9,:x10,:x11,:x12,:x13,:x14,:x15,:x16,:x17,:x18,:x19,:x20,:x21,:x22,:x23,:x24,:x25,:x26,:x27,:x28,:x29,:x30,:x31,:x32,:x33,:x34,:x35,:x36)');
			$stmt->bindValue(":x1",$_POST['academic_status'],PDO::PARAM_INT);
			$stmt->bindValue(":x2",$_POST['achievements'],PDO::PARAM_STR);
			$stmt->bindValue(":x3",$_POST['behaviour_history'],PDO::PARAM_STR);
			$stmt->bindValue(":x4",$_POST['block'],PDO::PARAM_INT);
			$stmt->bindValue(":x5",$_POST['cell_phone'],PDO::PARAM_STR);
			$stmt->bindValue(":x6",$_POST['family_history'],PDO::PARAM_STR);
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
			$stmt->execute();
			
				$response['success'] = 1;	
				$response['flag']=0;	
	
			$stmt = $db->prepare("insert into student_version(id_changed) values(:cid );");
			$stmt->bindValue(":cid",$_POST['id_number']);
			$stmt->execute();
			
			
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
die();

?>