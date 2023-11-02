/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class Usuario {
    private int IdCuenta;
    private String NombreCompleto;
    private String Username;
    private String Password;
    private String Direccion;
    private String Telefono;
    private String Descripcion;
    private String correo;
    private int id_rol;

    public Usuario(int IdCuenta, String NombreCompleto, String Username, String Password, String Direccion, String Telefono, String Descripcion, String correo, int id_rol) {
        this.IdCuenta = IdCuenta;
        this.NombreCompleto = NombreCompleto;
        this.Username = Username;
        this.Password = Password;
        this.Direccion = Direccion;
        this.Telefono = Telefono;
        this.Descripcion = Descripcion;
        this.correo = correo;
        this.id_rol = id_rol;
    }

    public Usuario(int IdCuenta, String NombreCompleto, String Username, String Password, String correo, int id_rol) {
        this.IdCuenta = IdCuenta;
        this.NombreCompleto = NombreCompleto;
        this.Username = Username;
        this.Password = Password;
        this.correo = correo;
        this.id_rol = id_rol;
    } //CONSTRUCTOR DEL TABLE VIEW 
    
    

    public int getIdCuenta() {
        return IdCuenta;
    }

    public void setIdCuenta(int IdCuenta) {
        this.IdCuenta = IdCuenta;
    }

    public String getNombreCompleto() {
        return NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }
    
    
}
 