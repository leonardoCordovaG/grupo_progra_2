package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.net.URL;

public class VistaCatalogoConciertos extends JFrame {

    public DefaultTableModel modeloTabla;
    public JTable            tablaCatalogo;
    public JButton           btnVerDetalle;
    public JButton           btnMisCompras;
    public JButton           btnCerrarSesion;

    public VistaCatalogoConciertos() {
        setTitle("Catálogo de Conciertos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        setContentPane(content);

        content.add(crearBanner(), BorderLayout.NORTH);

        String[] columnas = {"Concierto", "Fecha", "Zonas"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        tablaCatalogo = new JTable(modeloTabla);
        tablaCatalogo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCatalogo.setRowHeight(28);
        tablaCatalogo.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        content.add(new JScrollPane(tablaCatalogo), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnVerDetalle   = new JButton("Ver detalle del concierto");
        btnMisCompras   = new JButton("Mis compras");
        btnCerrarSesion = new JButton("Cerrar sesión");
        panelBotones.add(btnVerDetalle);
        panelBotones.add(btnMisCompras);
        panelBotones.add(btnCerrarSesion);
        content.add(panelBotones, BorderLayout.SOUTH);
    }

    private JPanel crearBanner() {
        JPanel banner = new JPanel(new BorderLayout());
        URL imgUrl = getClass().getResource("/imagenes/zona_imagen.png");
        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image img = icon.getImage().getScaledInstance(760, 150, Image.SCALE_SMOOTH);
            JLabel lblImagen = new JLabel(new ImageIcon(img));
            lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
            banner.add(lblImagen, BorderLayout.CENTER);
        }
        JLabel lblTitulo = new JLabel("Conciertos Disponibles", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        banner.add(lblTitulo, BorderLayout.SOUTH);
        return banner;
    }
}
