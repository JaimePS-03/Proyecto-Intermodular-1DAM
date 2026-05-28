<?php
require_once 'conexion.php';
header('Content-Type: application/json; charset=utf-8');

try {
    $sql = "SELECT p.n_plato, p.nombre, p.precio, p.tipo,
                   STRING_AGG(DISTINCT a.grupo, ',') AS alergenos
            FROM PLATOS p
            LEFT JOIN SUMINISTRAR s ON p.n_plato = s.n_plato
            LEFT JOIN CONTIENE c ON s.nombre_ingre = c.nombre_ingre
            LEFT JOIN ALERGENOS a ON c.ID_ale = a.id
            GROUP BY p.n_plato, p.nombre, p.precio, p.tipo
            ORDER BY p.tipo, p.nombre";
            
    $stmt = $conexion->query($sql);
    
    $platos = [];
    while ($fila = $stmt->fetch(PDO::FETCH_ASSOC)) {
        $fila['alergenos'] = $fila['alergenos'] ? explode(',', $fila['alergenos']) : [];
        $platos[] = $fila;
    }
    
    echo json_encode($platos);

} catch (PDOException $e) {
    http_response_code(500);
    echo json_encode(["error" => "Error al consultar la carta: " . $e->getMessage()]);
}
?>