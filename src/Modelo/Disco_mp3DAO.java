package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Disco_mp3DAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public boolean CrearMp3 (Disco_mp3 mp){
    String sql = "INSERT INTO disco_mp3(id_disco_mp3, id_autor, id_vendedor, nombre, genero, anio_salida,"
            + "precio, imagen) VALUES (?,?,?,?,?,?,?,?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, mp.getId_disco_mp3());
            ps.setInt(2, mp.getId_autor());
            ps.setInt(3, mp.getId_vendedor());
            ps.setString(4, mp.getNombre());
            ps.setString(5, mp.getGenero());
            ps.setInt(6, mp.getAnio_salida());
            ps.setDouble(7, mp.getPrecio());
            ps.setBytes(8, mp.getImagen());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            // ===== VALIDACIÓN DE ID DUPLICADO =====
            if ("23000".equals(e.getSQLState())) {
                JOptionPane.showMessageDialog(
                    null,
                    "El ID " + mp.getId_disco_mp3() + " ya existe.\nEscoja otro ID.",
                    "ID duplicado",
                    JOptionPane.ERROR_MESSAGE
                );
            return false;
            }

            // Otros errores SQL
            JOptionPane.showMessageDialog(
                null,
                "Error al guardar el mp3:\n" + e.getMessage(),
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
    public Disco_mp3 buscarPorIM(int id) {
        String sql = "SELECT dv.*, a.nombre AS autorNombre "
                + "FROM disco_mp3 dv "
                + "JOIN autor a ON dv.id_autor = a.id_autor "
                + "WHERE dv.id_disco_mp3 = ?";

        Disco_mp3 dm = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                dm = new Disco_mp3();
                dm.setId_disco_mp3(rs.getInt("id_disco_mp3"));
                dm.setId_vendedor(rs.getInt("id_vendedor"));  
                dm.setNombre(rs.getString("nombre"));
                dm.setGenero(rs.getString("genero"));
                dm.setAnio_salida(rs.getInt("anio_salida"));
                dm.setPrecio(rs.getDouble("precio"));
                dm.setImagen(rs.getBytes("imagen"));
                dm.setAutorNombre(rs.getString("autorNombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar mp3: " + e.getMessage());
        }
        return dm;
    }
    
       public List<Disco_mp3> listarDiscosPorVendedor(int idVendedor) {
        List<Disco_mp3> lista = new ArrayList<>();
        String sql = "SELECT dv.*, a.nombre AS autorNombre "
                + "FROM disco_mp3 dv "
                + "JOIN autor a ON dv.id_autor = a.id_autor "
                + "WHERE dv.id_vendedor = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVendedor);
            rs = ps.executeQuery();

            while (rs.next()) {
                Disco_mp3 dm = new Disco_mp3();
                dm.setId_disco_mp3(rs.getInt("id_disco_mp3"));
                dm.setId_autor(rs.getInt("id_autor"));
                dm.setAutorNombre(rs.getString("autorNombre"));
                dm.setId_vendedor(rs.getInt("id_vendedor"));
                dm.setNombre(rs.getString("nombre"));
                dm.setGenero(rs.getString("genero"));
                dm.setAnio_salida(rs.getInt("anio_salida"));
                dm.setPrecio(rs.getDouble("precio"));
                dm.setImagen(rs.getBytes("imagen")); // importante
                lista.add(dm);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar mp3: " + e.getMessage());
        }

        return lista;
    }
}
