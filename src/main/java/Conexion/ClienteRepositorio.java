/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import Modelos.Cliente;
import java.sql.Connection;
import java.sql.Date;
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
    public int getIdClienteByDni(String dni) {
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
        return 0;
    }

    /**
     * Lista los pedidos (pedido_especial) de un cliente por su id_cliente.
     * Cada entrada es una línea descriptiva: "ID - fecha_pedido -> descripcion (precio)"
     */
    public List<String> listarPedidosPorClienteId(int idCliente) {
        List<String> pedidos = new ArrayList<>();

        String sql = """
            SELECT p.id_pedidoespecial, p.fecha_pedido, p.total
            FROM pedidos_productos p
            WHERE p.id_cliente = ?
            ORDER BY p.fecha_pedido DESC
        """;

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idPedido = rs.getInt("id_pedidoespecial");
                Date fecha = rs.getDate("fecha_pedido");
                double total = rs.getDouble("total");

                String linea = "Pedido N° " + idPedido + " | Fecha: " + fecha + " | Total: S/ " + total;
                pedidos.add(linea);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar pedidos: " + e.getMessage());
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
    
    public List<Cliente> buscarPorDniParcial(String texto) {
    List<Cliente> lista = new ArrayList<>();
    String sql = "SELECT * FROM cliente WHERE dni_cliente LIKE ?";
    try (Connection con = Conexion.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, texto + "%");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Cliente c = new Cliente();
            c.setNombre(rs.getString("nombre_cliente"));
            c.setTelefono(rs.getString("telefono_cliente"));
            c.setDireccion(rs.getString("direccion_cliente"));
            c.setDni(rs.getInt("dni_cliente"));
            lista.add(c);
        }
    } catch (SQLException e) {
        System.out.println("❌ Error al buscar clientes por DNI parcial: " + e.getMessage());
    }
    return lista;
}

}
