SELECT `bibliotecas`.`idBibliotecas`,
		`bibliotecas`.`nombre`,
		`bibliotecario`.`idBibliotecario`,
        `usuarios`.`nombreUsuario`,
        `bibliotecario`.`nombre`,
        `bibliotecario`.`apellidos`
FROM bibliotecas, bibliotecario, usuarios
WHERE `bibliotecas`.`idBibliotecas` = `bibliotecario`.`bibliotecaAsociada`
AND `bibliotecario`.`idBibliotecario` = `usuarios`.`idUsuarios`
ORDER BY `bibliotecas`.`idBibliotecas` ASC
;