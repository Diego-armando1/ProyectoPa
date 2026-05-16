-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Provincia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Provincia` (
  `idProvincia` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idProvincia`),
  UNIQUE INDEX `nombre_UNIQUE` (`Nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Ciudad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Ciudad` (
  `idCiudad` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `Tarifa_base_kg` DECIMAL NOT NULL,
  `idProvincia` INT NOT NULL,
  PRIMARY KEY (`idCiudad`),
  INDEX `fk_Ciudad_Provincia_idx` (`idProvincia` ASC) VISIBLE,
  CONSTRAINT `fk_Ciudad_Provincia`
    FOREIGN KEY (`idProvincia`)
    REFERENCES `mydb`.`Provincia` (`idProvincia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(100) NOT NULL,
  `Cedula` VARCHAR(15) NOT NULL,
  `Telefono` VARCHAR(20) NULL,
  `Direccion` TEXT NOT NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `Cedula_UNIQUE` (`Cedula` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Trabajador`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Trabajador` (
  `idTrabajador` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Tipo_trabajadorl` ENUM('Recepcionista', 'Operador', 'Repartidor') NOT NULL,
  PRIMARY KEY (`idTrabajador`),
  UNIQUE INDEX `Username_UNIQUE` (`Username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Paquete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Paquete` (
  `idPaquete` INT NOT NULL AUTO_INCREMENT,
  `Codigo_seguimiento` VARCHAR(45) NOT NULL,
  `Direccion_entrega` VARCHAR(250) NOT NULL,
  `Peso` DECIMAL NOT NULL,
  `Tipo_envio` ENUM('Estandar', 'Express', 'Fragil') NOT NULL DEFAULT 'Estandar',
  `Costo_total` DECIMAL NOT NULL,
  `Estado_actual` ENUM('Registrado', 'En tránsito', 'Entregado') NOT NULL DEFAULT 'Registrado',
  `Fecha_registro` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idRemitente` INT NOT NULL,
  `idDestinatario` INT NOT NULL,
  `idCiudad` INT NOT NULL,
  PRIMARY KEY (`idPaquete`),
  UNIQUE INDEX `Codigo_seguimiento_UNIQUE` (`Codigo_seguimiento` ASC) VISIBLE,
  INDEX `fk_Paquete_Cliente1_idx` (`idRemitente` ASC) VISIBLE,
  INDEX `fk_Paquete_Cliente2_idx` (`idDestinatario` ASC) VISIBLE,
  INDEX `fk_Paquete_Ciudad1_idx` (`idCiudad` ASC) VISIBLE,
  CONSTRAINT `fk_Paquete_Cliente1`
    FOREIGN KEY (`idRemitente`)
    REFERENCES `mydb`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paquete_Cliente2`
    FOREIGN KEY (`idDestinatario`)
    REFERENCES `mydb`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Paquete_Ciudad1`
    FOREIGN KEY (`idCiudad`)
    REFERENCES `mydb`.`Ciudad` (`idCiudad`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Historial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Historial` (
  `idHistorial` INT NOT NULL AUTO_INCREMENT,
  `Estado` VARCHAR(45) NOT NULL,
  `Fecha_hora` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Observacion` TEXT NOT NULL,
  `Nombre_receptor` VARCHAR(45) NOT NULL,
  `idPaquete` INT NOT NULL,
  `idTrabajador` INT NOT NULL,
  PRIMARY KEY (`idHistorial`),
  INDEX `fk_Historial_Paquete1_idx` (`idPaquete` ASC) VISIBLE,
  INDEX `fk_Historial_Trabajador1_idx` (`idTrabajador` ASC) VISIBLE,
  CONSTRAINT `fk_Historial_Paquete1`
    FOREIGN KEY (`idPaquete`)
    REFERENCES `mydb`.`Paquete` (`idPaquete`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Historial_Trabajador1`
    FOREIGN KEY (`idTrabajador`)
    REFERENCES `mydb`.`Trabajador` (`idTrabajador`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
