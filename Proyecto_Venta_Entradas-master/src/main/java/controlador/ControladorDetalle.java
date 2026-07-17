package controlador;

import data.Sistema;
import modelo.Concierto;
import modelo.Entrada;
import modelo.Zona;
import vista.dlgDetalleConciertoUsuario;
import vista.dlgAlertaConfirmacionUsuario;
import vista.VistaCatalogoConciertos;
import vista.VistaMisTarjetas;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorDetalle {
    private static final int MAX_ENTRADAS = 4;

    private dlgDetalleConciertoUsuario vista;
    private DefaultTableModel modeloAsientos;

    public ControladorDetalle(dlgDetalleConciertoUsuario vista) {
        this.vista = vista;

        this.modeloAsientos = new DefaultTableModel(new Object[]{"Nro Asiento", "Estado", "Agregar"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 2 ? Boolean.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                if (column != 2 || !"disponible".equals(getValueAt(row, 1))) {
                    return false;
                }
                // Ya se llegó al máximo: solo se puede desmarcar, no agregar un asiento más.
                return Boolean.TRUE.equals(getValueAt(row, 2)) || contarMarcados() < MAX_ENTRADAS;
            }
        };
        this.vista.tblAsientos.setModel(modeloAsientos);

        this.vista.cboZona.addActionListener(e -> cargarAsientosZonaSeleccionada());

        this.vista.btnContinuar.addActionListener(e -> confirmarSeleccion());

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            VistaCatalogoConciertos vistaCatalogo = new VistaCatalogoConciertos();
            ControladorCatalogo controlador = new ControladorCatalogo(vistaCatalogo);
            controlador.iniciar();
        });
    }

    private void cargarDatos() {
        Concierto c = Sistema.conciertoSeleccionado;
        if (c == null)
            return;

        vista.nombreconcierto.setText(c.getNombre());
        vista.fechaConcierto.setText("Fecha: " + c.getFechaFormateada());

        String[] nombresZonas = new String[c.getZonas().size()];
        for (int i = 0; i < nombresZonas.length; i++) {
            nombresZonas[i] = c.getZonas().get(i).getNombre();
        }
        vista.cboZona.setModel(new DefaultComboBoxModel<>(nombresZonas));
        cargarAsientosZonaSeleccionada();
    }

    private void cargarAsientosZonaSeleccionada() {
        Zona zona = obtenerZonaSeleccionada();
        modeloAsientos.setRowCount(0);
        if (zona == null)
            return;

        vista.lblPrecio.setText("S/ " + zona.getPrecio());
        for (Entrada ent : zona.mostrarEntrada()) {
            if (ent == null)
                continue;
            modeloAsientos.addRow(new Object[]{ ent.getNumero(), ent.getEstado(), Boolean.FALSE });
        }
    }

    private Zona obtenerZonaSeleccionada() {
        int indice = vista.cboZona.getSelectedIndex();
        if (indice < 0 || Sistema.conciertoSeleccionado == null)
            return null;
        return Sistema.conciertoSeleccionado.getZonas().get(indice);
    }

    private int contarMarcados() {
        int total = 0;
        for (int fila = 0; fila < modeloAsientos.getRowCount(); fila++) {
            if (Boolean.TRUE.equals(modeloAsientos.getValueAt(fila, 2))) {
                total++;
            }
        }
        return total;
    }

    private void confirmarSeleccion() {
        Zona zona = obtenerZonaSeleccionada();
        if (zona == null) {
            JOptionPane.showMessageDialog(vista, "Selecciona una zona primero.");
            return;
        }

        int cantidad = contarMarcados();
        if (cantidad == 0) {
            JOptionPane.showMessageDialog(vista, "Selecciona al menos un asiento.");
            return;
        }
        if (cantidad > MAX_ENTRADAS) {
            JOptionPane.showMessageDialog(vista, "Solo puedes comprar un máximo de " + MAX_ENTRADAS + " entradas.");
            return;
        }

        Sistema.zonaSeleccionada = zona;
        Sistema.cantidadEntradas = cantidad;

        dlgAlertaConfirmacionUsuario alerta = new dlgAlertaConfirmacionUsuario(null, true);
        alerta.setTitle("Confirmar compra");

        alerta.btnConfirmarPago.addActionListener(ev -> {
            alerta.dispose();
            vista.dispose();
            VistaMisTarjetas vistaTarjetas = new VistaMisTarjetas();
            ControladorTarjetas controlador = new ControladorTarjetas(vistaTarjetas);
            controlador.iniciar();
        });
        alerta.btnCancelarPago.addActionListener(ev -> alerta.dispose());

        alerta.setLocationRelativeTo(vista);
        alerta.setVisible(true);
    }

    public void iniciar() {
        cargarDatos();
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}
