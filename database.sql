-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: baitaplon
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ban`
--

DROP TABLE IF EXISTS `ban`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ban` (
  `STT` int NOT NULL AUTO_INCREMENT,
  `maBan` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `sucChua` int NOT NULL,
  `tinhTrang` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`STT`,`maBan`),
  UNIQUE KEY `maBan_UNIQUE` (`maBan`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ban`
--

LOCK TABLES `ban` WRITE;
/*!40000 ALTER TABLE `ban` DISABLE KEYS */;
INSERT INTO `ban` VALUES (1,'B001',15,'Trong'),(2,'B002',20,'Da su dung'),(3,'B003',25,'Da su dung'),(4,'B004',20,'Trong'),(5,'B005',25,'Trong'),(6,'B006',18,'Trong'),(7,'B007',15,'Trong'),(8,'B008',15,'Trong'),(9,'B009',20,'Trong'),(10,'B010',11,'Trong'),(11,'B011',15,'Trong');
/*!40000 ALTER TABLE `ban` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bophan`
--

DROP TABLE IF EXISTS `bophan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bophan` (
  `maBP` int NOT NULL AUTO_INCREMENT,
  `tenBP` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`maBP`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bophan`
--

LOCK TABLES `bophan` WRITE;
/*!40000 ALTER TABLE `bophan` DISABLE KEYS */;
INSERT INTO `bophan` VALUES (1,'Tiep tan'),(2,'Phuc vu'),(3,'Quan ly'),(4,'Pha che');
/*!40000 ALTER TABLE `bophan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chitiethd`
--

DROP TABLE IF EXISTS `chitiethd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chitiethd` (
  `maHD` int NOT NULL,
  `maSP` int NOT NULL,
  `soLuong` int NOT NULL,
  PRIMARY KEY (`maHD`,`maSP`),
  KEY `fk_chitiethd_hoadon_idx` (`maHD`),
  KEY `fk_chitiethd_sanpham_idx` (`maSP`),
  CONSTRAINT `fk_chitiethd_hoadon` FOREIGN KEY (`maHD`) REFERENCES `hoadon` (`maHD`),
  CONSTRAINT `fk_chitiethd_sanpham` FOREIGN KEY (`maSP`) REFERENCES `sanpham` (`maSP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chitiethd`
--

LOCK TABLES `chitiethd` WRITE;
/*!40000 ALTER TABLE `chitiethd` DISABLE KEYS */;
INSERT INTO `chitiethd` VALUES (18,3,9),(18,4,15),(18,10,2),(19,5,3);
/*!40000 ALTER TABLE `chitiethd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `danhmuc`
--

DROP TABLE IF EXISTS `danhmuc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `danhmuc` (
  `maDM` int NOT NULL AUTO_INCREMENT,
  `tenDM` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`maDM`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `danhmuc`
--

LOCK TABLES `danhmuc` WRITE;
/*!40000 ALTER TABLE `danhmuc` DISABLE KEYS */;
INSERT INTO `danhmuc` VALUES (1,'Thuc An'),(2,'Thuc Uong');
/*!40000 ALTER TABLE `danhmuc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hoadon`
--

DROP TABLE IF EXISTS `hoadon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoadon` (
  `maHD` int NOT NULL AUTO_INCREMENT,
  `maBan` char(4) COLLATE utf8_unicode_ci NOT NULL,
  `ngayDat` date NOT NULL,
  PRIMARY KEY (`maHD`),
  KEY `fk_hoadon_ban_idx` (`maBan`),
  CONSTRAINT `fk_hoadon_ban` FOREIGN KEY (`maBan`) REFERENCES `ban` (`maBan`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoadon`
--

LOCK TABLES `hoadon` WRITE;
/*!40000 ALTER TABLE `hoadon` DISABLE KEYS */;
INSERT INTO `hoadon` VALUES (18,'B003','2015-05-20'),(19,'b002','2015-05-15');
/*!40000 ALTER TABLE `hoadon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `maNV` int NOT NULL AUTO_INCREMENT,
  `hoTenNV` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `gioiTinh` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `queQuan` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `ngaySinh` date NOT NULL,
  `ngayVaoLam` date NOT NULL,
  `maBP` int NOT NULL,
  PRIMARY KEY (`maNV`),
  KEY `fk_nhanvien_bophan_idx` (`maBP`),
  CONSTRAINT `fk_nhanvien_bophan` FOREIGN KEY (`maBP`) REFERENCES `bophan` (`maBP`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (2,'Pham Nguyen Phuong Uyen','Nu','Tien Giang','2000-02-25','2015-08-18',1),(4,'Pham Hoang Tam','Nam','Can Tho','2000-04-03','2015-02-01',3),(5,'Nguyen Ngoc Thanh','Nam','Tien Giang','2000-05-26','2016-07-13',4),(8,'Nguyen Dang Huy','Nam','TP.HCM','2000-05-06','2015-07-03',3),(9,'Tran Nguyen Lan Anh','Nu','Dong Thap','2000-07-31','2015-06-20',1),(10,'Nguyen Ngoc Diep','Nu','Dong Thap','2000-05-26','2015-03-07',2),(12,'Dam Thi Yen Xuan','Nu','Dong Thap','2000-05-16','2000-07-13',3),(13,'Le Thanh Phong','Nam','Dong Thap','2000-06-25','2015-05-18',3),(17,'Phan Thanh Vi','Nam','Dong Thap','2015-05-15','2018-06-12',5),(18,'Phan Thanh Vi','Nam','Dong Thap','2000-06-15','2015-07-18',5),(24,'Phan Thanh Vi','Nam','Dong Thap','2000-07-31','2015-05-25',4),(27,'Phan Thanh Vi','Nam','15/06/2000','2015-06-19','2015-06-20',3);
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanpham`
--

DROP TABLE IF EXISTS `sanpham`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanpham` (
  `maSP` int NOT NULL AUTO_INCREMENT,
  `tenSP` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tinhTrang` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `thoiDiemBan` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `maDM` int NOT NULL,
  `giaBan` double NOT NULL,
  `nuocDa` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `anChay` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`maSP`),
  KEY `fk_sanpham_danhmuc_idx` (`maDM`),
  CONSTRAINT `fk_sanpham_danhmuc` FOREIGN KEY (`maDM`) REFERENCES `danhmuc` (`maDM`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanpham`
--

LOCK TABLES `sanpham` WRITE;
/*!40000 ALTER TABLE `sanpham` DISABLE KEYS */;
INSERT INTO `sanpham` VALUES (1,'My cay','Con','Chieu',1,25000,NULL,'Khong an chay'),(2,'Tra sua','Con','Trua - Chieu',2,15000,'Co da',NULL),(3,'Ga ran','Con','Sang',1,20000,NULL,'Khong an chay'),(4,'Number one','Con','Sang',2,20000,'Co da',NULL),(5,'My Spagetti','Con','Sang',1,15000,NULL,'Khong an chay'),(6,'Cafe sua','Con','Sang - Trua',2,15000,'Co da',NULL),(7,'Banh mi','Con','Sang ',1,12000,NULL,'Khong an chay'),(8,'Banh bao','Con','Sang ',1,10000,NULL,'An chay duoc'),(9,'Soda','Con','Ca ngay',2,18000,'Co da',NULL),(10,'Sua nong','Con','Sang',2,15000,'Khong da',NULL),(11,'Hong tra','Con','Ca ngay',2,10000,'Co da',NULL);
/*!40000 ALTER TABLE `sanpham` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-17  0:49:57
