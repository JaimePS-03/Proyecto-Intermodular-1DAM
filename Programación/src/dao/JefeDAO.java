package dao;

import conexion.ConexionBD;
import modelo.Jefe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JefeDAO {

    public List<Jefe> getJefes() throws SQLException {
        List<Jefe> lista = new ArrayList<>();
        String sql = "SELECT dni, nombre, apellidos, telefono, email FROM JEFES";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Jefe j = new Jefe(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                lista.add(j);
            }
        }

        return lista;
    }

    public Jefe buscarPorDni(String dni) throws SQLException {
        String sql = "SELECT dni, nombre, apellidos, telefono, email FROM JEFES WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Jefe(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("telefono"),
                            rs.getString("email")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(Jefe j) throws SQLException {
        String sql = "INSERT INTO JEFES (dni, nombre, apellidos, telefono, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, j.getDni());
            ps.setString(2, j.getNombre());
            ps.setString(3, j.getApellidos());
            ps.setString(4, j.getTelefono());
            ps.setString(5, j.getEmail());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(String dni, Jefe j) throws SQLException {
        String sql = "UPDATE JEFES SET nombre = ?, apellidos = ?, telefono = ?, email = ? WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, j.getNombre());
            ps.setString(2, j.getApellidos());
            ps.setString(3, j.getTelefono());
            ps.setString(4, j.getEmail());
            ps.setString(5, dni);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String dni) throws SQLException {
        String sql = "DELETE FROM JEFES WHERE dni = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        }
    }
}