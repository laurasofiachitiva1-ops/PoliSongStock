package Modelo;

public class Cancion_disco_mp3 {
    private int id_cancion;
    private int id_disco_mp3;

    public Cancion_disco_mp3() {
            
    }

    public Cancion_disco_mp3(int id_cancion, int id_disco_mp3) {
        this.id_cancion = id_cancion;
        this.id_disco_mp3 = id_disco_mp3;
    }

    public int getId_cancion() {
        return id_cancion;
    }
    public void setId_cancion(int id_cancion) {
        this.id_cancion = id_cancion;
    }
    public int getId_disco_mp3() {
        return id_disco_mp3;
    }
    public void setId_disco_mp3(int id_disco_mp3) {
        this.id_disco_mp3 = id_disco_mp3;
    }
    
}
