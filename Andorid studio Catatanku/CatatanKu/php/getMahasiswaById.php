<?php

header('Content-type:application/json;charset=utf-8');
include "conn.php";

$q = mysqli_query($connect, "SELECT jumlah, tanggal FROM catatanku LIMIT 10");
$response = array();

if(mysqli_num_rows($q)>0){
 $response["data"] = array();
 while ( $r = mysqli_fetch_array($q)) {
  $catatanku = array();
  //$catatanku["id"] = $r["id"];
  $catatanku["jumlah"] = $r["jumlah"];
  $catatnku["tannggal"] = $r["tanggal"];
  array_push($response["data"], $catatanku);
 }
 $response["success"] = 1;
    $response["message"] = "Data mahasiswa berdasarkan ID berhasil dibaca";
    echo json_encode($response);
}
else{
 $response["success"] = 0;
    $response["message"] = "Tidak ada data";
    echo json_encode($response);
}
?>