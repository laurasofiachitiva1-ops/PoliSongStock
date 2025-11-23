package Modelo;


public class Detalle_venta {
    private int id_detalle;
    private int id_venta;
    private int id_vendedor;
    private int id_producto;
    private String tipo;
    private int cantidad;
    private double precio_unit;
    private double total;

    public Detalle_venta() {
    }

    public Detalle_venta(int id_detalle, int id_venta, int id_vendedor, int id_producto, String tipo, int cantidad, double precio_unit, double total) {
        this.id_detalle = id_detalle;
        this.id_venta = id_venta;
        this.id_vendedor = id_vendedor;
        this.id_producto = id_producto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.precio_unit = precio_unit;
        this.total = total;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unit() {
        return precio_unit;
    }

    public void setPrecio_unit(double precio_unit) {
        this.precio_unit = precio_unit;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    

}
