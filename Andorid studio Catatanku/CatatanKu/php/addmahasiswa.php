<?php
header('Content-type:application/json;charset=utf-8');
include "conn.php";

//$_POST['jumlah'] = '100000';
//$_POST['tanggal'] = '20/02/2000';


if(isset($_POST['jumlah']) && isset($_POST['tanggal'])){
  $nama = $_POST['jumlah'];
  $alamat = $_POST['tanggal'];


  $q=mysqli_query("INSERT INTO catatanku(jumlah, tanggal) VALUES('$jumlah','$tanggal')");
  $response = array();

  if($q){
    $response["success"] = 1;
    $response["message"] = "Data berhasil ditambah";
    echo json_encode($response);
  }
  else{
    $response["success"] = 0;
    $response["message"] = "Data gagal ditambah";
    echo json_encode($response);
  }
}
else{
  $response["success"] = -1;
  $response["message"] = "Data kosong";
  echo json_encode($response);
}

?>
