SELECT libros.idLibros,
		libros.bibliotecaAsociada,
        libros.isbn,
        libros.nombre,
        autores.nombreCompleto,
        IF(libros.estado = 1, "DISPONIBLE", "PRESTADO") "estado",
        libros.editorial,
        libros.precio
FROM bibliotecas.libros, bibliotecas.autores
WHERE libros.idLibros = autores.idAutores;