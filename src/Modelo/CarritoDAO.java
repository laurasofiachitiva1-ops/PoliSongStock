package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CarritoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public boolean agregarItemCarrito(Carrito carrito) {
        // Actualizado: Agregar id_vendedor en la consulta INSERT
        String sql = "INSERT INTO items_carrito(id_comprador, id_vendedor, tipo, id_producto, cantidad, precio_unitario, fecha_agregado, estado) VALUES (?,?,?,?,?,?,?,?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, carrito.getId_comprador());
            ps.setInt(2, carrito.getId_vendedor()); 
            ps.setString(3, carrito.getTipo());
            ps.setInt(4, carrito.getId_producto());
            ps.setInt(5, carrito.getCantidad());
            ps.setDouble(6, carrito.getPrecio_unitario());
            ps.setDate(7, carrito.getFecha_agregado());
            ps.setString(8, carrito.getEstado() != null ? carrito.getEstado() : "activo");

            ps.executeUpdate();

            // Obtener el ID generado y setearlo en el objeto
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                carrito.setId_item(rs.getInt(1));
            }

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error al agregar item al carrito:\n" + e.getMessage(),
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

    public Carrito buscarPorIdItem(int idItem) {
        // Actualizado: Incluir id_vendedor en el SELECT
        String sql = "SELECT * FROM items_carrito WHERE id_item = ?";

        Carrito carrito = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idItem);
            rs = ps.executeQuery();

            if (rs.next()) {
                carrito = new Carrito();
                carrito.setId_item(rs.getInt("id_item"));
                carrito.setId_comprador(rs.getInt("id_comprador"));
                carrito.setId_vendedor(rs.getInt("id_vendedor")); 
                carrito.setTipo(rs.getString("tipo"));
                carrito.setId_producto(rs.getInt("id_producto"));
                carrito.setCantidad(rs.getInt("cantidad"));
                carrito.setPrecio_unitario(rs.getDouble("precio_unitario"));
                carrito.setFecha_agregado(rs.getDate("fecha_agregado"));
                carrito.setEstado(rs.getString("estado"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar item del carrito: " + e.getMessage());
        }
        return carrito;
    }

    // Método para listar items del carrito por comprador (solo activos)
    public List<Carrito> listarCarritoPorComprador(int idComprador) {
        List<Carrito> lista = new ArrayList<>();
        String sql = "SELECT * FROM items_carrito WHERE id_comprador = ? AND estado = 'activo'";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idComprador);
            rs = ps.executeQuery();

            while (rs.next()) {
                Carrito carrito = new Carrito();
                carrito.setId_item(rs.getInt("id_item"));
                carrito.setId_comprador(rs.getInt("id_comprador"));
                carrito.setId_vendedor(rs.getInt("id_vendedor"));  
                carrito.setTipo(rs.getString("tipo"));
                carrito.setId_producto(rs.getInt("id_producto"));
                carrito.setCantidad(rs.getInt("cantidad"));
                carrito.setPrecio_unitario(rs.getDouble("precio_unitario"));
                carrito.setFecha_agregado(rs.getDate("fecha_agregado"));
                carrito.setEstado(rs.getString("estado"));
                lista.add(carrito);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al listar carrito: " + e.getMessage());
        }

        return lista;
    }

    /*// Método para eliminar un item del carrito
    public boolean eliminarItemCarrito(int idItem) {
        String sql = "DELETE FROM items_carrito WHERE id_item = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idItem);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar item del carrito: " + e.getMessage());
            return false;
        }
    }

    // Método para modificar un item del carrito (por ejemplo, cantidad)
    public boolean modificarItemCarrito(Carrito carrito) {
        // Si se descomenta y usa, actualizar para incluir id_vendedor si es necesario
        String sql = "UPDATE items_carrito SET id_vendedor = ?, cantidad = ?, precio_unitario = ?, estado = ? WHERE id_item = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, carrito.getId_vendedor());  // <-- Agregado si se usa
            ps.setInt(2, carrito.getCantidad());
            ps.setDouble(3, carrito.getPrecio_unitario());
            ps.setString(4, carrito.getEstado());
            ps.setInt(5, carrito.getId_item());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar item del carrito: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    }*/

    // Método para marcar el carrito de un comprador como pagado (cambia estado a 'pagado')
    public boolean marcarCarritoPagado(int idComprador) {
        String sql = "UPDATE items_carrito SET estado = 'pagado' WHERE id_comprador = ? AND estado = 'activo'";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idComprador);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al marcar carrito como pagado: " + e.getMessage());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
            }
        }
    }
}
