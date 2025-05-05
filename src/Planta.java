public class Planta extends Javaling {
    int fotosintesis;
  public Planta(Javaling javaling) {
    super(javaling.getNombre(), javaling.getVelocidad(), javaling.getHpBase(), javaling.getHpTotal(),
     javaling.getHpActual(), javaling.getNivel(), javaling.getTipo(), javaling.getMovimiento());
    this.setNombre(javaling.getNombre());
    this.setVelocidad(javaling.getVelocidad());
    this.setHpTotal(javaling.getHpTotal());
    this.setHpActual(javaling.getHpActual());
    this.setNivel(javaling.getNivel());
    this.setMovimientoArray(javaling.getMovimiento());
    this.fotosintesis = 0;
    this.setHpActual(65);
    this.setTipo(Tipo.PLANTA);
  }
  public Planta(String nombre, int hpBase, int velocidad, int hpTotal, int hpActual, int nivel, Tipo tipo, Movimiento[] movimiento) {
    super(nombre,hpBase, velocidad, hpTotal, hpActual, nivel, tipo, movimiento);
    this.setTipo(Tipo.PLANTA);
    this.fotosintesis = 0;
    this.setHpBase(65);
  }
}