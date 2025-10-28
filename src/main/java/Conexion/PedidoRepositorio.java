package Conexion;

import Modelos.PedidosProductos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoRepositorio {

    // ✅ Inserta un pedido y devuelve el ID autogenerado
    public int insertarPedido(PedidosProductos pedido) {
        String sql = "INSERT INTO pedidos_productos (id_cliente, id_producto, total, fecha_pedido, cantidad_pedidos_productos) VALUES (?, ?, ?, ?, ?)";
        int idGenerado = -1;

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pedido.getIdCliente());

            // Permitir null si el producto no está definido
            if (pedido.getIdProducto() > 0) {
                ps.setInt(2, pedido.getIdProducto());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setDouble(3, pedido.getTotal());
            ps.setDate(4, Date.valueOf(pedido.getFechaPedido()));
            ps.setInt(5, pedido.getCantidad());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
                System.out.println("✅ Pedido insertado con ID: " + idGenerado);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar pedido: " + e.getMessage());
        }

        return idGenerado;
    }

    // ✅ Listar todos los pedidos
    public List<PedidosProductos> listar() {
        List<PedidosProductos> lista = new ArrayList<>();
        String sql = "SELECT id_pedidoespecial, id_cliente, id_producto, total, fecha_pedido, cantidad_pedidos_productos FROM pedidos_productos";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PedidosProductos pedido = new PedidosProductos(
                        rs.getInt("id_pedidoespecial"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_producto"),
                        rs.getDouble("total"),
                        rs.getDate("fecha_pedido").toLocalDate(),
                        rs.getInt("cantidad_pedidos_productos")
                );
                lista.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar pedidos: " + e.getMessage());
        }

        return lista;
    }

    // ✅ Buscar pedidos por cliente
    public List<PedidosProductos> listarPorCliente(int idCliente) {
        List<PedidosProductos> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos_productos WHERE id_cliente = ?";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PedidosProductos pedido = new PedidosProductos(
                        rs.getInt("id_pedidoespecial"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_producto"),
                        rs.getDouble("total"),
                        rs.getDate("fecha_pedido").toLocalDate(),
                        rs.getInt("cantidad_pedidos_productos")
                );
                lista.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar pedidos por cliente: " + e.getMessage());
        }

        return lista;
    }

    // Listar todos los pedidos (información general)
    public List<PedidosProductos> listarPedidos() {
        List<PedidosProductos> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos_productos";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PedidosProductos pedido = new PedidosProductos();
                pedido.setIdPedidoEspecial(rs.getInt("id_pedidoespecial"));
                pedido.setIdCliente(rs.getInt("id_cliente"));
                pedido.setIdProducto(rs.getInt("id_producto"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setFechaPedido(rs.getDate("fecha_pedido").toLocalDate());
                pedido.setCantidad(rs.getInt("cantidad_pedidos_productos"));
                lista.add(pedido);
            }

        } catch (SQLException e) {
            System.err.println("❌ Error al listar pedidos: " + e.getMessage());
        }

        return lista;
    }

    // Eliminar un pedido completo
    public boolean eliminarPedido(int idPedidoEspecial) {
        String sqlDetalle = "DELETE FROM detalle_pedido WHERE id_pedidoespecial = ?";
        String sqlPedido = "DELETE FROM pedidos_productos WHERE id_pedidoespecial = ?";

        try (Connection con = Conexion.getConexion()) {
            con.setAutoCommit(false);

            try (PreparedStatement psDetalle = con.prepareStatement(sqlDetalle); PreparedStatement psPedido = con.prepareStatement(sqlPedido)) {

                psDetalle.setInt(1, idPedidoEspecial);
                psDetalle.executeUpdate();

                psPedido.setInt(1, idPedidoEspecial);
                int filas = psPedido.executeUpdate();

                con.commit();
                return filas > 0;

            } catch (SQLException e) {
                con.rollback();
                System.err.println("❌ Error al eliminar pedido: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.err.println("❌ Error en conexión: " + e.getMessage());
            return false;
        }
    }

}
