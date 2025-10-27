package Modelos;

public class Producto {
<<<<<<< HEAD
    private int idProducto;
=======

    private int idproducto;    
>>>>>>> d78abd3b82288bc9acd4a147a61ecddbfa0cdaca
    private String nombre;
    private double precio;
    private String categoria;

<<<<<<< HEAD
    public Producto(int idProducto, String nombre, double precio, String categoria) {
        this.idProducto = idProducto;
=======
    // Constructor con id (para listar desde BD)
    public Producto(int idproducto, String nombre, double precio, String categoria) {
        this.idproducto = idproducto;
>>>>>>> d78abd3b82288bc9acd4a147a61ecddbfa0cdaca
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

<<<<<<< HEAD
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

=======
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
>>>>>>> d78abd3b82288bc9acd4a147a61ecddbfa0cdaca

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
