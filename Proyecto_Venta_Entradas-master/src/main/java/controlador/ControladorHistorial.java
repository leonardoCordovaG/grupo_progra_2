package controlador;

import java.util.ArrayList;
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

            JOptionPane.showMessageDialog(vista, "Procesando la anulación de la compra...");
            
            actualizarTabla();
        });

        vista.getBtnVolver().addActionListener(e -> {
            vista.dispose(); 
        });
    }

    public void actualizarTabla() {
        DefaultTableModel modeloTabla = (DefaultTableModel) vista.getTablaCompras().getModel();
        modeloTabla.setRowCount(0); 

    }
}