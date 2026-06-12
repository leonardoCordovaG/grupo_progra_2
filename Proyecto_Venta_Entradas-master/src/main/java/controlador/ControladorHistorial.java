package controlador;

import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Cliente;
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
        vista.getBtnAnularCompra().addActionListener(e -> {
            int fila = vista.getTablaCompras().getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Selecciona una compra de la tabla.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(vista,
                "¿Confirmar la anulación de esta compra?",
                "Anular compra", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;

            Venta venta = cliente.getVentas().get(fila);

            // Liberar las entradas de vuelta a disponible
            if (venta.getZonaAsociada() != null) {
                modelo.Entrada[] entradas = venta.getZonaAsociada().mostrarEntrada();
                int liberadas = 0;
                for (modelo.Entrada en : entradas) {
                    if (liberadas >= venta.getCantidadEntradas()) break;
                    if (en != null && en.liberar()) liberadas++;
                }
            }

            cliente.getVentas().remove(fila);
            actualizarTabla();
            JOptionPane.showMessageDialog(vista, "Compra anulada correctamente.");
        });

        vista.getBtnVolver().addActionListener(e -> vista.dispose());
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
