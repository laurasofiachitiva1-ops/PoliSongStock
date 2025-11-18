package Modelo;

public class Sesion {
    private static int idComprador;
    private static int idVendedor;

    public static int getIdComprador() {
        return idComprador;
    }

    public static void setIdComprador(int id) {
        idComprador = id;
    }

    public static int getIdVendedor() {
        return idVendedor;
    }

    public static void setIdVendedor(int id) {
        idVendedor = id;
    }

    public static void cerrarSesion() {
        idComprador = 0;
        idVendedor = 0;
    }
    
    
    
    
}
