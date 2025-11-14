package Modelo;

public class Lista_reproduccion {
    private int id_lista;
    private String nombre;
    private int id_usuario;
    private String publicacion;
    private String fecha_creacion;

    public Lista_reproduccion() {

    }

    public Lista_reproduccion(int id_lista, String nombre, int id_usuario, String publicacion, String fecha_creacion) {
        this.id_lista = id_lista;
        this.nombre = nombre;
        this.id_usuario = id_usuario;
        this.publicacion = publicacion;
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
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public String getPublicacion() {
        return publicacion;
    }
    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }
    public String getFecha_creacion() {
        return fecha_creacion;
    }
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    
}
