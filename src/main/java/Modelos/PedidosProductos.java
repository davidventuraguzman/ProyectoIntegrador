package Modelos;

<<<<<<< HEAD
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
=======
public class PedidosProductos {
    private int IDproducto;
    private int cantidad; // cantidad_pedidos_productos

    public PedidosProductos() {}

    public PedidosProductos(int IDproducto, int cantidad) {
        this.IDproducto = IDproducto;
        this.cantidad = cantidad;
    }

    public int getIDproducto() { return IDproducto; }
    public void setIDproducto(int IDproducto) { this.IDproducto = IDproducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
>>>>>>> d78abd3b82288bc9acd4a147a61ecddbfa0cdaca
}
