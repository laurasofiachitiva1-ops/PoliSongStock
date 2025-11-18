package Modelo;

public class Cancion_vinilo {
    
    private int id_cancionvinilo;
    private int id_cancion;
    private int id_disco_vinilo;

    public Cancion_vinilo() {
            
    }

    public Cancion_vinilo(int id_cancionvinilo, int id_cancion, int id_disco_vinilo) {
        this.id_cancionvinilo = id_cancionvinilo;
        this.id_cancion = id_cancion;
        this.id_disco_vinilo = id_disco_vinilo;
    }

    public int getId_cancionvinilo() {
        return id_cancionvinilo;
    }

    public void setId_cancionvinilo(int id_cancionvinilo) {
        this.id_cancionvinilo = id_cancionvinilo;
    }

    public int getId_cancion() {
        return id_cancion;
    }

    public void setId_cancion(int id_cancion) {
        this.id_cancion = id_cancion;
    }

    public int getId_disco_vinilo() {
        return id_disco_vinilo;
    }

    public void setId_disco_vinilo(int id_disco_vinilo) {
        this.id_disco_vinilo = id_disco_vinilo;
    }
    
}
