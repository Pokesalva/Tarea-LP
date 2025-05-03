public class Planta extends Javaling {
    int fotosintesis;
public Planta(String nombre, int hpBase, int velocidad, int hpTotal, int hpActual,
                int nivel, Tipo tipo, Movimiento[] movimiento, int ataque, int xp, int nextXp) {
        super(nombre, hpBase, velocidad, hpTotal, hpActual,nivel , tipo, movimiento, xp, nextXp);
        this.fotosintesis = 0;
        this.tipo = Tipo.PLANTA;
        this.hpBase = 65;
        }
}