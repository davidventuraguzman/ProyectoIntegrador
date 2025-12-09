/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
/**
 *
 * @author LENOVO
 */
@ExtendWith(MockitoExtension.class)
public class ConexionTest {
    @Mock
    Connection fakeConnection;

    @Spy
    Conexion conexionSpy;
       @Test
    void testConexionMockeada() throws Exception {
        // Simula que la conexión se crea correctamente
        doReturn(fakeConnection).when(conexionSpy).crearConexion();

        Connection con = conexionSpy.crearConexion();

        assertNotNull(con);
        assertEquals(fakeConnection, con);
    }
}
