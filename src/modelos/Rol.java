/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class Rol {

    private int idRol;
    private String nombreRol;

    public Rol(int idRol) {
        this.idRol = idRol;
        this.nombreRol = asignarNombreRol(idRol);
    }

    public int getIdRol() {
        return this.idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    // Método para asignar el nombre del rol basado en el id del rol
    private String asignarNombreRol(int idRol) {
        String nombre_rol = null;
        switch (idRol) {
            case 1:
                nombre_rol = "Administrador";
            case 2:
                nombre_rol = "Entidad Ambiental";
            case 3:
                nombre_rol = "Ciudadano";
        }
        
        return nombre_rol;
    }

    @Override
    public String toString() {
        return "ID Rol: " + idRol + ", Nombre Rol: " + nombreRol;
    }
}
