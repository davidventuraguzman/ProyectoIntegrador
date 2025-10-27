/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.time.LocalDate;

public class PedidosProductos {
    private int idPedido;
    private int idCliente;
    private double total;
    private LocalDate fechaPedido;

    public PedidosProductos(int idCliente, double total, LocalDate fechaPedido) {
        this.idCliente = idCliente;
        this.total = total;
        this.fechaPedido = fechaPedido;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

  
    public String toString() {
        return "Pedido [idPedido=" + idPedido + ", idCliente=" + idCliente +
               ", total=" + total + ", fechaPedido=" + fechaPedido + "]";
    }
}
