/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import Modelos.Empleado;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author LENOVO
 */
public class EmpleadoRepositorioTest {

    void testValidarLogin_UsuarioExiste() throws Exception {

        try (MockedStatic<Conexion> conexionMock = mockStatic(Conexion.class)) {

            // 🔹 Mocks
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPs = mock(PreparedStatement.class);
            ResultSet mockRs = mock(ResultSet.class);

            // Mockear conexión del constructor
            conexionMock.when(Conexion::getConexion).thenReturn(mockConnection);

            // Preparar mocks
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPs);
            when(mockPs.executeQuery()).thenReturn(mockRs);

            // Cuando se llame rs.next(), devolver true
            when(mockRs.next()).thenReturn(true);

            // Instanciar repositorio (ya usa la conexión mockeada)
            EmpleadoRepositorio repo = new EmpleadoRepositorio();

            boolean resultado = repo.validarLogin("Juan", "123");

            assertTrue(resultado);
        }
    }

    @Test
    void testValidarLogin_UsuarioNoExiste() throws Exception {

        try (MockedStatic<Conexion> conexionMock = mockStatic(Conexion.class)) {

            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockPs = mock(PreparedStatement.class);
            ResultSet mockRs = mock(ResultSet.class);

            conexionMock.when(Conexion::getConexion).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(anyString())).thenReturn(mockPs);
            when(mockPs.executeQuery()).thenReturn(mockRs);

            // rs.next() = false
            when(mockRs.next()).thenReturn(false);

            EmpleadoRepositorio repo = new EmpleadoRepositorio();

            boolean resultado = repo.validarLogin("Juan", "malo");

            assertFalse(resultado);
        }
    }
    
    @Test
void testRegistrarEmpleado() throws Exception {

    try (MockedStatic<Conexion> conexionMock = mockStatic(Conexion.class)) {

        // 🔹 Mock para registrarEmpleado() (usa una conexión diferente)
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPs = mock(PreparedStatement.class);

        // Mockear Conexion.getConexion()
        conexionMock.when(Conexion::getConexion).thenReturn(mockConnection);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPs);
        when(mockPs.executeUpdate()).thenReturn(1);

        // Crear empleado falso
        Empleado empleado = mock(Empleado.class);
        when(empleado.getNombre()).thenReturn("Juan");
        when(empleado.getApellidos()).thenReturn("Perez");
        when(empleado.getTelefono()).thenReturn("999");
        when(empleado.getCorreo()).thenReturn("juan@test.com");
        when(empleado.getCargo()).thenReturn("Gerente");
        when(empleado.getSueldo()).thenReturn(1500.0);
        when(empleado.getFechaInicio()).thenReturn(java.time.LocalDate.now());
        when(empleado.getContraseña()).thenReturn("clave");
        when(empleado.getDni()).thenReturn(12345678);

        EmpleadoRepositorio repo = new EmpleadoRepositorio();

        boolean resultado = repo.registrarEmpleado(empleado);

        assertTrue(resultado);

        verify(mockPs, times(1)).executeUpdate();
    }
}

}
