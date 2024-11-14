DELIMITER //
DROP PROCEDURE IF EXISTS comprobarDisponibilidad//
CREATE PROCEDURE comprobarDisponibilidad (IN idLibro INT, OUT resultado TINYINT)
BEGIN 
	DECLARE estadoBuscar VARCHAR(20);
    SELECT estado INTO estadoBuscar
		FROM libros
        WHERE idLibros = idLibro;
	IF estadoBuscar LIKE "DISPONIBLE" THEN 
		SET resultado = 1;
	ELSE
		SET resultado = 0;
    END IF;
END//
DELIMITER ;