package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class Cancion_viniloDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public boolean agregarCancionAVinilo(Cancion_vinilo cav) {
        String sql = "INSERT INTO cancion_vinilo (id_cancion, id_disco_vinilo) VALUES (?, ?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cav.getId_cancion());
            ps.setInt(2, cav.getId_disco_vinilo());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public List<Cancion> listarCancionesPorVinilo(int idVinilo) {
        List<Cancion> lista = new ArrayList<>();
        String sql = "SELECT c.id_cancion, c.nombre, c.duracion "
                + "FROM cancion_vinilo cv "
                + "INNER JOIN cancion c ON cv.id_cancion = c.id_cancion "
                + "WHERE cv.id_disco_vinilo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVinilo);
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

    // ELIMINAR 
    public boolean eliminarCancionDeVinilo(int idVinilo, int idCancion) {
        String sql = "DELETE FROM cancion_vinilo WHERE id_disco_vinilo = ? AND id_cancion = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVinilo);
            ps.setInt(2, idCancion);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return false;
        }
    }

    public boolean existeCancionEnVinilo(int idVinilo, int idCancion) {
        String sql = "SELECT COUNT(*) FROM cancion_vinilo WHERE id_disco_vinilo = ? AND id_cancion = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVinilo);
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

    public boolean eliminarPorVinilo(int idVinilo) {
        String sql = "DELETE FROM cancion_vinilo WHERE id_disco_vinilo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVinilo);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar canciones del vinilo: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPorCancion(int idCancion) {
        String sql = "DELETE FROM cancion_vinilo WHERE id_cancion = ?";
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
