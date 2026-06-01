package dao;

import conexion.ConexionBD;
import modelo.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservasDAO {

    public List<Reserva> getReservas() throws SQLException {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT id, n_personas, tipo_reserva, fecha, id_cli FROM reservas";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reserva r = new Reserva(
                        rs.getInt("id"),
                        rs.getInt("n_Personas"),
                        rs.getString("tipo_reserva"),
                        rs.getTimestamp("fecha"),
                        rs.getString("id_cli")
                );
                lista.add(r);
            }
        }

        return lista;
    }

    public Reserva buscarPorId(int id) throws SQLException {
        String sql = "SELECT id, n_personas, tipo_reserva, fecha, id_cli FROM reservas WHERE id = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Reserva(
                            rs.getInt("id"),
                            rs.getInt("n_Personas"),
                            rs.getString("tipo_reserva"),
                            rs.getTimestamp("fecha"),
                            rs.getString("idCli")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(Reserva re) throws SQLException {
        String sql = "INSERT INTO reservas (id, n_personas, tipo_reserva, fecha, id_cli) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, re.getId());
            ps.setInt(2, re.getnPersonas());
            ps.setString(3, re.getTipoReserva());
            ps.setTimestamp(4, re.getFecha());
            ps.setString(5, re.getIdCliente());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(int id, Reserva re) throws SQLException {
        String sql = "UPDATE reservas SET n_personas = ?, tipo_reserva = ?, fecha = ?, id_cli = ? WHERE id = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, re.getnPersonas());
            ps.setString(2, re.getTipoReserva());
            ps.setTimestamp(3, re.getFecha());
            ps.setString(4, re.getIdCliente());
            ps.setInt(5, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int id) throws SQLException {
        String sql = "DELETE FROM reservas WHERE id = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public ArrayList<Reserva> listarPorCliente(String idCliente) throws SQLException {
        ArrayList<Reserva> lista = new ArrayList<>();
        String sql = "SELECT id, n_personas, tipo_reserva, fecha, id_cli FROM reservas WHERE id_cli = ?";

        try (Connection con = ConexionBD.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idCliente);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reserva r = new Reserva(
                            rs.getInt("id"),
                            rs.getInt("nPersonas"),
                            rs.getString("tipoReserva"),
                            rs.getTimestamp("fecha"),
                            rs.getString("idCliente")
                    );
                    lista.add(r);
                }
            }
        }

        return lista;
    }
}