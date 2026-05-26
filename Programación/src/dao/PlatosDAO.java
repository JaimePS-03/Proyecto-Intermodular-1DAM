package dao;

import conexion.ConexionBD;
import modelo.Plato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlatosDAO {

    public List<Plato> getPlatos() throws SQLException {
        List<Plato> lista = new ArrayList<>();
        String sql = "SELECT n_plato, nombre, precio, tipo FROM platos";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Plato p = new Plato(
                        rs.getString("n_plato"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getString("tipo")
                );
                lista.add(p);
            }
        }

        return lista;
    }

    public Plato buscarPorId(String id) throws SQLException {
        String sql = "SELECT n_plato, nombre, precio, tipo FROM platos WHERE n_plato = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Plato(
                            rs.getString("n_plato"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getString("tipo")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(Plato p) throws SQLException {
        String sql = "INSERT INTO clientes (n_plato, nombre, precio, tipo) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setDouble(3, p.getPrecio());
            ps.setString(4, p.getTipo());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(String id, Plato p) throws SQLException {
        String sql = "UPDATE platos SET n_plato = ?, nombre = ?, precio = ?, tipo = ? WHERE n_plato = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setDouble(3, p.getPrecio());
            ps.setString(4, p.getTipo());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String id) throws SQLException {
        String sql = "DELETE FROM platos WHERE n_plato = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}