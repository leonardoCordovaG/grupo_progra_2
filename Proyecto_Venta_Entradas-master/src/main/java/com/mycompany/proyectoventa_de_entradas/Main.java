package com.mycompany.proyectoventa_de_entradas;

import controlador.ControladorLogin;
import data.Sistema;
import modelo.Concierto;
import modelo.Zona;
import vista.VistaLogin;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {

        // Cargar datos de usuarios
        Sistema.inicializar();

        Calendar cal = Calendar.getInstance();

        cal.set(2026, Calendar.AUGUST, 15);
        Concierto c1 = new Concierto("Bad Bunny - Most Wanted Tour", cal.getTime());
        c1.agregarZona(new Zona("VIP",          50,  350));
        c1.agregarZona(new Zona("Preferencial", 100, 250));
        c1.agregarZona(new Zona("General",      300, 120));
        Sistema.conciertos.agregar(c1);

        cal.set(2026, Calendar.SEPTEMBER, 20);
        Concierto c2 = new Concierto("Maluma - Papi Juancho Tour", cal.getTime());
        c2.agregarZona(new Zona("VIP",     60,  300));
        c2.agregarZona(new Zona("General", 250, 100));
        Sistema.conciertos.agregar(c2);

        cal.set(2026, Calendar.OCTOBER, 5);
        Concierto c3 = new Concierto("Karol G - Mañana Será Bonito", cal.getTime());
        c3.agregarZona(new Zona("Platinum", 40,  450));
        c3.agregarZona(new Zona("VIP",      80,  280));
        c3.agregarZona(new Zona("General",  400,  90));
        Sistema.conciertos.agregar(c3);

        VistaLogin fLogin = new VistaLogin();
        
        ControladorLogin controlador = new ControladorLogin(fLogin);
        controlador.iniciar();
    }
}
