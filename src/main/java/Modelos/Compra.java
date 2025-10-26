/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;
import java.time.LocalDate;

public class Compra {
    private int idCliente;
    private int idProducto;
    private int cantidad;
    private LocalDate fechaCompra;

    public Compra(int idCliente, int idProducto, int cantidad, LocalDate fechaCompra) {
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.fechaCompra = fechaCompra;
    }

    // Getters y Setters
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public LocalDate getFechaCompra() { return fechaCompra; }
    public void setFechaCompra(LocalDate fechaCompra) { this.fechaCompra = fechaCompra; }

    @Override
    public String toString() {
        return "Compra [Cliente=" + idCliente + ", Producto=" + idProducto +
               ", Cantidad=" + cantidad + ", Fecha=" + fechaCompra + "]";
    }
}