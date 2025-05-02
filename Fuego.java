public class Fuego extends Javaling {
    int enLlamas;
    public Fuego(String nombre, int hpBase, int velocidad, int hpTotal, int hpActual,
                int nivel, Tipo tipo, Movimiento[] movimiento, int ataque, int xp, int nextXp) {
        super(nombre, hpBase, velocidad, hpTotal, hpActual,nivel, Tipo.FUEGO, movimiento,ataque,xp,nextXp);
        }
}