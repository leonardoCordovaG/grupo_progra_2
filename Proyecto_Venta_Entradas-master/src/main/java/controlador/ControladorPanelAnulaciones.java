package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Venta;
import modelo.VentaArreglo;
import vista.dlgPanelAnulaciones;

public class ControladorPanelAnulaciones implements ActionListener {
    private dlgPanelAnulaciones vista;
    private VentaArreglo listaVentas;
    private DefaultTableModel modeloTabla;
    private SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public ControladorPanelAnulaciones(dlgPanelAnulaciones vista, VentaArreglo listaVentas) {
        this.vista = vista;
        this.listaVentas = listaVentas;
        this.vista.btnAnularVenta.addActionListener(this);

        this.modeloTabla = (DefaultTableModel) this.vista.tblVentasGlobales.getModel();
    }

    public void iniciar() {
        vista.setTitle("Supervisión y Anulación de Transacciones");
        vista.setLocationRelativeTo(vista.getParent());
        cargarTablaVentas();
        vista.setVisible(true);
    }

    private void cargarTablaVentas() {
        modeloTabla.setRowCount(0);
        int correlativo = 1;
        for (Venta v : listaVentas) {
            modeloTabla.addRow(new Object[]{
                correlativo++,
                formatoFecha.format(v.getFecha()),
                "S/. " + v.getMonto()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAnularVenta) {
            int fila = vista.tblVentasGlobales.getSelectedRow();
            if (fila != -1) {
                Venta ventaObj = listaVentas.obtener(fila);
                if (ventaObj.anular()) {
                    listaVentas.eliminar(fila);
                    JOptionPane.showMessageDialog(vista, "Operación anulada exitosamente.");
                    cargarTablaVentas();
                }
            } else {
                JOptionPane.showMessageDialog(vista, "Seleccione un registro de la tabla para proceder.");
            }
        }
    }
}
