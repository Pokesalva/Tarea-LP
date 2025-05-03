import java.util.List;
import java.util.ArrayList;

public class Entrenador{
    private boolean esCampeon;
    private Javaling[] equipo = new Javaling[6];

    public static List<Javaling> generarEquipoAleatorio(int piso) {
        List<Javaling> equipo = new Javaling[6];

        // calcular el nivel base
        int nivelBase = (int) Math.floor(1.3 * piso);

        // calcular el nivel de los Javaling
        int nivelEntrenador = nivelBase + random.nextInt(7) - 3;
        if (piso <= 5) {
            nivelMin = 4;
            nivelMax = 6;
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

        // generar los Javaling
        int cantidad = random.nextInt(2) + cantidadMin;
        for (int i = 0; i < cantidad; i++) {
            Javaling javaling = DataManager().getJavalingAleatorio();
            javaling.setNivel(nivelEntrenador);

            equipo.add(javaling);
        }

        return equipo;
    }

}