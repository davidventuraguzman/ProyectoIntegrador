/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ProductoRepositorio {

    public List<Producto> listarProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, nombre_producto, precio_venta_producto, tipo_producto FROM Productos";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

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
                        rs.getString("nombre_producto"),
                        rs.getDouble("precio_venta_producto"),
                        rs.getString("tipo_producto")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error en buscarPorNombre: " + e.getMessage());
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
}
