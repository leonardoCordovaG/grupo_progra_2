package vista;

import com.mycompany.proyectoventa_de_entradas.ProyectoVenta_de_Entradas;
import modelo.Concierto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VistaCatalogoConciertos extends JPanel {

    private final ProyectoVenta_de_Entradas nav;
    private final DefaultTableModel modeloTabla;
    private final JTable tablaCatalogo;

    public VistaCatalogoConciertos(ProyectoVenta_de_Entradas nav) {
        this.nav = nav;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- Cabecera ---
        JLabel lblTitulo = new JLabel("Conciertos Disponibles", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Tabla de conciertos ---
        String[] columnas = {"Concierto", "Fecha", "Zonas"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tablaCatalogo = new JTable(modeloTabla);
        tablaCatalogo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCatalogo.setRowHeight(28);
        tablaCatalogo.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        add(new JScrollPane(tablaCatalogo), BorderLayout.CENTER);

        // --- Botones ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnVerDetalle = new JButton("Ver detalle del concierto");
        JButton btnMisCompras = new JButton("Mis compras");
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        panelBotones.add(btnVerDetalle);
        panelBotones.add(btnMisCompras);
        panelBotones.add(btnCerrarSesion);
        add(panelBotones, BorderLayout.SOUTH);

        btnVerDetalle.addActionListener(e -> {
            int fila = tablaCatalogo.getSelectedRow();
            if (fila < 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un concierto de la lista.");
                return;
            }
            List<Concierto> catalogo = Concierto.getCatalogo();
            nav.setConciertoSeleccionado(catalogo.get(fila));
            nav.cambiarVista("DetalleConcierto");
        });

        btnMisCompras.addActionListener(e -> {
            vista.VistaHistorialCompras vistaHistorial = new vista.VistaHistorialCompras();
            vistaHistorial.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            new controlador.ControladorHistorial(vistaHistorial, nav.getClienteActual());
            vistaHistorial.setTitle("Mis Compras — " + nav.getClienteActual().getNombres());
            vistaHistorial.setLocationRelativeTo(null);
            vistaHistorial.setVisible(true);
        });

        btnCerrarSesion.addActionListener(e -> {
            nav.setClienteActual(null);
            nav.cambiarVista("Login");
        });
    }

    public void refresh() {
        modeloTabla.setRowCount(0);
        for (Concierto c : Concierto.getCatalogo()) {
            modeloTabla.addRow(new Object[]{
                c.getNombre(),
                c.getFechaFormateada(),
                c.getZonas().size() + " zona(s)"
            });
        }
    }
}
