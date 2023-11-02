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
    private int contaminanteID;
    private int localidadID;
    private int unidadID;
    private int barrioID;
    private String Descripcion;

    public Reporte(int idReporte, int idUsuario, int contaminanteID, int localidadID, int unidadID, int barrioID, String Descripcion) {
        this.idReporte = idReporte;
        this.idUsuario = idUsuario;
        this.contaminanteID = contaminanteID;
        this.localidadID = localidadID;
        this.unidadID = unidadID;
        this.barrioID = barrioID;
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

    public int getContaminanteID() {
        return contaminanteID;
    }

    public void setContaminanteID(int contaminanteID) {
        this.contaminanteID = contaminanteID;
    }

    public int getLocalidadID() {
        return localidadID;
    }

    public void setLocalidadID(int localidadID) {
        this.localidadID = localidadID;
    }

    public int getUnidadID() {
        return unidadID;
    }

    public void setUnidadID(int unidadID) {
        this.unidadID = unidadID;
    }

    public int getBarrioID() {
        return barrioID;
    }

    public void setBarrioID(int barrioID) {
        this.barrioID = barrioID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    @Override
    public String toString() {
        return "Reporte{" + "idReporte=" + idReporte + ", idUsuario=" + idUsuario + ", contaminanteID=" + 
                contaminanteID + ", localidadID=" + localidadID + ", unidadID=" + unidadID + ", barrioID=" + barrioID + ", Descripcion=" + Descripcion + '}';
    }
    
    
}
