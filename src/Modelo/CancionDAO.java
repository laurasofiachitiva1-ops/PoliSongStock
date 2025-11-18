package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/*import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;*/

public class CancionDAO {
    Connection con;
    PreparedStatement ps;
    Conexion cn = new Conexion();
    
    public boolean CrearCancion (Cancion can){
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
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }        
    }
}
