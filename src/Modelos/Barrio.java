/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class Barrio {
    private int idBarrio;
    private String nombreBarrio;
    private int unidad_id;

    public Barrio(int idBarrio, String nombreBarrio, int unidad_id) {
        this.idBarrio = idBarrio;
        this.nombreBarrio = nombreBarrio;
        this.unidad_id = unidad_id;
    }

    public Barrio(int idBarrio, String nombreBarrio) {
        this.idBarrio = idBarrio;
        this.nombreBarrio = nombreBarrio;
    }
    
    

    public int getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(int idBarrio) {
        this.idBarrio = idBarrio;
    }

    public String getNombreBarrio() {
        return nombreBarrio;
    }

    public void setNombreBarrio(String nombreBarrio) {
        this.nombreBarrio = nombreBarrio;
    }

    public int getUnidad_id() {
        return unidad_id;
    }

    public void setUnidad_id(int unidad_id) {
        this.unidad_id = unidad_id;
    }

    @Override
    public String toString() {
        return nombreBarrio;
    }
    
    
}
