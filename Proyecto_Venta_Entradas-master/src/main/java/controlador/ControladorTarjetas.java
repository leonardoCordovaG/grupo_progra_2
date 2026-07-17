package controlador;

import data.Sistema;
import modelo.Tarjeta;
import modelo.TipoTarjeta;
import vista.VistaMisTarjetas;
import vista.VistaConfirmacionPago;
import vista.dlgDetalleConciertoUsuario;
import javax.swing.*;
import java.awt.*;

public class ControladorTarjetas {
    private VistaMisTarjetas vista;

    public ControladorTarjetas(VistaMisTarjetas vista) {
        this.vista = vista;

        this.vista.btnAgregar.addActionListener(e -> agregarTarjeta());

        this.vista.btnEliminar.addActionListener(e -> {
            int fila = vista.tablaTarjetas.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(vista, "Selecciona una tarjeta para eliminar.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(vista,
                "¿Eliminar la tarjeta seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Tarjeta t = Sistema.clienteActual.getTarjetas().get(fila);
                Sistema.clienteActual.quitarTarjeta(t);
                cargarTarjetas();
            }
        });

        this.vista.btnUsar.addActionListener(e -> {
            int fila = vista.tablaTarjetas.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(vista, "Selecciona una tarjeta para continuar.");
                return;
            }
            Sistema.tarjetaSeleccionada = Sistema.clienteActual.getTarjetas().get(fila);
            vista.dispose();
            VistaConfirmacionPago vistaConfirmacion = new VistaConfirmacionPago();
            ControladorConfirmacion controlador = new ControladorConfirmacion(vistaConfirmacion);
            controlador.iniciar();
        });

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            dlgDetalleConciertoUsuario vistaDetalle = new dlgDetalleConciertoUsuario(null, true);
            ControladorDetalle controlador = new ControladorDetalle(vistaDetalle);
            controlador.iniciar();
        });
    }

    public void iniciar() {
        cargarTarjetas();
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    private void cargarTarjetas() {
        vista.modeloTabla.setRowCount(0);
        if (Sistema.clienteActual == null) return;
        for (Tarjeta t : Sistema.clienteActual.getTarjetas()) {
            vista.modeloTabla.addRow(new Object[]{
                t.getNumeroEnmascarado(),
                t.getNombre(),
                t.getFecha(),
                t.getTipo().getNombre()
            });
        }
    }

    private void agregarTarjeta() {
        JComboBox<TipoTarjeta> cboTipo = new JComboBox<>(Sistema.tiposTarjeta);
        JTextField txtNumero = new JTextField(16);
        JTextField txtNombre = new JTextField(20);
        JTextField txtFecha  = new JTextField(5);
        JTextField txtCVV    = new JTextField(4);

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 8));
        form.add(new JLabel("Marca de la tarjeta:"));             form.add(cboTipo);
        form.add(new JLabel("Número de tarjeta:"));               form.add(txtNumero);
        form.add(new JLabel("Nombre del titular:"));              form.add(txtNombre);
        form.add(new JLabel("Vencimiento (MM/AA):"));             form.add(txtFecha);
        form.add(new JLabel("CVV:"));                             form.add(txtCVV);

        int resultado = JOptionPane.showConfirmDialog(vista, form,
            "Registrar nueva tarjeta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resultado != JOptionPane.OK_OPTION) return;

        TipoTarjeta tipo = (TipoTarjeta) cboTipo.getSelectedItem();
        String numStr  = txtNumero.getText().trim();
        String nombre  = txtNombre.getText().trim();
        String fecha   = txtFecha.getText().trim();
        String cvvStr  = txtCVV.getText().trim();

        if (numStr.isEmpty() || nombre.isEmpty() || fecha.isEmpty() || cvvStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Se valida el texto tal cual se ingresó (antes de parsearlo a long/int), porque esa
            // conversión pierde los ceros a la izquierda y arruinaría la validación de longitud.
            Tarjeta.verificarNumero(numStr, tipo);
            Tarjeta.verificarCVV(cvvStr, tipo);

            long numero = Long.parseLong(numStr);
            int cvv     = Integer.parseInt(cvvStr);

            Tarjeta nuevaTarjeta = new Tarjeta(numero, nombre, fecha, cvv, tipo);

            if (nuevaTarjeta.validarTarjeta()) {
                Sistema.clienteActual.agregarTarjeta(nuevaTarjeta);
                cargarTarjetas();
                JOptionPane.showMessageDialog(vista, "Tarjeta registrada correctamente.");
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
