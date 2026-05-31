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


-- 4 VISTAS
-- Muestra cada pedido con la mesa, la hora, la cantidad de platos y su total.
-- Esta vista se ha diseñado para ofrecer una visión rápida y resumida de cada pedido, permitiendo consultar en una sola tabla la información más importante de la gestión de pedidos.
-- Su creación se justifica porque facilita el control de ventas y ayuda a visualizar de forma clara el contenido y el importe total de cada pedido sin necesidad de hacer consultas complejas cada vez.
CREATE OR REPLACE VIEW vista_resumen_pedidos AS
SELECT 
    p.id AS id_pedido,
    p.hora,
    p.n_mesa,
    COUNT(t.n_plato) AS cantidad_platos,
    COALESCE(SUM(pl.precio), 0) AS total_pedido
FROM PEDIDOS p
LEFT JOIN TENER t ON p.id = t.id_pedido
LEFT JOIN PLATOS pl ON t.n_plato = pl.n_plato
GROUP BY p.id, p.hora, p.n_mesa;



-- Muestra por cliente cuántas reservas y pedidos ha realizado.
-- Esta vista se ha diseñado para obtener de forma conjunta la actividad de cada cliente, mostrando en una sola consulta cuántas reservas y pedidos ha realizado.
-- Su creación se justifica porque permite conocer fácilmente qué clientes usan más el restaurante, además de ser útil para estadísticas, control de actividad y futuras promociones o descuentos.
CREATE OR REPLACE VIEW vista_clientes_actividad AS
SELECT
    c.id,
    c.nombre,
    c.apellidos,
    COUNT(DISTINCT r.id) AS total_reservas,
    COUNT(DISTINCT re.id_pedi) AS total_pedidos
FROM CLIENTES c
LEFT JOIN RESERVAS r ON c.id = r.id_cli
LEFT JOIN REALIZAR re ON c.id = re.id_cli
GROUP BY c.id, c.nombre, c.apellidos;



-- 5 FUNCIONES
-- Devuelve la suma de precios de todos los platos de un pedido.
-- Esta función se ha diseñado para calcular el total económico de un pedido de forma automática.
-- Su creación se justifica porque permite reutilizar el cálculo en distintas partes del programa, evitando repetir la misma lógica cada vez que se quiera conocer el importe total de un pedido.
CREATE OR REPLACE FUNCTION fn_total_pedido(p_id_pedido INT)
RETURNS NUMERIC(8,2)
AS $$
DECLARE
    v_total NUMERIC(8,2);
BEGIN
    SELECT COALESCE(SUM(pl.precio), 0)
    INTO v_total
    FROM TENER t
    JOIN PLATOS pl ON t.n_plato = pl.n_plato
    WHERE t.id_pedido = p_id_pedido;

    RETURN v_total;
END;
$$ LANGUAGE plpgsql;



-- Devuelve el número total de pedidos hechos por un cliente.
-- Esta función se ha diseñado para consultar cuántos pedidos ha realizado un cliente concreto.
-- Su creación se justifica porque resulta útil para controlar la actividad de los clientes y para obtener información rápida sin necesidad de repetir la consulta en varias partes del proyecto.
CREATE OR REPLACE FUNCTION fn_num_pedidos_cliente(p_id_cli VARCHAR(4))
RETURNS INT
AS $$
DECLARE
    v_total INT;
BEGIN
    SELECT COUNT(*)
    INTO v_total
    FROM REALIZAR
    WHERE id_cli = p_id_cli;

    RETURN v_total;
END;
$$ LANGUAGE plpgsql;



-- Comprueba si un código de descuento existe y no está caducado.
-- Esta función se ha diseñado para validar si un descuento puede aplicarse correctamente.
-- Su creación se justifica porque evita usar códigos inválidos o caducados, mejorando el control de los descuentos y asegurando que solo se acepten promociones vigentes.
CREATE OR REPLACE FUNCTION fn_descuento_vigente(p_codigo VARCHAR(20))
RETURNS BOOLEAN
AS $$
DECLARE
    v_existe INT;
BEGIN
    SELECT COUNT(*)
    INTO v_existe
    FROM DESCUENTOS
    WHERE codigo = p_codigo
      AND (fecha_caducidad IS NULL OR fecha_caducidad >= CURRENT_DATE);

    RETURN v_existe > 0;
END;
$$ LANGUAGE plpgsql;



