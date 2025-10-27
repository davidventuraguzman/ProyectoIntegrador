package Conexion;

import Modelos.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio {

    // Listar todos los productos
    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
<<<<<<< HEAD
        String sql = "SELECT id_producto, nombre_producto, precio_venta_producto, tipo_producto FROM Productos";
=======
        String sql = "SELECT id_producto, nombre_producto, precio_venta_producto, tipo_producto FROM productos";
>>>>>>> d78abd3b82288bc9acd4a147a61ecddbfa0cdaca

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id_producto"),
<<<<<<< HEAD
                        rs.getString("nombre_producto"),
                        rs.getDouble("precio_venta_producto"),
                        rs.getString("tipo_producto")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
        return lista;
    }

    // Buscar productos por nombre (para el autocompletado)
    public List<Producto> buscarPorNombre(String filtro) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre_producto, precio_venta_producto, tipo_producto FROM Productos WHERE nombre_producto LIKE ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + filtro + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto(
                        rs.getInt("id_producto"), // ✅ AHORA se obtiene del resultado
=======
>>>>>>> d78abd3b82288bc9acd4a147a61ecddbfa0cdaca
                        rs.getString("nombre_producto"),
                        rs.getDouble("precio_venta_producto"),
                        rs.getString("tipo_producto")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar productos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;

    }

    public Producto buscarPorId(int idProducto) {
        Producto p = null;
        String sql = "SELECT id_producto, nombre_producto, precio_venta_producto, tipo_producto FROM productos WHERE id_producto = ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                p = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre_producto"), // ✅ corregido
                        rs.getDouble("precio_venta_producto"), // ✅ corregido
                        rs.getString("tipo_producto") // ✅ corregido
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar producto por ID: " + e.getMessage());
        }

        return p;
    }

    // Buscar productos por nombre (LIKE, case-insensitive)
    public List<Producto> buscarPorNombre(String filtro) {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre_producto, precio_venta_producto, tipo_producto "
                + "FROM productos WHERE LOWER(nombre_producto) LIKE LOWER(?)";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + filtro + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto(
                            rs.getInt("id_producto"),
                            rs.getString("nombre_producto"),
                            rs.getDouble("precio_venta_producto"),
                            rs.getString("tipo_producto")
                    );
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en buscarPorNombre: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // Insertar un producto (devuelve true si insertó)
    public boolean insertar(Producto p) {
        String sql = "INSERT INTO productos (nombre_producto, precio_venta_producto, tipo_producto) VALUES (?, ?, ?)";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setString(3, p.getCategoria());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar producto por id
    public boolean eliminar(int idProducto) {
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    // dentro de Conexion.ProductoRepositorio

    public boolean eliminarConDependencias(int idProducto) {
        String[] tablas = {
            "productos_ingredientes",
            "pedidos_productos",
            "compras",
            "ventas"
        };

        Connection con = null;
        boolean success = false;
        try {
            con = Conexion.getConexion();
            con.setAutoCommit(false);

            // Opcional: contar referencias por tabla (y guardar los conteos)
            String countSqlTemplate = "SELECT COUNT(*) AS cnt FROM %s WHERE id_producto = ?";
            for (String tabla : tablas) {
                String countSql = String.format(countSqlTemplate, tabla);
                try (PreparedStatement psCount = con.prepareStatement(countSql)) {
                    psCount.setInt(1, idProducto);
                    try (ResultSet rs = psCount.executeQuery()) {
                        if (rs.next()) {
                            int cnt = rs.getInt("cnt");
                            if (cnt > 0) {
                                System.out.println("Referencias en " + tabla + ": " + cnt);
                            }
                        }
                    }
                }
            }

            // Borrar en orden: tablas hijas primero
            String deleteTemplate = "DELETE FROM %s WHERE id_producto = ?";
            for (String tabla : tablas) {
                String delSql = String.format(deleteTemplate, tabla);
                try (PreparedStatement psDel = con.prepareStatement(delSql)) {
                    psDel.setInt(1, idProducto);
                    int borradas = psDel.executeUpdate();
                    if (borradas > 0) {
                        System.out.println("Borradas " + borradas + " filas en " + tabla);
                    }
                }
            }

            // Finalmente borrar el producto
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM productos WHERE id_producto = ?")) {
                ps.setInt(1, idProducto);
                int filas = ps.executeUpdate();
                if (filas == 0) {
                    // no existía el producto
                    con.rollback();
                    System.err.println("No se encontró el producto con id " + idProducto);
                    return false;
                }
            }

            con.commit();
            success = true;
            return true;
        } catch (SQLException ex) {
            // intento de rollback si algo falló
            System.err.println("Error en eliminarConDependencias: " + ex.getMessage());
            ex.printStackTrace();
            if (con != null) {
                try {
                    con.rollback();
                    System.err.println("Se hizo rollback de la transacción.");
                } catch (SQLException rbEx) {
                    System.err.println("Error al hacer rollback: " + rbEx.getMessage());
                    rbEx.printStackTrace();
                }
            }
            return false;
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Error al cerrar/restore autocommit: " + e.getMessage());
                }
            }
        }
    }
    public int contarReferencias(int idProducto) {
    String[] tablas = {"productos_ingredientes", "pedidos_productos", "compras", "ventas"};
    int total = 0;
    String sqlTemplate = "SELECT COUNT(*) AS cnt FROM %s WHERE id_producto = ?";

    try (Connection con = Conexion.getConexion()) {
        for (String tabla : tablas) {
            String sql = String.format(sqlTemplate, tabla);
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, idProducto);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) total += rs.getInt("cnt");
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error en contarReferencias: " + e.getMessage());
        e.printStackTrace();
    }
    return total;
}

}
