/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import Modelos.DetallePedido;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.mockito.Mockito.*;
/**
 *
 * @author LENOVO
 */
public class DetallePedidoRepositorioTest {
    @Test
    void testInsertarDetalle() throws Exception {

        // Mock estático de Conexion.getConexion()
        try (MockedStatic<Conexion> conexionMock = mockStatic(Conexion.class)) {

            // Mocks normales
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPs = mock(PreparedStatement.class);

            // Configurar mocks
            conexionMock.when(Conexion::getConexion).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPs);
            when(mockPs.executeUpdate()).thenReturn(1);

            // Instancia del repositorio
            DetallePedidoRepositorio repo = new DetallePedidoRepositorio();

            // Detalle de prueba
            DetallePedido detalle = new DetallePedido(1, 10, 5, 12.5);

            // Ejecutar método
            repo.insertarDetalle(detalle);

            // Verificar interacciones
            verify(mockPs).setInt(1, 1);
            verify(mockPs).setInt(2, 10);
            verify(mockPs).setInt(3, 5);
            verify(mockPs).setDouble(4, 12.5);
            verify(mockPs).executeUpdate();
        }
    }
}
