/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import Modelos.PedidosProductos;
import Modelos.Reportes;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author LENOVO
 */
public class PedidoRepositorioTest {
     @Test
    void testInsertarPedido() throws Exception {

        try (MockedStatic<Conexion> staticMock = mockStatic(Conexion.class)) {

            Connection con = mock(Connection.class);
            PreparedStatement ps = mock(PreparedStatement.class);
            ResultSet rs = mock(ResultSet.class);

            staticMock.when(Conexion::getConexion).thenReturn(con);

            when(con.prepareStatement(anyString(), anyInt())).thenReturn(ps);
            when(ps.getGeneratedKeys()).thenReturn(rs);

            when(rs.next()).thenReturn(true);
            when(rs.getInt(1)).thenReturn(99);

            PedidosProductos pedido = new PedidosProductos(1, 2, 10.5, LocalDate.now(), 3);

            PedidoRepositorio repo = new PedidoRepositorio();
            int id = repo.insertarPedido(pedido);

            assertEquals(99, id);
        }
    }

    // ---------------------------------------------------------
    // 2️⃣ TEST listar()
    // ---------------------------------------------------------
    @Test
    void testListar() throws Exception {

        try (MockedStatic<Conexion> staticMock = mockStatic(Conexion.class)) {

            Connection con = mock(Connection.class);
            PreparedStatement ps = mock(PreparedStatement.class);
            ResultSet rs = mock(ResultSet.class);

            staticMock.when(Conexion::getConexion).thenReturn(con);

            when(con.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);

            // Simular dos filas
            when(rs.next()).thenReturn(true, true, false);
            when(rs.getInt("id_pedidoespecial")).thenReturn(1, 2);
            when(rs.getInt("id_cliente")).thenReturn(10, 20);
            when(rs.getInt("id_producto")).thenReturn(100, 200);
            when(rs.getDouble("total")).thenReturn(50.0, 60.0);
            when(rs.getDate("fecha_pedido")).thenReturn(Date.valueOf(LocalDate.now()));
            when(rs.getInt("cantidad_pedidos_productos")).thenReturn(5, 10);

            PedidoRepositorio repo = new PedidoRepositorio();
            List<PedidosProductos> lista = repo.listar();

            assertEquals(2, lista.size());
        }
    }

    // ---------------------------------------------------------
    // 3️⃣ TEST listarPorCliente()
    // ---------------------------------------------------------
    @Test
    void testListarPorCliente() throws Exception {

        try (MockedStatic<Conexion> staticMock = mockStatic(Conexion.class)) {

            Connection con = mock(Connection.class);
            PreparedStatement ps = mock(PreparedStatement.class);
            ResultSet rs = mock(ResultSet.class);

            staticMock.when(Conexion::getConexion).thenReturn(con);

            when(con.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);

            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("id_pedidoespecial")).thenReturn(5);
            when(rs.getInt("id_cliente")).thenReturn(99);
            when(rs.getInt("id_producto")).thenReturn(20);
            when(rs.getDouble("total")).thenReturn(100.0);
            when(rs.getDate("fecha_pedido")).thenReturn(Date.valueOf(LocalDate.now()));
            when(rs.getInt("cantidad_pedidos_productos")).thenReturn(3);

            PedidoRepositorio repo = new PedidoRepositorio();
            List<PedidosProductos> lista = repo.listarPorCliente(99);

            assertEquals(1, lista.size());
            assertEquals(99, lista.get(0).getIdCliente());
        }
    }

    // ---------------------------------------------------------
    // 4️⃣ TEST listarPedidos()
    // ---------------------------------------------------------
    @Test
    void testListarPedidos() throws Exception {

        try (MockedStatic<Conexion> staticMock = mockStatic(Conexion.class)) {

            Connection con = mock(Connection.class);
            PreparedStatement ps = mock(PreparedStatement.class);
            ResultSet rs = mock(ResultSet.class);

            staticMock.when(Conexion::getConexion).thenReturn(con);

            when(con.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);

            when(rs.next()).thenReturn(true, false);
            when(rs.getInt("id_pedidoespecial")).thenReturn(1);
            when(rs.getInt("id_cliente")).thenReturn(10);
            when(rs.getInt("id_producto")).thenReturn(100);
            when(rs.getDouble("total")).thenReturn(20.0);
            when(rs.getDate("fecha_pedido")).thenReturn(Date.valueOf(LocalDate.now()));
            when(rs.getInt("cantidad_pedidos_productos")).thenReturn(2);

            PedidoRepositorio repo = new PedidoRepositorio();
            List<PedidosProductos> lista = repo.listarPedidos();

            assertEquals(1, lista.size());
        }
    }

    // ---------------------------------------------------------
    // 5️⃣ TEST eliminarPedido()
    // ---------------------------------------------------------
    @Test
    void testEliminarPedido() throws Exception {

        try (MockedStatic<Conexion> staticMock = mockStatic(Conexion.class)) {

            Connection con = mock(Connection.class);
            PreparedStatement psDetalle = mock(PreparedStatement.class);
            PreparedStatement psPedido = mock(PreparedStatement.class);

            staticMock.when(Conexion::getConexion).thenReturn(con);

            when(con.prepareStatement(contains("detalle_pedido"))).thenReturn(psDetalle);
            when(con.prepareStatement(contains("pedidos_productos"))).thenReturn(psPedido);

            when(psPedido.executeUpdate()).thenReturn(1);

            PedidoRepositorio repo = new PedidoRepositorio();
            boolean eliminado = repo.eliminarPedido(10);

            assertTrue(eliminado);
            verify(con).commit();
        }
    }

    // ---------------------------------------------------------
    // 6️⃣ TEST obtenerReportes()
    // ---------------------------------------------------------
    @Test
    void testObtenerReportes() throws Exception {

        try (MockedStatic<Conexion> staticMock = mockStatic(Conexion.class)) {

            Connection con = mock(Connection.class);
            PreparedStatement ps = mock(PreparedStatement.class);
            ResultSet rs = mock(ResultSet.class);

            staticMock.when(Conexion::getConexion).thenReturn(con);

            when(con.prepareStatement(anyString())).thenReturn(ps);
            when(ps.executeQuery()).thenReturn(rs);

            when(rs.next()).thenReturn(true, false);
            when(rs.getString("cliente")).thenReturn("Carlos");
            when(rs.getString("producto")).thenReturn("Laptop");
            when(rs.getInt("cantidad")).thenReturn(2);
            when(rs.getDouble("precio")).thenReturn(1500.0);
            when(rs.getDouble("total")).thenReturn(3000.0);

            PedidoRepositorio repo = new PedidoRepositorio();
            List<Reportes> lista = repo.obtenerReportes();

            assertEquals(1, lista.size());
            assertEquals("Carlos", lista.get(0).getCliente());
        }
    }
}
