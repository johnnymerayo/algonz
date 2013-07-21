-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         5.1.33-community - MySQL Community Server (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para cog
DROP DATABASE IF EXISTS `cog`;
CREATE DATABASE IF NOT EXISTS `cog` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */;
USE `cog`;


-- Volcando estructura para tabla cog.consignatario
DROP TABLE IF EXISTS `consignatario`;
CREATE TABLE IF NOT EXISTS `consignatario` (
  `CodEmpresa` char(3) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Codigo` int(6) NOT NULL AUTO_INCREMENT,
  `CodDpto` char(3) COLLATE latin1_spanish_ci DEFAULT NULL,
  `TipoCli` varchar(6) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Tipo` char(3) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Zona` char(2) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Ruta` char(2) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NomFiscal` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NomComercial` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Alias` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Direccion` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Municipio` varchar(30) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Poblacion` varchar(30) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Isla` varchar(30) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Provincia` varchar(30) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CCAA` varchar(30) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CodPostal` varchar(7) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CodGeografico` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Pais` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `TipoDocumento` varchar(5) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Documento` varchar(14) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CodEan` varchar(17) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CodProv` varchar(6) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Teleflin1` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Teleflin2` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Teleflin3` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `TeleFax` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `TelefMovil` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `eMail` varchar(60) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Url` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Repartidor` char(2) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Tarifa` varchar(4) COLLATE latin1_spanish_ci DEFAULT NULL,
  `DtoHab` decimal(8,4) DEFAULT NULL,
  `DtoEsp` decimal(8,4) DEFAULT NULL,
  `DtoRappel` decimal(6,2) DEFAULT NULL,
  `MonOperacion` char(3) COLLATE latin1_spanish_ci DEFAULT NULL,
  `SuProvNro` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NuevoCodigo` varchar(6) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ExentoIva` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ImporteGestion` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `SinPuntos` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `RecEquiv` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `TipoRetencion` int(1) DEFAULT NULL,
  `TipoVenta` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ModoAlbaran` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CopiasAlb` int(1) DEFAULT NULL,
  `ModoFactura` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FacCliCV` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `MinimoFct` decimal(14,4) DEFAULT NULL,
  `MinimoPortes` decimal(14,4) DEFAULT NULL,
  `CopiasFct` int(2) DEFAULT NULL,
  `FmtEnvio` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Margen` decimal(6,2) DEFAULT NULL,
  `Vendedor` char(2) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ComVendedor` decimal(6,2) DEFAULT NULL,
  `CodRepres` char(2) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ComRepres` decimal(6,2) DEFAULT NULL,
  `Cobrador` char(2) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Cartera` char(2) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CodBanco` char(2) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CodCuenta` char(2) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Banco` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `DomPago` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CCC` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `IBAN` varchar(34) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Swift` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Autorizacion` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FoPago` char(2) COLLATE latin1_spanish_ci DEFAULT NULL,
  `DudosoCobro` char(1) COLLATE latin1_spanish_ci DEFAULT 'N',
  `DiaPago1` int(2) DEFAULT NULL,
  `DiaPago2` int(2) DEFAULT NULL,
  `DiaPago3` int(2) DEFAULT NULL,
  `DiaNoPagoIni` int(2) unsigned DEFAULT NULL,
  `DiaNoPagoFin` int(2) unsigned DEFAULT NULL,
  `MesNoPagoIni` int(2) unsigned DEFAULT NULL,
  `MesNoPagoFin` int(2) unsigned DEFAULT NULL,
  `ImporteMinimoRecibo` decimal(14,4) DEFAULT NULL,
  `DirecEnvio` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `NombCorreo` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `DirecCorreo` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `PobCorreo` varchar(30) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ProCorreo` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CodPostalCorreo` varchar(7) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ApdoCorreos` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `DiaDescanso` int(1) DEFAULT NULL,
  `Tratamiento` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CodAgencia` varchar(4) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Estado` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FecActualizacion` date DEFAULT NULL,
  `EmpPedidos` char(3) COLLATE latin1_spanish_ci DEFAULT NULL,
  `RefCliRepres` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ObsGeneral` text CHARACTER SET latin1,
  `ObsInterna` text CHARACTER SET latin1,
  `Riesgo` decimal(14,4) DEFAULT NULL,
  `RefRiesgo` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CtaContable` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CtaVentas` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `TipoOpeIva` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Excluir347` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `DiaVto1` int(3) DEFAULT NULL,
  `DiaVto2` int(3) DEFAULT NULL,
  `DiaVto3` int(3) DEFAULT NULL,
  `DiaVto4` int(3) DEFAULT NULL,
  `DiaVto5` int(3) DEFAULT NULL,
  `DiaVto6` int(3) DEFAULT NULL,
  `TipoPago` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CliOferta` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `PtVerde` char(1) COLLATE latin1_spanish_ci DEFAULT 'S',
  `MonImpreso` char(3) COLLATE latin1_spanish_ci DEFAULT NULL,
  `SinGastos` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `PrecioConIva` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `JefeCompras` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Gerente` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `OtrosCargos` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FechUltPed` date DEFAULT NULL,
  `FechUltVisi` date DEFAULT NULL,
  `PersonaVisi` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Documentacion` text CHARACTER SET latin1,
  `Observaciones` text CHARACTER SET latin1,
  `IntegracionTlr` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `GestionOCR` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `SinRestos` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Delegacion` char(3) COLLATE latin1_spanish_ci DEFAULT NULL,
  `DelegacionLogo` char(3) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CodCliFct` varchar(6) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Idioma` char(3) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Consignatario` varchar(10) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Iniciales` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `GestionWeb` char(1) COLLATE latin1_spanish_ci DEFAULT 'N',
  `ComisionAgente` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `OcultarWeb` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ServirEquivalentes` char(1) COLLATE latin1_spanish_ci DEFAULT NULL,
  `PasswordWeb` varchar(32) COLLATE latin1_spanish_ci DEFAULT NULL,
  `FechaAlta` date DEFAULT NULL,
  `FechaBaja` date DEFAULT NULL,
  `FechaContrato` date DEFAULT NULL,
  `FechaBloqueoFct` date DEFAULT NULL,
  `ImporteAval` decimal(14,4) DEFAULT NULL,
  `FechaInicioAval` date DEFAULT NULL,
  `FechaFinAval` date DEFAULT NULL,
  `FechaMod` date DEFAULT NULL,
  `HoraMod` time DEFAULT NULL,
  `IdExterno` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `Imagen` blob,
  `ImagenNombre` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `PlantillaAlb` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL,
  `IdUsuario` int(10) DEFAULT NULL,
  PRIMARY KEY (`Codigo`),
  KEY `FK_CON_USUARIO` (`IdUsuario`),
  CONSTRAINT `FK_CON_USUARIO` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla cog.consignatario: ~0 rows (aproximadamente)
DELETE FROM `consignatario`;
/*!40000 ALTER TABLE `consignatario` DISABLE KEYS */;
/*!40000 ALTER TABLE `consignatario` ENABLE KEYS */;


-- Volcando estructura para tabla cog.rol
DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `IdRol` int(10) NOT NULL,
  `Authority` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `DescRol` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  PRIMARY KEY (`IdRol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla cog.rol: ~0 rows (aproximadamente)
DELETE FROM `rol`;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;


-- Volcando estructura para tabla cog.usuario
DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `IdUsuario` int(10) NOT NULL,
  `IdSistema` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `PwdSistema` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `Enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla cog.usuario: ~0 rows (aproximadamente)
DELETE FROM `usuario`;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;


-- Volcando estructura para tabla cog.usuario_rol
DROP TABLE IF EXISTS `usuario_rol`;
CREATE TABLE IF NOT EXISTS `usuario_rol` (
  `IdUsuario` int(10) NOT NULL,
  `IdRol` int(10) NOT NULL,
  PRIMARY KEY (`IdUsuario`,`IdRol`),
  KEY `FK_usuario_rol_rol` (`IdRol`),
  CONSTRAINT `FK_usuario_rol_rol` FOREIGN KEY (`IdRol`) REFERENCES `rol` (`IdRol`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_usuario_rol_usuario` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`IdUsuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- Volcando datos para la tabla cog.usuario_rol: ~0 rows (aproximadamente)
DELETE FROM `usuario_rol`;
/*!40000 ALTER TABLE `usuario_rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_rol` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
