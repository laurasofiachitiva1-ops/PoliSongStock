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

    public List<Cancion> listarCancionesPorDisco(int idDisco) {
        List<Cancion> lista = new ArrayList<>();
        String sql = "SELECT c.id_cancion, c.nombre, c.duracion "
                   + "FROM cancion_vinilo cv "
                   + "INNER JOIN cancion c ON cv.id_cancion = c.id_cancion "
                   + "WHERE cv.id_disco_vinilo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idDisco);
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

    // ELIMINAR RELACIÓN (si el usuario quita una canción del disco)
    public boolean eliminarCancionDeDisco(int idDisco, int idCancion) {
        String sql = "DELETE FROM disco_cancion WHERE id_disco = ? AND id_cancion = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idDisco);
            ps.setInt(2, idCancion);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error eliminando canción: ");
            return false;
        }
    }
}