-- Recorre los platos de un pedido y descuenta 1 unidad de cada ingrediente asociado.
-- Esta función se ha diseñado para actualizar automáticamente las existencias de ingredientes cuando se procesa un pedido. Para ello se utiliza un cursor, recorriendo uno a uno los ingredientes asociados a los platos del pedido.
-- Su creación se justifica porque permite mantener el stock actualizado de forma automática y refleja mejor el funcionamiento real de un restaurante.
CREATE OR REPLACE FUNCTION fn_descontar_existencias_pedido(p_id_pedido INT)
RETURNS VOID
AS $$
DECLARE
    v_ingrediente VARCHAR(20);

    cur_ingredientes CURSOR FOR
        SELECT DISTINCT s.nombre_ingre
        FROM TENER t
        JOIN SUMINISTRAR s ON t.n_plato = s.n_plato
        WHERE t.id_pedido = p_id_pedido;
BEGIN
    OPEN cur_ingredientes;
    LOOP
        FETCH cur_ingredientes INTO v_ingrediente;
        EXIT WHEN NOT FOUND;

        UPDATE INGREDIENTES
        SET existencias = existencias - 1
        WHERE nombre = v_ingrediente
          AND existencias > 0;
    END LOOP;
    CLOSE cur_ingredientes;
END;
$$ LANGUAGE plpgsql;



-- 6 TRIGGERS
-- Evita borrar un cliente si tiene reservas o pedidos asociados.
-- Este trigger se ha diseñado como una validación global para impedir eliminaciones incorrectas.
-- Su creación se justifica porque evita perder información relacionada con reservas o pedidos, garantizando la integridad de los datos y evitando borrar clientes con historial en el sistema.
CREATE OR REPLACE FUNCTION trg_validar_borrado_cliente()
RETURNS TRIGGER
AS $$
BEGIN
    IF EXISTS (SELECT 1 FROM RESERVAS WHERE id_cli = OLD.id) THEN
        RAISE EXCEPTION 'No se puede borrar el cliente %, tiene reservas asociadas', OLD.id;
    END IF;

    IF EXISTS (SELECT 1 FROM REALIZAR WHERE id_cli = OLD.id) THEN
        RAISE EXCEPTION 'No se puede borrar el cliente %, tiene pedidos asociados', OLD.id;
    END IF;

    RETURN OLD;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER tg_no_borrar_cliente_con_historial
BEFORE DELETE ON CLIENTES
FOR EACH ROW
EXECUTE FUNCTION trg_validar_borrado_cliente();



-- Comprueba que la mesa indicada en el pedido exista en MESAS.
-- Este trigger se ha diseñado para validar, antes de insertar un pedido, que la mesa existe realmente.
-- Su creación se justifica porque evita introducir pedidos asociados a mesas inexistentes, manteniendo la coherencia de los datos entre tablas.
CREATE OR REPLACE FUNCTION trg_validar_mesa_pedido()
RETURNS TRIGGER
AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM MESAS
        WHERE n_mesa = NEW.n_mesa
    ) THEN
        RAISE EXCEPTION 'La mesa % no existe', NEW.n_mesa;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER tg_validar_mesa_en_pedido
BEFORE INSERT ON PEDIDOS
FOR EACH ROW
EXECUTE FUNCTION trg_validar_mesa_pedido();



-- Si no se indica hora en el pedido, asigna automáticamente la fecha y hora actuales.
-- Este trigger se ha diseñado para automatizar la inserción de la hora del pedido cuando no se introduce manualmente.
-- Su creación se justifica porque simplifica el trabajo del usuario y asegura que todos los pedidos queden registrados con una fecha y hora válidas.
CREATE OR REPLACE FUNCTION trg_asignar_hora_pedido()
RETURNS TRIGGER
AS $$
BEGIN
    IF NEW.hora IS NULL THEN
        NEW.hora := CURRENT_TIMESTAMP;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER tg_asignar_hora_pedido
BEFORE INSERT ON PEDIDOS
FOR EACH ROW
EXECUTE FUNCTION trg_asignar_hora_pedido();



-- Evita insertar un descuento caducado o inexistente en la tabla OFRECER.
-- Este trigger se ha diseñado para validar que solo se puedan asignar descuentos válidos a una reserva.
-- Su creación se justifica porque impide aplicar promociones caducadas o inexistentes, reforzando el control de los descuentos dentro del sistema.
CREATE OR REPLACE FUNCTION trg_validar_ofrecer_descuento()
RETURNS TRIGGER
AS $$
BEGIN
    IF NOT fn_descuento_vigente(NEW.codigo_des) THEN
        RAISE EXCEPTION 'El descuento % no existe o está caducado', NEW.codigo_des;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER tg_validar_descuento_ofrecer
BEFORE INSERT ON OFRECER
FOR EACH ROW
EXECUTE FUNCTION trg_validar_ofrecer_descuento();
