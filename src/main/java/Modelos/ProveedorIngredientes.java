/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author LENOVO
 */

public class ProveedorIngredientes {
    private int idProveedor;
    private int idIngrediente;
    private double cantidad;

    public ProveedorIngredientes(int idProveedor, int idIngrediente, double cantidad) {
        this.idProveedor = idProveedor;
        this.idIngrediente = idIngrediente;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdProveedor() { return idProveedor; }
    public int getIdIngrediente() { return idIngrediente; }
    public double getCantidad() { return cantidad; }
}