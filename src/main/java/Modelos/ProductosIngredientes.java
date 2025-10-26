/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author LENOVO
 */
public class ProductosIngredientes {
    private int idProducto;
    private int idIngrediente;
    private double cantidadNecesaria;

    public ProductosIngredientes(int idProducto, int idIngrediente, double cantidadNecesaria) {
        this.idProducto = idProducto;
        this.idIngrediente = idIngrediente;
        this.cantidadNecesaria = cantidadNecesaria;
    }

    // Getters y Setters
    public int getIdProducto() { return idProducto; }
    public int getIdIngrediente() { return idIngrediente; }
    public double getCantidadNecesaria() { return cantidadNecesaria; }
}
