package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaDetalleConcierto extends JFrame {

    public JLabel            lblNombre;
    public JLabel            lblFecha;
    public DefaultTableModel modeloTabla;
    public JTable            tablaZonas;
    public JComboBox<Integer> comboCantidad;
    public JButton           btnContinuar;
    public JButton           btnVolver;

    public VistaDetalleConcierto() {
        setTitle("Detalle del Concierto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(content);

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
        content.add(panelHeader, BorderLayout.NORTH);

        String[] columnas = {"Zona", "Capacidad total", "Precio (S/)", "Entradas disponibles"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaZonas = new JTable(modeloTabla);
        tablaZonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaZonas.setRowHeight(28);
        tablaZonas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        content.add(new JScrollPane(tablaZonas), BorderLayout.CENTER);

        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelFooter.add(new JLabel("Cantidad de entradas:"));
        comboCantidad = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        panelFooter.add(comboCantidad);
        btnVolver    = new JButton("Volver al catálogo");
        btnContinuar = new JButton("Continuar al pago");
        panelFooter.add(btnVolver);
        panelFooter.add(btnContinuar);
        content.add(panelFooter, BorderLayout.SOUTH);
    }
}
