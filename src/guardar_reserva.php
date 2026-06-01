<?php
session_start();
require_once 'conexion.php';

header('Content-Type: application/json; charset=utf-8');

if (!isset($_SESSION['id_cliente'])) {
    http_response_code(401);
    echo json_encode(["mensaje" => "Debes iniciar sesión para realizar una reserva."]);
    exit();
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {

    $n_personas = $_POST['guests'] ?? null;
    $tipo_reserva = $_POST['tipo'] ?? null;
    $fecha_input = $_POST['date'] ?? null;  
    $hora_input = $_POST['time'] ?? null;   

    $id_cli = $_SESSION['id_cliente'];

    if (empty($n_personas) || empty($tipo_reserva) || empty($fecha_input) || empty($hora_input)) {
        http_response_code(400);
        echo json_encode(["mensaje" => "Faltan campos obligatorios para procesar la reserva."]);
        exit();
    }

    try {
        $fecha_timestamp = $fecha_input . ' ' . $hora_input . ':00';

        $sql = "INSERT INTO RESERVAS (n_personas, tipo_reserva, fecha, id_cli) 
                VALUES (:n_personas, :tipo_reserva, :fecha, :id_cli)";
        
        $stmt = $conexion->prepare($sql);
        $stmt->execute([
            ':n_personas'   => $n_personas,
            ':tipo_reserva' => $tipo_reserva,
            ':fecha'        => $fecha_timestamp,
            ':id_cli'       => $id_cli
        ]);

        echo json_encode(["mensaje" => "¡Reserva de tipo ' " . $tipo_reserva . " ' guardada con éxito!"]);

    } catch (PDOException $e) {
        http_response_code(500);
        echo json_encode(["mensaje" => "Error en la base de datos: " . $e->getMessage()]);
    }
}
?>