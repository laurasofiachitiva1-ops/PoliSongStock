package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Canciones_listasDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    // Insertar una nueva canción en lista
    public boolean insertarCancionLista(Canciones_listas cancionLista) {
        String sql = "INSERT INTO canciones_lista(id_lista, id_cancion) VALUES (?,?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cancionLista.getId_lista());
            ps.setInt(2, cancionLista.getId_cancion());

            ps.executeUpdate();

            // Obtener el ID generado y setearlo en el objeto
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                cancionLista.setId_cancion_lista(rs.getInt(1));
            }

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al insertar canción en lista:\n" + e.getMessage(),
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

    // Buscar canción en lista por ID
    public Canciones_listas buscarPorIdCancionLista(int idCancionLista) {
        String sql = "SELECT * FROM canciones_lista WHERE id_cancionlista = ?";

        Canciones_listas cancionLista = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCancionLista);
            rs = ps.executeQuery();

            if (rs.next()) {
                cancionLista = new Canciones_listas();
                cancionLista.setId_cancion_lista(rs.getInt("id_cancionlista"));
                cancionLista.setId_lista(rs.getInt("id_lista"));
                cancionLista.setId_cancion(rs.getInt("id_cancion"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar canción en lista: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return cancionLista;
    }

    // Listar canciones por lista
    public List<Canciones_listas> listarCancionesPorLista(int idLista) {
        List<Canciones_listas> lista = new ArrayList<>();
        String sql = "SELECT * FROM canciones_lista WHERE id_lista = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idLista);
            rs = ps.executeQuery();

            while (rs.next()) {
                Canciones_listas item = new Canciones_listas();
                item.setId_cancion_lista(rs.getInt("id_cancionlista"));
                item.setId_lista(rs.getInt("id_lista"));
                item.setId_cancion(rs.getInt("id_cancion"));
                lista.add(item);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar canciones en lista: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return lista;
    }

    // Eliminar canción de lista por ID
    public boolean eliminarCancionLista(int idCancionLista) {
        String sql = "DELETE FROM canciones_lista WHERE id_cancionlista = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCancionLista);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar canción de lista: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    // Modificar canción en lista (aunque rara vez necesario, para consistencia)
    public boolean modificarCancionLista(Canciones_listas cancionLista) {
        String sql = "UPDATE canciones_lista SET id_lista = ?, id_cancion = ? WHERE id_cancionlista = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cancionLista.getId_lista());
            ps.setInt(2, cancionLista.getId_cancion());
            ps.setInt(3, cancionLista.getId_cancion_lista());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar canción en lista: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
}