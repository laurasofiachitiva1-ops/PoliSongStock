package Modelo;

import java.sql.Date;

public class Carrito {

    private int id_item;
    private int id_comprador;
    private int id_vendedor;
    private String tipo;  // "vinilo", "mp3", "cancion"
    private int id_producto;
    private int cantidad;
    private double precio_unitario;
    private Date fecha_agregado;
    private String estado;

    // Constructor vacío
    public Carrito() {
    }

    public Carrito(int id_item, int id_comprador, int id_vendedor, String tipo, int id_producto, int cantidad, double precio_unitario, Date fecha_agregado, String estado) {
        this.id_item = id_item;
        this.id_comprador = id_comprador;
        this.id_vendedor = id_vendedor;
        this.tipo = tipo;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.fecha_agregado = fecha_agregado;
        this.estado = estado;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getId_comprador() {
        return id_comprador;
    }

    public void setId_comprador(int id_comprador) {
        this.id_comprador = id_comprador;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Date getFecha_agregado() {
        return fecha_agregado;
    }

    public void setFecha_agregado(Date fecha_agregado) {
        this.fecha_agregado = fecha_agregado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

 
}
