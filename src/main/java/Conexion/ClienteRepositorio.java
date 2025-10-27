/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import Modelos.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author David
 */
public class ClienteRepositorio {
     private final Connection conn;

    public ClienteRepositorio() {
        conn = Conexion.getConexion();
    }

    /**
     * Busca un cliente por su DNI (dni_cliente en la BD).
     * Retorna null si no existe.
     * Nota: dni en el modelo Cliente es int; aquí intentamos parsearlo.
     */
    public Cliente buscarPorDni(String dni) {
        String sql = "SELECT id_cliente, nombre_cliente, telefono_cliente, direccion_cliente, correo_cliente, dni_cliente "
                   + "FROM cliente WHERE dni_cliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre_cliente");
                    String telefono = rs.getString("telefono_cliente");
                    String direccion = rs.getString("direccion_cliente");
                    String correo = rs.getString("correo_cliente");
                    String dniBd = rs.getString("dni_cliente");

                    int dniInt = 0;
                    try {
                        dniInt = Integer.parseInt(dniBd);
                    } catch (Exception ex) {
                        // si no parsea, dejamos 0 (o podrías guardar -1)
                    }

                    Cliente c = new Cliente(nombre, telefono, direccion, correo, dniInt);
                    return c;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error buscarPorDni: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Devuelve el id_cliente asociado al dni, o null si no existe.
     */
    public Integer getIdClienteByDni(String dni) {
        String sql = "SELECT id_cliente FROM cliente WHERE dni_cliente = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_cliente");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getIdClienteByDni: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Lista los pedidos (pedido_especial) de un cliente por su id_cliente.
     * Cada entrada es una línea descriptiva: "ID - fecha_pedido -> descripcion (precio)"
     */
    public List<String> listarPedidosPorClienteId(int idCliente) {
        List<String> pedidos = new ArrayList<>();
        String sql = "SELECT id_pedidoespecial, fecha_pedidoespecial, fecha_entrega_pedidoespecial, descripcion_pedidoespecial, precio_pedidoespecial "
                   + "FROM pedido_especial WHERE id_cliente = ? ORDER BY fecha_pedidoespecial DESC";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idPed = rs.getInt("id_pedidoespecial");
                    java.sql.Date fecha = rs.getDate("fecha_pedidoespecial");
                    java.sql.Date fechaEntrega = rs.getDate("fecha_entrega_pedidoespecial");
                    String desc = rs.getString("descripcion_pedidoespecial");
                    double precio = rs.getDouble("precio_pedidoespecial");

                    String linea = String.format("Pedido %d — pedido: %s — entrega: %s — %s (S/. %.2f)",
                            idPed,
                            fecha != null ? fecha.toString() : "sin fecha",
                            fechaEntrega != null ? fechaEntrega.toString() : "sin fecha entrega",
                            (desc != null ? desc : ""),
                            precio);
                    pedidos.add(linea);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error listarPedidosPorClienteId: " + e.getMessage());
            e.printStackTrace();
        }
        return pedidos;
    }

    // ----- Métodos útiles extra (opcionales) -----
    // insertar cliente (si quieres usarlo más tarde)
    public boolean insertarCliente(String nombre, String dni, String telefono, String direccion, String correo) {
        String sql = "INSERT INTO cliente (nombre_cliente, dni_cliente, telefono_cliente, direccion_cliente, correo_cliente) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, dni);
            ps.setString(3, telefono);
            ps.setString(4, direccion);
            ps.setString(5, correo);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error insertarCliente: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
