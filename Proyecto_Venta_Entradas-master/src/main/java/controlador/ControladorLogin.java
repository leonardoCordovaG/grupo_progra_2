package controlador;

import data.Sistema;
import modelo.Cliente;
import vista.VistaLogin;
import vista.VistaRegistro;
import vista.VistaCatalogoConciertos;
import vista.frmAdminDashboard;
import javax.swing.JOptionPane;

public class ControladorLogin {
    private VistaLogin vista;

    public ControladorLogin(VistaLogin vista) {
        this.vista = vista;

        // Agregamos un listener al boton Ingresar
        this.vista.btnIngresar.addActionListener(e -> {
            String usuario = vista.txtUsuario.getText().trim();
            String clave   = new String(vista.txtClave.getPassword()).trim();

            if (usuario.equals("admin") && clave.equals("admin123")) {
                // Ingresar como admin
                JOptionPane.showMessageDialog(vista, "Bienvenido Administrador");
                vista.dispose(); // cerrar el login antes de abrir el panel admin

                // Cargamos la vista de admin
                frmAdminDashboard dashboard = new frmAdminDashboard();
                ControladorAdminDashboard controlador = new ControladorAdminDashboard(dashboard, Sistema.conciertos, Sistema.ventas);
                controlador.iniciar();
            } else {
                // Ingresar como usuario normal
                Cliente c = Sistema.clientes.buscarPorCredenciales(usuario, clave);
                if (c != null) {
                    JOptionPane.showMessageDialog(vista, "Bienvenido " + c.getNombres());
                    Sistema.clienteActual = c;
                    vista.dispose(); // se oculta la vista de login

                    // Cargamos la vista de Catalogo
                    VistaCatalogoConciertos vistaCatalogo = new VistaCatalogoConciertos();
                    ControladorCatalogo controlador = new ControladorCatalogo(vistaCatalogo);
                    controlador.iniciar();
                } else {
                    JOptionPane.showMessageDialog(vista, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Agregamos un listener al boton Registro
        this.vista.btnIrRegistro.addActionListener(e -> {
            vista.dispose();
            VistaRegistro vistaRegistro = new VistaRegistro();
            ControladorRegistro controlador = new ControladorRegistro(vistaRegistro);
            controlador.iniciar();
        });
    }

    public void iniciar() {
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }
}
