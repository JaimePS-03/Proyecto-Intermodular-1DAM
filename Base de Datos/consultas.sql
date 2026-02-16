-- CONSULTAS SIMPLES DE UNA SOLA TABLA

-- Devuelve los nombres de todos los platos
SELECT nombre
FROM platos;

-- Devuelve el id y el nombre de los clientes
SELECT id, nombre
FROM CLIENTES;

-- Devuelve el id de los pedidos realizados los dias 14
SELECT id
FROM PEDIDOS
WHERE to_char(hora, 'dd') = '14';

-- Devuelve el codigo de los descuentos cual fecha de caducidad sea nula

SELECT codigo
FROM DESCUENTOS
WHERE fecha_caducidad IS NULL;

-- Devuelve el cif y nombre de los proveedores que esten en una calle

SELECT cif, nombre
FROM proveedores
WHERE lower(direccion) LIKE ('c/%');

-- ACTUALIZACIONES Y BORRADOS EN CUALQUIER TABLA

-- 3 CONSULTAS CON MAS DE 1 TABLA

-- Devuelve el nombre de los clientes, el tipo de reserva y la fecha de esta
SELECT c.nombre, r.tipo_reserva, r.fecha
FROM clientes c, reservas r
WHERE c.id = r.id_cli;

-- Devuelve el nombre de los proveedor y el nombre de los ingredientes que suministran
SELECT pro.nombre, sus.nombre_ingre
FROM proveedores pro, suministrar sus
WHERE pro.cif = sus.cif_provee;

--Muestra el nombre de la zona junto a su bartender
SELECT e.nombre, z.nombre
FROM empleados e, zonas z, servir s
WHERE e.dni = s.dni_bartender 
AND s.n_zona = z.n_zona;

-- 3 CONSULTAS USANDO FUNCIONES

-- Muestra la cantidad de reservas
SELECT count(*) AS cantidad_reservas
FROM RESERVAS;

-- Muestra la cantidad de platos con el tipo 'arroces'
SELECT count(*) AS Arroces
FROM platos
WHERE lower(tipo) LIKE 'arroces%';

-- Suma todas las existencias de los alimentos frescos
SELECT SUM(i.existencias) AS Frescos
FROM ingredientes i
WHERE upper(tipo) LIKE 'FRESCOS';

-- 2 CONSULTAS USANDO GROUP BY

-- Muestra el nombre del cliente y la cantidad de pedidos totales que ha realizado
SELECT cli.nombre, count(p.*) as pedidos_totales
FROM clientes cli, pedidos p, realizar r
WHERE cli.id = r.id_cli AND r.id_pedi = p.id
GROUP BY cli.id, cli.nombre
ORDER BY cli.id;

-- Muestra el nombre del jefe y la cantidad de empleados que tiene a su supervision
SELECT j.nombre, count(o.*) AS Mandados
FROM jefes j, organizar o
WHERE o.dni_jefe = j.dni
GROUP BY j.nombre;

-- 2 CONSULTAS UTULIZANDO SUBCONSULTAS

-- 2 CONSULTAS USANDO GROUP BY CON HAVING

-- 3 ACTUALIZACIONES USANDO SUBCONSULTAS EN WHERE Y SET