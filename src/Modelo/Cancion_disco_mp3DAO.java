package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Cancion_disco_mp3DAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean agregarCancionAMp3(Cancion_disco_mp3 cam) {
        String sql = "INSERT INTO cancion_disco_mp3 (id_cancion, id_disco_mp3) VALUES (?, ?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cam.getId_cancion());
            ps.setInt(2, cam.getId_disco_mp3());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public List<Cancion> listarCancionesPorMp3(int idMp3) {
        List<Cancion> lista = new ArrayList<>();
        String sql = "SELECT c.id_cancion, c.nombre, c.duracion "
                + "FROM cancion_disco_mp3 cv "
                + "INNER JOIN cancion c ON cv.id_cancion = c.id_cancion "
                + "WHERE cv.id_disco_mp3 = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMp3);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cancion c = new Cancion();
                c.setId_cancion(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setDuracion(rs.getString(3));
                lista.add(c);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return lista;
    }

    public boolean eliminarCancionDeMp3(int idMp3, int idCancion) {
        String sql = "DELETE FROM cancion_disco_mp3 WHERE id_disco_mp3 = ? AND id_cancion = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMp3);
            ps.setInt(2, idCancion);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public boolean existeCancionEnMp3(int idMp3, int idCancion) {
        String sql = "SELECT COUNT(*) FROM cancion_disco_mp3 WHERE id_disco_mp3 = ? AND id_cancion = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMp3);
            ps.setInt(2, idCancion);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        return false;
    }

    public boolean eliminarPorMp3(int idMp3) {
        String sql = "DELETE FROM cancion_disco_mp3 WHERE id_disco_mp3 = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idMp3);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar canciones del MP3: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPorCancion(int idCancion) {
        String sql = "DELETE FROM cancion_disco_mp3 WHERE id_cancion = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idCancion);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

}
