package controlador;

import data.Sistema;
import modelo.Concierto;
import vista.VistaCatalogoConciertos;
import vista.dlgDetalleConciertoUsuario;
import vista.VistaHistorialCompras;
import vista.VistaLogin;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class ControladorCatalogo {
    private VistaCatalogoConciertos vista;

    public ControladorCatalogo(VistaCatalogoConciertos vista) {
        this.vista = vista;

        this.vista.btnVerDetalle.addActionListener(e -> {
            int fila = vista.tablaCatalogo.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(vista, "Selecciona un concierto de la lista.");
                return;
            }
            Sistema.conciertoSeleccionado = Sistema.conciertos.obtener(fila);
            vista.dispose();

            dlgDetalleConciertoUsuario vistaDetalle = new dlgDetalleConciertoUsuario(null, true);
            ControladorDetalle controlador = new ControladorDetalle(vistaDetalle);
            controlador.iniciar();
        });

        this.vista.btnMisCompras.addActionListener(e -> {
            VistaHistorialCompras vistaHistorial = new VistaHistorialCompras();
            vistaHistorial.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // Evitar que se cierre toda la app al cerrar esta ventana secundaria.
            
            vistaHistorial.setTitle("Mis Compras — " + Sistema.clienteActual.getNombres());
            vistaHistorial.setLocationRelativeTo(null);
            vistaHistorial.setVisible(true);
        });

        this.vista.btnCerrarSesion.addActionListener(e -> {
            Sistema.clienteActual = null;
            vista.dispose();
            VistaLogin vistaLogin = new VistaLogin();
            ControladorLogin controlador = new ControladorLogin(vistaLogin);
            controlador.iniciar();
        });
    }
    
    // Rellena la tabla de la vista con todos los conciertos disponibles en el sistema.
    private void cargarTabla() {
        vista.modeloTabla.setRowCount(0);
        for (Concierto c : Sistema.conciertos) {
            vista.modeloTabla.addRow(new Object[]{
                c.getNombre(),
                c.getFechaFormateada(),
                c.getZonas().size() + " zona(s)"
            });
        }
    }

    public void iniciar() {
        cargarTabla(); // llenar la tabla con los conciertos antes de mostrar la vista
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}
