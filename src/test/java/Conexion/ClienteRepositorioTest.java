/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelos.Cliente;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author LENOVO
 */
public class ClienteRepositorioTest {
    
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private ClienteRepositorio repo;

    @BeforeEach
    void setUp() throws Exception {
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        when(mockConnection.prepareStatement(anyString()))
                .thenReturn(mockPreparedStatement);

        when(mockPreparedStatement.executeQuery())
                .thenReturn(mockResultSet);

        // Usamos el constructor con dependencia inyectada
        repo = new ClienteRepositorio(mockConnection);
    }

    // ============================================================
    // 1. CLIENTE ENCONTRADO
    // ============================================================
    @Test
    void testBuscarPorDniEncontrado() throws Exception {

        when(mockResultSet.next()).thenReturn(true);

        when(mockResultSet.getString("nombre_cliente")).thenReturn("Juan Perez");
        when(mockResultSet.getString("telefono_cliente")).thenReturn("999999999");
        when(mockResultSet.getString("direccion_cliente")).thenReturn("Calle Falsa 123");
        when(mockResultSet.getString("correo_cliente")).thenReturn("juan@gmail.com");
        when(mockResultSet.getString("dni_cliente")).thenReturn("12345678");

        Cliente c = repo.buscarPorDni("12345678");

        assertNotNull(c);
        assertEquals("Juan Perez", c.getNombre());
        assertEquals(12345678, c.getDni());

        // Seguridad SQL
        verify(mockPreparedStatement).setString(1, "12345678");
        verify(mockPreparedStatement).executeQuery();
    }

    // ============================================================
    // 2. CLIENTE NO ENCONTRADO
    // ============================================================
    @Test
    void testBuscarPorDniNoEncontrado() throws Exception {

        when(mockResultSet.next()).thenReturn(false);

        Cliente c = repo.buscarPorDni("00000000");

        assertNull(c);

        verify(mockPreparedStatement).setString(1, "00000000");
    }

    // ============================================================
    // 3. ATAQUE DE INYECCIÓN SQL DETECTADO Y MANEJADO
    // ============================================================
    @Test
    void testBuscarPorDniConInyeccionSql() throws Exception {

        when(mockResultSet.next()).thenReturn(false);

        String dniMalicioso = "12345678 OR 1=1";

        Cliente c = repo.buscarPorDni(dniMalicioso);

        assertNull(c);

        // Verificamos que NO se concatenó texto, sino que se usa PreparedStatement
        verify(mockPreparedStatement).setString(1, dniMalicioso);
    }

    // ============================================================
    // 4. MANEJO DE SQLException
    // ============================================================
    @Test
    void testBuscarPorDniSQLException() throws Exception {

        when(mockPreparedStatement.executeQuery())
                .thenThrow(new SQLException("Error simulado"));

        Cliente c = repo.buscarPorDni("12345678");

        assertNull(c);  // tu método retorna null ante error
    }
}
