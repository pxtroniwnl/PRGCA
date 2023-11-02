/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class Contaminante {
    private int idContaminante;
    private String nombreContam;

    public Contaminante(int idContaminante, String nombreContam) {
        this.idContaminante = idContaminante;
        this.nombreContam = nombreContam;
    }

    public int getIdContaminante() {
        return idContaminante;
    }

    public void setIdContaminante(int idContaminante) {
        this.idContaminante = idContaminante;
    }

    public String getNombreContam() {
        return nombreContam;
    }

    public void setNombreContam(String nombreContam) {
        this.nombreContam = nombreContam;
    }

    @Override
    public String toString() {
        return "Contaminante{" + "idContaminante=" + idContaminante + ", nombreContam=" + nombreContam + '}';
    }
    
    
}
