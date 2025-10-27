package Conexion;

import Modelos.PedidosProductos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositorio {
    private Connection connection;

    public PedidoRepositorio() {
        connection = Conexion.getConexion();
    }

    // Listar todos (solo id_producto y cantidad)
    public List<PedidosProductos> listar() {
        List<PedidosProductos> lista = new ArrayList<>();
        String sql = "SELECT id_producto, cantidad_pedidos_productos FROM pedidos_productos";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                int cantidad = rs.getInt("cantidad_pedidos_productos");

                PedidosProductos pp = new PedidosProductos(idProducto, cantidad);
                lista.add(pp);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar pedidos_productos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // Listar por id_pedidoespecial (filtra por pedido) — devuelve sólo idProducto + cantidad
    public List<PedidosProductos> listarPorPedido(int idPedidoEspecial) {
        List<PedidosProductos> lista = new ArrayList<>();
        String sql = "SELECT id_producto, cantidad_pedidos_productos FROM pedidos_productos WHERE id_pedidoespecial = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idPedidoEspecial);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idProducto = rs.getInt("id_producto");
                    int cantidad = rs.getInt("cantidad_pedidos_productos");
                    PedidosProductos pp = new PedidosProductos(idProducto, cantidad);
                    lista.add(pp);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listarPorPedido: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // Insertar: solo id_producto + cantidad (ojo con FK en BD)
    public boolean insertar(PedidosProductos pp) {
        String sql = "INSERT INTO pedidos_productos (id_producto, cantidad_pedidos_productos) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pp.getIDproducto());
            ps.setInt(2, pp.getCantidad());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar pedidos_productos: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar cantidad por id_producto (si hay varias filas por producto en distintos pedidos, actualizará todas)
    public boolean actualizarPorProducto(PedidosProductos pp) {
        String sql = "UPDATE pedidos_productos SET cantidad_pedidos_productos = ? WHERE id_producto = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, pp.getCantidad());
            ps.setInt(2, pp.getIDproducto());
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar pedidos_productos por producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar por id_producto
    public boolean eliminarPorProducto(int idProducto) {
        String sql = "DELETE FROM pedidos_productos WHERE id_producto = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, idProducto);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar pedidos_productos por producto: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
