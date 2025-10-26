/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Modelos.Empleado;
import Conexion.Conexion;
import Modelos.Empleado;

public class EmpleadoRepositorio {

    private Connection conexion;

    public EmpleadoRepositorio() {
        conexion = Conexion.getConexion();
    }

    // 1️⃣ Validar login por nombre y contraseña
    public boolean validarLogin(String nombre, String contraseña) {
        String sql = "SELECT * FROM Empleados WHERE nombre_empleado = ? AND contraseña = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, contraseña);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // True si existe el registro
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registrarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO Empleados (nombre_empleado, apellidos_empleado, telefono_empleado, correo_empleado, "
                + "cargo_empleado, sueldo_empleado, fecha_ingreso_empleado, contraseña) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexion.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidos());
            ps.setString(3, empleado.getTelefono());
            ps.setString(4, empleado.getCorreo());
            ps.setString(5, empleado.getCargo());
            ps.setDouble(6, empleado.getSueldo());
            ps.setDate(7, java.sql.Date.valueOf(empleado.getFechaInicio()));
            ps.setString(8, empleado.getContraseña());

            int filas = ps.executeUpdate();
            return filas > 0; // Devuelve true si se insertó correctamente

        } catch (SQLException e) {
            System.out.println("❌ Error al registrar empleado: " + e.getMessage());
            return false;
        }
    }

    // 2️⃣ Guardar nuevo empleado
    public boolean guardarEmpleado(Empleado emp) {
        String sql = "INSERT INTO Empleados (nombre_empleado, apellidos_empleado, cargo_empleado, sueldo_empleado, fecha_ingreso_empleado, contraseña) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApellidos());
            ps.setString(3, emp.getCargo());
            ps.setDouble(4, emp.getSueldo());
            ps.setDate(5, Date.valueOf(emp.getFechaInicio()));
            ps.setString(6, emp.getContraseña());

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3️⃣ Obtener todos los empleados
    public List<Empleado> obtenerTodos() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM Empleados";
        try (Statement st = conexion.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Empleado emp = new Empleado(
                        rs.getString("nombre_empleado"),
                        rs.getString("apellidos_empleado"),
                        rs.getString("cargo_empleado"),
                        rs.getString("telefono_empleado"), // 📞 teléfono
                        rs.getString("correo_empleado"), // 📧 correo
                        rs.getDouble("sueldo_empleado"), // 💰 sueldo
                        rs.getDate("fecha_ingreso_empleado").toLocalDate(),
                        rs.getString("contraseña")
                );

                lista.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // 4️⃣ Buscar empleado por ID
    public Empleado buscarPorId(int id) {
        String sql = "SELECT * FROM Empleados WHERE id_empleado = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Empleado(
                        rs.getString("nombre_empleado"),
                        rs.getString("apellidos_empleado"),
                        rs.getString("cargo_empleado"),
                        rs.getString("telefono_empleado"), // 📞 teléfono
                        rs.getString("correo_empleado"), // 📧 correo
                        rs.getDouble("sueldo_empleado"), // 💰 sueldo
                        rs.getDate("fecha_ingreso_empleado").toLocalDate(),
                        rs.getString("contraseña")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
