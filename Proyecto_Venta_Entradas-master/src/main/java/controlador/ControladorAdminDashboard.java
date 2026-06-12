package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.Concierto;
import modelo.Venta;
import vista.frmAdminDashboard;
import vista.frmGestionConcierto;
import vista.dlgPanelAnulaciones;

public class ControladorAdminDashboard implements ActionListener {
    private frmAdminDashboard vista;
    private ArrayList<Concierto> listaConciertos;
    private ArrayList<Venta> listaVentas;

    public ControladorAdminDashboard(frmAdminDashboard vista, ArrayList<Concierto> conciertos, ArrayList<Venta> ventas) {
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
            new ControladorGestionConcierto(vistaGestion, listaConciertos).iniciar();
        } 
        else if (e.getSource() == vista.btnPanelAnulaciones) {
            dlgPanelAnulaciones vistaAnulaciones = new dlgPanelAnulaciones(vista, true);
            new ControladorPanelAnulaciones(vistaAnulaciones, listaVentas).iniciar();
        }
    }
}