package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;



public class AutorDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
     
    public boolean CrearAutor (Autor au){
        String sql = "INSERT INTO autor(nombre, fecha_nacimiento, nacionalidad) VALUES (?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, au.getNombre());
            ps.setDate(2, au.getFecha_nacimiento());
            ps.setString(3, au.getNacionalidad());
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
    public List<String> obtenerAutores() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre FROM autor";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(rs.getString("nombre"));
        }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            System.out.println(e.toString());
            }
        }
        return lista;
    }
    public int obtenerIdPorNombre(String nombre) {
        String sql = "SELECT id_autor FROM autor WHERE nombre = ?";
        int id = -1;  // Valor por defecto si no encuentra el autor

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);

            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_autor");
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener ID del autor: " + e.getMessage());
        }

        return id;
    }


}
