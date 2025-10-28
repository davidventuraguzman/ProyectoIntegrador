package Modelos;

import java.time.LocalDate;

public class PedidosProductos {

    private int idPedidoEspecial;       // id_pedidoespecial (PK)
    private int idCliente;              // id_cliente
    private int idProducto;             // id_producto (FK)
    private double total;               // total
    private LocalDate fechaPedido;      // fecha_pedido
    private int cantidad;               // cantidad_pedidos_productos

    // Constructor completo
    public PedidosProductos(int idPedidoEspecial, int idCliente, int idProducto,
            double total, LocalDate fechaPedido, int cantidad) {
        this.idPedidoEspecial = idPedidoEspecial;
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.total = total;
        this.fechaPedido = fechaPedido;
        this.cantidad = cantidad;
    }

    public PedidosProductos(int idCliente, double total, LocalDate fechaPedido, int cantidad) {
        this.idCliente = idCliente;
        this.total = total;
        this.fechaPedido = fechaPedido;
        this.cantidad=cantidad;
    }

    public PedidosProductos() {
    }

    // Constructor sin ID (para insertar nuevos)
    public PedidosProductos(int idCliente, int idProducto,
            double total, LocalDate fechaPedido, int cantidad) {
        this.idCliente = idCliente;
        this.idProducto = idProducto;
        this.total = total;
        this.fechaPedido = fechaPedido;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdPedidoEspecial() {
        return idPedidoEspecial;
    }

    public void setIdPedidoEspecial(int idPedidoEspecial) {
        this.idPedidoEspecial = idPedidoEspecial;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "PedidosProductos [idPedidoEspecial=" + idPedidoEspecial
                + ", idCliente=" + idCliente
                + ", idProducto=" + idProducto
                + ", total=" + total
                + ", fechaPedido=" + fechaPedido
                + ", cantidad=" + cantidad + "]";
    }
}
