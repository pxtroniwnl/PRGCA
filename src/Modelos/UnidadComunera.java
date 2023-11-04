/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class UnidadComunera {
    private int idUnidad;
    private String nombreUnidad;
    private int localidad_id;

    public UnidadComunera(int idUnidad, String nombreUnidad, int localidad_id) {
        this.idUnidad = idUnidad;
        this.nombreUnidad = nombreUnidad;
        this.localidad_id = localidad_id;
    }

    public UnidadComunera(int idUnidad, String nombreUnidad) {
        this.idUnidad = idUnidad;
        this.nombreUnidad = nombreUnidad;
    }
    
    

    public int getIdUnidad() {
        return idUnidad;
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad = idUnidad;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public int getLocalidad_id() {
        return localidad_id;
    }

    public void setLocalidad_id(int localidad_id) {
        this.localidad_id = localidad_id;
    }

    @Override
    public String toString() {
        return "UnidadComunera{" + "idUnidad=" + idUnidad + ", nombreUnidad=" + nombreUnidad + ", localidad_id=" + localidad_id + '}';
    }
    
    
}
