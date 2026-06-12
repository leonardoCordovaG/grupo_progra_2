package vista;

import com.mycompany.proyectoventa_de_entradas.ProyectoVenta_de_Entradas;
import modelo.Cliente;
import modelo.Tarjeta;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaMisTarjetas extends JPanel {

    private final ProyectoVenta_de_Entradas nav;
    private final DefaultTableModel modeloTabla;
    private final JTable tablaTarjetas;

    public VistaMisTarjetas(ProyectoVenta_de_Entradas nav) {
        this.nav = nav;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Cabecera ---
        JLabel lblTitulo = new JLabel("Mis Tarjetas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Tabla de tarjetas ---
        String[] columnas = {"Número", "Titular", "Vencimiento"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaTarjetas = new JTable(modeloTabla);
        tablaTarjetas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaTarjetas.setRowHeight(28);
        tablaTarjetas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        add(new JScrollPane(tablaTarjetas), BorderLayout.CENTER);

        // --- Botones ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAgregar   = new JButton("Registrar tarjeta");
        JButton btnEliminar  = new JButton("Eliminar tarjeta");
        JButton btnUsar      = new JButton("Pagar con esta tarjeta");
        JButton btnVolver    = new JButton("Volver");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnUsar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> mostrarFormularioAgregarTarjeta());

        btnEliminar.addActionListener(e -> {
            int fila = tablaTarjetas.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona una tarjeta para eliminar.");
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar la tarjeta seleccionada?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Tarjeta t = nav.getClienteActual().getTarjetas().get(fila);
                nav.getClienteActual().quitarTarjeta(t);
                refresh();
            }
        });

        btnUsar.addActionListener(e -> {
            int fila = tablaTarjetas.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona una tarjeta para continuar.");
                return;
            }
            Tarjeta t = nav.getClienteActual().getTarjetas().get(fila);
            nav.setTarjetaSeleccionada(t);
            nav.cambiarVista("ConfirmacionPago");
        });

        btnVolver.addActionListener(e -> nav.cambiarVista("DetalleConcierto"));
    }

    private void mostrarFormularioAgregarTarjeta() {
        JTextField txtNumero = new JTextField(16);
        JTextField txtNombre = new JTextField(20);
        JTextField txtFecha  = new JTextField(5);
        JTextField txtCVV    = new JTextField(3);

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 8));
        form.add(new JLabel("Número de tarjeta (16 dígitos):"));
        form.add(txtNumero);
        form.add(new JLabel("Nombre del titular:"));
        form.add(txtNombre);
        form.add(new JLabel("Vencimiento (MM/AA):"));
        form.add(txtFecha);
        form.add(new JLabel("CVV (3 dígitos):"));
        form.add(txtCVV);

        int resultado = JOptionPane.showConfirmDialog(this, form,
            "Registrar nueva tarjeta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado != JOptionPane.OK_OPTION) return;

        String numStr   = txtNumero.getText().trim();
        String nombre   = txtNombre.getText().trim();
        String fecha    = txtFecha.getText().trim();
        String cvvStr   = txtCVV.getText().trim();

        if (numStr.isEmpty() || nombre.isEmpty() || fecha.isEmpty() || cvvStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            long numero = Long.parseLong(numStr);
            int cvv = Integer.parseInt(cvvStr);
            Tarjeta nueva = new Tarjeta(numero, nombre, fecha, cvv);
            nav.getClienteActual().agregarTarjeta(nueva);
            refresh();
            JOptionPane.showMessageDialog(this, "Tarjeta registrada correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                "El número de tarjeta y el CVV deben ser solo dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refresh() {
        modeloTabla.setRowCount(0);
        Cliente cliente = nav.getClienteActual();
        if (cliente == null) return;
        for (Tarjeta t : cliente.getTarjetas()) {
            modeloTabla.addRow(new Object[]{
                t.getNumeroEnmascarado(),
                t.getNombre(),
                t.getFecha()
            });
        }
    }
}
