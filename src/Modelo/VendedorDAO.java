package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class VendedorDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
     
    public boolean CrearVendedor (Vendedor ven){
        String sql = "INSERT INTO vendedor(nombre, correo, direccion, password) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ven.getNombre());
            ps.setString(2, ven.getCorreo());
            ps.setString(3, ven.getDireccion());
            ps.setString(4, ven.getPassword());
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
    public Vendedor logV (String correo, String password ){
        Vendedor v = new Vendedor();
        String sql = "SELECT * FROM vendedor WHERE correo = ? AND password = ?";
         
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                v.setId_vendedor(rs.getInt("id_vendedor"));
                v.setNombre(rs.getString("nombre"));
                v.setCorreo(rs.getString("correo"));
                v.setDireccion(rs.getString("direccion"));
                v.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return v;
    }
}
