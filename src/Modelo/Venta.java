package Modelo;

import java.util.Date;

public class Venta {
    private int id_venta;
    private int id_comprador;
    private Date fecha;
    private Double total;  
    private String metodo_pago;
    private String estado;
    private String observacion;

    public Venta() {
    }

    public Venta(int id_venta, int id_comprador, Date fecha, Double total, String metodo_pago, String estado, String observacion) {
        this.id_venta = id_venta;
        this.id_comprador = id_comprador;
        this.fecha = fecha;
        this.total = total;
        this.metodo_pago = metodo_pago;
        this.estado = estado;
        this.observacion = observacion;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_comprador() {
        return id_comprador;
    }

    public void setId_comprador(int id_comprador) {
        this.id_comprador = id_comprador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    
}
