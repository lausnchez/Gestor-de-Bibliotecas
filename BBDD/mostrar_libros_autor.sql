SELECT `libros`.`idLibros`,
    `libros`.`bibliotecaAsociada`,
    `libros`.`isbn`,
    `libros`.`nombre`,
    `autores`.`nombreCompleto`,
    `libros`.`editorial`,
    `libros`.`precio`,
    `libros`.`estado`
FROM `bibliotecas`.`libros`, `bibliotecas`.`autores`
WHERE `libros`.`autor` = `autores`.`idAutores`;
