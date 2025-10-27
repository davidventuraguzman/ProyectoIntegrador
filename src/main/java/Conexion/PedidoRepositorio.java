/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelos.PedidosProductos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author LENOVO
 */
public class PedidoRepositorio {
    public int insertarPedido(PedidosProductos pedido) {
        String sql = "INSERT INTO pedidos_productos (id_cliente, total) VALUES (?, ?)";
        int idGenerado = -1;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, pedido.getIdCliente());
            ps.setDouble(2, pedido.getTotal());
            ps.executeUpdate();

            // Recuperar el ID autogenerado del pedido
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar pedido: " + e.getMessage());
        }

        return idGenerado; // devolver el id_pedido generado
    }
}
