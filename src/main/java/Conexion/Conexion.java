/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.SQLException;


public class Conexion {
    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/bdSugarPasteleria";
    private static final String USER = "root"; // tu usuario de MySQL
    private static final String PASSWORD = "123456"; // tu contraseña (déjala vacía si no tienes)

    // Variable de conexión
    private static Connection conexion = null;

    // Método para obtener la conexión
    public static Connection getConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                // Registrar el driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Crear la conexión
                conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión exitosa a la base de datos.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error de conexión: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("❌ No se encontró el driver de MySQL: " + e.getMessage());
        }
        return conexion;
    }

    // Método para cerrar la conexión
    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("🔒 Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.out.println("⚠️ Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
