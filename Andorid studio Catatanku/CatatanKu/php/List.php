<?php

if ($_SERVER['REQUEST_METHOD']  == 'POST'){

	$kategori = $_POST['kategori'];
	$jumlah = $_POST['jumlah'];
	$tanggal = $_POST['tanggal']

	require_once('conn.php');

	$query = "INSERT INTO 'expense'(kategori, jumlah, tanggal) VALUES ('$kategori', '$jumlah', '$tanggal')";
	
	if(mysqli_query($connect, $query)){
		$response['success'] = true;
		$response['message'] = 'Successful';
	} else{
		$response['success'] = false;
		$response['message'] = 'Fail';
	}
}else{
	$response['success'] = false;
	$response['message'] = 'Error';
}

echo json_encode($response);

?>