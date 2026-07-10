package controlador;

import data.Sistema;
import modelo.Concierto;
import modelo.Entrada;
import modelo.Venta;
import modelo.Zona;
import vista.VistaConfirmacionPago;
import vista.VistaCatalogoConciertos;
import vista.VistaMisTarjetas;
import javax.swing.JOptionPane;
import java.util.Date;

public class ControladorConfirmacion {
    private VistaConfirmacionPago vista;

    public ControladorConfirmacion(VistaConfirmacionPago vista) {
        this.vista = vista;

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            VistaMisTarjetas vistaTarjetas = new VistaMisTarjetas();
            ControladorTarjetas controlador = new ControladorTarjetas(vistaTarjetas);
            controlador.iniciar();
        });

        this.vista.btnConfirmar.addActionListener(e -> realizarCompra());
    }

    public void iniciar() {
        cargarResumen();
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    // Precio de la zona por la cantidad de entradas, menos el % de descuento asociado a la marca de la tarjeta.
    private int calcularTotal(Zona zona, int cantidad) {
        int bruto = zona.getPrecio() * cantidad;
        double descuento = Sistema.tarjetaSeleccionada.getTipo().getPorcentajeDescuento();
        return (int) Math.round(bruto * (1 - descuento / 100.0));
    }

    private void cargarResumen() {
        Concierto concierto = Sistema.conciertoSeleccionado;
        Zona zona           = Sistema.zonaSeleccionada;
        int cantidad        = Sistema.cantidadEntradas;

        if (concierto == null || zona == null || Sistema.tarjetaSeleccionada == null) return;

        int total = calcularTotal(zona, cantidad);
        double descuento = Sistema.tarjetaSeleccionada.getTipo().getPorcentajeDescuento();
        vista.lblConcierto.setText(concierto.getNombre());
        vista.lblZona.setText(zona.getNombre());
        vista.lblCantidad.setText(cantidad + " entrada(s)");
        vista.lblPrecioUnit.setText("S/ " + zona.getPrecio());
        vista.lblTotal.setText("S/ " + total + (descuento > 0 ? "  (-" + (int) descuento + "% " + Sistema.tarjetaSeleccionada.getTipo().getNombre() + ")" : ""));
        vista.lblTarjeta.setText(
            Sistema.tarjetaSeleccionada.getNumeroEnmascarado() + "  —  " + Sistema.tarjetaSeleccionada.getNombre()
        );
    }

    private void realizarCompra() {
        Concierto concierto = Sistema.conciertoSeleccionado;
        Zona zona           = Sistema.zonaSeleccionada;
        int cantidad        = Sistema.cantidadEntradas;
        int total           = calcularTotal(zona, cantidad);

        int confirm = JOptionPane.showConfirmDialog(vista,
            "¿Confirmar el pago de S/ " + total + "?",
            "Confirmar compra", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        // Crear la venta primero para poder registrar en ella cada entrada vendida
        Venta venta = new Venta(new Date(), total, zona, null, concierto.getNombre(), cantidad);

        // Marcar entradas como vendidas y guardarlas en la venta para poder anularlas después
        Entrada[] entradas = zona.mostrarEntrada();
        int marcadas = 0;
        for (Entrada e : entradas) {
            if (marcadas >= cantidad) break;
            if (e != null && e.vender()) {
                venta.agregarEntradaVendida(e);
                marcadas++;
            }
        }
        Sistema.clienteActual.agregarVenta(venta);
        Sistema.ventas.agregar(venta);

        JOptionPane.showMessageDialog(vista,
            "¡Compra realizada con éxito!\n\n" +
            "  Concierto : " + concierto.getNombre() + "\n" +
            "  Zona      : " + zona.getNombre() + "\n" +
            "  Cantidad  : " + cantidad + " entrada(s)\n" +
            "  Total     : S/ " + total,
            "Compra exitosa", JOptionPane.INFORMATION_MESSAGE);

        vista.dispose();
        VistaCatalogoConciertos vistaCatalogo = new VistaCatalogoConciertos();
        ControladorCatalogo controlador = new ControladorCatalogo(vistaCatalogo);
        controlador.iniciar();
    }
}
