package modelos;

public class Valoracion {
    private int num_valoracion;
    private String nombre_valoracion;

    public Valoracion(int num_valoracion) {
        this.num_valoracion = num_valoracion;
    }

    public Valoracion(int num_valoracion, String nombre_valoracion) {
        this.num_valoracion = num_valoracion;
        this.nombre_valoracion = nombre_valoracion;
    }
    
    // Método para asignar el nombre del contaminante basado en el id del contaminante
    private String asignarContaminante(int idContaminante) {
        String nombre_val = null;
        switch (idContaminante) {
            case 1:
                nombre_val = "Baja";
                break;
            case 2:
                nombre_val = "Media";
                break;
            case 3:
                nombre_val = "Alta";
                break;
            case 4:
                nombre_val = "Critica";
                break;
                
            }
        return nombre_val;
    }
}