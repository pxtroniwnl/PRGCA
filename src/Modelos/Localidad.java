/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class Localidad {
    private int numeroLocalidad;
    private String nombreLocalidad;

    public Localidad(int numeroLocalidad, String nombreLocalidad) {
        this.numeroLocalidad = numeroLocalidad;
        this.nombreLocalidad = nombreLocalidad;
    }

    public int getNumeroLocalidad() {
        return numeroLocalidad;
    }

    public void setNumeroLocalidad(int numeroLocalidad) {
        this.numeroLocalidad = numeroLocalidad;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }

    @Override
    public String toString() {
        return "Localidad{" + "numeroLocalidad=" + numeroLocalidad + ", nombreLocalidad=" + nombreLocalidad + '}';
    }
    
    
}
