package controlador;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
import modelo.Entrada;
import modelo.Venta;
import vista.VistaHistorialCompras;

public class ControladorHistorial {
    private VistaHistorialCompras vista;
    private Cliente cliente;

    public ControladorHistorial(VistaHistorialCompras vista, Cliente cliente) {
        this.vista = vista;
        this.cliente = cliente;
        this.initListeners();
        this.actualizarTabla();
    }

    private void initListeners() {
        vista.getBtnAnularCompra().addActionListener(e -> procesarAnulacion());
        vista.getBtnVolver().addActionListener(e -> vista.dispose());
    }

    private void procesarAnulacion() {
        int fila = vista.getTablaCompras().getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona una compra de la tabla.");
            return;
        }

        Venta venta = cliente.getVentas().get(fila);
        int totalEntradas = venta.getCantidadEntradas();

        // Pedir cuantas entradas anular (entre 1 y el total de esa compra)
        String input = JOptionPane.showInputDialog(vista,
            "Cuantas entradas deseas anular? (1 a " + totalEntradas + ")",
            String.valueOf(totalEntradas));
        if (input == null) return;

        int cantidadAnular;
        try {
            cantidadAnular = Integer.parseInt(input.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Ingresa un numero valido.");
            return;
        }

        if (cantidadAnular < 1 || cantidadAnular > totalEntradas) {
            JOptionPane.showMessageDialog(vista,
                "La cantidad debe estar entre 1 y " + totalEntradas + ".");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(vista,
            "Confirmar anulacion de " + cantidadAnular + " entrada(s)?",
            "Anular", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        // Liberar exactamente las N entradas especificas de esta compra
        List<Entrada> entradasVendidas = venta.getEntradasVendidas();
        for (int i = 0; i < cantidadAnular; i++) {
            Entrada en = entradasVendidas.remove(0);
            en.liberar();
        }

        if (cantidadAnular == totalEntradas) {
            // Anulacion total: sacar la venta del historial
            cliente.getVentas().remove(fila);
        } else {
            // Anulacion parcial: actualizar cantidad y monto en la misma venta
            int precioPorEntrada = venta.getZonaAsociada().getPrecio();
            venta.setCantidadEntradas(totalEntradas - cantidadAnular);
            venta.setMonto(venta.getMonto() - (precioPorEntrada * cantidadAnular));
        }

        actualizarTabla();
        JOptionPane.showMessageDialog(vista, cantidadAnular + " entrada(s) anulada(s).");
    }

    public void actualizarTabla() {
        DefaultTableModel modeloTabla = new DefaultTableModel(
            new String[]{"Fecha", "Concierto", "Zona", "Entradas", "Monto", "Estado"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        vista.getTablaCompras().setModel(modeloTabla);

        if (cliente == null) return;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        for (Venta v : cliente.getVentas()) {
            String zona = v.getZonaAsociada() != null ? v.getZonaAsociada().getNombre() : "-";
            modeloTabla.addRow(new Object[]{
                sdf.format(v.getFecha()),
                v.getConciertoNombre(),
                zona,
                v.getCantidadEntradas() + " entrada(s)",
                "S/ " + v.getMonto(),
                "Activa"
            });
        }
    }
}
