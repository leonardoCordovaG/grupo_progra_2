package data;

import modelo.ClienteArreglo;
import modelo.ConciertoArreglo;
import modelo.VentaArreglo;
import modelo.Cliente;
import modelo.Concierto;
import modelo.Zona;
import modelo.Tarjeta;

// Repositorio global de datos en memoria.
// Centraliza todas las colecciones compartidas por la aplicación.
public class Sistema {

    public static ClienteArreglo   clientes   = new ClienteArreglo(50);
    public static ConciertoArreglo conciertos = new ConciertoArreglo(20);
    public static VentaArreglo     ventas     = new VentaArreglo(200);

    // Info de la sesión del usuario activo
    public static Cliente  clienteActual         = null;
    public static Concierto conciertoSeleccionado = null;
    public static Zona     zonaSeleccionada       = null;
    public static int      cantidadEntradas       = 0;
    public static Tarjeta  tarjetaSeleccionada    = null;
}
