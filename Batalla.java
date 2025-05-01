public class Batalla {
    public static void iniciar(Jugador j1, Jugador j2) {
        System.out.println("¡Batalla entre " + j1.getNombre() + " y " + j2.getNombre() + "!");
        
        Javaling p1 = j1.getJavaling();
        Javaling p2 = j2.getJavaling();

        while (p1.estaVivo() && p2.estaVivo()) {
            p2.recibirDano(p1.atacar());
            System.out.println(p1.getNombre() + " ataca a " + p2.getNombre() + ". Vida restante: " + p2.getVida());

            if (!p2.estaVivo()) break;

            p1.recibirDano(p2.atacar());
            System.out.println(p2.getNombre() + " ataca a " + p1.getNombre() + ". Vida restante: " + p1.getVida());
        }

        if (p1.estaVivo()) {
            System.out.println(j1.getNombre() + " gana la batalla.");
        } else {
            System.out.println(j2.getNombre() + " gana la batalla.");
        }
    }
}
