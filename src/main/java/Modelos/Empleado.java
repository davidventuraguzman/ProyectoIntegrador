/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.time.LocalDate;

/**
 *
 * @author LENOVO
 */
public class Empleado {

    private String nombre;
    private String apellidos;
    private String cargo;
    private double sueldo;
    private LocalDate fechaInicio;
    private String contraseña;
    public Empleado() {
    }

    public Empleado(String nombre, String apellidos, String cargo, double sueldo, LocalDate fechaInicio, String contraseña) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.fechaInicio = fechaInicio;
        this.contraseña = contraseña;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    

  
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " - " + cargo + " (desde " + fechaInicio + ")";
    }
}
