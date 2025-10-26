/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author LENOVO
 */
import java.time.LocalDate;

public class Venta {
    private int idEmpleado;
    private int idProducto;
    private int cantidad;
    private LocalDate fechaVenta;

    public Venta(int idEmpleado, int idProducto, int cantidad, LocalDate fechaVenta) {
        this.idEmpleado = idEmpleado;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.fechaVenta = fechaVenta;
    }

    // Getters y Setters
    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDate getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(LocalDate fechaVenta) { this.fechaVenta = fechaVenta; }

    @Override
    public String toString() {
        return "Venta [Empleado=" + idEmpleado + ", Producto=" + idProducto +
               ", Cantidad=" + cantidad + ", Fecha=" + fechaVenta + "]";
    }
}