package modelos;

public class Reporte {

    private static int ultimoIdReporte = 0; // Variable estática para llevar un seguimiento del último idReporte
    private int IdReporte;
    private int IdCuenta;
    //ATRIBUTOS DE CLASE UBICACION (FALTA CREAR CLASE UBICACION QUE ESTARA CONFORMADA POR: CLASE LOCALIDAD, UNIDAD COMUNERA, BARRIO)
    private Contaminante contaminante;
    private String descripcion;

    public Reporte(int IdCuenta, String direccion, String descripcion) {
        this.IdReporte = ++ultimoIdReporte; // Aumenta el idReporte en 1 con cada instancia
        this.IdCuenta = IdCuenta;
        this.descripcion = descripcion;
    }

    public int getIdReporte() {
        return this.IdReporte;
    }

    public void setIdReporte(int idReporte) {
        this.IdReporte = idReporte;
    }

    public int getIdCuenta() {
        return this.IdCuenta;
    }

    public void setIdCuenta(int IdCuenta) {
        this.IdCuenta = IdCuenta;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
