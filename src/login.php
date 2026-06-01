<?php
require_once 'conexion.php';

if ($_SERVER['REQUEST_METHOD'] !== 'POST') {
    http_response_code(405);
    echo json_encode(["mensaje" => "Método no permitido"]);
    exit();
}

$email_input = $_POST['email'] ?? '';
$password_input = $_POST['password'] ?? '';

if (empty($email_input) || empty($password_input)) {
    http_response_code(400);
    echo json_encode(["mensaje" => "Por favor, rellena todos los campos."]);
    exit();
}

try {
    $sql = "SELECT id_usuario, email, password, rol, id_cliente, dni_empleado FROM USUARIOS WHERE email = :email";
    $stmt = $conexion->prepare($sql);
    $stmt->bindParam(':email', $email_input, PDO::PARAM_STR);
    $stmt->execute();
    
    $usuario = $stmt->fetch();

    if ($usuario && password_verify($password_input, $usuario['password'])) {
        
        session_start();
        $_SESSION['id_usuario'] = $usuario['id_usuario'];
        $_SESSION['email'] = $usuario['email'];
        $_SESSION['rol'] = $usuario['rol'];
        $_SESSION['id_cliente'] = $usuario['id_cliente'];
        $_SESSION['dni_empleado'] = $usuario['dni_empleado'];

        echo json_encode([
            "mensaje" => "¡Inicio de sesión exitoso!",
            "rol" => $usuario['rol']
        ]);
        
    } else {
        http_response_code(401);
        echo json_encode(["mensaje" => "El email o la contraseña son incorrectos."]);
    }

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["mensaje" => "Error en el servidor: " . $e->getMessage()]);
}
?>