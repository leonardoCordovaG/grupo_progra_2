package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaMisTarjetas extends JFrame {

    public DefaultTableModel modeloTabla;
    public JTable            tablaTarjetas;
    public JButton           btnAgregar;
    public JButton           btnEliminar;
    public JButton           btnUsar;
    public JButton           btnVolver;

    public VistaMisTarjetas() {
        setTitle("Mis Tarjetas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setContentPane(content);

        JLabel lblTitulo = new JLabel("Mis Tarjetas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        content.add(lblTitulo, BorderLayout.NORTH);

        String[] columnas = {"Número", "Titular", "Vencimiento"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tablaTarjetas = new JTable(modeloTabla);
        tablaTarjetas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaTarjetas.setRowHeight(28);
        tablaTarjetas.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        content.add(new JScrollPane(tablaTarjetas), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnAgregar  = new JButton("Registrar tarjeta");
        btnEliminar = new JButton("Eliminar tarjeta");
        btnUsar     = new JButton("Pagar con esta tarjeta");
        btnVolver   = new JButton("Volver");
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnUsar);
        panelBotones.add(btnVolver);
        content.add(panelBotones, BorderLayout.SOUTH);
    }
}
