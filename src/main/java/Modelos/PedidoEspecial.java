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

public class PedidoEspecial {
    private int idPedido;
    private int idCliente;
    private String descripcion;
    private double precioTotal;
    private LocalDate fechaEntrega;

    public PedidoEspecial(int idPedido, int idCliente, String descripcion, double precioTotal, LocalDate fechaEntrega) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.descripcion = descripcion;
        this.precioTotal = precioTotal;
        this.fechaEntrega = fechaEntrega;
    }

    // Getters y Setters
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioTotal() { return precioTotal; }
    public void setPrecioTotal(double precioTotal) { this.precioTotal = precioTotal; }

    public LocalDate getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDate fechaEntrega) { this.fechaEntrega = fechaEntrega; }
}