-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: proyectoFinal
-- ------------------------------------------------------
-- Server version	5.7.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `mantenimiento`
--
USE proyectoFinal;

DROP TABLE IF EXISTS `mantenimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mantenimiento` (
  `codMantenimiento` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codMantenimiento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mantenimiento`
--

LOCK TABLES `mantenimiento` WRITE;
/*!40000 ALTER TABLE `mantenimiento` DISABLE KEYS */;
INSERT INTO `mantenimiento` VALUES (1,'limpieza'),(2,'DRT'),(3,'CHH');
/*!40000 ALTER TABLE `mantenimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maquina`
--

DROP TABLE IF EXISTS `maquina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `maquina` (
  `codMaquina` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codMaquina`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maquina`
--

LOCK TABLES `maquina` WRITE;
/*!40000 ALTER TABLE `maquina` DISABLE KEYS */;
INSERT INTO `maquina` VALUES (1,'Prensa grande'),(2,'Prensa pequeña'),(3,'Soldador');
/*!40000 ALTER TABLE `maquina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarea`
--

DROP TABLE IF EXISTS `tarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tarea` (
  `codTarea` int(11) NOT NULL,
  `descripcion` varchar(45) DEFAULT NULL,
  `maquina_codMaquina` int(11) DEFAULT NULL,
  PRIMARY KEY (`codTarea`),
  KEY `fk_tarea_maquina2_idx` (`maquina_codMaquina`),
  CONSTRAINT `fk_tarea_maquina2` FOREIGN KEY (`maquina_codMaquina`) REFERENCES `maquina` (`codMaquina`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarea`
--

LOCK TABLES `tarea` WRITE;
/*!40000 ALTER TABLE `tarea` DISABLE KEYS */;
INSERT INTO `tarea` VALUES (1,'Soldadura de ejes',NULL),(2,'Soldadura de ejes avanzada',NULL),(3,'Poner remaches',2),(4,'Prensar acero',1),(5,'Fundir acero',NULL);
/*!40000 ALTER TABLE `tarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajador`
--

DROP TABLE IF EXISTS `trabajador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trabajador` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `apellido2` varchar(45) DEFAULT NULL,
  `foto` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajador`
--

LOCK TABLES `trabajador` WRITE;
/*!40000 ALTER TABLE `trabajador` DISABLE KEYS */;
INSERT INTO `trabajador` VALUES ('67873412S','Amaia','Urcelay','Perez',NULL),('72768720R','Diego','Garcia','Ramos',NULL),('76879832S','Pilar','Lopez','Fernandez',NULL),('87561234J','Itziar','Poza','Garcia',NULL);
/*!40000 ALTER TABLE `trabajador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajo`
--

DROP TABLE IF EXISTS `trabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trabajo` (
  `trabajador_dni` varchar(9) NOT NULL,
  `tarea_codTarea` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `tiempo` varchar(45) DEFAULT NULL,
  `codTrabajo` int(11) NOT NULL,
  PRIMARY KEY (`trabajador_dni`,`tarea_codTarea`,`codTrabajo`),
  KEY `fk_trabajador_has_tarea_tarea2_idx` (`tarea_codTarea`),
  KEY `fk_trabajador_has_tarea_trabajador2_idx` (`trabajador_dni`),
  CONSTRAINT `fk_trabajador_has_tarea_tarea2` FOREIGN KEY (`tarea_codTarea`) REFERENCES `tarea` (`codTarea`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_trabajador_has_tarea_trabajador2` FOREIGN KEY (`trabajador_dni`) REFERENCES `trabajador` (`dni`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajo`
--

LOCK TABLES `trabajo` WRITE;
/*!40000 ALTER TABLE `trabajo` DISABLE KEYS */;
INSERT INTO `trabajo` VALUES ('72768720R',1,'2019-01-01','1h',1),('72768720R',4,'2020-01-26','0:15',5),('72768720R',4,'2020-01-26','31',8),('76879832S',3,'2020-01-26','1:15',4),('76879832S',3,'2020-01-26','12',7),('76879832S',3,'2020-01-26','1:18',10),('87561234J',2,'2019-01-01','22',2),('87561234J',2,'2020-01-26','32',3),('87561234J',5,'2020-01-26','1',6),('87561234J',5,'2020-01-26','2',9);
/*!40000 ALTER TABLE `trabajo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trabajoMantenimiento`
--

DROP TABLE IF EXISTS `trabajoMantenimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trabajoMantenimiento` (
  `mantenimiento_codMantenimiento` int(11) NOT NULL,
  `trabajador_dni` varchar(9) NOT NULL,
  `fecha` date DEFAULT NULL,
  `tiempo` varchar(45) DEFAULT NULL,
  `codTrabajoMantenimiento` int(11) NOT NULL,
  PRIMARY KEY (`mantenimiento_codMantenimiento`,`trabajador_dni`,`codTrabajoMantenimiento`),
  KEY `fk_mantenimiento_has_trabajador_trabajador1_idx` (`trabajador_dni`),
  KEY `fk_mantenimiento_has_trabajador_mantenimiento1_idx` (`mantenimiento_codMantenimiento`),
  CONSTRAINT `fk_mantenimiento_has_trabajador_mantenimiento1` FOREIGN KEY (`mantenimiento_codMantenimiento`) REFERENCES `mantenimiento` (`codMantenimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mantenimiento_has_trabajador_trabajador1` FOREIGN KEY (`trabajador_dni`) REFERENCES `trabajador` (`dni`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trabajoMantenimiento`
--

LOCK TABLES `trabajoMantenimiento` WRITE;
/*!40000 ALTER TABLE `trabajoMantenimiento` DISABLE KEYS */;
INSERT INTO `trabajoMantenimiento` VALUES (1,'72768720R','2019-01-01','1:00',1),(2,'72768720R','2020-01-26','31',3),(3,'76879832S','2020-01-26','12',2),(3,'76879832S','2020-01-26','1:15',5),(3,'87561234J','2020-01-26','2',4);
/*!40000 ALTER TABLE `trabajoMantenimiento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-26 18:26:42
