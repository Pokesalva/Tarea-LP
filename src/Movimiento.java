import java.util.Random;
import java.util.List;

public class Movimiento{
    private String nombre;
    private int potencia;
    private int precision;
    private Tipo tipo;
    private boolean esEstado;


    public Movimiento(String nombre, int potencia, int precision, Tipo tipo, boolean esEstado){
        this.nombre = nombre;
        this.potencia = potencia;
        this.precision = precision;
        this.tipo = tipo;
        this.esEstado = esEstado;
    }
    @Override
    public String toString() {
        return "Movimiento [nombre=" + nombre + ", tipo=" + tipo + "]";
    }
    public Tipo getTipo(){
        return this.tipo;
    }
    public int getPotencia(){
        return this.potencia;
    }
    public int getPrecision(){
        return this.precision;
    }
    public String getNombre(){
        return this.nombre;
    }
    public boolean esEstado(){
        return this.esEstado;
    }
}