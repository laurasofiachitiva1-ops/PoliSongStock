package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Disco_viniloDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public boolean CrearVinilo(Disco_vinilo vin) {
        String sql = "INSERT INTO disco_vinilo(id_disco_vinilo, id_autor, id_vendedor, nombre, genero, anio_salida,"
                + "precio, cantidad, imagen) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, vin.getId_disco_vinilo());
            ps.setInt(2, vin.getId_autor());
            ps.setInt(3, vin.getId_vendedor());
            ps.setString(4, vin.getNombre());
            ps.setString(5, vin.getGenero());
            ps.setInt(6, vin.getAnio_salida());
            ps.setDouble(7, vin.getPrecio());
            ps.setInt(8, vin.getCantidad());
            ps.setBytes(9, vin.getImagen());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            // ===== VALIDACIÓN DE ID DUPLICADO =====
            if ("23000".equals(e.getSQLState())) {
                JOptionPane.showMessageDialog(
                        null,
                        "El ID " + vin.getId_disco_vinilo() + " ya existe.\nEscoja otro ID.",
                        "ID duplicado",
                        JOptionPane.ERROR_MESSAGE
                );
                return false;
            }

            // Otros errores SQL
            JOptionPane.showMessageDialog(
                    null,
                    "Error al guardar el vinilo:\n" + e.getMessage(),
                    "Error SQL",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public Disco_vinilo buscarPorIdV(int id) {
        String sql = "SELECT dv.*, a.nombre AS autorNombre "
                + "FROM disco_vinilo dv "
                + "JOIN autor a ON dv.id_autor = a.id_autor "
                + "WHERE dv.id_disco_vinilo = ?";

        Disco_vinilo dv = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                dv = new Disco_vinilo();
                dv.setId_disco_vinilo(rs.getInt("id_disco_vinilo"));
                dv.setId_vendedor(rs.getInt("id_vendedor"));
                dv.setNombre(rs.getString("nombre"));
                dv.setGenero(rs.getString("genero"));
                dv.setAnio_salida(rs.getInt("anio_salida"));
                dv.setPrecio(rs.getDouble("precio"));
                dv.setCantidad(rs.getInt("cantidad"));
                dv.setImagen(rs.getBytes("imagen"));
                dv.setAutorNombre(rs.getString("autorNombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar vinilo: " + e.getMessage());
        }
        return dv;
    }

}
