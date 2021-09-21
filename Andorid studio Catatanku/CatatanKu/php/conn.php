<?php

define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','catatanku');

$connect = mysqli_connect(HOST,USER,PASS,DB) or die ("Koneksi gagal");

if($connect){
    echo "bener";
}

?>
