<?php
header('Content-type:application/json;charset=utf-8');
include "conn.php";

if(isset($_POST['id']) && isset($_POST['jumlah']) && isset($_POST['tanggal'])){
	$id = $_POST['id'];
	$nama = $_POST['jumlah'];
	$alamat = $_POST['tanggal'];

	$q=mysqli_query($connect, "DELETE FROM mahasiswa WHERE id='$id'");
	$response = array();

	if($q){
		$response["success"] = 1;
		$response["message"] = "Data berhasil dihapus";
		echo json_encode($response);
	}
	else{
		$response["success"] = 0;
		$response["message"] = "Data gagal dihapus";
		echo json_encode($response);
	}
}
else {
	$response["success"] = -1;
	$response["message"] = "Data Kosong";
		echo json_encode($response);
}

?>