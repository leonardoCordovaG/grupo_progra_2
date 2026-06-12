package vista;

import com.mycompany.proyectoventa_de_entradas.ProyectoVenta_de_Entradas;
import modelo.Concierto;
import modelo.Tarjeta;
import modelo.Zona;
import javax.swing.*;
import java.awt.*;

public class VistaConfirmacionPago extends JPanel {

    private final ProyectoVenta_de_Entradas nav;
    private final JLabel lblConcierto;
    private final JLabel lblZona;
    private final JLabel lblCantidad;
    private final JLabel lblPrecioUnit;
    private final JLabel lblTotal;
    private final JLabel lblTarjeta;

    public VistaConfirmacionPago(ProyectoVenta_de_Entradas nav) {
        this.nav = nav;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // --- Cabecera ---
        JLabel lblTitulo = new JLabel("Confirmación de Pago", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblTitulo, BorderLayout.NORTH);

        // --- Resumen de compra ---
        JPanel panelResumen = new JPanel(new GridLayout(6, 2, 10, 14));
        panelResumen.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Resumen de compra"),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        lblConcierto  = new JLabel();
        lblZona       = new JLabel();
        lblCantidad   = new JLabel();
        lblPrecioUnit = new JLabel();
        lblTotal      = new JLabel();
        lblTarjeta    = new JLabel();

        Font fuenteValor = new Font("Arial", Font.BOLD, 13);
        lblConcierto.setFont(fuenteValor);
        lblZona.setFont(fuenteValor);
        lblCantidad.setFont(fuenteValor);
        lblPrecioUnit.setFont(fuenteValor);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 15));
        lblTarjeta.setFont(fuenteValor);

        panelResumen.add(etiqueta("Concierto:"));    panelResumen.add(lblConcierto);
        panelResumen.add(etiqueta("Zona:"));         panelResumen.add(lblZona);
        panelResumen.add(etiqueta("Cantidad:"));     panelResumen.add(lblCantidad);
        panelResumen.add(etiqueta("Precio unit.:"));  panelResumen.add(lblPrecioUnit);
        panelResumen.add(etiqueta("Monto total:"));  panelResumen.add(lblTotal);
        panelResumen.add(etiqueta("Tarjeta:"));      panelResumen.add(lblTarjeta);

        add(panelResumen, BorderLayout.CENTER);

        // --- Botones ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnVolver    = new JButton("Volver");
        JButton btnConfirmar = new JButton("Confirmar compra");
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        panelBotones.add(btnVolver);
        panelBotones.add(btnConfirmar);
        add(panelBotones, BorderLayout.SOUTH);

        btnVolver.addActionListener(e -> nav.cambiarVista("MisTarjetas"));
        btnConfirmar.addActionListener(e -> realizarCompra());
    }

    private JLabel etiqueta(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.PLAIN, 13));
        return lbl;
    }

    private void realizarCompra() {
        Concierto concierto = nav.getConciertoSeleccionado();
        Zona zona           = nav.getZonaSeleccionada();
        int cantidad        = nav.getCantidadEntradas();
        int total           = zona.getPrecio() * cantidad;

        int confirm = JOptionPane.showConfirmDialog(this,
            "¿Confirmar el pago de S/ " + total + "?",
            "Confirmar compra", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        JOptionPane.showMessageDialog(this,
            "¡Compra realizada con éxito!\n\n" +
            "  Concierto : " + concierto.getNombre() + "\n" +
            "  Zona      : " + zona.getNombre() + "\n" +
            "  Cantidad  : " + cantidad + " entrada(s)\n" +
            "  Total     : S/ " + total,
            "Compra exitosa", JOptionPane.INFORMATION_MESSAGE);

        nav.cambiarVista("Catalogo");
    }

    public void refresh() {
        Concierto concierto = nav.getConciertoSeleccionado();
        Zona zona           = nav.getZonaSeleccionada();
        Tarjeta tarjeta     = nav.getTarjetaSeleccionada();
        int cantidad        = nav.getCantidadEntradas();

        if (concierto == null || zona == null || tarjeta == null) return;

        int total = zona.getPrecio() * cantidad;

        lblConcierto.setText(concierto.getNombre());
        lblZona.setText(zona.getNombre());
        lblCantidad.setText(cantidad + " entrada(s)");
        lblPrecioUnit.setText("S/ " + zona.getPrecio());
        lblTotal.setText("S/ " + total);
        lblTarjeta.setText(tarjeta.getNumeroEnmascarado() + "  —  " + tarjeta.getNombre());
    }
}
