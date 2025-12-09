package Conexion;

import Modelos.PedidosProductos;
import Modelos.Reportes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PedidoRepositorio {
    private static final Logger logger = LoggerFactory.getLogger(PedidoRepositorio.class);
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
                logger.info("✅ Pedido insertado con ID: {}", idGenerado);

                
            }

        } catch (SQLException e) {
            logger.error("❌ Error al insertar pedido: {}", e.getMessage(), e);
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
            logger.error("❌ Error al listar pedidos: {}", e.getMessage(), e);
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
             logger.error("❌ Error al listar pedidos por cliente: {}", e.getMessage(), e);
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
             logger.error("❌ Error al listar pedidos:  {}", e.getMessage(), e);
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
         logger.error("❌ Error al eliminar pedido:  {}", e.getMessage(), e);
                return false;
            }

        } catch (SQLException e) {
           logger.error("❌ Error conexion EliminarPedido{}", e.getMessage(), e);
            return false;
        }
    }

    public List<Reportes> obtenerReportes() {
        List<Reportes> lista = new ArrayList<>();

        String sql = """
        SELECT 
            c.nombre_cliente AS cliente,
            p.nombre_producto AS producto,
            co.cantidad_compra AS cantidad,
            p.precio_venta_producto AS precio,
            (co.cantidad_compra * p.precio_venta_producto) AS total
        FROM compras co
        INNER JOIN cliente c ON co.id_cliente = c.id_cliente
        INNER JOIN productos p ON co.id_producto = p.id_producto;
    """;

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reportes r = new Reportes(
                        rs.getString("cliente"),
                        rs.getString("producto"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"),
                        rs.getDouble("total")
                );
                lista.add(r);
            }

        } catch (SQLException e) {
             logger.error("❌ Error al obtener reportes:{}", e.getMessage(), e);
        }

        return lista;
    }

}
