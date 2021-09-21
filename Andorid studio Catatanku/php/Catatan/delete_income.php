<?php

// menghapus field dari table income berdasarkan id income
// <alamat ip server>/catatan/delete_income.php?id=3

// ini file buat mengakses database conn.php
include "conn.php";

// Mengambil parameter yang berasal dari data url yang dikirim dari android (?id=3)
if(isset($_GET['id'])) {	
	$id = $_GET['id'];

	$check_id=mysqli_query($connect, "SELECT * FROM income WHERE id_income='$id'");
	$response = array();

	if($check_id){
		// jika data tersedia dalam database, lanjutkan dengan query delete
		$hasil_query=mysqli_query($connect, "DELETE FROM income WHERE id_income='$id'");
		
		// Jika query database berhasil mengirimkan respon http code 200, berserta pesan
		$response["code"] = 200;
		$response["message"] = "Data berhasil dihapus";
		
		// Data variable response diformat kedalam tipe data JSON
		// echo mirip seperti System.out.print("blablabla");
		// yang berarti mengirimkan data melalui url ke aplikasi android
		echo json_encode($response);
	}
	else{
		// Jika query database gagal mengirimkan respon http code 422, berserta pesan
		$response["code"] = 422;
		$response["message"] = "Data tidak ditemukan";
		
		// Data variable response diformat kedalam tipe data JSON
		// echo mirip seperti System.out.print("blablabla");
		// yang berarti mengirimkan data ke aplikasi android
		echo json_encode($response);
	}
}
else {
	// data id yang dikirimkan dari android kosong, mengirimkan respon http code 422, berserta pesan
	$response["code"] = 422;
	$response["message"] = "Data Kosong";
	
	// Data variable response diformat kedalam tipe data JSON
	// echo mirip seperti System.out.print("blablabla");
	// yang berarti mengirimkan data ke aplikasi android
	echo json_encode($response);
}

?>