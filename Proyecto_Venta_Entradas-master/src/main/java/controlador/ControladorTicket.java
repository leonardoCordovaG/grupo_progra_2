package controlador;

import modelo.Venta;
import vista.VistaTicketVirtual;

public class ControladorTicket {
    private VistaTicketVirtual vista;
    private Venta venta;

    public ControladorTicket(VistaTicketVirtual vista, Venta venta) {
        this.vista = vista;
        this.venta = venta;
        
        this.llenarDatosTicket();
        
        this.initListeners();
        
        this.vista.setLocationRelativeTo(null);
    }

    private void llenarDatosTicket() {
        vista.getLblMonto().setText("S/. " + venta.getMonto());
        
        if (venta.getZonaAsociada() != null) {
            vista.getLblZona().setText(venta.getZonaAsociada().getNombre());
        }
        
        if (venta.getEntradaAsociada() != null) {
            vista.getLblNumeroEntrada().setText("N° " + venta.getEntradaAsociada().getNumero());
        }
        
        vista.getLblConcierto().setText("Evento Confirmado"); 
    }

    private void initListeners() {
        vista.getBtnCerrarTicket().addActionListener(e -> {
            vista.dispose(); 
        });
    }
}