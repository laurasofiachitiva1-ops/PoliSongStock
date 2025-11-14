package Modelo;

public class Historial_venta {
    private int id_historial_venta;
    private int id_venta;
    private int numero_ventas;
    private String fecha_hora;

    public Historial_venta() {

    }

    public Historial_venta(int id_historial_venta, int id_venta, int numero_ventas, String fecha_hora) {
        this.id_historial_venta = id_historial_venta;
        this.id_venta = id_venta;
        this.numero_ventas = numero_ventas;
        this.fecha_hora = fecha_hora;
    }

    public int getId_historial_venta() {
        return id_historial_venta;
    }
    public void setId_historial_venta(int id_historial_venta) {
        this.id_historial_venta = id_historial_venta;
    }
    public int getId_venta() {
        return id_venta;
    }
    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    public int getNumero_ventas() {
        return numero_ventas;
    }
    public void setNumero_ventas(int numero_ventas) {
        this.numero_ventas = numero_ventas;
    }
    public String getFecha_hora() {
        return fecha_hora;
    }
    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

}
