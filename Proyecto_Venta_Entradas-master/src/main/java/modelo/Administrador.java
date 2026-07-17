package modelo;

public class Administrador extends Persona {

    public Administrador(String nombres, String apellidos, String dni, String contrasena) {
        super(nombres, apellidos, dni, contrasena);
    }

    // Los métodos de este contrato quedan como stub (igual que los de Persona): el registro real
    // de conciertos/zonas/ventas se hace hoy contra los arreglos globales en Sistema, desde el
    // controlador correspondiente (ControladorGestionConcierto, ControladorPanelAnulaciones), para
    // no acoplar el modelo a la capa de datos.
    public boolean registrarConcierto(Concierto c) {
        return false;
    }

    public boolean eliminarConcierto(Concierto c) {
        return false;
    }

    public boolean registrarZona(Zona z) {
        return false;
    }

    public boolean modificarZona(Zona z) {
        return false;
    }

    public void consultarVentas() {
    }
}
