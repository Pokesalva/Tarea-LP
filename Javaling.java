public class Javaling {
    private String nombre;
    private int hpTotal;
    private int hpActual;
    private int nivel;
    private Tipo tipo;
    private Movimiento movimiento;
    private int ataque;
    

    public Javaling(String nombre, int hpTotal, int hpActual, int nivel, Tipo tipo, Movimiento movimiento, int ataque) {
        this.nombre = nombre;
        this.hpTotal = hpTotal;
        this.hpActual = hpActual;
        this.nivel = nivel;
        this.tipo = tipo;
        this.movimiento = movimiento;
        this.ataque = ataque;

    }

    // public String getNombre() {
    //     return nombre;
    // }
    // public String getTipo(){
    //     return tipo;
    // }

    // public int gethpTotal() {
    //     return hpTotal;
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
