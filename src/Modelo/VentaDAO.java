package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class VentaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    // Insertar una nueva venta
    public boolean insertarVenta(Venta venta) {
        String sql = "INSERT INTO ventas(id_comprador, fecha, total, metodo_pago, estado, observacion) VALUES (?,?,?,?,?,?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, venta.getId_comprador());
            ps.setDate(2, new java.sql.Date(venta.getFecha().getTime()));  // Convertir a sql.Date
            if (venta.getTotal() != null) {
                ps.setDouble(3, venta.getTotal());
            } else {
                ps.setNull(3, java.sql.Types.DECIMAL);
            }
            ps.setString(4, venta.getMetodo_pago());
            ps.setString(5, venta.getEstado());
            ps.setString(6, venta.getObservacion());

            ps.executeUpdate();

            // Obtener el ID generado y setearlo en el objeto
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                venta.setId_venta(rs.getInt(1));
            }

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al insertar venta:\n" + e.getMessage(),
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

    // Buscar venta por ID
    public Venta buscarPorIdVenta(int idVenta) {
        String sql = "SELECT * FROM ventas WHERE id_venta = ?";

        Venta venta = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            rs = ps.executeQuery();

            if (rs.next()) {
                venta = new Venta();
                venta.setId_venta(rs.getInt("id_venta"));
                venta.setId_comprador(rs.getInt("id_comprador"));
                venta.setFecha(rs.getDate("fecha"));  // Se convierte automáticamente a util.Date si es necesario
                venta.setTotal(rs.getDouble("total"));
                venta.setMetodo_pago(rs.getString("metodo_pago"));
                venta.setEstado(rs.getString("estado"));
                venta.setObservacion(rs.getString("observacion"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar venta: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return venta;
    }

    // Listar ventas por comprador
    public List<Venta> listarVentasPorComprador(int idComprador) {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM ventas WHERE id_comprador = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idComprador);
            rs = ps.executeQuery();

            while (rs.next()) {
                Venta venta = new Venta();
                venta.setId_venta(rs.getInt("id_venta"));
                venta.setId_comprador(rs.getInt("id_comprador"));
                venta.setFecha(rs.getDate("fecha"));
                venta.setTotal(rs.getDouble("total"));
                venta.setMetodo_pago(rs.getString("metodo_pago"));
                venta.setEstado(rs.getString("estado"));
                venta.setObservacion(rs.getString("observacion"));
                lista.add(venta);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar ventas: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return lista;
    }

    // Eliminar venta por ID
    public boolean eliminarVenta(int idVenta) {
        String sql = "DELETE FROM ventas WHERE id_venta = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar venta: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    // Modificar venta
    public boolean modificarVenta(Venta venta) {
        String sql = "UPDATE ventas SET id_comprador = ?, fecha = ?, total = ?, metodo_pago = ?, estado = ?, observacion = ? WHERE id_venta = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta.getId_comprador());
            ps.setDate(2, new java.sql.Date(venta.getFecha().getTime()));
            if (venta.getTotal() != null) {
                ps.setDouble(3, venta.getTotal());
            } else {
                ps.setNull(3, java.sql.Types.DECIMAL);
            }
            ps.setString(4, venta.getMetodo_pago());
            ps.setString(5, venta.getEstado());
            ps.setString(6, venta.getObservacion());
            ps.setInt(7, venta.getId_venta());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar venta: " + e.getMessage());
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
