package Modelo;

public class Autor {
    private int id_autor;
    private String nombre;
    private String fecha_nacimiento; 
    private String nacionalidad; 

    public Autor(){

    }

    public Autor(int id_autor, String nombre, String fecha_nacimiento, String nacionalidad){
        this.id_autor = id_autor;
        this.nombre = nombre;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
    }

    public int getId_Autor() {
        return id_autor;
    }
    public void setId_Autor(int id_autor) {
        this.id_autor = id_autor;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }
    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    public String getNacionalidad() {
        return nacionalidad;
    }
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

}
