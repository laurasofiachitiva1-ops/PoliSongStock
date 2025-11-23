package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
     

public class CancionDAO {

    Connection con;
    PreparedStatement ps;
    Conexion cn = new Conexion();
    ResultSet rs;

    public boolean CrearCancion(Cancion can) {
        String sql = "INSERT INTO cancion(id_autor, id_vendedor, nombre, genero, duracion, tamano_mb, calidad_kbps, precio) VALUES (?,?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, can.getId_autor());
            ps.setInt(2, can.getId_vendedor());
            ps.setString(3, can.getNombre());
            ps.setString(4, can.getGenero());
            ps.setString(5, can.getDuracion());
            ps.setDouble(6, can.getTamano_mb());
            ps.setInt(7, can.getCalidad_kbps());
            ps.setDouble(8, can.getPrecio());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    public List<Cancion> listarTodas() {
        List<Cancion> lista = new ArrayList<>();
        String sql = "SELECT c.*, a.nombre AS autorNombre "
                + "FROM cancion c "
                + "JOIN autor a ON c.id_autor = a.id_autor";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cancion c = new Cancion();
                c.setId_cancion(rs.getInt("id_cancion"));
                c.setId_autor(rs.getInt("id_autor"));
                c.setAutorNombre(rs.getString("autorNombre"));
                c.setNombre(rs.getString("nombre"));
                c.setGenero(rs.getString("genero"));
                c.setDuracion(rs.getString("duracion"));
                c.setTamano_mb(rs.getDouble("tamano_mb"));
                c.setCalidad_kbps(rs.getInt("calidad_kbps"));
                c.setPrecio(rs.getDouble("precio"));
                c.setId_vendedor(rs.getInt("id_vendedor"));
                lista.add(c);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar todas las canciones: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return lista;
    }

    public List<Cancion> listarCancionesPorVendedor(int idVendedor) {
        List<Cancion> lista = new ArrayList<>();
        String sql = "SELECT c.*, a.nombre AS autorNombre "
                + "FROM cancion c "
                + "JOIN autor a ON c.id_autor = a.id_autor "
                + "WHERE c.id_vendedor = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVendedor);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cancion c = new Cancion();
                c.setId_cancion(rs.getInt("id_cancion"));
                c.setId_autor(rs.getInt("id_autor"));
                c.setAutorNombre(rs.getString("autorNombre"));
                c.setNombre(rs.getString("nombre"));
                c.setGenero(rs.getString("genero"));
                c.setDuracion(rs.getString("duracion"));
                lista.add(c);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar canciones: " + e.getMessage());
        }
        return lista;
    }

    public boolean eliminarCancion(int idCancion) {
        String sql = "DELETE FROM cancion WHERE id_cancion = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCancion);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar canción: " + e.getMessage());
            return false;
        }
    }

    public Cancion obtenerPorId(int id) {
        Cancion c = null;

        String sql = "SELECT c.*, a.nombre AS autorNombre "
                + "FROM cancion c "
                + "JOIN autor a ON c.id_autor = a.id_autor "
                + "WHERE id_cancion = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                c = new Cancion();
                c.setId_cancion(rs.getInt("id_cancion"));
                c.setId_autor(rs.getInt("id_autor"));
                c.setAutorNombre(rs.getString("autorNombre"));
                c.setNombre(rs.getString("nombre"));
                c.setGenero(rs.getString("genero"));
                c.setDuracion(rs.getString("duracion"));
                c.setTamano_mb(rs.getDouble("tamano_mb"));
                c.setCalidad_kbps(rs.getInt("calidad_kbps"));
                c.setPrecio(rs.getDouble("precio"));

                c.setId_vendedor(rs.getInt("id_vendedor"));

            }

        } catch (Exception e) {
            System.out.println("ERROR obtenerPorId: " + e.getMessage());
        }

        return c;
    }

    public boolean modificarCancion(Cancion c) {
        String sql = "UPDATE cancion SET nombre=?, genero=?, tamano_mb=?, calidad_kbps=?, precio=?, duracion=?, id_autor=? WHERE id_cancion=?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getGenero());
            ps.setDouble(3, c.getTamano_mb());
            ps.setInt(4, c.getCalidad_kbps());
            ps.setDouble(5, c.getPrecio());
            ps.setString(6, c.getDuracion());
            ps.setInt(7, c.getId_autor());
            ps.setInt(8, c.getId_cancion());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error modificando canción: " + e.getMessage());
            return false;
        }
    }
    
    public static void main(String[] args) {
         CancionDAO dao = new CancionDAO();
         List<Cancion> lista = dao.listarTodas();
         System.out.println("Lista: " + lista);
         if (lista != null && !lista.isEmpty()) {
             for (Cancion c : lista) {
                 System.out.println(c.getNombre() + " - " + c.getAutorNombre());
             }
         } else {
             System.out.println("Lista vacía o null - Error en BD o consulta");
         }
         
         }

}
