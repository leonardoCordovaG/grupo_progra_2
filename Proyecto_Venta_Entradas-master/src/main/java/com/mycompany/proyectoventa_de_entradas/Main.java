package com.mycompany.proyectoventa_de_entradas;

import controlador.ControladorLogin;
import data.PersistenciaArchivo;
import data.Sistema;
import modelo.Concierto;
import modelo.Zona;
import vista.VistaLogin;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {

        // Cargar datos de usuarios (demo) y luego lo persistido en disco, si existe.
        // El orden importa: inicializar() siembra admin/cliente1-4 primero, y si cargar()
        // encuentra un archivo previo reemplaza clientes/conciertos/ventas con lo guardado
        // (que ya incluye esos mismos clientes demo, con lo que hayan acumulado en sesiones
        // anteriores — tarjetas, compras, etc.). Si es la primera ejecución, cargar() no hace
        // nada y queda en pie lo recién sembrado.
        Sistema.inicializar();
        PersistenciaArchivo.cargar();

        Calendar cal = Calendar.getInstance();

        // Los conciertos de ejemplo solo se siembran si no se cargó nada de disco;
        // si no, se duplicarían en cada ejecución (ConciertoArreglo no filtra duplicados).
        if (Sistema.conciertos.tamanio() == 0) {
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
        }

        VistaLogin fLogin = new VistaLogin();
        
        ControladorLogin controlador = new ControladorLogin(fLogin);
        controlador.iniciar();
    }
}
