package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Concierto;
import modelo.Zona;
import vista.frmGestionConcierto;
import vista.dlgZona;

public class ControladorGestionConcierto implements ActionListener {
    private frmGestionConcierto vista;
    private ArrayList<Concierto> listaGlobalConciertos;
    private DefaultTableModel modeloTabla;
    private Concierto conciertoTemporal; 

    public ControladorGestionConcierto(frmGestionConcierto vista, ArrayList<Concierto> listaGlobalConciertos) {
        this.vista = vista;
        this.listaGlobalConciertos = listaGlobalConciertos;
        this.conciertoTemporal = new Concierto("", new Date());
        
        this.vista.btnAbrirAñadirZona.addActionListener(this);
        this.vista.btnEliminarZonaSeleccionada.addActionListener(this);
        this.vista.btnRegistrarConcierto.addActionListener(this);
        
        this.modeloTabla = (DefaultTableModel) this.vista.tblZonasConcierto.getModel();
        this.modeloTabla.setRowCount(0);
    }

    public void iniciar() {
        vista.setTitle("Registrar Evento y Configurar Stock");
        vista.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }


    public void recibirNuevaZona(Zona nuevaZona) {
        conciertoTemporal.agregarZona(nuevaZona);
        refrescarTabla();
    }

    private void refrescarTabla() {
        modeloTabla.setRowCount(0);
        for (Zona z : conciertoTemporal.getListaZonas()) {
            modeloTabla.addRow(new Object[]{z.getNombre(), z.getCapacidad(), z.getPrecio()});
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAbrirAñadirZona) {
            dlgZona vistaZona = new dlgZona(vista, true);
            new ControladorZona(vistaZona, this).iniciar();
        } 
        else if (e.getSource() == vista.btnEliminarZonaSeleccionada) {
            int fila = vista.tblZonasConcierto.getSelectedRow();
            if (fila != -1) {
                String nombreZona = modeloTabla.getValueAt(fila, 0).toString();
                conciertoTemporal.eliminarZona(nombreZona);
                refrescarTabla();
            } else {
                JOptionPane.showMessageDialog(vista, "Seleccione una zona de la tabla para eliminar.");
            }
        } 
        else if (e.getSource() == vista.btnRegistrarConcierto) {
            String nombre = vista.txtNombreConcierto.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Ingrese un nombre válido para el concierto.");
                return;
            }
            if (conciertoTemporal.getListaZonas().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe añadir al menos una zona al concierto.");
                return;
            }

            Concierto conciertoFinal = new Concierto(nombre, new Date());
            for (Zona z : conciertoTemporal.getListaZonas()) {
                conciertoFinal.agregarZona(z);
            }
            
            listaGlobalConciertos.add(conciertoFinal);
            JOptionPane.showMessageDialog(vista, "¡Concierto '" + nombre + "' registrado de forma exitosa!");
            vista.dispose();
        }
    }
}