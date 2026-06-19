package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ConciertoArreglo;
import modelo.VentaArreglo;
import vista.frmAdminDashboard;
import vista.frmGestionConcierto;
import vista.dlgPanelAnulaciones;

public class ControladorAdminDashboard implements ActionListener {
    private frmAdminDashboard vista;
    private ConciertoArreglo listaConciertos;
    private VentaArreglo listaVentas;

    public ControladorAdminDashboard(frmAdminDashboard vista, ConciertoArreglo conciertos, VentaArreglo ventas) {
        this.vista = vista;
        this.listaConciertos = conciertos;
        this.listaVentas = ventas;

        this.vista.btnGestionarConciertos.addActionListener(this);
        this.vista.btnPanelAnulaciones.addActionListener(this);
    }

    public void iniciar() {
        vista.setTitle("Módulo de Administración de Eventos");
        vista.setLocationRelativeTo(null);
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGestionarConciertos) {
            frmGestionConcierto vistaGestion = new frmGestionConcierto();
            ControladorGestionConcierto controlador = new ControladorGestionConcierto(vistaGestion, listaConciertos);
            controlador.iniciar();
        }
        else if (e.getSource() == vista.btnPanelAnulaciones) {
            dlgPanelAnulaciones vistaAnulaciones = new dlgPanelAnulaciones(vista, true);
            ControladorPanelAnulaciones controlador = new ControladorPanelAnulaciones(vistaAnulaciones, listaVentas);
            controlador.iniciar();
        }
    }
}
