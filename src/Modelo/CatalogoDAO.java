package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogoDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();

    public List<ProductoCatalogo> listarTodoPorVendedor(int idVendedor) {
        List<ProductoCatalogo> lista = new ArrayList<>();

        try {
            con = cn.getConnection();

            // =============================
            // 1. VINILOS
            // =============================
            String sqlVin = """
                SELECT dv.id_disco_vinilo AS id, dv.nombre, a.nombre AS artista, 
                       dv.genero, dv.precio, dv.imagen
                FROM disco_vinilo dv
                JOIN autor a ON dv.id_autor = a.id_autor
                WHERE dv.id_vendedor = ?
            """;

            ps = con.prepareStatement(sqlVin);
            ps.setInt(1, idVendedor);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductoCatalogo p = new ProductoCatalogo();
                p.setId(rs.getInt("id"));
                p.setTipo("Vinilo");
                p.setNombre(rs.getString("nombre"));
                p.setArtista(rs.getString("artista"));
                p.setGenero(rs.getString("genero"));
                p.setPrecio(rs.getDouble("precio"));
                p.setImagen(rs.getBytes("imagen"));
                lista.add(p);
            }

            // =============================
            // 2. MP3
            // =============================
            String sqlMp3 = """
                SELECT m.id_disco_mp3 AS id, m.nombre, a.nombre AS artista, 
                       m.genero, m.precio, m.imagen
                FROM disco_mp3 m
                JOIN autor a ON m.id_autor = a.id_autor
                WHERE m.id_vendedor = ?
            """;

            ps = con.prepareStatement(sqlMp3);
            ps.setInt(1, idVendedor);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductoCatalogo p = new ProductoCatalogo();
                p.setId(rs.getInt("id"));
                p.setTipo("MP3");
                p.setNombre(rs.getString("nombre"));
                p.setArtista(rs.getString("artista"));
                p.setGenero(rs.getString("genero"));
                p.setPrecio(rs.getDouble("precio"));
                p.setImagen(rs.getBytes("imagen"));
                lista.add(p);
            }

            // =============================
            // 3. CANCIONES
            // =============================
            String sqlCan = """
                SELECT c.id_cancion AS id, c.nombre, a.nombre AS artista, 
                    c.genero, c.precio
                FROM cancion c
                JOIN autor a ON c.id_autor = a.id_autor
                WHERE c.id_vendedor = ?
            """;

            ps = con.prepareStatement(sqlCan);
            ps.setInt(1, idVendedor);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductoCatalogo p = new ProductoCatalogo();
                p.setId(rs.getInt("id"));
                p.setTipo("Canción");
                p.setNombre(rs.getString("nombre"));
                p.setArtista(rs.getString("artista"));
                p.setGenero(rs.getString("genero"));
                p.setPrecio(rs.getDouble("precio"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar catálogo: " + e.getMessage());
        }

        return lista;
    }

    public List<ProductoCatalogo> listarTodo() {
        List<ProductoCatalogo> lista = new ArrayList<>();

        try {
            con = cn.getConnection();

            // =============================
            // 1. VINILOS
            // =============================
            String sqlVin = """
            SELECT dv.id_disco_vinilo AS id, dv.nombre, a.nombre AS artista, 
                   dv.genero, dv.precio, dv.imagen
            FROM disco_vinilo dv
            JOIN autor a ON dv.id_autor = a.id_autor
        """;

            ps = con.prepareStatement(sqlVin);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductoCatalogo p = new ProductoCatalogo();
                p.setId(rs.getInt("id"));
                p.setTipo("Vinilo");
                p.setNombre(rs.getString("nombre"));
                p.setArtista(rs.getString("artista"));
                p.setGenero(rs.getString("genero"));
                p.setPrecio(rs.getDouble("precio"));
                p.setImagen(rs.getBytes("imagen"));
                lista.add(p);
            }

            // =============================
            // 2. MP3
            // =============================
            String sqlMp3 = """
            SELECT m.id_disco_mp3 AS id, m.nombre, a.nombre AS artista, 
                   m.genero, m.precio, m.imagen
            FROM disco_mp3 m
            JOIN autor a ON m.id_autor = a.id_autor
        """;

            ps = con.prepareStatement(sqlMp3);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductoCatalogo p = new ProductoCatalogo();
                p.setId(rs.getInt("id"));
                p.setTipo("MP3");
                p.setNombre(rs.getString("nombre"));
                p.setArtista(rs.getString("artista"));
                p.setGenero(rs.getString("genero"));
                p.setPrecio(rs.getDouble("precio"));
                p.setImagen(rs.getBytes("imagen"));
                lista.add(p);
            }

            // =============================
            // 3. CANCIONES
            // =============================
            String sqlCan = """
            SELECT c.id_cancion AS id, c.nombre, a.nombre AS artista, 
                c.genero, c.precio
            FROM cancion c
            JOIN autor a ON c.id_autor = a.id_autor
        """;

            ps = con.prepareStatement(sqlCan);
            rs = ps.executeQuery();

            while (rs.next()) {
                ProductoCatalogo p = new ProductoCatalogo();
                p.setId(rs.getInt("id"));
                p.setTipo("Canción");
                p.setNombre(rs.getString("nombre"));
                p.setArtista(rs.getString("artista"));
                p.setGenero(rs.getString("genero"));
                p.setPrecio(rs.getDouble("precio"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar catálogo completo: " + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

        return lista;
    }

}
