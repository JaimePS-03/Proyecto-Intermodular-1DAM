package dao;

import conexion.ConexionBD;
import modelo.Cocinero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CocineroDAO {

    public List<Cocinero> getCocineros() throws SQLException {
        List<Cocinero> lista = new ArrayList<>();

        String sql = """
                SELECT e.dni, e.nombre, e.apellidos, e.telefono, e.nuss, e.colectivo, e.email,
                       c.especialidad
                FROM EMPLEADOS e
                INNER JOIN COCINERO c ON e.dni = c.dni_empleado
                """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cocinero c = new Cocinero(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("nuss"),
                        rs.getString("colectivo"),
                        rs.getString("email"),
                        rs.getString("especialidad")
                );
                lista.add(c);
            }
        }

        return lista;
    }

    public Cocinero buscarPorDni(String dni) throws SQLException {
        String sql = """
                SELECT e.dni, e.nombre, e.apellidos, e.telefono, e.nuss, e.colectivo, e.email,
                       c.especialidad
                FROM EMPLEADOS e
                INNER JOIN COCINERO c ON e.dni = c.dni_empleado
                WHERE e.dni = ?
                """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cocinero(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("telefono"),
                            rs.getString("nuss"),
                            rs.getString("colectivo"),
                            rs.getString("email"),
                            rs.getString("especialidad")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(Cocinero c) throws SQLException {
        String sql = "INSERT INTO COCINERO (especialidad, dni_empleado) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getEspecialidad());
            ps.setString(2, c.getDni());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(String dni, Cocinero c) throws SQLException {
        String sql = "UPDATE COCINERO SET especialidad = ? WHERE dni_empleado = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getEspecialidad());
            ps.setString(2, dni);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String dni) throws SQLException {
        String sql = "DELETE FROM COCINERO WHERE dni_empleado = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        }
    }
}