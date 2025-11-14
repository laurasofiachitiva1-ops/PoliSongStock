package Modelo;

public class Disco_mp3 {
    private int id_disco_mp3;
    private int id_autor;
    private String nombre;
    private String genero;
    private String anio_salida;
    private float precio;
    private String imagen;

    public Disco_mp3() {
    
    }

    public Disco_mp3(int id_disco_mp3, int id_autor, String nombre, String genero, String anio_salida, float precio, String imagen) {
        this.id_disco_mp3 = id_disco_mp3;
        this.id_autor = id_autor;
        this.nombre = nombre;
        this.genero = genero;
        this.anio_salida = anio_salida;
        this.precio = precio;
        this.imagen = imagen;
    }

    public int getId_disco_mp3() {
        return id_disco_mp3;
    }
    public void setId_disco_mp3(int id_disco_mp3) {
        this.id_disco_mp3 = id_disco_mp3;
    }
    public int getId_autor() {
        return id_autor;
    }
    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
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
    public String getAnio_salida() {
        return anio_salida;
    }
    public void setAnio_salida(String anio_salida) {
        this.anio_salida = anio_salida;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}
