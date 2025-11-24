package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Detalle_ventaDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    // Insertar un nuevo detalle de venta
    public boolean insertarDetalleVenta(Detalle_venta detalle) {
        String sql = "INSERT INTO detalle_venta(id_venta, id_vendedor, id_producto, tipo, cantidad, precio_unit, total) VALUES (?,?,?,?,?,?,?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, detalle.getId_venta());
            ps.setInt(2, detalle.getId_vendedor());  // Agregado
            ps.setInt(3, detalle.getId_producto());
            ps.setString(4, detalle.getTipo());
            ps.setInt(5, detalle.getCantidad());
            ps.setDouble(6, detalle.getPrecio_unit());
            ps.setDouble(7, detalle.getTotal());

            ps.executeUpdate();

            // Obtener el ID generado y setearlo en el objeto
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                detalle.setId_detalle(rs.getInt(1));
            }

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al insertar detalle de venta:\n" + e.getMessage(),
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

    // Buscar detalle por ID
    public Detalle_venta buscarPorIdDetalle(int idDetalle) {
        String sql = "SELECT * FROM detalle_venta WHERE id_detalle = ?";

        Detalle_venta detalle = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idDetalle);
            rs = ps.executeQuery();

            if (rs.next()) {
                detalle = new Detalle_venta();
                detalle.setId_detalle(rs.getInt("id_detalle"));
                detalle.setId_venta(rs.getInt("id_venta"));
                detalle.setId_vendedor(rs.getInt("id_vendedor"));  // Agregado
                detalle.setId_producto(rs.getInt("id_producto"));
                detalle.setTipo(rs.getString("tipo"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio_unit(rs.getDouble("precio_unit"));
                detalle.setTotal(rs.getDouble("total"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar detalle de venta: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return detalle;
    }

    // Listar detalles por venta
    public List<Detalle_venta> listarDetallesPorVenta(int idVenta) {
        List<Detalle_venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta WHERE id_venta = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVenta);
            rs = ps.executeQuery();

            while (rs.next()) {
                Detalle_venta detalle = new Detalle_venta();
                detalle.setId_detalle(rs.getInt("id_detalle"));
                detalle.setId_venta(rs.getInt("id_venta"));
                detalle.setId_vendedor(rs.getInt("id_vendedor"));  // Agregado
                detalle.setId_producto(rs.getInt("id_producto"));
                detalle.setTipo(rs.getString("tipo"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio_unit(rs.getDouble("precio_unit"));
                detalle.setTotal(rs.getDouble("total"));
                lista.add(detalle);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar detalles de venta: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return lista;
    }

    // Eliminar detalle por ID
    public boolean eliminarDetalleVenta(int idDetalle) {
        String sql = "DELETE FROM detalle_venta WHERE id_detalle = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idDetalle);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar detalle de venta: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }

    // Modificar detalle de venta
    public boolean modificarDetalleVenta(Detalle_venta detalle) {
        String sql = "UPDATE detalle_venta SET id_venta = ?, id_vendedor = ?, id_producto = ?, tipo = ?, cantidad = ?, precio_unit = ?, total = ? WHERE id_detalle = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, detalle.getId_venta());
            ps.setInt(2, detalle.getId_vendedor());  // Agregado
            ps.setInt(3, detalle.getId_producto());
            ps.setString(4, detalle.getTipo());
            ps.setInt(5, detalle.getCantidad());
            ps.setDouble(6, detalle.getPrecio_unit());
            ps.setDouble(7, detalle.getTotal());
            ps.setInt(8, detalle.getId_detalle());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar detalle de venta: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    }
    
    
    public List<Detalle_venta> listarDetallesPorVendedor(int idVendedor) {
        List<Detalle_venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM detalle_venta WHERE id_vendedor = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idVendedor);
            rs = ps.executeQuery();
            while (rs.next()) {
                Detalle_venta detalle = new Detalle_venta();
                detalle.setId_detalle(rs.getInt("id_detalle"));
                detalle.setId_venta(rs.getInt("id_venta"));
                detalle.setId_vendedor(rs.getInt("id_vendedor"));
                detalle.setId_producto(rs.getInt("id_producto"));
                detalle.setTipo(rs.getString("tipo"));
                detalle.setCantidad(rs.getInt("cantidad"));
                detalle.setPrecio_unit(rs.getDouble("precio_unit"));
                detalle.setTotal(rs.getDouble("total"));
                lista.add(detalle);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar detalles por vendedor: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return lista;
    }
    
}
