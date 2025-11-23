package Modelo;

public class Lista_reproduccion {

    private int id_lista;
    private String nombre;
    private int id_comprador;
    private String publica;
    private String fecha_creacion;
    private String nombre_creador;

    public Lista_reproduccion() {

    }
    
    public boolean isPublica() {
        return "Sí".equalsIgnoreCase(this.publica);
    }

    public Lista_reproduccion(int id_lista, String nombre, int id_comprador, String publica, String fecha_creacion) {
        this.id_lista = id_lista;
        this.nombre = nombre;
        this.id_comprador = id_comprador;
        this.publica = publica;
        this.fecha_creacion = fecha_creacion;
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_comprador() {
        return id_comprador;
    }

    public void setId_comprador(int id_comprador) {
        this.id_comprador = id_comprador;
    }

    public String getPublica() {
        return publica;
    }

    public void setPublica(String publica) {
        this.publica = publica;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getNombre_creador() {
        return nombre_creador;
    }

    public void setNombre_creador(String nombre_creador) {
        this.nombre_creador = nombre_creador;
    }
    

}
