/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;


import Modelos.Producto;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoRepositorioTest {
       private ProductoRepositorio repositorio;
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private MockedStatic<Conexion> conexionMock;

    @BeforeEach
    void setUp() throws Exception {
        repositorio = new ProductoRepositorio();

        con = mock(Connection.class);
        ps = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);

        // Mockear el método estático Conexion.getConexion()
        conexionMock = mockStatic(Conexion.class);
        conexionMock.when(Conexion::getConexion).thenReturn(con);

        when(con.prepareStatement(anyString())).thenReturn(ps);
        when(ps.executeQuery()).thenReturn(rs);
    }

    @AfterEach
    void tearDown() {
        conexionMock.close();
    }

    // ------------------------------------------------------------
    // 🔍 Test listarProductos()
    // ------------------------------------------------------------
    @Test
    void testListarProductos() throws Exception {
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getInt("id_producto")).thenReturn(1, 2);
        when(rs.getString("nombre_producto")).thenReturn("Pan", "Leche");
        when(rs.getDouble("precio_venta_producto")).thenReturn(1.50, 3.20);
        when(rs.getString("tipo_producto")).thenReturn("Abarrotes", "Lácteos");

        List<Producto> lista = repositorio.listarProductos();

        assertEquals(2, lista.size());
        assertEquals("Pan", lista.get(0).getNombre());
        assertEquals("Leche", lista.get(1).getNombre());
    }

    // ------------------------------------------------------------
    // 🔍 Test buscarPorNombre()
    // ------------------------------------------------------------
    @Test
    void testBuscarPorNombre() throws Exception {
        when(rs.next()).thenReturn(true, false);
        when(rs.getInt("id_producto")).thenReturn(3);
        when(rs.getString("nombre_producto")).thenReturn("Azúcar");
        when(rs.getDouble("precio_venta_producto")).thenReturn(2.50);
        when(rs.getString("tipo_producto")).thenReturn("Abarrotes");

        List<Producto> lista = repositorio.buscarPorNombre("azu");

        assertEquals(1, lista.size());
        assertEquals("Azúcar", lista.get(0).getNombre());
    }

    // ------------------------------------------------------------
    // 🔎 Test buscarPorId()
    // ------------------------------------------------------------
    @Test
    void testBuscarPorId() throws Exception {
        when(rs.next()).thenReturn(true);
        when(rs.getInt("id_producto")).thenReturn(10);
        when(rs.getString("nombre_producto")).thenReturn("Café");
        when(rs.getDouble("precio_venta_producto")).thenReturn(8.90);
        when(rs.getString("tipo_producto")).thenReturn("Bebidas");

        Producto p = repositorio.buscarPorId(10);

        assertNotNull(p);
        assertEquals(10, p.getIdProducto());
        assertEquals("Café", p.getNombre());
    }

    // ------------------------------------------------------------
    // ➕ Test insertar()
    // ------------------------------------------------------------
    @Test
    void testInsertar() throws Exception {
        when(ps.executeUpdate()).thenReturn(1);

        Producto nuevo = new Producto(0, "Té", 4.5, "Bebidas");

        boolean ok = repositorio.insertar(nuevo);

        assertTrue(ok);
        verify(ps).setString(1, "Té");
        verify(ps).setDouble(2, 4.5);
        verify(ps).setString(3, "Bebidas");
    }

    // ------------------------------------------------------------
    // ❌ Test eliminar()
    // ------------------------------------------------------------
    @Test
    void testEliminar() throws Exception {
        when(ps.executeUpdate()).thenReturn(1);

        boolean ok = repositorio.eliminar(5);

        assertTrue(ok);
        verify(ps).setInt(1, 5);
    }

    // ------------------------------------------------------------
    // 📊 Test contarReferencias()
    // ------------------------------------------------------------
    @Test
    void testContarReferencias() throws Exception {
        when(rs.next()).thenReturn(true);
        when(rs.getInt("cnt")).thenReturn(2);

        int total = repositorio.contarReferencias(7);

        // 4 tablas → cada una devuelve 2 → total 8
        assertEquals(8, total);
        verify(ps, times(4)).setInt(1, 7);
    }
}
