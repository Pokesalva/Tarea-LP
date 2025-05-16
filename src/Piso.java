import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;

public class Piso{
    private boolean centroSansanito;
    private int decision;
    public int piso;
    static DataManager dataManager = new DataManager();
    private static Random random = new Random();

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
        int nivelBase;
        int nivelPiso;
        if (piso < 6) {
            nivelPiso = 4 + random.nextInt(3);
        }else{
            nivelBase = (int) Math.floor(1.3 * piso);
            nivelPiso = nivelBase + random.nextInt(7) - 3;
        }
        return nivelPiso;
    }
    public void subirPiso(){
        this.piso++;
        if (this.piso % 10 == 0) {
            this.centroSansanito =true;
        }
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
    public void ejecutarDecision(Jugador jugador,Scanner scanner){;
        if(getCentroSansanito()){
            Main.print("Ólvidate de eso, bienvenido al SANSANITO\n", "aqua claro");
            Main.print("Curaremos tus Javaling y recibirás dos item\n", "aqua claro");
            Main.print("Buscando objetos polvorientos ***************************", 80, "aqua claro", true, "Listo!");

            Objeto objetoAleatorio2 = dataManager.getItemAleatorio();
            jugador.agregarObjeto(objetoAleatorio2);          
            Main.print("Has obtenido: ", "aqua claro", 15);
            Main.print(objetoAleatorio2.getNombre()+ "\n", "rojo claro", 15);

            Objeto objetoAleatorio1 = dataManager.getItemAleatorio();
            jugador.agregarObjeto(objetoAleatorio1);          
            Main.print("Has obtenido: ", "aqua claro", 15);
            Main.print(objetoAleatorio1.getNombre()+ "\n", "rojo claro", 15);
            
            for(int i =0; i <6; i++){
                if(jugador.getEquipo()[i]!=null){
                    jugador.getEquipo()[i].recuperarSaludCompleta();
                }
            }
            Main.print("Cuidando Javaling roñosos *****************************", 80, "aqua claro", true, "Listo!");
            subirPiso();
            return;
        }
        // System.out.println("¿Que quieres hacer?");
        // System.out.println("(1) Batalla (2) Captura (3) Item Random");
        // this.decision = scanner.nextInt();
        if (this.decision == 1){
            boolean vivos = false;
            for (int i = 0; i < jugador.getEquipoTamaño(); i++) {
                if (jugador.getEquipo()[i]!=null){
                    if ( jugador.getEquipo()[i].getHpActual() > 0) {
                        vivos = true;
                        break;
                    }
                }
            }
            if(vivos==false){
                Main.print("No te quedan Javaling vivos\nPierdes por tontito\n", "rojo");
                Main.imprimirASCII("gameover", "rojo");
                System.exit(0);
            }
            int probCampeon= 0;
            if(this.piso >=30){
                probCampeon = this.piso -30 +5;
            }
            Entrenador entrenador;
            if(random.nextDouble()<=probCampeon/100){
                entrenador = new Entrenador(true, this, dataManager);
                Main.print("¡¡¡¡HA APARECIDO EL CAMPEÓN!!!!\nSi le ganas eres el mejor de Chile\n", "rojo");
            }
            else{
                entrenador = new Entrenador(false, this, dataManager);
            }
            




            Main.print(">>>Cargando Batalla. No cierres la ventana SWING *********************************************",25,"cyan",true,"Batalla iniciada. NO CIERRES LA VENTANA\n" );
            BatallaGUI batalla = new BatallaGUI(jugador, entrenador);
            batalla.mostrar();
            batalla.esperarFin();
            if(batalla.getVictoria()){
                if(entrenador.esCampeon()){
                    Main.print("LE GANASTE AL MEJOR DE CHILEEEEEEEE\n\n", "aqua claro");
                    System.err.print("\u001b[5m");
                    Main.imprimirASCII("victoria", "aqua claro");
                }
                this.subirPiso();
            }
            batalla=null;
            Main.print(">>>Cargando****************************************",35,"cyan",true,"Fase de Batalla completada\n" );
            return;
        }
        

        else if (this.decision == 2){ //captura
            System.out.println("nivel piso:"+ this.getNivelPiso());
            Javaling javaling = dataManager.getJavalingAleatorioSalvaje(this.getNivelPiso());
            
            Main.imprimirASCII(javaling.getNombre(),javaling.getColor());
            Main.print("Has encontrado un " + javaling.getNombre(), "morado", javaling.getColor(),50);
            Main.print("Nivel " + javaling.getNivel()+ "\n", javaling.getColor());
            Main.print(javaling.getMovimientosString(),javaling.getColor(),10);
            System.out.println("¿Quieres capturarlo? (1) Si (2) No");

            int choice = scanner.nextInt();
            if (choice == 1){ //DECISION CAPTURAR
                double probabilidad = random.nextDouble();
                if (probabilidad < 0.4){
                    jugador.agregarJavaling(javaling,scanner);
                    Main.print(" Has capturado a " + javaling.getNombre()+ "\n",20,"morado", false, null);
                    Main.print("Terminando fase de captura ********************************", 40,"cyan",true,"Fase Terminada...");
                    this.subirPiso();
                    return;
                }else{
                    Main.print(" Has fallado la captura\n","rojo"); 
                    System.out.println("Quieres intentar capturarlo de nuevo? (1) Si (2) No");
                    int choice1 = scanner.nextInt();
                    if (choice1 == 1){
                        boolean continuar= true;
                        probabilidad = random.nextDouble();
                        while(continuar){
                            System.out.println(">>> (ciclo) Intentando capturar a " + javaling.getNombre());
                            
                            if (probabilidad < 0.4){
                                jugador.agregarJavaling(javaling,scanner);
                                Main.print(" Has capturado a " + javaling.getNombre()+ "\n",20,"morado", false, null);
                                Main.print("Terminando fase de captura ********************************", 40,"cyan",true,"Fase Terminada...\n");
                                this.subirPiso();
                                continuar = false;
                                return;
                            }else{
                                Main.print(" Has fallado la captura\n","rojo");
                                System.out.println("Quieres intentar capturarlo de nuevo? (1) Si (2) No");
                                choice1 = scanner.nextInt();
                                if (choice1 != 1){
                                    continuar = false;
                                    return;
                                }
                                probabilidad= random.nextDouble();
                                if (probabilidad < 0.6){
                                    Main.print(" Has fallado la captura\n","rojo");
                                    Main.print("Se ha escapado...\n","rojo");
                                    Main.print("Terminando fase de captura ********************************", 40,"cyan",true,"Fase Terminada...");
                                    return;
                                }
                            }probabilidad = random.nextDouble();
                        }
                    }else{
                        System.out.println(" El javaling se ha escapado. No lo has capturado");
                        Main.print("Terminando fase de captura ********************************", 40,"cyan",true,"Fase Terminada...");
                        return;
                    }
                }
            }else if (choice == 2){
                System.out.println(" Has decidido no capturarlo.");
                Main.print("Se ha escapado...\n","rojo");
                Main.print("Terminando fase de captura ********************************", 40,"cyan",true,"Fase Terminada...");
                return;
            }
        }
        else if (this.decision == 3){ //item random
            Objeto objetoAleatorio = dataManager.getItemAleatorio();
            jugador.agregarObjeto(objetoAleatorio);          
            Main.print("Has obtenido: ", "aqua claro", 15);
            Main.print(objetoAleatorio.getNombre()+ "\n", "rojo claro", 15);
            Main.print(jugador.getBolsaString(),"purpura claro", 15 );
            Main.print("\nQuieres usar un item? (1) SI   (2) NO\n" , "aqua claro");
            for (int i = 0; i < 2; i++) {
                try {
                    int opcion = scanner.nextInt();
                    if(opcion ==1){
                        Main.print("\nQué item usarás?\n" , "aqua claro");
                        opcion =scanner.nextInt();
                        Objeto objeto = jugador.getBolsa().get(opcion -1);
                        Main.print("\nEn qué javaling lo usarás?\n" , "aqua claro");
                        Main.print(jugador.getEquipoString(),"purpura claro",15);
                        opcion =scanner.nextInt();
                        objeto.usar(jugador.getEquipo()[opcion-1]);
                        this.subirPiso();
                        return;
                    }
                    else{
                        Main.print("Como quieras...\n", "aqua claro");
                        return;
                    }
                } catch (Exception e) {
                    Main.print("Anota un número weno o te vai funao\n", "aqua claro");
                    scanner.next(); // Limpiar el buffer del scanner
                }
            }
            // Main.print("De hecho perdiste por chistosito\n", "rojo");
            //         Main.imprimirASCII("gameover", "rojo");
            //         System.exit(0);


            Main.print("No hay caso... intenta de nuevo\n", "aqua claro");
            return;
        }
        else if(this.decision==4){ //ver Equipo
            Main.print(jugador.getEquipoString(),"purpura claro",15);
        }
        else if(this.decision==5){
            Main.print("malito...\n", "rojo");
            Main.imprimirASCII("gameover", "rojo");
            System.exit(0);
        }
    }
}