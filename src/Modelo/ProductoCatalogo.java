
package Modelo;


public class ProductoCatalogo {
    private int id;
    private String tipo; 
    private String nombre;
    private String artista;
    private String genero;
    private double precio;
    private byte[] imagen;

    public ProductoCatalogo() {
    }

    public ProductoCatalogo(int id, String tipo, String nombre, String artista, String genero, double precio, byte[] imagen) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.artista = artista;
        this.genero = genero;
        this.precio = precio;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    
    
}


