package Modelo;

public class Venta {
    private int id_venta;
    private int id_usuario;
    private int id_disco_vinillo;
    private int id_disco_mp3;
    private int cantidad;
    private String fecha;
    private String estado;
    private String observacion;
    private String metodo_pago;
    private String fecha_envio;
    private String fecha_recepcion;
    private String valoracion; 

    public Venta() {

    }

    public Venta(int id_venta, int id_usuario, int id_disco_vinillo, int id_disco_mp3, int cantidad, String fecha, String estado, String observacion, String metodo_pago, String fecha_envio, String fecha_recepcion, String valoracion) {
        this.id_venta = id_venta;
        this.id_usuario = id_usuario;
        this.id_disco_vinillo = id_disco_vinillo;
        this.id_disco_mp3 = id_disco_mp3;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.estado = estado;
        this.observacion = observacion;
        this.metodo_pago = metodo_pago;
        this.fecha_envio = fecha_envio;
        this.fecha_recepcion = fecha_recepcion;
        this.valoracion = valoracion;
    }

    public int getId_venta() {
        return id_venta;
    }
    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public int getId_disco_vinillo() {
        return id_disco_vinillo;
    }
    public void setId_disco_vinillo(int id_disco_vinillo) {
        this.id_disco_vinillo = id_disco_vinillo;
    }
    public int getId_disco_mp3() {
        return id_disco_mp3;
    }
    public void setId_disco_mp3(int id_disco_mp3) {
        this.id_disco_mp3 = id_disco_mp3;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
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
    public String getMetodo_pago() {
        return metodo_pago;
    }
    public void setMetodo_pago(String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }
    public String getFecha_envio() {
        return fecha_envio;
    }
    public void setFecha_envio(String fecha_envio) {
        this.fecha_envio = fecha_envio;
    }
    public String getFecha_recepcion() {
        return fecha_recepcion;
    }
    public void setFecha_recepcion(String fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }
    public String getValoracion() {
        return valoracion;
    }
    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
    
}
