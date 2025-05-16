public class Dragon extends Javaling {
    boolean multiescamas;;
    public Dragon(Javaling javaling) {
        super(javaling.getNombre(), javaling.getVelocidad(), javaling.getHpBase(), javaling.getHpTotal(),
     javaling.getHpActual(), javaling.getNivel(), javaling.getTipo(), javaling.getMovimiento());
        this.setNombre(javaling.getNombre());
        this.setVelocidad(javaling.getVelocidad());
        this.setHpTotal(javaling.getHpTotal());
        this.setHpActual(javaling.getHpActual());
        this.setNivel(javaling.getNivel());
        this.setMovimientoArray(javaling.getMovimiento());
        this.multiescamas = false;
        this.setHpBase(70);
        this.setTipo(Tipo.DRAGON);
        this.setColor("morado");
        }
    public Dragon(String nombre, int hpBase, int velocidad, int hpTotal, int hpActual, int nivel, Tipo tipo, Movimiento[] movimiento) {
        super(nombre,hpBase, velocidad, hpTotal, hpActual, nivel, tipo, movimiento);
        this.setTipo(Tipo.DRAGON);
        this.multiescamas = false;
        this.setHpBase(70);
        this.setColor("morado");
    }
}