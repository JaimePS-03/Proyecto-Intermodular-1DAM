package dao;

import conexion.ConexionBD;
import modelo.Camarero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CamareroDAO {

    public List<Camarero> getCamareros() throws SQLException {
        List<Camarero> lista = new ArrayList<>();

        String sql = """
                SELECT e.dni, e.nombre, e.apellidos, e.telefono, e.nuss, e.colectivo, e.email,
                       c.anos_servicio, c.n_zona, c.camarero_jefe
                FROM EMPLEADOS e
                INNER JOIN CAMAREROS c ON e.dni = c.dni_empleado
                """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Camarero c = new Camarero(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("nuss"),
                        rs.getString("colectivo"),
                        rs.getString("email"),
                        rs.getInt("anos_servicio"),
                        rs.getString("n_zona"),
                        rs.getString("camarero_jefe")
                );
                lista.add(c);
            }
        }

        return lista;
    }

    public Camarero buscarPorDni(String dni) throws SQLException {
        String sql = """
                SELECT e.dni, e.nombre, e.apellidos, e.telefono, e.nuss, e.colectivo, e.email,
                       c.anos_servicio, c.n_zona, c.camarero_jefe
                FROM EMPLEADOS e
                INNER JOIN CAMAREROS c ON e.dni = c.dni_empleado
                WHERE e.dni = ?
                """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Camarero(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("telefono"),
                            rs.getString("nuss"),
                            rs.getString("colectivo"),
                            rs.getString("email"),
                            rs.getInt("anos_servicio"),
                            rs.getString("n_zona"),
                            rs.getString("camarero_jefe")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(Camarero c) throws SQLException {
        String sql = "INSERT INTO CAMAREROS (anos_servicio, dni_empleado, n_zona, camarero_jefe) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getAnoServicio());
            ps.setString(2, c.getDni());
            ps.setString(3, c.getnZona());
            ps.setString(4, c.getCamareroJefe());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(String dni, Camarero c) throws SQLException {
        String sql = "UPDATE CAMAREROS SET anos_servicio = ?, n_zona = ?, camarero_jefe = ? WHERE dni_empleado = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, c.getAnoServicio());
            ps.setString(2, c.getnZona());
            ps.setString(3, c.getCamareroJefe());
            ps.setString(4, dni);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String dni) throws SQLException {
        String sql = "DELETE FROM CAMAREROS WHERE dni_empleado = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        }
    }
}