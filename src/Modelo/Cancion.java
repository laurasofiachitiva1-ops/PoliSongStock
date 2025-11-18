package Modelo;

public class Cancion {
    private int id_cancion;
    private int id_autor;
    private int id_vendedor;
    private String nombre;
    private String genero;
    private String duracion;
    private double tamano_mb;
    private int calidad_kbps;
    private double precio;
    private String autorNombre;

    public Cancion() {
    }

    public Cancion(int id_cancion, int id_autor, int id_vendedor, String nombre, String genero, String duracion, double tamano_mb, int calidad_kbps, double precio) {
        this.id_cancion = id_cancion;
        this.id_autor = id_autor;
        this.id_vendedor = id_vendedor;
        this.nombre = nombre;
        this.genero = genero;
        this.duracion = duracion;
        this.tamano_mb = tamano_mb;
        this.calidad_kbps = calidad_kbps;
        this.precio = precio;
    }

    public int getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(int id_cancion) {
        this.id_cancion = id_cancion;
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

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public double getTamano_mb() {
        return tamano_mb;
    }

    public void setTamano_mb(double tamano_mb) {
        this.tamano_mb = tamano_mb;
    }

    public int getCalidad_kbps() {
        return calidad_kbps;
    }

    public void setCalidad_kbps(int calidad_kbps) {
        this.calidad_kbps = calidad_kbps;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public String getAutorNombre() {
        return autorNombre;
    }

    public void setAutorNombre(String autorNombre) {
        this.autorNombre = autorNombre;
    }

}
