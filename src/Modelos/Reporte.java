/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class Reporte {
    private int idReporte;
    private int idUsuario;
    private String contaminante;
    private String localidad;
    private String unidad;
    private String barrio;
    private String Descripcion;

    public Reporte(int idReporte, int idUsuario, String contaminante, String localidad, String unidad, String barrio, String Descripcion) {
        this.idReporte = idReporte;
        this.idUsuario = idUsuario;
        this.contaminante = contaminante;
        this.localidad = localidad;
        this.unidad = unidad;
        this.barrio = barrio;
        this.Descripcion = Descripcion;
    }

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    @Override
    public String toString() {
        return "Reporte{" + "idReporte=" + idReporte + ", idUsuario=" + idUsuario + ", contaminante=" + contaminante + ", localidad=" + localidad + ", unidad=" + unidad + ", barrio=" + barrio + ", Descripcion=" + Descripcion + '}';
    }

    
    
}
