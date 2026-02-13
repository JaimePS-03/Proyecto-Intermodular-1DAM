-- CONSULTAS SIMPLES DE UNA SOLA TABLA

-- Devuelve los nombres de todos los platos
SELECT nombre
FROM platos;
-- 78 filas

-- Devuelve el id y el nombre de los clientes
SELECT id, nombre
FROM CLIENTES;
-- 30 filas

-- Devuelve el id de los pedidos realizados los dias 14
SELECT id
FROM PEDIDOS
WHERE to_char(hora, 'dd') = '14';
-- 1 fila

-- Devuelve el codigo de los descuentos cual fecha de caducidad sea nula

SELECT codigo
FROM DESCUENTOS
WHERE fecha_caducidad IS NULL;
-- 4 filas

-- Devuelve el cif y nombre de los proveedores que esten en una calle

SELECT cif, nombre
FROM proveedores
WHERE lower(direccion) LIKE ('c/%');
-- 7 filas

-- ACTUALIZACIONES Y BORRADOS EN CUALQUIER TABLA

-- 3 CONSULTAS CON MAS DE 1 TABLA

-- 3 CONSULTAS USANDO FUNCIONES

-- 2 CONSULTAS USANDO GROUP BY

-- 2 CONSULTAS UTULIZANDO SUBCONSULTAS

-- 2 CONSULTAS USANDO GROUP BY CON HAVING

-- 3 ACTUALIZACIONES USANDO SUBCONSULTAS EN WHERE Y SET