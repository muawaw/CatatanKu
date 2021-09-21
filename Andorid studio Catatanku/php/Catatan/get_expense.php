<?php
	include "conn.php";

	$st_check = $connect->prepare("select * from expense");
	$st_check->execute();
	$rs = $st_check->get_result();
	
	$arr=array();
	while($row = $rs->fetch_assoc()) {
		array_push($arr, $row);
	}
	
	echo json_encode($arr);

?>