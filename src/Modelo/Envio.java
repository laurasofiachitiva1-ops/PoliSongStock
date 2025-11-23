package Modelo;

import java.util.Date;

public class Envio {
    private int id_envio;
    private int id_venta;
    private int id_vendedor;
    private int id_comprador;
    private Date fecha_envio;
    private String estado;
    private String valoracion;

    public Envio() {
    }

    public Envio(int id_envio, int id_venta, int id_vendedor, int id_comprador, Date fecha_envio, String estado, String valoracion) {
        this.id_envio = id_envio;
        this.id_venta = id_venta;
        this.id_vendedor = id_vendedor;
        this.id_comprador = id_comprador;
        this.fecha_envio = fecha_envio;
        this.estado = estado;
        this.valoracion = valoracion;
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

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public int getId_comprador() {
        return id_comprador;
    }

    public void setId_comprador(int id_comprador) {
        this.id_comprador = id_comprador;
    }

    public Date getFecha_envio() {
        return fecha_envio;
    }

    public void setFecha_envio(Date fecha_envio) {
        this.fecha_envio = fecha_envio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
    
}
