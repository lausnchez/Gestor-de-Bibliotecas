DELIMITER $$

CREATE TRIGGER `actualizar_estado_libro_devolucion`
AFTER UPDATE ON `bibliotecas`.`prestamos`
FOR EACH ROW
BEGIN
    -- Si el estado del préstamo cambia a 'DEVUELTO', se cambia el estado del libro a 'DISPONIBLE'
    IF NEW.estado = 'DEVUELTO' THEN
        UPDATE `bibliotecas`.`libros`
        SET `estado` = 'DISPONIBLE'
        WHERE `idLibros` = NEW.libro;
    END IF;
END$$

DELIMITER ;
