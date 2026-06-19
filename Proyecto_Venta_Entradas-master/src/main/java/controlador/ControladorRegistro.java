package controlador;

import data.Sistema;
import modelo.Cliente;
import vista.VistaRegistro;
import vista.VistaLogin;
import javax.swing.JOptionPane;

public class ControladorRegistro {
    private VistaRegistro vista;

    public ControladorRegistro(VistaRegistro vista) {
        this.vista = vista;

        this.vista.btnRegistrar.addActionListener(e -> {
            String nombres   = vista.txtNombres.getText().trim();
            String apellidos = vista.txtApellidos.getText().trim();
            String dni       = vista.txtDni.getText().trim();
            String clave     = new String(vista.txtClave.getPassword()).trim();

            if (nombres.isEmpty() || apellidos.isEmpty() || dni.isEmpty() || clave.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Llene todos los campos");
                return;
            }

            Cliente nuevo = new Cliente(0, nombres, apellidos, dni, clave);
            if (Sistema.clientes.agregar(nuevo)) {
                JOptionPane.showMessageDialog(vista, "Registro exitoso. Ya puede iniciar sesión.");
                vista.dispose();
                VistaLogin vistaLogin = new VistaLogin();
                ControladorLogin controlador = new ControladorLogin(vistaLogin);
                controlador.iniciar();
            } else {
                JOptionPane.showMessageDialog(vista, "El DNI ya se encuentra registrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            VistaLogin vistaLogin = new VistaLogin();
            ControladorLogin controlador = new ControladorLogin(vistaLogin);
            controlador.iniciar();
        });
    }

    public void iniciar() {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}
