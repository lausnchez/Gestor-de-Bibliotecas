DELIMITER $$

CREATE TRIGGER `actualizar_estado_libro_prestamo`
AFTER INSERT ON `bibliotecas`.`prestamos`
FOR EACH ROW
BEGIN
    -- Cuando se hace un préstamo, se cambia el estado del libro a 'PRESTADO'
    UPDATE `bibliotecas`.`libros`
    SET `estado` = 'PRESTADO'
    WHERE `idLibros` = NEW.libro;
END$$

DELIMITER ;

