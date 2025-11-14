package Modelo;

public class Reporte_ventas {
    private int id_reporte;
    private String fecha_inicio;
    private String fecha_fin;
    private int total_ventas;
    private double total_ingresos;
    private String estado_resporte;
    private int venta_id_venta;

    public Reporte_ventas() {

    }
    public Reporte_ventas(int id_reporte, String fecha_inicio, String fecha_fin, int total_ventas, double total_ingresos, String estado_resporte, int venta_id_venta) {
        this.id_reporte = id_reporte;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.total_ventas = total_ventas;
        this.total_ingresos = total_ingresos;
        this.estado_resporte = estado_resporte;
        this.venta_id_venta = venta_id_venta;
    }

    public int getId_reporte() {
        return id_reporte;
    }
    public void setId_reporte(int id_reporte) {
        this.id_reporte = id_reporte;
    }
    public String getFecha_inicio() {
        return fecha_inicio;
    }
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public String getFecha_fin() {
        return fecha_fin;
    }
    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    public int getTotal_ventas() {
        return total_ventas;
    }
    public void setTotal_ventas(int total_ventas) {
        this.total_ventas = total_ventas;
    }
    public double getTotal_ingresos() {
        return total_ingresos;
    }
    public void setTotal_ingresos(double total_ingresos) {
        this.total_ingresos = total_ingresos;
    }
    public String getEstado_resporte() {
        return estado_resporte;
    }
    public void setEstado_resporte(String estado_resporte) {
        this.estado_resporte = estado_resporte;
    }
    public int getVenta_id_venta() {
        return venta_id_venta;
    }
    public void setVenta_id_venta(int venta_id_venta) {
        this.venta_id_venta = venta_id_venta;
    }

}
