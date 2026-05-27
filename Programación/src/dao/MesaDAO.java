package dao;

import conexion.ConexionBD;
import modelo.Mesa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MesaDAO {

    public List<Mesa> getMesas() throws SQLException {
        List<Mesa> lista = new ArrayList<>();
        String sql = "SELECT n_mesa, n_personas FROM mesas";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Mesa m = new Mesa(
                        rs.getInt("n_mesa"),
                        rs.getInt("n_personas")
                );
                lista.add(m);
            }
        }

        return lista;
    }

    public Mesa buscarPorId(int id) throws SQLException {
        String sql = "SELECT n_mesa, n_personas FROM mesas WHERE n_mesa = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Mesa(
                            rs.getInt("n_mesa"),
                            rs.getInt("n_personas")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(Mesa m) throws SQLException {
        String sql = "INSERT INTO clientes (n_mesa, n_personas) VALUES (?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getnMesa());
            ps.setInt(2, m.getnPersonas());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(int id, Mesa m) throws SQLException {
        String sql = "UPDATE clientes SET n_mesa = ?, n_personas = ? WHERE n_mesa = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getnMesa());
            ps.setInt(2, m.getnPersonas());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM mesas WHERE n_mesa = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}