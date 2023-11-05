/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class Valoracion {
    private int idValoracion;
    private String nombreValoracion;

    public Valoracion(int idValoracion, String nombreValoracion) {
        this.idValoracion = idValoracion;
        this.nombreValoracion = nombreValoracion;
    }

    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    public String getNombreValoracion() {
        return nombreValoracion;
    }

    public void setNombreValoracion(String nombreValoracion) {
        this.nombreValoracion = nombreValoracion;
    }

    @Override
    public String toString() {
        return nombreValoracion;
    }
    
    
}
