package Modelo;

public class Envio {
    private int id_envio;
    private int id_venta;
    private String fecha_envio;
    private String estado_envio;
    private String codigo_seguimiento;

    public Envio() {

    }

    public Envio(int id_envio, int id_venta, String fecha_envio, String estado_envio, String codigo_seguimiento) {
        this.id_envio = id_envio;
        this.id_venta = id_venta;
        this.fecha_envio = fecha_envio;
        this.estado_envio = estado_envio;
        this.codigo_seguimiento = codigo_seguimiento;
    }

    public int getId_envio() {
        return id_envio;
    }
    public void setId_envio(int id_envio) {
        this.id_envio = id_envio;
    }
    public int getId_venta() {
        return id_venta;
    }
    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    public String getFecha_envio() {
        return fecha_envio;
    }
    public void setFecha_envio(String fecha_envio) {
        this.fecha_envio = fecha_envio;
    }
    public String getEstado_envio() {
        return estado_envio;
    }
    public void setEstado_envio(String estado_envio) {
        this.estado_envio = estado_envio;
    }
    public String getCodigo_seguimiento() {
        return codigo_seguimiento;
    }
    public void setCodigo_seguimiento(String codigo_seguimiento) {
        this.codigo_seguimiento = codigo_seguimiento;
    }
    
}
