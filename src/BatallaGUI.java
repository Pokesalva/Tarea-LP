import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.print.attribute.standard.JobPriority;
import javax.swing.*;

public class BatallaGUI {
    private boolean batallaTerminada = false;
    private final Object lock = new Object();
    private JFrame frame;
    public boolean victoria;
    
    private Random random = new Random();

    private Jugador jugador;
    private Entrenador entrenador;

    private Javaling javalingPropio;
    private Javaling javalingEnemigo;

    private JLabel lblVidaPropia;
    private JLabel lblVidaEnemiga;
    private JLabel labelMensaje;
    private JPanel bolsaCentral;
    private JPanel javalinesCentral;
    private CardLayout cardLayout;
    private JPanel panelCards;
    private JPanel panelCentral;
    private JPanel panelSuperior;
    private JPanel panelSuperiorIzq;
    private JPanel panelSuperiorDer;

    private boolean javalingMuerto; //logica para cambiar Javaling después de muerte
    private List<String> mensajes = new ArrayList<>(); 
    public BatallaGUI(Jugador jugador, Entrenador entrenador) {

        //SELECCIÓN JAVALING
        this.victoria=false;
        this.jugador = jugador;
        this.entrenador = entrenador;
        for (int i = 0; i < jugador.getEquipo().length; i++) {
            if (jugador.getEquipo()[i] != null && jugador.getEquipo()[i].getHpActual() > 0) {
                javalingPropio = jugador.getEquipo()[i];
                break;
            }
        }

        for (int i = 0; i < entrenador.getEquipo().length; i++) {
            if (entrenador.getEquipo()[i] != null && entrenador.getEquipo()[i].getHpActual() > 0) {
                javalingEnemigo = entrenador.getEquipo()[i];
                break;
            }
        }
        labelMensaje = new JLabel();

        //CENTRAL
        cardLayout = new CardLayout();
        panelCards = new JPanel(cardLayout);
        javalinesCentral = new JPanel();
        bolsaCentral = new JPanel();
        panelCentral = new JPanel();
        bolsaCentral.setLayout(new BoxLayout(bolsaCentral, BoxLayout.Y_AXIS));
        javalinesCentral.setLayout(new BoxLayout(javalinesCentral, BoxLayout.Y_AXIS));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
 
        
        //SUPERIOR
        lblVidaPropia = new JLabel("Vida " + javalingPropio.getNombre() + ": " + javalingPropio.getHpActual());
        lblVidaEnemiga = new JLabel("Vida " + javalingEnemigo.getNombre() + ": " + javalingEnemigo.getHpActual());
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        panelSuperiorDer = new JPanel();
        panelSuperiorIzq = new JPanel();
        panelSuperiorIzq.setLayout(new FlowLayout());
        panelSuperiorDer.setLayout(new FlowLayout());
        lblVidaPropia.setFont(new Font("Arial", Font.BOLD, 18));
        lblVidaEnemiga.setFont(new Font("Arial", Font.BOLD, 18));

        
        //AJUSTE MENSAJES
        labelMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        labelMensaje.setFont(new Font("Arial", Font.BOLD, 16));
    }
    public boolean getVictoria(){
        return victoria;
    }
    public void setVictoria(boolean vic){
        this.victoria=vic;
    }
    public boolean getJavalingMuerto(){
        return this.javalingMuerto;
    }
    public void setJavalingMuerto(boolean set){
        this.javalingMuerto=set;
    }
    public void mostrar() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Batalla Pokémon");
            setVidas();
            actualizarBolsa();
            actualizarJavalinesCentral();
            actualizarPanelCentral();
            //SUPERIOR
            panelSuperiorDer.add(lblVidaPropia);
            panelSuperiorIzq.add(lblVidaEnemiga);; 
            panelSuperior.add(panelSuperiorDer, BorderLayout.WEST);
            panelSuperior.add(panelSuperiorIzq, BorderLayout.EAST);

