package modelos;

public class Contaminante {

    private int idContaminante;
    private String nombreContaminante;

    public Contaminante(int idContaminante) {
        this.idContaminante = idContaminante;
    }

    // Método para asignar el nombre del contaminante basado en el id del contaminante
    private String asignarContaminante(int idContaminante) {
        private String nombre_contaminante = null;
        switch (idContaminante) {
            case 1:
                nombre_contaminante = "Contaminación del aire";
            case 2:
                nombre_contaminante = "Contaminación del agua";
            case 3:
                nombre_contaminante = "Contaminación del suelo";
            case 4:
                nombre_contaminante = "Contaminación acústica";
            case 5:
                nombre_contaminante = "Contaminación lumínica";
            case 6:
                nombre_contaminante = "Contaminación visual";
            case 7:
                nombre_contaminante = "Contaminación de playas y costas";
            case 8:
                nombre_contaminante = "Contaminación por desechos sólidos";
            case 9:
                nombre_contaminante = "Contaminación biológica";
        }

        return nombre_contaminante;

    }

    public int getIdContaminante() {
        return this.idContaminante;
    }

    public void setIdContaminante(int idContaminante) {
        this.idContaminante = idContaminante;
    }

    public String getNombreContaminante() {
        return this.nombreContaminante;
    }

    public void setNombreContaminante(String nombreContaminante) {
        this.nombreContaminante = nombreContaminante;
    }
    
}
