-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema accesos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema accesos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `accesos` DEFAULT CHARACTER SET latin1 ;
USE `accesos` ;

-- -----------------------------------------------------
-- Table `accesos`.`jornadas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accesos`.`jornadas` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `lunes` VARCHAR(45) NOT NULL,
  `martes` VARCHAR(45) NOT NULL,
  `miercoles` VARCHAR(45) NOT NULL,
  `jueves` VARCHAR(45) NOT NULL,
  `viernes` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `especial` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `accesos`.`empleados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accesos`.`empleados` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellidos` VARCHAR(100) NOT NULL,
  `dni` VARCHAR(45) NOT NULL,
  `identificador` VARCHAR(100) NULL DEFAULT NULL,
  `fecha_alta` DATETIME NULL DEFAULT NULL,
  `fecha_baja` DATETIME NULL DEFAULT NULL,
  `jornadas_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `dni` (`dni` ASC),
  UNIQUE INDEX `IDENTIFICADOR` (`identificador` ASC),
  INDEX `fk_empleados_jornadas1_idx` (`jornadas_id` ASC),
  CONSTRAINT `fk_empleados_jornadas1`
    FOREIGN KEY (`jornadas_id`)
    REFERENCES `accesos`.`jornadas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `accesos`.`estados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accesos`.`estados` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `descripcion` VARCHAR(45) NOT NULL,
  `tipo` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `accesos`.`calendarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accesos`.`calendarios` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` DATETIME NOT NULL,
  `estados_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_calendario_estados_idx` (`estados_id` ASC),
  CONSTRAINT `fk_calendario_estados`
    FOREIGN KEY (`estados_id`)
    REFERENCES `accesos`.`estados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `accesos`.`USUARIOS_ESTADOS`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accesos`.`USUARIOS_ESTADOS` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `EMPLEADOS_ID` INT(11) NOT NULL,
  `ESTADOS_ID` INT(11) NOT NULL,
  `CALENDARIOS_ID` INT(11) NOT NULL,
  `JORNADAS_ID` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unico` (`EMPLEADOS_ID` ASC, `CALENDARIOS_ID` ASC),
  INDEX `fk__estad_idx` (`ESTADOS_ID` ASC),
  INDEX `fk_usuario_idx` (`EMPLEADOS_ID` ASC),
  INDEX `fk_calendario_idx` (`CALENDARIOS_ID` ASC),
  INDEX `FXJORNADAS_idx` (`JORNADAS_ID` ASC),
  CONSTRAINT `FXJORNADAS`
    FOREIGN KEY (`JORNADAS_ID`)
    REFERENCES `accesos`.`jornadas` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkempleados`
    FOREIGN KEY (`EMPLEADOS_ID`)
    REFERENCES `accesos`.`empleados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkestado`
    FOREIGN KEY (`ESTADOS_ID`)
    REFERENCES `accesos`.`estados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `flcalendario`
    FOREIGN KEY (`CALENDARIOS_ID`)
    REFERENCES `accesos`.`calendarios` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `accesos`.`accesos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `accesos`.`accesos` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `year` INT(11) NOT NULL,
  `empleados_id` INT(11) NOT NULL,
  `month` INT(11) NOT NULL,
  `day` INT(11) NOT NULL,
  `minuto` INT(11) NOT NULL,
  `hora` INT(11) NOT NULL,
  `tipo` INT(11) NOT NULL,
  `fecha` DATETIME NOT NULL,
  `horaReal` INT(11) NOT NULL DEFAULT '0',
  `minutoReal` INT(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `unico` (`year` ASC, `empleados_id` ASC, `month` ASC, `day` ASC, `minuto` ASC, `hora` ASC),
  INDEX `fk_accesos_empleados_idx` (`empleados_id` ASC),
  CONSTRAINT `fk_accesos_empleados`
    FOREIGN KEY (`empleados_id`)
    REFERENCES `accesos`.`empleados` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
