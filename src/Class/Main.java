package Class;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author atlas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            Pacman p = new Pacman();
            PacmanUI animation = new PacmanUI();
            JFrame frame = new JFrame("Pacman");
            frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.setResizable(false);
            frame.setResizable(true);
            frame.getContentPane().setBackground(Color.black);
            JMenuBar menu_principal = new JMenuBar();

            JMenu menu_prncipal_opciones = new JMenu("Opciones");
            JMenu menu_prncipal_informacion = new JMenu("Información");

            JMenuItem Sub_menu_Salir = new JMenuItem("Salir");
            JMenuItem Sub_menu_Pausar = new JMenuItem("Pausar");
            JMenuItem Sub_menu_Info = new JMenuItem("Informacion creador");

            Sub_menu_Salir.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(1);
                }
            }
            );

            Sub_menu_Info.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Desarrollado por:\nJulián David Dominguez.\nCod: 1253280", "info", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            Sub_menu_Pausar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    animation.Stop_animate();
                }
            });
            menu_prncipal_opciones.add(Sub_menu_Pausar);
            menu_prncipal_opciones.add(Sub_menu_Salir);
            menu_prncipal_informacion.add(Sub_menu_Info);

            menu_principal.add(menu_prncipal_opciones);
            menu_principal.add(menu_prncipal_informacion);

            frame.add(menu_principal, BorderLayout.BEFORE_FIRST_LINE);

            frame.setBounds(100, 100, animation.ancho(), animation.largo());
            frame.setLocationRelativeTo(frame);
            frame.add(animation, BorderLayout.CENTER);

            frame.setVisible(true);
            //animation.Star_animated();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
