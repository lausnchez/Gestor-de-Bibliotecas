-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Bibliotecas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Bibliotecas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Bibliotecas` DEFAULT CHARACTER SET utf8 ;
USE `Bibliotecas` ;

-- -----------------------------------------------------
-- Table `Bibliotecas`.`Clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`Clientes` (
  `idCliente` INT NOT NULL,
  `bibliotecaAsociada` INT NOT NULL,
  `dni` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `provincia` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `calle` VARCHAR(45) NOT NULL,
  `numSanciones` INT NOT NULL DEFAULT 0,
  `cuentaBancaria` VARCHAR(45) NOT NULL,
  `pago` TINYINT NOT NULL,
  PRIMARY KEY (`idCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bibliotecas`.`Libros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`Libros` (
  `idLibros` INT NOT NULL,
  `bibliotecaAsociada` INT NOT NULL,
  `isbn` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `autor` INT NOT NULL,
  `editorial` VARCHAR(45) NOT NULL,
  `precio` INT NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idLibros`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bibliotecas`.`Bibliotecas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`Bibliotecas` (
  `idBibliotecas` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `provincia` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `calle` VARCHAR(45) NOT NULL,
  `telefono` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `administrador` VARCHAR(45) NOT NULL,
  `bibliotecario1` VARCHAR(45) NOT NULL,
  `bibliotecario2` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idBibliotecas`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bibliotecas`.`Prestamos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`Prestamos` (
  `idPrestamos` INT NOT NULL,
  `usuario` INT NOT NULL,
  `biblioteca` INT NOT NULL,
  `fecha` VARCHAR(45) NOT NULL,
  `libro` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPrestamos`, `usuario`, `biblioteca`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bibliotecas`.`Bibliotecario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`Bibliotecario` (
  `idBibliotecario` INT NOT NULL,
  `bibliotecaAsociada` INT NOT NULL,
  `dni` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `usuario` INT NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idBibliotecario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bibliotecas`.`Administradores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`Administradores` (
  `idAdministradores` INT NOT NULL,
  `bibliotecaAsociada` INT NOT NULL,
  `dni` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `usuario` INT NOT NULL,
  PRIMARY KEY (`idAdministradores`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bibliotecas`.`AntiguosClientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`AntiguosClientes` (
  `idAntiguoCliente` INT NOT NULL,
  `bibliotecaAsociada` INT NOT NULL,
  `dni` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `provincia` VARCHAR(45) NOT NULL,
  `ciudad` VARCHAR(45) NOT NULL,
  `calle` VARCHAR(45) NOT NULL,
  `cuentaBancaria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAntiguoCliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bibliotecas`.`InformePagos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`InformePagos` (
  `idInformePagos` INT NOT NULL,
  `cliente` INT NOT NULL,
  `pagoRealizado` TINYINT NOT NULL,
  PRIMARY KEY (`idInformePagos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bibliotecas`.`Sanciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`Sanciones` (
  `idSanciones` INT NOT NULL,
  `fecha` DATE NOT NULL,
  `prestamo` INT NOT NULL,
  `bibliotecaAsociada` INT NOT NULL,
  `cliente` INT NOT NULL,
  `libro` INT NOT NULL,
  `tipo` VARCHAR(45) NOT NULL,
  `precio` DECIMAL NOT NULL,
  `estado` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idSanciones`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bibliotecas`.`Autores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`Autores` (
  `idAutores` INT NOT NULL,
  `nombreCompleto` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`idAutores`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bibliotecas`.`Usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`Usuarios` (
  `idUsuarios` INT NOT NULL,
  `nombreUsuario` VARCHAR(45) NOT NULL,
  `almacen` INT NOT NULL,
  PRIMARY KEY (`idUsuarios`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bibliotecas`.`Almacen`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bibliotecas`.`Almacen` (
  `idAlmacen` INT NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAlmacen`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