            //AJUSTE PANELES en FRAME
            panelCards.add(panelCentral, "panelCentral");
            panelCards.add(bolsaCentral, "bolsaCentral");
            panelCards.add(javalinesCentral, "javalinesCentral");
            frame.add(panelCards, BorderLayout.CENTER);
            frame.add(panelSuperior, BorderLayout.NORTH);
            frame.add(labelMensaje,BorderLayout.SOUTH);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    finalizarBatalla("Cierre manual");
                }
            });
            cardLayout.show(panelCards, "panelCentral");
    
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    private void finalizarBatalla(String origen) {
        if (frame != null && frame.isDisplayable()) {
            frame.dispose();
        }
        System.out.println("La batalla ha terminado (" + origen + ").");
        synchronized (lock) {
            batallaTerminada = true;
            lock.notify();
        }
    }
    public void esperarFin() {
        synchronized (lock) {
            while (!batallaTerminada) {
                try {
                    lock.wait(); // Espera hasta que se termine la batalla
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Interrumpido mientras esperaba la batalla.");
                }
            }
        }
    }



   //******************** ****************** ****************** ****************** */
    private void actualizarPaneles(){
        actualizarBolsa();
        actualizarJavalinesCentral();
        actualizarPanelCentral();
        setVidas();
    }
    
    private boolean muereJavaling() {
        // Ambos Javalings derrotados al mismo tiempo
        if (javalingPropio.getHpActual() <= 0 && javalingEnemigo.getHpActual() <= 0){
            JOptionPane.showMessageDialog(frame,"Ambos Javaling han sido derrotados.");
    
            boolean jugadorTieneRelevo = false;
            boolean entrenadorTieneRelevo = false;
    
            // Buscar reemplazo para el jugador
            for (int i = 0; i < jugador.getEquipoTamaño(); i++) {
                if (jugador.getEquipo()[i] != null && jugador.getEquipo()[i].getHpActual() > 0) {
                    javalingPropio = jugador.getEquipo()[i];
                    jugadorTieneRelevo = true;
                    break;
                }
            }
    
            // Buscar reemplazo para el entrenador
            for (int i = 0; i < entrenador.getEquipoTamaño(); i++) {
                if (entrenador.getEquipo()[i] != null && entrenador.getEquipo()[i].getHpActual() > 0) {
                    javalingEnemigo = entrenador.getEquipo()[i];
                    entrenadorTieneRelevo = true;
                    break;
                }
            }
    
            // Evaluar resultado
            if (!jugadorTieneRelevo && !entrenadorTieneRelevo) {
                JOptionPane.showMessageDialog(frame, "No quedan Javalings con vida en ninguno de los equipos. Empate.");
                finalizarBatalla("EMPATE");
                this.victoria=false;
                return true;
            } else if (!jugadorTieneRelevo) {
                JOptionPane.showMessageDialog(frame, "No te quedan más Javalings con vida. Has sido derrotado.");
                finalizarBatalla("DERROTA");
                return true;
            } else if (!entrenadorTieneRelevo) {
                JOptionPane.showMessageDialog(frame, "El enemigo no tiene más Javalings con vida. Has ganado la batalla.\nTus Javaling suben 2 niveles.");
                for (int j = 0; j < jugador.getEquipoTamaño(); j++) {
                    if (jugador.getEquipo()[j] != null) {
                        jugador.getEquipo()[j].subirNivel();
                        jugador.getEquipo()[j].subirNivel();
                    }
                }
                finalizarBatalla("VICTORIA");
                this.victoria=true;
                return true;
            } else {
                // Ambos tienen reemplazo, continuar
                setVidas(); // actualiza los labels si los usas
                mostrarMensajeTemporal(">>> Nuevos Javalings entran a la batalla.");
                cardLayout.show(panelCards, "panelCentral");
                return true;
            }
        }
    
        // Solo Javaling propio derrotado
        if (javalingPropio.getHpActual() <= 0) {
            JOptionPane.showMessageDialog(frame, javalingPropio.getNombre() + " ha sido derrotado.");
            for (int i = 0; i < jugador.getEquipoTamaño(); i++) {
                if (jugador.getEquipo()[i].getHpActual() > 0) {
                    mostrarMensajeTemporal(">>> Mostrando equipo...");
                    this.javalingMuerto =true;
                    cardLayout.show(panelCards, "javalinesCentral");
                    return true;
                }
            }
            JOptionPane.showMessageDialog(frame,"No quedan Javalings con vida. Has sido derrotado.");
            finalizarBatalla("DERROTA");
            this.victoria=false;
            return true;
        }
    
        // Solo Javaling enemigo derrotado
        if (javalingEnemigo.getHpActual() <= 0) {
            JOptionPane.showMessageDialog(frame, javalingEnemigo.getNombre() + " ha sido derrotado.");
            cardLayout.show(panelCards, "panelCentral");
            for (int i = 0; i < entrenador.getEquipoTamaño(); i++) {
                if (entrenador.getEquipo()[i] != null && entrenador.getEquipo()[i].getHpActual() > 0) {
                    javalingEnemigo = entrenador.getEquipo()[i];
                    setVidas();
                    return true;
                }
            }
            JOptionPane.showMessageDialog(frame,"No quedan Javalings con vida. Has ganado la batalla.\nTus Javaling aumentan 2 niveles.");
            for (int j = 0; j < jugador.getEquipoTamaño(); j++) {
                if (jugador.getEquipo()[j] != null) {
                    jugador.getEquipo()[j].subirNivel();
                    jugador.getEquipo()[j].subirNivel();
                }
            }
            finalizarBatalla("VICTORIA");
            this.victoria=true;
            return true;
        }
    
        return false; // Continúa la batalla normalmente
    }
    private void mostrarMensajeTemporal(String mensaje) {
        if (mensaje != null && !mensaje.isEmpty()) {
            mensajes.add(mensaje);
        }
    
        // Limitar la lista a los últimos 3 mensajes
        while (mensajes.size() > 3) {
            mensajes.remove(0);
        }
    
        StringBuilder print = new StringBuilder("<html>");
        for (String msg : mensajes) {
            if (msg != null && !msg.isEmpty()) {
                print.append(msg).append("<br>");
            }
        }
        print.append("</html>");
    
        labelMensaje.setText(print.toString());
    
        Timer timer = new Timer(5000, e -> labelMensaje.setText(""));
        timer.setRepeats(false);
        timer.start();
    }
    

    private void recibirDano(Javaling javaling, int dano) {
        int vidaActual = javaling.getHpActual();
        vidaActual -= dano;
        if (vidaActual < 0) {
            vidaActual = 0;
        } 
        javaling.setHpActual(vidaActual);
    }
    private void realizarTurnoEnemigo() {
        int indiceMov = random.nextInt(4);
        while (javalingEnemigo.getMovimiento()[indiceMov] == null) {
            indiceMov = random.nextInt(4);
        }
        int dano = javalingEnemigo.atacarObjetivo(javalingPropio, indiceMov);
        mostrarMensajeTemporal(">>> "+ javalingEnemigo.getNombre() + "enemigo ha atacado a usado " + javalingEnemigo.getMovimiento()[indiceMov].getNombre() +"(-" + dano + ")");
        recibirDano(javalingPropio, dano);
        setVidas();
        //(JOptionPane.showMessageDialog(frame,">>> Tu Pokémon tiene " + javalingPropio.getHpActual() + " de vida restante.");
        mostrarMensajeTemporal(">>> Tu Pokémon tiene " + javalingPropio.getHpActual() + " de vida restante.");
    }
    private void setVidas(){
        lblVidaPropia.setText("Vida " + javalingPropio.getNombre()+ ": " +javalingPropio.getHpActual());
        lblVidaEnemiga.setText("Vida "+ javalingEnemigo.getNombre()+": " + javalingEnemigo.getHpActual());
    }
    private void realizarTurno(String mov, int indiceMov){
        // Validar que el movimiento sea válido antes de usarlo
        if(muereJavaling()){
            return;
        }

        mostrarMensajeTemporal(javalingPropio.aplicarEfectoEstado());
        mostrarMensajeTemporal(javalingEnemigo.aplicarEfectoEstado());
        setVidas();
        if (muereJavaling()){
            return;
        }
        if(javalingPropio.getVelocidad()>= javalingEnemigo.getVelocidad()){
            realizarTurnoPropio(indiceMov);
            if (muereJavaling()){
                return;
            }
            realizarTurnoEnemigo();
            if (muereJavaling()){
                return;
            }
        }
        else{
            realizarTurnoEnemigo();
            if (muereJavaling()){
                return;
            }
            realizarTurnoPropio(indiceMov);
            if (muereJavaling()){
                return;
            }
        }
    }
    public void realizarTurnoPropio(int indiceMov){
        if(indiceMov<5){
            int dano = javalingPropio.atacarObjetivo(javalingEnemigo, indiceMov);
            mostrarMensajeTemporal(">>> Atacaste a " + javalingEnemigo.getNombre() + " con " + javalingPropio.getMovimiento()[indiceMov].getNombre() + " infligiendo (-" + dano + ")");
            recibirDano(javalingEnemigo, dano);
            setVidas();
        }
    }

    public void actualizarBolsa() {
        bolsaCentral.removeAll();
        for (int i = 0; i < jugador.getBolsa().size(); i++) {
            if (jugador.getBolsa().get(i).getCantidad() > 0) {
                final int indice = i;
                JButton btnObjeto = new JButton(jugador.getBolsa().get(i).getNombre() + " x" + jugador.getBolsa().get(i).getCantidad());
                btnObjeto.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jugador.getBolsa().get(indice).usar(javalingPropio);
                        mostrarMensajeTemporal(">>> Usaste " + jugador.getBolsa().get(indice).getNombre());
                        setVidas();
                        actualizarJavalinesCentral();
                        actualizarPanelCentral();
                        actualizarBolsa(); // Actualiza la bolsa después de usar un objeto
                        realizarTurnoEnemigo();
                        cardLayout.show(panelCards,"javalinesCentral");

                    }
                });
                bolsaCentral.add(btnObjeto);
            }
        }
        JButton btnCerrarBolsa = new JButton("Cerrar bolsa");
            btnCerrarBolsa.addActionListener(e ->{
                mostrarMensajeTemporal("Cerrando bolsa... malito");
                cardLayout.show(panelCards,"panelCentral");
            }
            );
        bolsaCentral.add(btnCerrarBolsa);
        bolsaCentral.revalidate();
        bolsaCentral.repaint();
    }
    public void actualizarPanelCentral() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
    
        for (int i = 0; i < 4; i++) {
            final int indiceMov = i;
            Movimiento mov = javalingPropio.getMovimiento()[i];
            if(mov != null){
                JButton btnAtaque = new JButton(mov.getNombre());
                btnAtaque.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        realizarTurno(mov.getNombre(), indiceMov);
                    }
                });
                panelCentral.add(btnAtaque);
            }
        }
    
        JButton btnUsarItem = new JButton("Usar objeto");
        btnUsarItem.addActionListener(e -> {
            mostrarMensajeTemporal(">>> Mostrando bolsa...");
            cardLayout.show(panelCards, "bolsaCentral");
        });
        panelCentral.add(btnUsarItem);


        JButton btnCambiarJavaling = new JButton("Cambiar Javaling");
        btnCambiarJavaling.addActionListener(e -> {
            mostrarMensajeTemporal(">>> Mostrando equipo...");
            cardLayout.show(panelCards, "javalinesCentral");
        });
        panelCentral.add(btnCambiarJavaling);
        panelCentral.revalidate();
        panelCentral.repaint();
        
        JButton btnEscapar = new JButton("Escapar (cobarde)");
        btnEscapar.addActionListener(e ->{
            finalizarBatalla("Escapando");
            this.victoria=false;
        });
        panelCentral.add(btnEscapar);

    }


    public void actualizarJavalinesCentral() {
        javalinesCentral.removeAll();
        javalinesCentral.setLayout(new BoxLayout(javalinesCentral, BoxLayout.Y_AXIS));
    
        for (int i = 0; i < jugador.getEquipoTamaño(); i++) {
            if (jugador.getEquipo()[i] != null && jugador.getEquipo()[i].getHpActual() > 0 && jugador.getEquipo()[i] != javalingPropio) {
                final int indice = i;
                JButton btnJavaling = new JButton(jugador.getEquipo()[i].getNombre());
                btnJavaling.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        javalingPropio = jugador.getEquipo()[indice];
                        mostrarMensajeTemporal(">>> Cambiaste a " + javalingPropio.getNombre());
                        setVidas();
                        if(getJavalingMuerto()==false){
                            realizarTurno("", 5);
                            if(batallaTerminada){
                                return;
                            }
                        }
                        setJavalingMuerto(false);
                        actualizarPaneles();
                        cardLayout.show(panelCards, "panelCentral");
                    }
                });
                javalinesCentral.add(btnJavaling);
            }
        }
        if(getJavalingMuerto()==false){
            JButton btnCerrarJavales = new JButton("No cambiar");
            btnCerrarJavales.addActionListener(e-> {
                cardLayout.show(panelCards, "panelCentral");
                mostrarMensajeTemporal(">>> No cambiaste de Javaling.");
            });
            javalinesCentral.add(btnCerrarJavales);
        }
        setJavalingMuerto(false);
    
        javalinesCentral.revalidate();
        javalinesCentral.repaint();
    }

}
