public class Dragon extends Javaling {
    boolean multiescamas;
    public Dragon(String nombre, int hpBase, int velocidad, int hpTotal, int hpActual,
                int nivel, Tipo tipo, Movimiento[] movimiento, int ataque, int xp, int nextXp) {
        super(nombre, hpBase, velocidad, hpTotal, hpActual,nivel , tipo, movimiento,xp,nextXp);
        this.multiescamas = false;
        this.tipo = Tipo.DRAGON;
        this.hpBase = 70;
        }
}