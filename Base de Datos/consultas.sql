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

UPDATE CLIENTES
SET telefono = '623423197'
WHERE id = '0010';

UPDATE PLATOS
SET precio = 12.50
WHERE n_plato = '003';

DELETE FROM CLIENTES
WHERE id = '0009';

DELETE FROM RESERVAS
WHERE id = 7;

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

-- 2 CONSULTAS UTILIZANDO SUBCONSULTAS

-- Muestra los clientes que no tengan reservas
SELECT C.id, C.nombre
FROM CLIENTES C, RESERVAS R
WHERE C.id = R.id_cli
AND C.nombre NOT IN (SELECT id_cli
                    FROM RESERVAS);

-- Muestra los platos e ingredientes que se hayan repartido por "NESTLE"
SELECT nombre
FROM platos
WHERE n_plato IN (SELECT S.n_plato
                FROM SUMINISTRAR s
                WHERE cif_provee like '%A12345678%');

-- Pedidos que tienen clientes asociados
SELECT p.id, p.n_mesa,p.hora
FROM PEDIDOS p
WHERE p.id IN (
    SELECT r.id_pedi
    FROM REALIZAR r
);

-- Clientes que han pedido un plato en concreto
SELECT c.id,c.nombre
FROM CLIENTES c
WHERE c.id IN (
    SELECT r.id_cli
    FROM REALIZAR r
    WHERE r.id_pedi IN (
        SELECT t.id_pedido
        FROM TENER t
        WHERE t.n_plato = '001'));


-- 2 CONSULTAS USANDO GROUP BY CON HAVING

-- Obten el id y el nombre de los clientes que hayn hecho más de 3 reservas en total
SELECT C.nombre, C.id, COUNT(R.id_cli)
FROM CLIENTES C, RESERVAS R
WHERE C.id = R.id_cli
GROUP BY C.nombre
HAVING COUNT(R.id_cli) > 3;

-- Muestra los dni de los empleados de los camareros y la cantidad de pedidos que han hecho, solo mostrar más de 5.
SELECT E.dni, COUNT(A.id_camarero)
FROM EMPLEADOS E, ANOTAR A
WHERE E.dni = A.id_camarero
GROUP BY E.dni
HAVING COUNT(A.id_camarero) > 5;

-- Clientes con mas de 3 pedidos
SELECT r.id_cli, COUNT(*) AS num_pedidos
FROM REALIZAR r
GROUP BY r.id_cli
HAVING COUNT(*) > 3;

-- Mesas con mas pedidos que la media
SELECT p.n_mesa, COUNT(*) AS num_pedidos
FROM PEDIDOS p
GROUP BY p.n_mesa
HAVING COUNT(*) > (SELECT AVG(cnt_pedidos)FROM (
    SELECT n_mesa, COUNT(*) AS cnt_pedidos
    FROM PEDIDOS
    GROUP BY n_mesa));

-- 3 ACTUALIZACIONES USANDO SUBCONSULTAS EN WHERE Y SET

-- Pon el precio de los platos al precio medio de los precios de los proveedores
UPDATE PLATOS p
SET precio = (
    SELECT AVG(precio)
    FROM SUMINISTRAR S
    WHERE s.n_plato = p.n_plato
);

-- Actualiza el colectivo de empleados que sean cocineros
UPDATE EMPLEADOS
SET colectivo = 'COCINA'
WHERE dni IN (
    SELECT dni_empleado
    FROM COCINERO
);

-- Actualiza el campo 'n_personas' de una mesa al máximo de personas que aparecen en reservas.
UPDATE MESAS
SET n_personas = (
    SELECT MAX(n_personas)
    FROM RESERVAS
);
