package controlador;

import data.Sistema;
import modelo.Concierto;
import modelo.Zona;
import vista.VistaDetalleConcierto;
import vista.VistaCatalogoConciertos;
import vista.VistaMisTarjetas;
import javax.swing.JOptionPane;

public class ControladorDetalle {
    private VistaDetalleConcierto vista;

    public ControladorDetalle(VistaDetalleConcierto vista) {
        this.vista = vista;

        this.vista.btnContinuar.addActionListener(e -> {
            // Continuar con el pago
            int fila = vista.tablaZonas.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(vista, "Selecciona una zona primero.");
                return;
            }
            Zona zona     = Sistema.conciertoSeleccionado.getZonas().get(fila);
            int cantidad  = (Integer) vista.comboCantidad.getSelectedItem();

            if (zona.getDisponibles() < cantidad) {
                JOptionPane.showMessageDialog(vista,
                    "Solo hay " + zona.getDisponibles() + " entradas disponibles en esa zona.",
                    "Sin disponibilidad", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Sistema.zonaSeleccionada   = zona;
            Sistema.cantidadEntradas   = cantidad;
            vista.dispose();
            VistaMisTarjetas vistaTarjetas = new VistaMisTarjetas();
            ControladorTarjetas controlador = new ControladorTarjetas(vistaTarjetas);
            controlador.iniciar();
        });

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            VistaCatalogoConciertos vistaCatalogo = new VistaCatalogoConciertos();
            ControladorCatalogo controlador = new ControladorCatalogo(vistaCatalogo);
            controlador.iniciar();
        });
    }

    private void cargarDatos() {
        Concierto c = Sistema.conciertoSeleccionado;
        if (c == null) return;

        vista.lblNombre.setText(c.getNombre());
        vista.lblFecha.setText("Fecha: " + c.getFechaFormateada());
        vista.modeloTabla.setRowCount(0);
        
        for (Zona z : c.getZonas()) {
            vista.modeloTabla.addRow(new Object[]{
                z.getNombre(),
                z.getCapacidad(),
                "S/ " + z.getPrecio(),
                z.getDisponibles()
            });
        }
        vista.comboCantidad.setSelectedIndex(0);
    }

    public void iniciar() {
        cargarDatos();
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}
