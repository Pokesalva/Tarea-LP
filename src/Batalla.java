import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Batalla extends JFrame {

    private int continuarBatalla;
    private int vidaPropia = 100;
    private int vidaEnemiga = 100;
    private Random random = new Random();

    private Jugador jugador;
    private Entrenador entrenador;

    private Javaling javalingPropio;
    private Javaling javalingEnemigo;

    private JLabel lblVidaPropia;
    private JLabel lblVidaEnemiga;
    private JLabel lblPokemonPropio;
    private JLabel lblPokemonEnemigo;
    private JLabel labelMensaje;
    private JPanel bolsaCentral;
    private JPanel javalinesCentral;
    private CardLayout cardLayout;
    private JPanel panelCards;
    private JPanel panelCentral;
    private JPanel panelSuperior;
    private JPanel panelSuperiorIzq;
    private JPanel panelSuperiorDer;



    public Batalla(Jugador jugador, Entrenador entrenador) {
        continuarBatalla=1;
        this.jugador = jugador;
        this.entrenador = entrenador;
        cardLayout = new CardLayout();
        panelCards = new JPanel(cardLayout);
        labelMensaje = new JLabel();
        javalinesCentral = new JPanel();
        bolsaCentral = new JPanel();
        panelCentral = new JPanel();
        panelSuperior = new JPanel();
        panelSuperior.setLayout(new FlowLayout());
        panelSuperiorDer = new JPanel();
        panelSuperiorIzq = new JPanel();
        setLayout(new BorderLayout());
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

        if (javalingPropio == null || javalingEnemigo == null) {
            JOptionPane.showMessageDialog(this, "Error: No hay Javalings válidos para iniciar la batalla.");
            System.exit(1);
        }
        
        vidaPropia = javalingPropio.getHpActual();
        vidaEnemiga = javalingEnemigo.getHpActual();

        // PANEL BOLSA CENTRAL
        bolsaCentral.setLayout(new BoxLayout(bolsaCentral, BoxLayout.Y_AXIS));
        for (int i = 0; i < jugador.getBolsa().size(); i++) {
            if (jugador.getBolsa().get(i).getCantidad() > 0) {
                final int indice = i;
                JButton btnObjeto = new JButton(jugador.getBolsa().get(i).getNombre() + " x" + jugador.getBolsa().get(i).getCantidad());
                btnObjeto.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        jugador.getBolsa().get(indice).usar(javalingPropio);
                        mostrarMensajeTemporal(">>> Usaste " + jugador.getBolsa().get(indice).getNombre());
                        setVidas();
                        actualizarBolsa(); // Actualiza la bolsa después de usar un objeto
                    }
                });
                bolsaCentral.add(btnObjeto);
            }
        }

        JButton btnCerrarBolsa = new JButton("Cerrar bolsa");
        btnCerrarBolsa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelCards, "panelCentral");
                mostrarMensajeTemporal(">>> Bolsa cerrada.");
            }
        });
        bolsaCentral.add(btnCerrarBolsa);

        /******* */
        //PANEL JAVALINES CENTRAL
        javalinesCentral.setLayout(new BoxLayout(javalinesCentral, BoxLayout.Y_AXIS));
        for (int i = 0; i < jugador.getEquipo().length; i++) {
            if (jugador.getEquipo()[i] != null && jugador.getEquipo()[i].getHpActual() > 0 && jugador.getEquipo()[i] != javalingPropio) {
                final int indice = i;
                JButton btnJavaling = new JButton(jugador.getEquipo()[i].getNombre());
                btnJavaling.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        javalingPropio = jugador.getEquipo()[indice];
                        mostrarMensajeTemporal(">>> Cambiaste a " + javalingPropio.getNombre());

                        setVidas();
                        actualizarPanelCentral();
                        actualizarJavalinesCentral();
                        cardLayout.show(panelCards, "panelCentral");
                    }
                });
                javalinesCentral.add(btnJavaling);
            }
        }
        JButton btnCerrarJavales = new JButton("No cambiar");
        btnCerrarJavales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelCards, "panelCentral");
                mostrarMensajeTemporal(">>> No cambiaste de Javaling.");
            }
        });
        javalinesCentral.add(btnCerrarJavales);


        // PANEL CENTRAL PRINCIPAL
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        for (int i = 0; i < 4; i++) {
            final int indiceMov = i;
            String mov = (javalingPropio.getMovimiento()[i] != null) // Validar que el movimiento no sea null
                ? javalingPropio.getMovimiento()[i].getNombre()
                : "No aprendido";
            JButton btnAtaque = new JButton(mov);
            btnAtaque.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    realizarTurno(mov, indiceMov);
                }
            });
            panelCentral.add(btnAtaque);
        }

        JButton btnUsarItem = new JButton("Usar objeto");
        btnUsarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarMensajeTemporal(">>> Mostrando bolsa...");
                cardLayout.show(panelCards, "bolsaCentral");
            }
        });
        panelCentral.add(btnUsarItem);

        JButton btnCambiarJavaling = new JButton("Cambiar Pokémon");
        btnCambiarJavaling.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarMensajeTemporal(">>> Mostrando equipo...");
                cardLayout.show(panelCards, "javalinesCentral");
            }
        });
        panelCentral.add(btnCambiarJavaling);

        // PANEL SUPERIOR
        ImageIcon iconPropio = new ImageIcon("path/to/pokemon_propio.png");
        Image imgPropio = iconPropio.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        iconPropio = new ImageIcon(imgPropio);
        lblPokemonPropio = new JLabel(iconPropio);
        panelSuperiorDer.add(lblPokemonPropio);
        lblVidaPropia = new JLabel("Vida " + javalingPropio.getNombre() + ": " + vidaPropia);
        lblVidaPropia.setFont(new Font("Arial", Font.BOLD, 18));
        panelSuperiorDer.add(lblVidaPropia);
        panelSuperiorIzq.setLayout(new FlowLayout());
        lblVidaEnemiga = new JLabel("Vida " + javalingEnemigo.getNombre() + ": " + vidaEnemiga);
        lblVidaEnemiga.setFont(new Font("Arial", Font.BOLD, 18));
        panelSuperiorIzq.add(lblVidaEnemiga);

        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.add(panelSuperiorDer, BorderLayout.WEST);
        panelSuperior.add(panelSuperiorIzq, BorderLayout.EAST);
        add(panelSuperior, BorderLayout.NORTH);
        // Panel para mensajes de batalla
        labelMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        labelMensaje.setFont(new Font("Arial", Font.BOLD, 16));
        add(labelMensaje, BorderLayout.SOUTH);

        // Ejemplo de cómo mostrar un mensaje y borrarlo después de un tiempo
        mostrarMensajeTemporal("¡Comienza la batalla!");

        //add(panelCentral, BorderLayout.CENTER);
        panelCards.add(panelCentral, "panelCentral");
        panelCards.add(bolsaCentral, "bolsaCentral");
        panelCards.add(javalinesCentral, "javalinesCentral");
        add(panelCards, BorderLayout.CENTER);
        cardLayout.show(panelCards, "panelCentral");

        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        return;

    }

    private int muereJavaling() {
        if (javalingPropio.getHpActual() <= 0) {
            JOptionPane.showMessageDialog(this, javalingPropio.getNombre() + " ha sido derrotado.");
            for (int i=0; i<6; i++){
                if(jugador.getEquipo()[i].getHpActual()>0){
                    mostrarMensajeTemporal(">>> Mostrando equipo...");
                    cardLayout.show(panelCards, "javalinesCentral");
                }
                else{
                    JOptionPane.showMessageDialog(this,"No quedan Javalings con vida. Has sido derrotado.");
                    return 0; // TERMINAR
                 }

            }
        }
        if (javalingEnemigo.getHpActual() <= 0) {
            JOptionPane.showMessageDialog(this, javalingEnemigo.getNombre() + " ha sido derrotado.");
            cardLayout.show(panelCards, "panelCentral"); // Asegurar que regrese al panel principal
            for (int i=0; i<6; i++){
                if(entrenador.getEquipo()[i].getHpActual()>0){
                    javalingEnemigo = entrenador.getEquipo()[i];
                    setVidas();
                }
                else{
                    JOptionPane.showMessageDialog(this,"No quedan Javalings con vida. Has ganado la batalla\n Tus Javaling aumentan 2 niveles.");
                    for ( int j=0; j<6; j++){
                        if(jugador.getEquipo()[j] != null){
                            jugador.getEquipo()[j].subirNivel();
                            jugador.getEquipo()[j].subirNivel();
                        }
                    }
                    jugador.mostrarEquipo();
                    return 0;} // TERMINAR
                       
            }
        }
        return 1; //CONTINUAR
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
        continuarBatalla = muereJavaling();
        if (continuarBatalla ==0){
            return;
        }
        JOptionPane.showMessageDialog(this,">>> Tu Pokémon tiene " + vidaPropia + " de vida restante.");
        continuarBatalla = muereJavaling();
        if (continuarBatalla ==0){
            return;
        }
    }
    private void mostrarMensajeTemporal(String mensaje) {
        labelMensaje.setText(mensaje);
        Timer timer = new Timer(5000, e -> labelMensaje.setText(""));
        timer.setRepeats(false);
        timer.start();
    }
    private void setVidas(){
        vidaPropia = javalingPropio.getHpActual();
        lblVidaPropia.setText("Vida " + javalingPropio.getNombre()+ ": " +vidaPropia);
        vidaEnemiga = javalingEnemigo.getHpActual();
        lblVidaEnemiga.setText("Vida "+ javalingEnemigo.getNombre()+": " + vidaEnemiga);
    }
    

    private void realizarTurno(String mov, int indiceMov){
        // Validar que el movimiento sea válido antes de usarlo
        if (javalingPropio.getMovimiento()[indiceMov] == null) {
            mostrarMensajeTemporal(">>> Movimiento no válido.");
            return;
        }

        javalingPropio.aplicarEfectoEstado();
        javalingEnemigo.aplicarEfectoEstado();
        setVidas();
        continuarBatalla = muereJavaling();
        if (continuarBatalla ==0){
            return;
        }
        int dano = javalingPropio.atacarObjetivo(javalingEnemigo, indiceMov);
        mostrarMensajeTemporal(">>> Atacaste a " + javalingEnemigo.getNombre() + " con " + mov + " infligiendo (-" + dano + ")");
        recibirDano(javalingEnemigo, dano);
        setVidas();
        continuarBatalla = muereJavaling();
        if (continuarBatalla ==0){
            return;
        }
        realizarTurnoEnemigo();
    }
    private void inicializarPaneles() {
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelSuperiorIzq.setLayout(new FlowLayout());
        panelSuperiorDer.setLayout(new FlowLayout());
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.add(panelSuperiorDer, BorderLayout.WEST);
        panelSuperior.add(panelSuperiorIzq, BorderLayout.EAST);
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
                        actualizarBolsa(); // Actualiza la bolsa después de usar un objeto
                    }
                });
                bolsaCentral.add(btnObjeto);
            }
        }
        bolsaCentral.revalidate();
        bolsaCentral.repaint();
    }
    public void actualizarPanelCentral() {
        panelCentral.removeAll();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
    
        for (int i = 0; i < 4; i++) {
            final int indiceMov = i;
            String mov = (javalingPropio.getMovimiento()[i] != null) 
                ? javalingPropio.getMovimiento()[i].getNombre()
                : "No aprendido";
            JButton btnAtaque = new JButton(mov);
            btnAtaque.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    realizarTurno(mov, indiceMov);
                }
            });
            panelCentral.add(btnAtaque);
        }
    
        JButton btnUsarItem = new JButton("Usar objeto");
        btnUsarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarMensajeTemporal(">>> Mostrando bolsa...");
                cardLayout.show(panelCards, "bolsaCentral");
            }
        });
        panelCentral.add(btnUsarItem);
    
        JButton btnCambiarJavaling = new JButton("Cambiar Pokémon");
        btnCambiarJavaling.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarMensajeTemporal(">>> Mostrando equipo...");
                cardLayout.show(panelCards, "javalinesCentral");
            }
        });
        panelCentral.add(btnCambiarJavaling);
    
        panelCentral.revalidate();
        panelCentral.repaint();
    }
    public void actualizarJavalinesCentral() {
        javalinesCentral.removeAll();
        javalinesCentral.setLayout(new BoxLayout(javalinesCentral, BoxLayout.Y_AXIS));
    
        for (int i = 0; i < jugador.getEquipo().length; i++) {
            if (jugador.getEquipo()[i] != null && jugador.getEquipo()[i].getHpActual() > 0 && jugador.getEquipo()[i] != javalingPropio) {
                final int indice = i;
                JButton btnJavaling = new JButton(jugador.getEquipo()[i].getNombre());
                btnJavaling.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        javalingPropio = jugador.getEquipo()[indice];
                        mostrarMensajeTemporal(">>> Cambiaste a " + javalingPropio.getNombre());
    
                        setVidas();
                        actualizarJavalinesCentral();
                        actualizarPanelCentral(); // Actualiza el panel central después de cambiar de Javaling
                        cardLayout.show(panelCards, "panelCentral");
                    }
                });
                javalinesCentral.add(btnJavaling);
            }
        }
    
        JButton btnCerrarJavales = new JButton("No cambiar");
        btnCerrarJavales.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelCards, "panelCentral");
                mostrarMensajeTemporal(">>> No cambiaste de Javaling.");
            }
        });
        javalinesCentral.add(btnCerrarJavales);
    
        javalinesCentral.revalidate();
        javalinesCentral.repaint();
    }
}