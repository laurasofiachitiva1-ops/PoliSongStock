package Modelo;


public class Vendedor {
    private int id_vendedor;
    private String nombre;
    private String correo;
    private String direccion;
    private String password;

    public Vendedor() {
    }

    public Vendedor(int id_vendedor, String nombre, String correo, String direccion, String password) {
        this.id_vendedor = id_vendedor;
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.password = password;
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
