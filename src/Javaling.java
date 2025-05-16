import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class Javaling {
    private String nombre;
    protected int hpBase;
    protected int velocidad;
    private int hpTotal;
    private int hpActual;
    private int nivel;
    private Tipo tipo;
    private Movimiento[] movimiento = new Movimiento[4];
    private Estado estado;
    private int turnosRestantes;
    private static Random random = new Random();
    private String color;
    
    public enum Estado{
        PARALIZADO,
        DORMIDO,
        QUEMADO,
        MODOFURIA
    }
    


    

    public Javaling(String nombre, int hpBase, int velocidad, int hpTotal, int hpActual,
                    int nivel, Tipo tipo, Movimiento[] movimiento) {
        this.nombre = nombre;
        this.hpBase = hpBase;
        this.velocidad = velocidad;
        this.hpTotal = hpTotal;
        this.hpActual = hpActual;
        this.nivel = nivel;
        this.tipo = tipo;
        this.movimiento = movimiento;
        this.estado = null; // Inicializar el estado como nulo
        this.turnosRestantes = 0; // Inicializar los turnos restantes como 0
        random = new Random(); // Inicializar el generador de números aleatorios
        
        // Inicializar el nivel y la experiencia
    }
    @Override
    public String toString() {
        return "Javaling [nombre=" + nombre + ", tipo=" + tipo  + "]";
    }
    public void printJavaling(){
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Movimientos: " + Arrays.toString(this.movimiento));
    }
    public void printMovimientoJavaling(){
        System.out.print("Movimientos: " );
        for(int i=0; i< 4;i++){
            if(this.movimiento[i]!=null){
                System.out.print(i+1 + ". "+ this.movimiento[i].getNombre() +" ");
            }
        }
    } 
    public String getMovimientosString(){
        String  movs ="";
        movs = movs + "Movimientos: " ;
        for(int i=0; i< 4;i++){
            if(this.movimiento[i]!=null){
                movs = movs +(i+1) + ". "+ this.movimiento[i].getNombre() +" ";
            }
        }
        movs = movs +"\n";
        return movs;
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
    public Estado getEstado() {
        return estado;
    }
    public String getColor(){
        return color;
    }
    public int getTurnosRestantes() {
        return turnosRestantes;
    }
    public void setMovimientoArray(Movimiento[] mov){
        /**
         * Cambia el array de movimientos por otro.
         * 
         * @param mov Array de movimientos.
         */
        this.movimiento = mov;
    }
    public void setMovimiento(Movimiento movRemplazo, Movimiento movNuevo){
        /**
         * Cambia un movimiento por otro. Si el movimiento a reemplazar no existe, se
         * añade el nuevo movimiento al primer espacio vacío.
         * 
         * @param movRemplazo Movimiento a reemplazar.
         * @param movNuevo Movimiento nuevo.
         */
        if (movRemplazo != null) {
            for (int i = 0; i < this.getMovimiento().length; i++){
                if (this.getMovimiento()[i].getNombre() == movRemplazo.getNombre()){
                    this.getMovimiento()[i] = movNuevo;
                    return;
                }
            }
        }else{
            for (int i = 0; i < this.getMovimiento().length; i++){
                if (this.getMovimiento()[i] == null){
                    this.getMovimiento()[i] = movNuevo;
                    return;
                }
            }
        }
    }
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
    public void setHpBase(int hpBase) {
        this.hpBase = hpBase;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setHpTotal(int hpTotal) {
        this.hpTotal = hpTotal;
    }
    public void setHpTotalNivel(int nivel) {
        this.hpTotal = 2*this.getHpBase()*nivel/100  + (nivel + 10);
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
    public void setColor(String color){
        this.color = color;
    }
    public float getStab(int indiceMov){
        if (this.movimiento[indiceMov].getTipo() == this.getTipo()){
            return 1.5f;
        }else{
            return 1.0f;
        }
    }
    public void setEstado(Estado estado){
        if (estado != null) {
            this.estado = estado;
            this.turnosRestantes = 2; // Establecer la duración del estado a 3 turnos
        }
        else{System.out.println("El Javaling ya tiene un Estado");}
    }
    public void setTurnosRestantes(int turnosRestantes) {
        this.turnosRestantes = turnosRestantes;
    }
    public void restarTurnoEstado() {
        if (this.estado != null) {
            this.turnosRestantes--;
            if (this.turnosRestantes <= 0) {
                this.turnosRestantes = 0;
                this.estado = null;
            }
        }
    }
    private int atacar(Javaling objetivo, int indiceMov){
        float efectividad = this.movimiento[indiceMov].getTipo().getEficacia(objetivo.getTipo());
        
        float stab = this.getStab(indiceMov);
        int habilidad =1;
        int hb= this.gethpTotal();
        int potencia = this.movimiento[indiceMov].getPotencia();
        int n = this.getNivel();
        int dano = (int) ((((2*n/5+2)*potencia+(hb/100))/50+2)*stab*efectividad*habilidad); 
        return dano;
    }
    public int atacarObjetivo(Javaling objetivo, int indiceMov){
        return atacar(objetivo, indiceMov);
    }
    public boolean tieneMovimiento(Movimiento mov){
        for (int i = 0; i < this.movimiento.length; i++){
            if (this.movimiento[i] == mov){
                return true;
            }
        }
        return false;
    }
    public void recuperarSalud(int cantidad) {
            this.hpActual += cantidad;
            if (this.hpActual > this.hpTotal) {
                this.hpActual = this.hpTotal;
            }
    }
    public void recuperarSaludCompleta() {
        this.hpActual = this.hpTotal;
    }

    public void subirNivel(){  //
        this.nivel +=1;
        this.setHpTotal(2*this.getHpBase()*this.nivel/100  + (this.nivel + 10));
        this.setHpActual(this.getHpTotal());
    }
    public String aplicarEfectoEstado() {
        if (this.estado != null) {
            // Aplicar el efecto del estado al Javaling
            switch (this.estado) {
                case PARALIZADO:
                    // Lógica para el estado paralizado
                    this.turnosRestantes--;
                    if (this.turnosRestantes <= 0) {
                        this.estado = null; // Eliminar el estado después de su duración
                    }
                    return this.nombre + " está paralizado, quizás no ataque atacar.";
                case DORMIDO:
                    // Lógica para el estado dormido
                    this.turnosRestantes--;
                    if (this.turnosRestantes <= 0) {
                        this.estado = null; // Eliminar el estado después de su duración
                    }
                    return this.nombre + " está dormido y no puede atacar.";
                case QUEMADO:
                    // Lógica para el estado quemado
                    System.out.println(this.nombre + " está quemado y pierde salud.");
                    this.hpActual -= 10; // Ejemplo de daño por quemadura
                    this.turnosRestantes--;
                    if (this.turnosRestantes <= 0) {
                        this.estado = null; // Eliminar el estado después de su duración
                    }
                    return this.nombre + " está quemado y pierde salud.";
                case MODOFURIA:
                    // Lógica para el modo furia
                    System.out.println(this.nombre + " está furioso y atacará con más potencia.");
                    this.turnosRestantes--;
                    if (this.turnosRestantes <= 0) {
                        this.estado = null; // Eliminar el estado después de su duración
                    }
                    return this.nombre + " está furioso y atacará con más potencia.";
            }
        }
        return "";
    }
}
