package modelos;

public class Usuario {

    private String Username;
    private String Password;
    private String NombreCompleto;
    private int IdCuenta;
    private Rol rol;
    private String Direccion;
    private String Descripcion;
    private int Telefono;
    private String correo;

    public Usuario(String Username, String Password, String NombreCompleto, int IdCuenta, Rol rol, String Direccion, int Telefono, String correo, String Descripcion) {
        this.Username = Username;
        this.Password = Password;
        this.NombreCompleto = NombreCompleto;
        this.IdCuenta = IdCuenta;
        this.rol = rol;
        this.Direccion = Direccion;
        this.Descripcion = Descripcion;
        this.Telefono = Telefono;
        this.correo = correo;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getNombreCompleto() {
        return this.NombreCompleto;
    }

    public void setNombreCompleto(String NombreCompleto) {
        this.NombreCompleto = NombreCompleto;
    }

    public int getIdCuenta() {
        return this.IdCuenta;
    }

    public void setIdCuenta(int IdCuenta) {
        this.IdCuenta = IdCuenta;
    }

    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getDireccion() {
        return this.Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getDescripcion() {
        return this.Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getTelefono() {
        return this.Telefono;
    }

    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "Username=" + Username + ", Password=" + Password + ", NombreCompleto=" + NombreCompleto + ", IdCuenta="
                + IdCuenta + ", rol=" + rol + ", Direccion=" + Direccion + ", Descripcion=" + Descripcion + ", Telefono=" + Telefono + ", correo=" + correo + '}';
    }

}
