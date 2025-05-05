import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Entrenador{
    public String nombre="Entrenador";
    private boolean esCampeon;
    private Javaling[] equipo = new Javaling[6];


    public Entrenador(boolean esCampeon, Piso piso, DataManager dataManager) {
        this.esCampeon = esCampeon;
        if (esCampeon) {
            this.equipo = this.generarEquipoAleatorio(piso.getPiso(), dataManager);
        } else {
            this.equipo = this.generarEquipoAleatorio(piso.getPiso(), dataManager);
        }
    }
    public String getNombre() {
        return nombre;
    }
    public boolean esCampeon() {
        return esCampeon;
    }
    public Javaling[] getEquipo() {
        return equipo;
    }
    public Javaling[] generarEquipoAleatorio(int piso, DataManager dataManager) {
        /**
         * Genera un equipo aleatorio de Javaling.
         * 
         * @param piso Piso del entrenador.
         * @return Array de Javaling.
         */
        Javaling[] equipo = new Javaling[6];
        // calcular el nivel base
        int nivelBase = (int) Math.floor(1.3 * piso);
        Random random = new Random();
        int nivelEntrenador = nivelBase + random.nextInt(7) - 3;
        if (piso <= 5) {
            nivelEntrenador = 4 + random.nextInt(3);
        }
        // calcular la cantidad de Javaling
        int cantidadMin = 1;
        int cantidadMax = 1;
        if (piso >= 1 && piso <= 20) {
            cantidadMax = 2;
        } else if (piso >= 21 && piso <= 30) {
            cantidadMin = 2;
            cantidadMax = 3;
        } else if (piso >= 31 && piso <= 40) {
            cantidadMin = 3;
            cantidadMax = 4;
        } else if (piso >= 41 && piso <= 50) {
            cantidadMin = 4;
            cantidadMax = 5;
        } else {
            cantidadMin = 5;
            cantidadMax = 6;
        }
        if (this.esCampeon) {
            cantidadMax = 6;
        }
        // generar los Javaling
        // int cantidad = random.nextInt(cantidadMax - cantidadMin + 1) + cantidadMin;
        // for (int i = 0; i < cantidad; i++) {
        //     Javaling javaling = dataManager.getJavalingAleatorioEntrenador(nivelEntrenador);
        //     this.equipo[i] = javaling;
        // }
        // if (this.esCampeon) {
        //     Javaling javaling = dataManager.getJavalingAleatorioTipo(nivelEntrenador,Tipo.DRAGON);
        //     this.equipo[0] = javaling;
        //     for(Javaling j : equipo){
        //         j.setMovimiento(null, dataManager.getMovimientoAleatorioTipo(j.getTipo()));
        //     }
        // }
        int cantidad = random.nextInt(cantidadMax - cantidadMin + 1) + cantidadMin;
        for (int i = 0; i < cantidad; i++) {
            Javaling javaling = dataManager.getJavalingAleatorioEntrenador(nivelEntrenador);
            if (javaling != null) {
                this.equipo[i] = javaling;
            } else {
                System.out.println("No se pudo generar un Javaling");
            }
        }
        if (this.esCampeon) {
            Javaling javaling = dataManager.getJavalingAleatorioTipo(nivelEntrenador, Tipo.DRAGON);
            if (javaling != null) {
                this.equipo[0] = javaling;
            } else {
                System.out.println("No se pudo generar un Javaling de tipo DRAGON");
            }
        }
        for (Javaling j : equipo) {
            if (j != null) {
                j.setMovimiento(null, dataManager.getMovimientoAleatorioTipo(j.getTipo()));
            } else {
                System.out.println("Javaling es null, no se puede asignar movimiento");
            }
        }
        return equipo;
    }
}