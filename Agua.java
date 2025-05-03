public class Agua extends Javaling {
    boolean oleaje;
    public Agua(String nombre, int hpBase, int velocidad, int hpTotal, int hpActual,
                int nivel, Tipo tipo, Movimiento[] movimiento, int ataque, int xp, int nextXp) {
        super(nombre, hpBase, velocidad, hpTotal, hpActual,nivel , tipo, movimiento,xp,nextXp);
        this.tipo = Tipo.AGUA;
        this.oleaje = false;
        this.hpBase = 55;
        }

}