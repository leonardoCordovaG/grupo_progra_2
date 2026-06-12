
package com.mycompany.proyectoventa_de_entradas;

import modelo.Cliente;
import vista.VistaLogin;
import vista.VistaRegistro;
import javax.swing.*;
import java.awt.*;

public class ProyectoVenta_de_Entradas extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelPrincipal;

    public ProyectoVenta_de_Entradas() {
        setTitle("Sistema de Venta de Entradas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        // --- INTEGRANTE 1: Vistas de Acceso ---
        panelPrincipal.add(new VistaLogin(this), "Login");
        panelPrincipal.add(new VistaRegistro(this), "Registro");

        // --- Aquí tus compañeros irán agregando sus paneles ---
        // panelPrincipal.add(new frmBuscarZona(this), "BuscarZona");
        // panelPrincipal.add(new VistaHistorialCompras(this), "HistorialCompras");

        add(panelPrincipal);
        cardLayout.show(panelPrincipal, "Login");
    }

    // Este es el método clave que usarán todos para navegar
    public void cambiarVista(String nombreVista) {
        cardLayout.show(panelPrincipal, nombreVista);
    }

    public static void main(String[] args) {
        // Le pasamos: 0 puntos, "Admin" (nombres), "" (apellidos), "admin" (dni/usuario), "admin123" (clave)
        Cliente.registrarCliente(new Cliente(0, "Admin", "", "admin", "admin123"));
      
        SwingUtilities.invokeLater(() -> {
            new ProyectoVenta_de_Entradas().setVisible(true);
        });
    }
}