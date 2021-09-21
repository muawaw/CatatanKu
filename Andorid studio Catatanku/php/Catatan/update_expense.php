<?php

// update field dari table expense berdasarkan id expense
// <alamat ip server>/catatan/update_expense.php?id_expense=1&kategori=jajan&jumlah=30000&tanggal=2020-05-04

// ini file buat mengakses database conn.php
include "conn.php";

// Mengambil parameter yang berasal dari url yang dikirim dari android (id_expense, kategori, jumlah tanggal)
// setalah tanda tanya pada url tiap parameter dipisahkan dengan simbol &
if(isset($_GET['id']) && isset($_GET['kategori']) && isset($_GET['jumlah']) && isset($_GET['tanggal'])){
  $id = $_GET['id'];
  $kategori = $_GET['kategori'];
  $jumlah = $_GET['jumlah'];
  $tanggal = $_GET['tanggal'];

  $hasil_query=mysqli_query($connect, "UPDATE expense SET kategori='$kategori', jumlah='$jumlah', tanggal='$tanggal' WHERE id_expense='$id'");
  $response = array();

  if($hasil_query){
		// Jika query database berhasil mengirimkan respon http code 200, berserta pesan
		$response["code"] = 200;
		$response["message"] = "Data berhasil diupdate";
		
		// Data variable response diformat kedalam tipe data JSON
		// echo mirip seperti System.out.print("blablabla");
		// yang berarti mengirimkan data melalui url ke aplikasi android
		echo json_encode($response);
	}
	else{
		// Jika query database gagal mengirimkan respon http code 422, berserta pesan
		$response["code"] = 422;
		$response["message"] = "Data gagal diupdate";
		
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