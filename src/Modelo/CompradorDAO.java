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
                c.setCompras(rs.getInt("compras"));
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

    public Comprador buscarPorId(int idComprador) {
        Comprador c = null;  // Inicializar como null para devolver null si no se encuentra
        String sql = "SELECT * FROM comprador WHERE id_comprador = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idComprador);
            rs = ps.executeQuery();
            if (rs.next()) {
                c = new Comprador();
                c.setId_comprador(rs.getInt("id_comprador"));
                c.setNombre(rs.getString("nombre"));
                c.setCorreo(rs.getString("correo"));
                c.setDireccion(rs.getString("direccion"));
                c.setPassword(rs.getString("password"));
                c.setCompras(rs.getInt("compras"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar comprador por ID: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return c;  // Devuelve el objeto Comprador o null si no se encuentra
    }

    public int contarVentasPorComprador(int idComprador) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS total FROM ventas WHERE id_comprador = ?";  // Cambié 'venta' a 'ventas'
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idComprador);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al contar ventas por comprador: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return count;
    }

    public boolean incrementarCompras(int idComprador) {
        String sql = "UPDATE comprador SET compras = compras + 1 WHERE id_comprador = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idComprador);
            int rows = ps.executeUpdate();
            return rows > 0;  // Devuelve true si se actualizó al menos una fila
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al incrementar compras: " + e.getMessage());
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
