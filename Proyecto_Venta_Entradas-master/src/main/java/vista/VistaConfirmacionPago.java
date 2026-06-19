package vista;

import javax.swing.*;
import java.awt.*;

public class VistaConfirmacionPago extends JFrame {

    public JLabel  lblConcierto;
    public JLabel  lblZona;
    public JLabel  lblCantidad;
    public JLabel  lblPrecioUnit;
    public JLabel  lblTotal;
    public JLabel  lblTarjeta;
    public JButton btnVolver;
    public JButton btnConfirmar;

    public VistaConfirmacionPago() {
        setTitle("Confirmación de Pago");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        setContentPane(content);

        JLabel lblTitulo = new JLabel("Confirmación de Pago", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        content.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelResumen = new JPanel(new GridLayout(6, 2, 10, 14));
        panelResumen.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Resumen de compra"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        Font fuenteValor = new Font("Arial", Font.BOLD, 13);
        lblConcierto  = new JLabel(); lblConcierto.setFont(fuenteValor);
        lblZona       = new JLabel(); lblZona.setFont(fuenteValor);
        lblCantidad   = new JLabel(); lblCantidad.setFont(fuenteValor);
        lblPrecioUnit = new JLabel(); lblPrecioUnit.setFont(fuenteValor);
        lblTotal      = new JLabel(); lblTotal.setFont(new Font("Arial", Font.BOLD, 15));
        lblTarjeta    = new JLabel(); lblTarjeta.setFont(fuenteValor);

        panelResumen.add(etiqueta("Concierto:"));    panelResumen.add(lblConcierto);
        panelResumen.add(etiqueta("Zona:"));         panelResumen.add(lblZona);
        panelResumen.add(etiqueta("Cantidad:"));     panelResumen.add(lblCantidad);
        panelResumen.add(etiqueta("Precio unit.:"));  panelResumen.add(lblPrecioUnit);
        panelResumen.add(etiqueta("Monto total:"));  panelResumen.add(lblTotal);
        panelResumen.add(etiqueta("Tarjeta:"));      panelResumen.add(lblTarjeta);
        content.add(panelResumen, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnVolver    = new JButton("Volver");
        btnConfirmar = new JButton("Confirmar compra");
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        panelBotones.add(btnVolver);
        panelBotones.add(btnConfirmar);
        content.add(panelBotones, BorderLayout.SOUTH);
    }

    private JLabel etiqueta(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.PLAIN, 13));
        return lbl;
    }
}
