<?php
// ¡Fíjate que el 'host' es el nombre del servicio en docker-compose (db)!
$host = 'db'; 
$port = '5432';
$dbname = 'restaurante_don_joan';
$user = 'user_admin';
$password = 'd0nj0an';

$dsn = "pgsql:host=$host;port=$port;dbname=$dbname";

try {
    // Intentar conectar
    $pdo = new PDO($dsn, $user, $password);
    
    // Configurar el manejo de errores
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    echo "<h1>¡Conexión a PostgreSQL exitosa desde Docker! 🎉</h1>";
    echo "<p>Ya puedes empezar a hacer consultas y pruebas.</p>";
    
} catch (PDOException $e) {
    echo "<h1>Error de conexión 💔</h1>";
    echo "<p>" . $e->getMessage() . "</p>";
}
?>