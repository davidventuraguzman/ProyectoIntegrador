package Conexion;

import Modelos.DetallePedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DetallePedidoRepositorio {

    private static final Logger logger = LoggerFactory.getLogger(DetallePedido.class);
    public void insertarDetalle(DetallePedido detalle) {
        String sql = "INSERT INTO detalle_pedido (id_pedidoespecial, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdPedido());
            ps.setInt(2, detalle.getIdProducto());
            ps.setInt(3, detalle.getCantidad());
            ps.setDouble(4, detalle.getPrecioUnitario());

            int filas = ps.executeUpdate();
            logger.info("✅ Detalle insertado correctamente: filas afectadas = {}", filas);
            

        } catch (SQLException e) {
            logger.error("❌ Error al insertar detalle: {}", e.getMessage(), e);
            e.printStackTrace();
        }
    }
}
