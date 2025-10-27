package Modelos;

public class Producto {

    private int idproducto;    
    private String nombre;
    private double precio;
    private String categoria;

    // Constructor con id (para listar desde BD)
    public Producto(int idproducto, String nombre, double precio, String categoria) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    // Constructor sin id (para insertar nuevos productos)
    public Producto(String nombre, double precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return nombre + " - S/" + precio;
    }
}
