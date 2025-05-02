import java.util.List;
import java.util.ArrayList;

public abstract class Javaling {
    public String nombre;
    protected int hpBase;
    protected int velocidad;
    public int hpTotal;
    public int hpActual;
    public int nivel;
    public Tipo tipo;
    public Movimiento[] movimiento = new Movimiento[4];
    public int ataque;
    public int xp;
    public int nextXp;
    

    public Javaling(String nombre, int hpBase, int velocidad, int hpTotal, int hpActual,
                    int nivel, Tipo tipo, Movimiento[] movimiento, int ataque, int xp, int nextXp) {
        this.nombre = nombre;
        this.hpBase = hpBase;
        this.velocidad = velocidad;
        this.hpTotal = hpTotal;
        this.hpActual = hpActual;
        this.nivel = 1;
        this.tipo = tipo;
        this.movimiento = movimiento;
        this.ataque = ataque;
        this.xp = xp;
        this.nextXp = nextXp;
        // Inicializar el nivel y la experiencia
        if(nivel ==1){
            this.xp = 0;
            this.nextXp = 12;
        }
        else{
            int i = 1;
            while (i <= nivel) {
                this.subirNivel();
                i++;
            }
        }
    }
    public String getNombre() {
        return nombre;
    }
    public int getHpBase() {
        return hpBase;
    }
    public int getVelocidad() {
        return velocidad;
    }
    public int getHpTotal() {
        return hpTotal;
    }
    public int getHpActual() {
        return hpActual;
    }
    public int getAtaque() {
        return ataque;
    }
    public int getXp() {
        return xp;
    }
    public int getNextXp() {
        return nextXp;
    }
    public Movimiento[] getMovimiento() {
        return movimiento;
    }
    public Tipo getTipo(){
        return this.tipo;
     }
    public int getNivel(){
        return this.nivel;
    }
    public int gethpTotal() {
        return this.hpTotal;
    }
    public void setMovimiento(Javaling javaling, Movimiento movRemplazo, Movimiento movNuevo){
        /**
         * Cambia un movimiento por otro. Si el movimiento a reemplazar no existe, se
         * añade el nuevo movimiento al primer espacio vacío.
         * 
         * @param movRemplazo Movimiento a reemplazar.
         * @param movNuevo Movimiento nuevo.
         */
        if (movRemplazo != null) {
            for (int i = 0; i < javaling.getMovimiento().length; i++){
                if (javaling.getMovimiento()[i].getNombre() == movRemplazo.getNombre()){
                    javaling.getMovimiento()[i] = movNuevo;
                }else if(javaling.getMovimiento()[i] == null){
                    javaling.getMovimiento()[i] = movNuevo;
                }
            }
        }else{
            for (int i = 0; i < javaling.getMovimiento().length; i++){
                if (javaling.getMovimiento()[i] == null){
                    javaling.getMovimiento()[i] = movNuevo;
                }
            }
        }
    }
    public void setHpTotal(int hpTotal) {
        this.hpTotal = hpTotal;
    }
    public void setHpActual(int hpActual) {
        this.hpActual = hpActual;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public float getStab(int indiceMov){
        if (this.movimiento[indiceMov].getTipo() == this.getTipo()){
            return 1.5f;
        }else{
            return 1.0f;
        }
    }
    private int atacar(Javaling objetivo, int indiceMov){
        //float efectividad = Tipo.AGUA.getEficacia(this.movimiento[indiceMov],objetivo);
        float efectividad = this.movimiento[indiceMov].getTipo().getEficacia(objetivo.getTipo());
        
        float stab = this.getStab(indiceMov);
        int habilidad =1;
        int hb= this.gethpTotal();
        int potencia = this.movimiento[indiceMov].getPotencia();
        int n = this.getNivel();
        int dano = (int) ((((2*n/5+2)*potencia+(hb/100))/50+2)*stab*efectividad*habilidad); 
        return objetivo.recibirDano(dano);
    }
    public int recibirDano(int dano){
        this.hpActual -=dano;
        if (this.hpActual <=0){
            return 1; //MUERE
        }else{return 0;} //VIVE
    }
    public void recuperarSalud(int cantidad) {
            this.hpActual += cantidad;
            if (this.hpActual > this.hpTotal) {
                this.hpActual = this.hpTotal;
            }
        }
    public void aumentarXp(int nivel) { //nivel del Javaling que mata
        this.xp += 3*nivel + 10;
        while(this.xp >= this.nextXp){
            this.subirNivel();
        }           
    }
    public void subirNivel(){  //se elimina parametro nivel, ya que se emplea un sistema de experiencia
        this.nivel +=1;
        this.nextXp += (int) (Math.log10(Math.pow((this.nivel*3 + 10.0) / 8.0, 3)) * 10 * Math.pow((3*this.nivel+10)/8,0.5)); 
    }

    // public String getNombre() {
    //     return nombre;
    // }

    // public void recibirDano(int cantidad) {
    //     hpTotal -= cantidad;
    //     if (hpTotal < 0) hpTotal = 0;
    // }

    // public int atacar() {
    //     return ataque;
    // }

    // public boolean estaVivo() {
    //     return hpTotal > 0;
    // }
}
