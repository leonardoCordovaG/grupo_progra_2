package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Zona;
import modelo.Entrada;
import vista.dlgZona;

public class ControladorZona implements ActionListener {
    private dlgZona vista;
    private ControladorGestionConcierto controladorPadre;

    public ControladorZona(dlgZona vista, ControladorGestionConcierto padre) {
        this.vista = vista;
        this.controladorPadre = padre;
        
        this.vista.btnAceptarZona.addActionListener(this);
        this.vista.btnCancelarZona.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Configurar Parámetros de Zona");
        vista.setLocationRelativeTo(vista.getParent());
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAceptarZona) {
            try {
                String nombre = vista.txtNombreZona.getText().trim();
                int capacidad = (int) vista.spnCapacidadZona.getValue();
                int precio = Integer.parseInt(vista.txtPrecioZona.getText().trim());

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "El nombre de la zona es obligatorio.");
                    return;
                }
                if (capacidad <= 0 || precio <= 0) {
                    JOptionPane.showMessageDialog(vista, "La capacidad y el precio deben ser mayores a cero.");
                    return;
                }

                Zona nuevaZona = new Zona(nombre, capacidad, precio, new Entrada[0]);
                controladorPadre.recibirNuevaZona(nuevaZona); 
                vista.dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "El precio de la zona debe ser un valor numérico entero.");
            }
        } 
        else if (e.getSource() == vista.btnCancelarZona) {
            vista.dispose();
        }
    }
}