import java.util.Scanner;
import java.util.Random;

public class Piso{
    private boolean centroSansanito;
    private int decision;
    public int piso;
    static DataManager dataManager = new DataManager();

    public Piso(int piso){
        this.piso = piso;
        this.decision = 0;
        this.centroSansanito = false;
    }
    public int getPiso(){
        return this.piso;
    }
    public int getNivelPiso(){
        Random random = new Random();
        int nivelBase = (int) Math.floor(1.3 * piso);
        int nivelPiso = nivelBase + random.nextInt(7) - 3;
        if (piso <= 5) {
            nivelPiso = 4 + random.nextInt(3);
        }return nivelPiso;
    }
    public void subirPiso(){
        this.piso++;
    }
    public void setPiso(int piso){
        this.piso = piso;
    }
    public boolean getCentroSansanito(){
        return this.centroSansanito;
    }
    public boolean  multiploDiez(){
        if(this.piso % 10 == 0){
            return true;
        }
        return false;
    }
    public void setCentroSansanito(boolean centroSansanito){
        this.centroSansanito = centroSansanito;
    }
    public int getDecision(){
        return this.decision;
    }
    public void setDecision(int decision){
        this.decision = decision;
    }
    public void curar(Jugador jugador){
        for (Javaling j : jugador.getEquipo()){
            j.recuperarSaludCompleta();
        }
    }
    public void ejecutarDecision(Jugador jugador){
        Scanner scanner = new Scanner(System.in);
        // System.out.println("¿Que quieres hacer?");
        // System.out.println("(1) Batalla (2) Captura (3) Item Random");
        // this.decision = scanner.nextInt();
        if (this.decision == 1){
            //batalla(jugador);
        }
        

        else if (this.decision == 2){ //captura
            Javaling javaling = dataManager.getJavalingAleatorioSalvaje(jugador.getPisoActual().getNivelPiso());
            System.out.println("Has encontrado un " + javaling.getNombre());
            System.out.println("¿Quieres capturarlo? (1) Si (2) No");

            int choice = scanner.nextInt();
            if (choice == 1){ //DECISION CAPTURAR
                Random random = new Random();
                double probabilidad = random.nextDouble();
                if (probabilidad < 0.4){
                    jugador.agregarJavaling(javaling);
                    System.out.println("Has capturado a " + javaling.getNombre());
                    this.subirPiso();
                }else{
                    System.out.println("Has fallado la captura");
                    Random random1 = new Random();
                    double probabilidad1 = random1.nextDouble();
                    System.out.println("Quieres intentar capturarlo de nuevo? (1) Si (2) No");
                    int choice1 = scanner.nextInt();
                    if (choice1 == 1){
                        if (probabilidad1 < 0.4){
                            jugador.agregarJavaling(javaling);
                            System.out.println("Has capturado a " + javaling.getNombre());
                            this.subirPiso();
                        }else{
                            System.out.println("Has fallado la captura");
                        }
                    }else{
                        System.out.println("El javaling se ha escapado");
                    }
                }
            }else if (choice == 2){
                System.out.println("Has decidido no capturarlo");
            }
        }
        else if (this.decision == 3){ //item random
            Objeto objetoAleatorio = dataManager.getItemAleatorio();
            jugador.agregarObjeto(objetoAleatorio);
        };
        scanner.close();
    }
}