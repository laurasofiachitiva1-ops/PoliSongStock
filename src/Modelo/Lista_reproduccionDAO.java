package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Lista_reproduccionDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    // Insertar una nueva lista de reproducción
    public boolean insertarListaReproduccion(Lista_reproduccion lista) {
        String sql = "INSERT INTO lista_reproduccion(nombre, id_comprador, publica, fecha_creacion) VALUES (?,?,?,?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, lista.getNombre());
            ps.setInt(2, lista.getId_comprador());
            ps.setString(3, lista.getPublica());
            ps.setString(4, lista.getFecha_creacion());

            ps.executeUpdate();

            // Obtener el ID generado y setearlo en el objeto
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                lista.setId_lista(rs.getInt(1));
            }

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al insertar lista de reproducción:\n" + e.getMessage(),
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

    // Buscar lista por ID
    public Lista_reproduccion buscarPorIdLista(int idLista) {
        String sql = "SELECT * FROM lista_reproduccion WHERE id_lista = ?";

        Lista_reproduccion lista = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idLista);
            rs = ps.executeQuery();

            if (rs.next()) {
                lista = new Lista_reproduccion();
                lista.setId_lista(rs.getInt("id_lista"));
                lista.setNombre(rs.getString("nombre"));
                lista.setId_comprador(rs.getInt("id_comprador"));
                lista.setPublica(rs.getString("publica"));
                lista.setFecha_creacion(rs.getString("fecha_creacion"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar lista de reproducción: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return lista;
    }

    // Listar listas por comprador
    public List<Lista_reproduccion> listarListasPorComprador(int idComprador) {
        List<Lista_reproduccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM lista_reproduccion WHERE id_comprador = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idComprador);
            rs = ps.executeQuery();

            while (rs.next()) {
                Lista_reproduccion item = new Lista_reproduccion();
                item.setId_lista(rs.getInt("id_lista"));
                item.setNombre(rs.getString("nombre"));
                item.setId_comprador(rs.getInt("id_comprador"));
                item.setPublica(rs.getString("publica"));
                item.setFecha_creacion(rs.getString("fecha_creacion"));
                lista.add(item);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar listas de reproducción: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return lista;
    }

    // Eliminar lista por ID 
    public boolean eliminarListaReproduccion(int idLista) {
        String sqlEliminarCanciones = "DELETE FROM canciones_lista WHERE id_lista = ?"; 
        String sqlEliminarLista = "DELETE FROM lista_reproduccion WHERE id_lista = ?";

        try {
            con = cn.getConnection();
            con.setAutoCommit(false);  // Iniciar transacción

            // Paso 1: Eliminar las canciones asociadas
            ps = con.prepareStatement(sqlEliminarCanciones);
            ps.setInt(1, idLista);
            ps.executeUpdate();

            // Paso 2: Eliminar la lista
            ps = con.prepareStatement(sqlEliminarLista);
            ps.setInt(1, idLista);
            int rows = ps.executeUpdate();

            con.commit();  // Confirmar
            return rows > 0;

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException rollbackEx) {
                System.out.println("Error en rollback: " + rollbackEx.toString());
            }
            JOptionPane.showMessageDialog(null, "Error al eliminar lista de reproducción: " + e.getMessage());
            return false;
        } finally {
            try {
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    // Modificar lista de reproducción
    public boolean modificarListaReproduccion(Lista_reproduccion lista) {
        String sql = "UPDATE lista_reproduccion SET nombre = ?, publica = ? WHERE id_lista = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, lista.getNombre());
            ps.setString(2, lista.getPublica());
            ps.setInt(3, lista.getId_lista());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar lista de reproducción: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    // Listar listas públicas (excluyendo las del comprador logueado)

    public List<Lista_reproduccion> listarListasPublicas(int idCompradorLogueado) {
        List<Lista_reproduccion> lista = new ArrayList<>();
        // JOIN con la tabla comprador para obtener el nombre del creador
        String sql = "SELECT lr.*, c.nombre AS nombre_creador FROM lista_reproduccion lr INNER JOIN comprador c ON lr.id_comprador = c.id_comprador WHERE lr.publica = 'Sí' AND lr.id_comprador != ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCompradorLogueado);
            rs = ps.executeQuery();

            while (rs.next()) {
                Lista_reproduccion item = new Lista_reproduccion();
                item.setId_lista(rs.getInt("id_lista"));
                item.setNombre(rs.getString("nombre"));
                item.setId_comprador(rs.getInt("id_comprador"));
                item.setPublica(rs.getString("publica"));
                item.setFecha_creacion(rs.getString("fecha_creacion"));
                item.setNombre_creador(rs.getString("nombre_creador"));
                lista.add(item);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar listas públicas: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return lista;
    }

}
