/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class Informes {
    private int idInforme;
    private String contaminante;
    private String localidad;
    private String unidad;
    private String barrio;
    private String valoracion;
    private String estado;

    public Informes(int idInforme, String contaminante, String localidad, String unidad, String barrio, String valoracion, String estado) {
        this.idInforme = idInforme;
        this.contaminante = contaminante;
        this.localidad = localidad;
        this.unidad = unidad;
        this.barrio = barrio;
        this.valoracion = valoracion;
        this.estado = estado;
    }

    public int getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(int idInforme) {
        this.idInforme = idInforme;
    }

    public String getContaminante() {
        return contaminante;
    }

    public void setContaminante(String contaminante) {
        this.contaminante = contaminante;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Informes{" + "idInforme=" + idInforme + ", contaminante=" + contaminante + ", localidad=" + localidad 
                + ", unidad=" + unidad + ", barrio=" + barrio + ", valoracion=" + valoracion + ", estado=" + estado + '}';
    }
    
    
}
