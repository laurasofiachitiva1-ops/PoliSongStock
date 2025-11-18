package Modelo;

public class Disco_vinilo {

    private int id_disco_vinilo;
    private int id_autor;
    private int id_vendedor;
    private String nombre;
    private String genero;
    private int anio_salida;
    private double precio;
    private int cantidad;
    private byte[] imagen;
    private String autorNombre;

    public Disco_vinilo(int id_disco_vinilo, int id_autor, int id_vendedor, String nombre, String genero, int anio_salida, double precio, int cantidad, byte[] imagen) {
        this.id_disco_vinilo = id_disco_vinilo;
        this.id_autor = id_autor;
        this.id_vendedor = id_vendedor;
        this.nombre = nombre;
        this.genero = genero;
        this.anio_salida = anio_salida;
        this.precio = precio;
        this.cantidad = cantidad;
        this.imagen = imagen;
    }

    public int getId_disco_vinilo() {
        return id_disco_vinilo;
    }

    public void setId_disco_vinilo(int id_disco_vinilo) {
        this.id_disco_vinilo = id_disco_vinilo;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnio_salida() {
        return anio_salida;
    }

    public void setAnio_salida(int anio_salida) {
        this.anio_salida = anio_salida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getAutorNombre() {
        return autorNombre;
    }

    public void setAutorNombre(String autorNombre) {
        this.autorNombre = autorNombre;
    }

    public Disco_vinilo() {

    }

}
