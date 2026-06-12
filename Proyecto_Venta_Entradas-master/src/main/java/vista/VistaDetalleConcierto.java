package vista;

import com.mycompany.proyectoventa_de_entradas.ProyectoVenta_de_Entradas;
import modelo.Concierto;
import modelo.Zona;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaDetalleConcierto extends JPanel {

    private final ProyectoVenta_de_Entradas nav;
    private final JLabel lblNombre;
    private final JLabel lblFecha;
    private final DefaultTableModel modeloTabla;
    private final JTable tablaZonas;
    private final JSpinner spinnerCantidad;

    public VistaDetalleConcierto(ProyectoVenta_de_Entradas nav) {
        this.nav = nav;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Cabecera con info del concierto ---
        JPanel panelHeader = new JPanel(new GridLayout(3, 1, 4, 4));
        JLabel lblTitulo = new JLabel("Detalle del Concierto", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblNombre = new JLabel("", SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblFecha = new JLabel("", SwingConstants.CENTER);
        lblFecha.setFont(new Font("Arial", Font.PLAIN, 13));
        panelHeader.add(lblTitulo);
        panelHeader.add(lblNombre);
        panelHeader.add(lblFecha);
        add(panelHeader, BorderLayout.NORTH);

        // --- Tabla de zonas ---
        String[] columnas = {"Zona", "Capacidad total", "Precio (S/)", "Entradas disponibles"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaZonas = new JTable(modeloTabla);
        tablaZonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaZonas.setRowHeight(28);
        tablaZonas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        add(new JScrollPane(tablaZonas), BorderLayout.CENTER);

        // --- Pie: cantidad y botones ---
        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelFooter.add(new JLabel("Cantidad de entradas:"));
        spinnerCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        ((JSpinner.DefaultEditor) spinnerCantidad.getEditor()).getTextField().setColumns(3);
        panelFooter.add(spinnerCantidad);

        JButton btnContinuar = new JButton("Continuar al pago");
        JButton btnVolver = new JButton("Volver al catálogo");
        panelFooter.add(btnVolver);
        panelFooter.add(btnContinuar);
        add(panelFooter, BorderLayout.SOUTH);

        btnContinuar.addActionListener(e -> {
            int fila = tablaZonas.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona una zona primero.");
                return;
            }
            Zona zona = nav.getConciertoSeleccionado().getZonas().get(fila);
            int cantidad = (Integer) spinnerCantidad.getValue();
            if (zona.getDisponibles() < cantidad) {
                JOptionPane.showMessageDialog(this,
                    "Solo hay " + zona.getDisponibles() + " entradas disponibles en esa zona.",
                    "Sin disponibilidad", JOptionPane.WARNING_MESSAGE);
                return;
            }
            nav.setZonaSeleccionada(zona);
            nav.setCantidadEntradas(cantidad);
            nav.cambiarVista("MisTarjetas");
        });

        btnVolver.addActionListener(e -> nav.cambiarVista("Catalogo"));
    }

    public void refresh() {
        Concierto c = nav.getConciertoSeleccionado();
        if (c == null) return;
        lblNombre.setText(c.getNombre());
        lblFecha.setText("Fecha: " + c.getFechaFormateada());
        modeloTabla.setRowCount(0);
        for (Zona z : c.getZonas()) {
            modeloTabla.addRow(new Object[]{
                z.getNombre(),
                z.getCapacidad(),
                "S/ " + z.getPrecio(),
                z.getDisponibles()
            });
        }
        spinnerCantidad.setValue(1);
    }
}
