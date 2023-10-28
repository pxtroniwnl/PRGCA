package modelos;

public class EstadoRevision {

    private int idEstado;
    private String estado;

    public EstadoRevision(int idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoRevision(int idEstado, String estado) {
        this.idEstado = idEstado;
        this.estado = estado;
    }

    // Método para asignar el nombre del estado basado en el id del estado
    private String asignarEstado(int idEstado) {
        private String estadoRevision = null;
        switch (idEstado) {
            case 1:
                estadoRevision = "Sin atender";
            case 2:
                estadoRevision = "Atendido";
        }
        return estadoRevision; 
    }

    public int getIdEstado() {
        return this.idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
