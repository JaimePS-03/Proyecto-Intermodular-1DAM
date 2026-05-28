<?php
require_once 'conexion.php';

try {
    $id_cliente = '9999';
    $nombre = 'Admin';
    $apellidos = 'Don Joan';
    $telefono = '600123456';
    $email = 'admin@donjoan.com';
    $password_plana = '123456';

    $password_encriptada = password_hash($password_plana, PASSWORD_BCRYPT);
    $rol = 'admin';

    $conexion->beginTransaction();

    $sql_cliente = "INSERT INTO CLIENTES (id, nombre, apellidos, telefono, email) 
                    VALUES (:id, :nombre, :apellidos, :telefono, :email)
                    ON CONFLICT (id) DO NOTHING";
    
    $stmt1 = $conexion->prepare($sql_cliente);
    $stmt1->execute([
        ':id' => $id_cliente,
        ':nombre' => $nombre,
        ':apellidos' => $apellidos,
        ':telefono' => $telefono,
        ':email' => $email
    ]);

    $sql_usuario = "INSERT INTO USUARIOS (email, password, rol, id_cliente, dni_empleado) 
                    VALUES (:email, :password, :rol, :id_cliente, NULL)
                    ON CONFLICT (email) DO NOTHING";
    
    $stmt2 = $conexion->prepare($sql_usuario);
    $stmt2->execute([
        ':email' => $email,
        ':password' => $password_encriptada,
        ':rol' => $rol,
        ':id_cliente' => $id_cliente
    ]);

    $conexion->commit();
    echo "Usuario de pruebas ('admin@donjoan.com' con clave '123456') creado con éxito";

} catch (PDOException $e) {
    if(isset($conexion)) { $conexion->rollBack(); }
    echo "Error al crear el usuario: " . $e->getMessage();
}
?>