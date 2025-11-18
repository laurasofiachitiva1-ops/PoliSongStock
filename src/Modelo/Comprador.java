package Modelo;

public class Comprador {
    private int id_comprador;
    private String nombre;
    private String correo;
    private String direccion;
    private String password;

    public Comprador() {
    }

    public Comprador(int id_comprador, String nombre, String correo, String direccion, String password) {
        this.id_comprador = id_comprador;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.password = password;
    }

    public int getId_comprador() {
        return id_comprador;
    }

    public void setId_comprador(int id_comprador) {
        this.id_comprador = id_comprador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
