<?php

define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','catatan');

$connect = mysqli_connect(HOST,USER,PASS,DB) or die ("Koneksi gagal");

?>
