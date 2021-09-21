-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 30 Apr 2020 pada 15.40
-- Versi server: 10.4.6-MariaDB
-- Versi PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `catatanku`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `expense`
--

CREATE TABLE `expense` (
  `id_expense` int(50) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `jumlah` int(50) NOT NULL,
  `tanngal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `income`
--

CREATE TABLE `income` (
  `id_income` int(50) NOT NULL,
  `kategori` varchar(50) NOT NULL,
  `jumlah` int(50) NOT NULL,
  `tanggal` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `expense`
--
ALTER TABLE `expense`
  ADD PRIMARY KEY (`id_expense`);

--
-- Indeks untuk tabel `income`
--
ALTER TABLE `income`
  ADD PRIMARY KEY (`id_income`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `expense`
--
ALTER TABLE `expense`
  MODIFY `id_expense` int(50) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `income`
--
ALTER TABLE `income`
  MODIFY `id_income` int(50) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
