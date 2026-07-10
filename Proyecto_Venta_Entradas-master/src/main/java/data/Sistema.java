package data;

import modelo.ClienteArreglo;
import modelo.ConciertoArreglo;
import modelo.VentaArreglo;
import modelo.Cliente;
import modelo.Concierto;
import modelo.Zona;
import modelo.Tarjeta;
import modelo.TipoTarjeta;

// Repositorio global de datos en memoria.
// Centraliza todas las colecciones compartidas por la aplicación.
public class Sistema {

    public static ClienteArreglo clientes = new ClienteArreglo(50);
    public static ConciertoArreglo conciertos = new ConciertoArreglo(20);
    public static VentaArreglo ventas = new VentaArreglo(200);

    // Usuario administrador del sistema
    public static Cliente admin = new Cliente(0, "Admin", "", "admin", "admin123");

    public static Cliente cliente1 = new Cliente(0, "Juan", "Pérez", "12345678", "1234");
    public static Cliente cliente2 = new Cliente(0, "María", "García", "87654321", "abcd");
    public static Cliente cliente3 = new Cliente(0,"Kepler","García","7057","123");
    public static Cliente cliente4 = new Cliente(0,"Manuel","Santa Cruz","112233","1234");

    // Catálogo de marcas de tarjeta soportadas (nombre, dígitos del número, dígitos del CVV, % de descuento)
    // TODO: cuando se migre a SQLite, esto pasa a ser una tabla propia en vez de constantes en memoria.
    public static TipoTarjeta VISA             = new TipoTarjeta("VISA", 16, 3, 5);
    public static TipoTarjeta MASTERCARD       = new TipoTarjeta("MASTERCARD", 16, 3, 10);
    public static TipoTarjeta DINERS           = new TipoTarjeta("DINERS CLUB", 14, 3, 15);
    public static TipoTarjeta AMERICAN_EXPRESS = new TipoTarjeta("AMERICAN EXPRESS", 15, 4, 7);

    public static TipoTarjeta[] tiposTarjeta = { VISA, MASTERCARD, DINERS, AMERICAN_EXPRESS };

    public static void inicializar() {
        clientes.agregar(admin);

        clientes.agregar(cliente1);
        clientes.agregar(cliente2);
        clientes.agregar(cliente3);
        clientes.agregar(cliente4);
    }

    // Info de la sesión del usuario activo
    public static Cliente clienteActual = null;
    public static Concierto conciertoSeleccionado = null;
    public static Zona zonaSeleccionada = null;
    public static int cantidadEntradas = 0;
    public static Tarjeta tarjetaSeleccionada = null;
}
