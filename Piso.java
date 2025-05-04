public class Piso{
    private boolean centroSansanito;
    private int decision;
    public int piso;

    public Piso(int piso){
        this.piso = piso;
        this.decision = 0;
        this.centroSansanito = false;
    }
    public int getPiso(){
        return this.piso;
    }
    public void setPiso(int piso){
        this.piso = piso;
    }
    public boolean getCentroSansanito(){
        return this.centroSansanito;
    }



    // public curar(Jugador jugador){

    // }
    // public ejecutarDecision(Jugador jugador){
    // }

}