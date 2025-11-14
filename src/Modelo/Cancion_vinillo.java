package Modelo;

public class Cancion_vinillo {
    private int id_cancion;
    private int id_disco_vinillo;

    public Cancion_vinillo() {
            
    }
    public Cancion_vinillo(int id_cancion, int id_disco_vinillo) {
        this.id_cancion = id_cancion;
        this.id_disco_vinillo = id_disco_vinillo;
    }

    public int getId_cancion() {
        return id_cancion;
    }
    public void setId_cancion(int id_cancion) {
        this.id_cancion = id_cancion;
    }
    public int getId_disco_vinillo() {
        return id_disco_vinillo;
    }
    public void setId_disco_vinillo(int id_disco_vinillo) {
        this.id_disco_vinillo = id_disco_vinillo;
    }
    
}
