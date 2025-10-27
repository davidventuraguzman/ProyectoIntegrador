package Modelos;

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
}
