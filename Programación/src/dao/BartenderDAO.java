package dao;

import conexion.ConexionBD;
import modelo.Bartender;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BartenderDAO {

    public List<Bartender> getBartenders() throws SQLException {
        List<Bartender> lista = new ArrayList<>();

        String sql = """
                SELECT e.dni, e.nombre, e.apellidos, e.telefono, e.nuss, e.colectivo, e.email
                FROM EMPLEADOS e
                INNER JOIN BARTENDERS b ON e.dni = b.dni_empleado
                """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bartender b = new Bartender(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("nuss"),
                        rs.getString("colectivo"),
                        rs.getString("email")
                );
                lista.add(b);
            }
        }

        return lista;
    }

    public Bartender buscarPorDni(String dni) throws SQLException {
        String sql = """
                SELECT e.dni, e.nombre, e.apellidos, e.telefono, e.nuss, e.colectivo, e.email
                FROM EMPLEADOS e
                INNER JOIN BARTENDERS b ON e.dni = b.dni_empleado
                WHERE e.dni = ?
                """;

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Bartender(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("telefono"),
                            rs.getString("nuss"),
                            rs.getString("colectivo"),
                            rs.getString("email")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(Bartender b) throws SQLException {
        String sql = "INSERT INTO BARTENDERS (dni_empleado) VALUES (?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, b.getDni());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(String dni, Bartender b) throws SQLException {
        String sql = "UPDATE BARTENDERS SET dni_empleado = ? WHERE dni_empleado = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, b.getDni());
            ps.setString(2, dni);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String dni) throws SQLException {
        String sql = "DELETE FROM BARTENDERS WHERE dni_empleado = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        }
    }
}