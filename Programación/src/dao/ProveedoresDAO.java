package dao;

import conexion.ConexionBD;
import modelo.Proveedores;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedoresDAO {

    public List<Proveedores> getProveedores() throws SQLException {
        List<Proveedores> lista = new ArrayList<>();
        String sql = "SELECT id, nombre, direccion, telefono, email FROM proveedores";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Proveedores c = new Proveedores(
                        rs.getString("cif"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email")
                );
                lista.add(c);
            }
        }

        return lista;
    }

    public Proveedores buscarPorId(String cif) throws SQLException {
        String sql = "SELECT cif, nombre, direccion, telefono, email FROM proveedores WHERE cif = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cif);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Proveedores(
                            rs.getString("cif"),
                            rs.getString("nombre"),
                            rs.getString("direccion"),
                            rs.getString("telefono"),
                            rs.getString("email")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(Proveedores c) throws SQLException {
        String sql = "INSERT INTO proveedores (cif, nombre, direccion, telefono, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCif());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getDireccion());
            ps.setString(4, c.getTelefono());
            ps.setString(5, c.getEmail());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(String id, Proveedores c) throws SQLException {
        String sql = "UPDATE proveedores SET nombre = ?, direccion = ?, telefono = ?, email = ? WHERE cif = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDireccion());
            ps.setString(3, c.getTelefono());
            ps.setString(4, c.getEmail());
            ps.setString(5, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String id) throws SQLException {
        String sql = "DELETE FROM proveedores WHERE cif = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}