
ALTER TABLE `algonz`.`usuario` ADD COLUMN `teNombre` VARCHAR(100) NULL  AFTER `PwdSistema` , ADD COLUMN `teApellido1` VARCHAR(100) NULL  AFTER `teNombre` , ADD COLUMN `teApellido2` VARCHAR(100) NULL  AFTER `teApellido1`, ADD COLUMN `teEmail` VARCHAR(50) NULL  AFTER `teApellido2` ;
