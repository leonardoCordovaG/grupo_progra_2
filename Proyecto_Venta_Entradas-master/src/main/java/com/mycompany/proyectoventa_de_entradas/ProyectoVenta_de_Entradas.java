package com.mycompany.proyectoventa_de_entradas;

import modelo.Cliente;
import modelo.Concierto;
import modelo.Tarjeta;
import modelo.Zona;
import vista.VistaLogin;
import vista.VistaRegistro;
import vista.VistaCatalogoConciertos;
import vista.VistaDetalleConcierto;
import vista.VistaMisTarjetas;
import vista.VistaConfirmacionPago;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class ProyectoVenta_de_Entradas extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelPrincipal;

    // --- Estado de sesión ---
    private Cliente clienteActual;
    private Concierto conciertoSeleccionado;
    private Zona zonaSeleccionada;
    private int cantidadEntradas;
    private Tarjeta tarjetaSeleccionada;

    // --- Referencias a mis vistas para poder llamar refresh() ---
    private VistaCatalogoConciertos vistaCatalogo;
    private VistaDetalleConcierto vistaDetalle;
    private VistaMisTarjetas vistaTarjetas;
    private VistaConfirmacionPago vistaConfirmacion;

    public ProyectoVenta_de_Entradas() {
        setTitle("Sistema de Venta de Entradas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        // --- Integrante 1: Vistas de acceso ---
        panelPrincipal.add(new VistaLogin(this), "Login");
        panelPrincipal.add(new VistaRegistro(this), "Registro");

        // --- Integrante 2 (Kepler): Catálogo y pago ---
        vistaCatalogo = new VistaCatalogoConciertos(this);
        vistaDetalle = new VistaDetalleConcierto(this);
        vistaTarjetas = new VistaMisTarjetas(this);
        vistaConfirmacion = new VistaConfirmacionPago(this);

        panelPrincipal.add(vistaCatalogo, "Catalogo");
        panelPrincipal.add(vistaDetalle, "DetalleConcierto");
        panelPrincipal.add(vistaTarjetas, "MisTarjetas");
        panelPrincipal.add(vistaConfirmacion, "ConfirmacionPago");

        add(panelPrincipal);
        cardLayout.show(panelPrincipal, "Login");
    }

    // Método de navegación central — llama refresh en cada vista al mostrarse
    public void cambiarVista(String nombreVista) {
        switch (nombreVista) {
            case "Catalogo":        vistaCatalogo.refresh();     break;
            case "DetalleConcierto": vistaDetalle.refresh();     break;
            case "MisTarjetas":     vistaTarjetas.refresh();     break;
            case "ConfirmacionPago": vistaConfirmacion.refresh(); break;
            default: break;
        }
        cardLayout.show(panelPrincipal, nombreVista);
    }

    // --- Getters y setters de sesión ---
    public Cliente getClienteActual() { return clienteActual; }
    public void setClienteActual(Cliente c) { this.clienteActual = c; }

    public Concierto getConciertoSeleccionado() { return conciertoSeleccionado; }
    public void setConciertoSeleccionado(Concierto c) { this.conciertoSeleccionado = c; }

    public Zona getZonaSeleccionada() { return zonaSeleccionada; }
    public void setZonaSeleccionada(Zona z) { this.zonaSeleccionada = z; }

    public int getCantidadEntradas() { return cantidadEntradas; }
    public void setCantidadEntradas(int cantidad) { this.cantidadEntradas = cantidad; }

    public Tarjeta getTarjetaSeleccionada() { return tarjetaSeleccionada; }
    public void setTarjetaSeleccionada(Tarjeta t) { this.tarjetaSeleccionada = t; }

    // --- Datos de demo para el catálogo ---
    private static void sembrarCatalogo() {
        Calendar cal = Calendar.getInstance();

        cal.set(2026, Calendar.AUGUST, 15);
        Concierto c1 = new Concierto("Bad Bunny - Most Wanted Tour", cal.getTime());
        c1.agregarZona(new Zona("VIP", 50, 350));
        c1.agregarZona(new Zona("Preferencial", 100, 250));
        c1.agregarZona(new Zona("General", 300, 120));
        Concierto.agregarAlCatalogo(c1);

        cal.set(2026, Calendar.SEPTEMBER, 20);
        Concierto c2 = new Concierto("Maluma - Papi Juancho Tour", cal.getTime());
        c2.agregarZona(new Zona("VIP", 60, 300));
        c2.agregarZona(new Zona("General", 250, 100));
        Concierto.agregarAlCatalogo(c2);

        cal.set(2026, Calendar.OCTOBER, 5);
        Concierto c3 = new Concierto("Karol G - Mañana Será Bonito", cal.getTime());
        c3.agregarZona(new Zona("Platinum", 40, 450));
        c3.agregarZona(new Zona("VIP", 80, 280));
        c3.agregarZona(new Zona("General", 400, 90));
        Concierto.agregarAlCatalogo(c3);
    }

    public static void main(String[] args) {
        Cliente.registrarCliente(new Cliente(0, "Admin", "", "admin", "admin123"));
        sembrarCatalogo();

        SwingUtilities.invokeLater(() -> {
            new ProyectoVenta_de_Entradas().setVisible(true);
        });
    }
}
