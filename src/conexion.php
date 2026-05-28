<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

$host = "localhost";
$port = "5432";
$db_name = "DonJoan"; 
$username = "postgres";
$password = "1234"; 

try {
    $conexion = new PDO("pgsql:host=$host;port=$port;dbname=$db_name", $username, $password);
    $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    $conexion->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);
    
    // echo json_encode(["mensaje" => "Conexión exitosa a la base de datos DonJoan"]);
} catch (PDOException $exception) {
    http_response_code(500);
    echo json_encode(["mensaje" => "Error de conexión (PostgreSQL): " . $exception->getMessage()]);
    exit();
}
?>