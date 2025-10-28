package Conexion;

import Modelos.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio {

    // 📦 Listar todos los productos
    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre_producto, precio_venta_producto, tipo_producto FROM productos";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre_producto"),
                        rs.getDouble("precio_venta_producto"),
                        rs.getString("tipo_producto")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
        }
        return lista;
    }

    // 🔍 Buscar producto por nombre (para autocompletado)
    public List<Producto> buscarPorNombre(String filtro) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre_producto, precio_venta_producto, tipo_producto "
                   + "FROM productos WHERE LOWER(nombre_producto) LIKE LOWER(?)";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + filtro + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre_producto"),
                        rs.getDouble("precio_venta_producto"),
                        rs.getString("tipo_producto")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error en buscarPorNombre: " + e.getMessage());
        }
        return lista;
    }

    // 🔎 Buscar producto por ID
    public Producto buscarPorId(int idProducto) {
        Producto p = null;
        String sql = "SELECT id_producto, nombre_producto, precio_venta_producto, tipo_producto "
                   + "FROM productos WHERE id_producto = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre_producto"),
                        rs.getDouble("precio_venta_producto"),
                        rs.getString("tipo_producto")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar producto por ID: " + e.getMessage());
        }

        return p;
    }

    // ➕ Insertar un nuevo producto
    public boolean insertar(Producto p) {
        String sql = "INSERT INTO productos (nombre_producto, precio_venta_producto, tipo_producto) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setString(3, p.getCategoria());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
            return false;
        }
    }

    // ❌ Eliminar un producto por ID
    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            return false;
        }
    }

    // ⚙️ Eliminar producto junto con dependencias
    public boolean eliminarConDependencias(int idProducto) {
        String[] tablas = {"productos_ingredientes", "pedidos_productos", "compras", "ventas"};

        Connection con = null;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

            for (String tabla : tablas) {
                String sql = "DELETE FROM " + tabla + " WHERE id_producto = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idProducto);
                    ps.executeUpdate();
                }
            }

            try (PreparedStatement ps = con.prepareStatement("DELETE FROM productos WHERE id_producto = ?")) {
                ps.setInt(1, idProducto);
                ps.executeUpdate();
            }

            con.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("Error en eliminarConDependencias: " + e.getMessage());
            try {
                if (con != null) con.rollback();
            } catch (SQLException ex) {
                System.err.println("Error en rollback: " + ex.getMessage());
            }
            return false;

        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }

    // 📊 Contar referencias del producto en otras tablas
    public int contarReferencias(int idProducto) {
        String[] tablas = {"productos_ingredientes", "pedidos_productos", "compras", "ventas"};
        int total = 0;

        try (Connection con = Conexion.getConexion()) {
            for (String tabla : tablas) {
                String sql = "SELECT COUNT(*) AS cnt FROM " + tabla + " WHERE id_producto = ?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, idProducto);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) total += rs.getInt("cnt");
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en contarReferencias: " + e.getMessage());
        }
        return total;
    }
}
