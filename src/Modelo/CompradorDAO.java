package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CompradorDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public boolean CrearComprador(Comprador co) {
        String sql = "INSERT INTO comprador(nombre, correo, direccion, password) VALUES (?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, co.getNombre());
            ps.setString(2, co.getCorreo());
            ps.setString(3, co.getDireccion());
            ps.setString(4, co.getPassword());
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

    public Comprador logC(String correo, String password) {
        Comprador c = new Comprador();
        String sql = "SELECT * FROM comprador WHERE correo = ? AND password = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                c.setId_comprador(rs.getInt("id_comprador"));
                c.setNombre(rs.getString("nombre"));
                c.setCorreo(rs.getString("correo"));
                c.setDireccion(rs.getString("direccion"));
                c.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return c;
    }

    public boolean existeCorreo(String correo) {
        String sql = "SELECT correo FROM comprador WHERE correo = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();

            // Si encuentra un registro ->el correo ya está ocupado
            return rs.next();

        } catch (SQLException e) {
            System.out.println(e.toString());
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
