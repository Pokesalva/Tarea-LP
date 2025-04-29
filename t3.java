import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class JuegoAdivinaNumero extends JFrame {
    private JButton botonJugar;
    private Random random;

    public JuegoAdivinaNumero() {
        super("Adivina el Número");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        botonJugar = new JButton("¡Jugar!");
        botonJugar.setBounds(90, 40, 120, 30);
        add(botonJugar);

        random = new Random();

        botonJugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jugar();
            }
        });
    }

    private void jugar() {
        int numeroAleatorio = random.nextInt(10) + 1; // Número del 1 al 10
        String input = JOptionPane.showInputDialog(this, "Elige un número del 1 al 10:");

        try {
            int elegido = Integer.parseInt(input);

            if (elegido == numeroAleatorio) {
                JOptionPane.showMessageDialog(this, "¡Ganaste! Adivinaste el número.");
            } else {
                JOptionPane.showMessageDialog(this, "Perdiste. El número era " + numeroAleatorio + ".");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida. Por favor ingresa un número entero.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new JuegoAdivinaNumero().setVisible(true);
        });
    }
}
