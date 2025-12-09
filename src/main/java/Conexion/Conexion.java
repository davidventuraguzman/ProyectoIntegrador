/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Conexion {
        private static final Logger logger = LoggerFactory.getLogger(Conexion.class);
    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/bdSugarPasteleria";
    private static final String USER = "root"; // tu usuario de MySQL
    private static final String PASSWORD = "123456"; // tu contraseña (déjala vacía si no tienes)

 
    // Método separado para permitir mockeo
    protected Connection crearConexion() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método original estático PERO delega en crearConexion()
    public static Connection getConexion() {
        try {
            // Llamamos a una instancia para permitir mockeo
            return new Conexion().crearConexion();
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("❌ Error de conexión: {}", e.getMessage(), e);
    
            return null;
        }
    }
}
