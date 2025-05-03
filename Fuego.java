public class Fuego extends Javaling {
    int enLlamas;
    public Fuego(String nombre, int hpBase, int velocidad, int hpTotal, int hpActual,
                int nivel, Tipo tipo, Movimiento[] movimiento, int ataque, int xp, int nextXp) {
        super(nombre, hpBase, velocidad, hpTotal, hpActual,nivel, tipo, movimiento,xp,nextXp);
        this.enLlamas = 0;
        this.tipo = Tipo.FUEGO;
        this.hpBase = 60;
        }
}