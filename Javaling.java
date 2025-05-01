import java.util.List;
import java.util.ArrayList;

public class Javaling {
    private String nombre;
    private int hpTotal;
    private int hpActual;
    private int nivel;
    private Tipo tipo;
    private Movimiento[] movimiento = new Movimiento[4];
    private int ataque;
    private int xp;
    private int nextXp;
    

    public Javaling(String nombre, int hpTotal, int hpActual, int nivel, Tipo tipo, Movimiento movimiento, int ataque) {
        this.nombre = nombre;
        this.hpTotal = hpTotal;
        this.hpActual = hpActual;
        this.nivel = nivel;
        this.tipo = tipo;
        this.movimiento = movimiento;
        this.ataque = ataque;
        if(nivel ==1){
            this.xp = 0;
            this.nextXp = 12;
        }
        else{
            this.xp = 0;
            this.nextXp = 12;
        }
    }
    public float getStab(int indiceMov){
        if (this.movimiento[indiceMov].getTipo() == this.getTipo()){
            return 1.5f;
        }else{
            return 1.0f;
        }
    }
    public int atacar(Javaling objetivo, int indiceMov){
        float efectividad = getEficacia(this.movimiento[indiceMov],objetivo);
        float stab = this.getStab(indiceMov);
        int habilidad =1;
        int hb= this.gethpTotal;
        int potencia = this.movimiento[indiceMov].getPotencia();
        int n = this.getNivel();
        int dano = (int) ((((2*n/5+2)*potencia+(hb/100))/50+2)stab*efectividad*habilidad); 
        objetivo.recibirDano(dano)
    }

    public void recibirDano(int dano){
        this.hpActual -=dano;
        if (this.hpActual <=0){
            this.muereJavaling()
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
        this.nextXp += (int) (Math.log10(Math.pow((this.nivel*3 + 10.0) / 8.0, 3)) * 10 * math.pow((3*this.nivel+10)/8,0.5)); 
    }

    // public String getNombre() {
    //     return nombre;
    // }
    public Tipo getTipo(){
        return this.tipo;
     }
    public int getNivel(){
        return this.nivel;
    }
    public int gethpTotal() {
        return this.hpTotal;
    }

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
