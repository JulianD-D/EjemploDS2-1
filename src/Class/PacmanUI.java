package Class;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author julián D_D
 *
 *
 */
public class PacmanUI extends JComponent implements Runnable {

    private static final int IMAGEN_SIZE = 30;
    private int cap = 0;
    private int direccion_pacman = 3;
    private int dri_f_rojo, dri_f_marron = 0;
    private int dir = 0;
    private int nivel = 1;
    private int vidas = 3;
    private Random r = new Random();

    private Thread thread = null;
    private Pacman pacman = null;

    private final Image[] imagenes_escenario = new Image[3];
    private final Image[][] imagenes_pacman = new Image[4][4];
    private final Image[][] imagenes_fantasma_rojo = new Image[1][4];
    private final Image[][] imagenes_fantasma_marron = new Image[1][4];
    private final Image[] imagene_vida = new Image[1];
    private final Image[] imagene_logo = new Image[1];
    private final Image[] imagene_logo_univalle = new Image[1];

    public PacmanUI() throws IOException {

        this.pacman = new Pacman();
        this.pacman.Iniciar(nivel);
        super.setBounds(0, 0, IMAGEN_SIZE * pacman.getColumnas(), IMAGEN_SIZE * pacman.getFilas());
        this.imagenes_escenario[0] = ImageIO.read(this.getClass().getResource("/Imagenes/fondo.png"));
        this.imagenes_escenario[1] = ImageIO.read(this.getClass().getResource("/Imagenes/pared.png"));
        this.imagenes_escenario[2] = ImageIO.read(this.getClass().getResource("/Imagenes/galleta.png"));
        this.imagenes_pacman[0][0] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanAB01.png"));
        this.imagenes_pacman[0][1] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanAB02.png"));
        this.imagenes_pacman[0][2] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanAB03.png"));
        this.imagenes_pacman[0][3] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanAB04.png"));
        this.imagenes_pacman[1][0] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanAR01.png"));
        this.imagenes_pacman[1][1] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanAR02.png"));
        this.imagenes_pacman[1][2] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanAR03.png"));
        this.imagenes_pacman[1][3] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanAR04.png"));
        this.imagenes_pacman[2][0] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanI01.png"));
        this.imagenes_pacman[2][1] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanI02.png"));
        this.imagenes_pacman[2][2] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanI03.png"));
        this.imagenes_pacman[2][3] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanI04.png"));
        this.imagenes_pacman[3][0] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanD01.png"));
        this.imagenes_pacman[3][1] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanD02.png"));
        this.imagenes_pacman[3][2] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanD03.png"));
        this.imagenes_pacman[3][3] = ImageIO.read(this.getClass().getResource("/Imagenes/pacmanD04.png"));
        this.imagenes_fantasma_rojo[0][0] = ImageIO.read(this.getClass().getResource("/Imagenes/f-rojo-abajo.png"));
        this.imagenes_fantasma_rojo[0][1] = ImageIO.read(this.getClass().getResource("/Imagenes/f-rojo-arriba.png"));
        this.imagenes_fantasma_rojo[0][2] = ImageIO.read(this.getClass().getResource("/Imagenes/f-rojo-izq.png"));
        this.imagenes_fantasma_rojo[0][3] = ImageIO.read(this.getClass().getResource("/Imagenes/f-rojo-der.png"));
        this.imagenes_fantasma_marron[0][0] = ImageIO.read(this.getClass().getResource("/Imagenes/f-marron-abajo.png"));
        this.imagenes_fantasma_marron[0][1] = ImageIO.read(this.getClass().getResource("/Imagenes/f-marron-arriba.png"));
        this.imagenes_fantasma_marron[0][2] = ImageIO.read(this.getClass().getResource("/Imagenes/f-marron-izq.png"));
        this.imagenes_fantasma_marron[0][3] = ImageIO.read(this.getClass().getResource("/Imagenes/f-marron-der.png"));
        this.imagene_vida[0] = ImageIO.read(this.getClass().getResource("/Imagenes/vida_p.png"));
        //this.imagene_logo[0] = ImageIO.read(this.getClass().getResource("/Imagenes/logo.png"));
        //this.imagene_logo_univalle[0] = ImageIO.read(this.getClass().getResource("/Imagenes/univalle.png"));

        //..
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Start_animated();
            }
        });

        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                super.keyReleased(e);

                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    direccion_pacman = 0;

                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    direccion_pacman = 1;

                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    direccion_pacman = 2;

                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    direccion_pacman = 3;

                }

            }

        });

    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int px, py;

        for (int fila = 0; fila < this.pacman.getFilas(); fila++) {
            for (int col = 0; col < this.pacman.getColumnas(); col++) {
                byte valor = this.pacman.valor(fila, col);
                px = col * IMAGEN_SIZE;
                py = fila * IMAGEN_SIZE;
                g.drawImage(imagenes_escenario[valor], px, py, this);

                if ((fila == pacman.getPosx() && col == pacman.getPosy()) && (pacman.Valor_Posicion(fila, col) != 0)) {
                    pacman.comer(fila, col);
                }

            }
        }

        g.drawImage(imagenes_pacman[direccion_pacman][cap], pacman.getPosx() * 30, pacman.getPosy() * 30, this);

        g.drawImage(imagenes_fantasma_rojo[0][dri_f_rojo], pacman.getPosx_F_rojo() * 30, pacman.getPosy_F_rojo() * 30, this);
        g.drawImage(imagenes_fantasma_marron[0][dri_f_marron], pacman.getPosx_F_marron() * 30, pacman.getPosy_F_marron() * 30, this);

        g.drawImage(imagene_logo[0], 520, 10, this);
        g.drawImage(imagene_logo_univalle[0], 580, this.getHeight() - 130, this);

        g.setColor(Color.red);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Tu puntucion", 558, 100);
        g.drawString("Vidas Restantes", 548, 160);
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.HANGING_BASELINE, 18));
        g.drawString("" + pacman.getPuntuacion() + " Pts.", 590, 130);

        for (int i = 0; i < vidas; i++) {
            g.drawImage(imagene_vida[0], 595, 190 + (i * 60), this);
        }

    }

    public void Start_animated() {
        if (thread == null) {
            this.thread = new Thread(this);
            thread.start();

        }
    }

    public void Stop_animate() {
        thread = null;
    }

    public int ancho() {
        int ancho = pacman.getColumnas() * 44;
        return ancho;
    }

    public int largo() {
        int largo = pacman.getFilas() * 34;
        return largo;
    }

    public void Reiniciar() {
        nivel = 1;
        JOptionPane.showMessageDialog(this, "Has perdido.\nTu puntuacion total es: " + pacman.getPuntuacion() + " Pts.");
        vidas = 3;
        this.pacman = new Pacman();
        this.pacman.Iniciar(nivel);
        cap = 0;
        Stop_animate();
    }

    public void Fin_Juego() {
        if (nivel > 5) {
            int i = JOptionPane.showConfirmDialog(this, "Has Ganado.\nTu puntuacion total es: " + pacman.getPuntuacion() + " Pts.\nFelicitaciones. Desea Volver A intentar", "Fin del juego", JOptionPane.YES_NO_OPTION);
            if (i == 0) {
                nivel = 1;
                vidas = 3;
                this.pacman = new Pacman();
                this.pacman.Iniciar(nivel);
                cap = 0;
                Stop_animate();
            } else {
                System.exit(1);
            }

        }

    }

    @Override
    public void run() {
        this.requestFocus();
        int Mov = 0;

        while (this.thread != null) {
            cap++;
            Mov++;

            if (direccion_pacman == 0) {
                pacman.move_down();
            }
            if (direccion_pacman == 1) {
                pacman.move_Up();
            }
            if (direccion_pacman == 2) {
                pacman.move_Left();
            }
            if (direccion_pacman == 3) {
                pacman.move_Right();
            }

            // Sirve para animar el pacman
            if (cap == 4) {
                cap = 0;
            }

            if (pacman.getGalletas() == 0) {
                nivel++;
                JOptionPane.showMessageDialog(this, "Felicitaciones.\nHas terminado el nivel exitosamente");
                Fin_Juego();
                this.pacman.Iniciar(nivel);
                direccion_pacman = 3;
            }

            // movimiento fantasma
            if (pacman.getDir_f_rojo() == 1) {
                dri_f_rojo = 3;
                pacman.move_Right_f_rojo();
            }
            if (pacman.getDir_f_rojo() == 2) {
                dri_f_rojo = 2;
                pacman.move_Left_f_rojo();
            }
            if (pacman.getDir_f_rojo() == 3) {
                dri_f_rojo = 1;
                pacman.move_Up_f_rojo();
            }
            if (pacman.getDir_f_rojo() == 4) {
                dri_f_rojo = 0;
                pacman.move_down_f_rojo();
            }
            ////////////////////////////////////////////////////
            // movimiento fantasma rojo
            if (pacman.getDir_f_marron() == 1) {
                dri_f_marron = 3;
                pacman.move_Right_f_marron();
            }
            if (pacman.getDir_f_marron() == 2) {
                dri_f_marron = 2;
                pacman.move_Left_f_marron();
            }
            if (pacman.getDir_f_marron() == 3) {
                dri_f_marron = 1;
                pacman.move_Up_f_marron();
            }
            if (pacman.getDir_f_marron() == 4) {
                dri_f_marron = 0;
                pacman.move_down_f_marron();
            }
            /////////////////////////////////////////////////////
            if (Mov == 4) {
                int dir_f_rojo = r.nextInt(4) + 1;
                int dir_f_marron = r.nextInt(4) + 1;
                pacman.setDir_f_rojo(dir_f_rojo);
                pacman.setDir_f_marron(dir_f_marron);
                Mov = 0;
            }
            // muerte pacman
            if (vidas == 0) {
                Reiniciar();
            }

            // colision pacman
            if ((pacman.getPosx() == pacman.getPosx_F_rojo() && pacman.getPosy() == pacman.getPosy_F_rojo()) || (pacman.getPosx() == pacman.getPosx_F_marron() && pacman.getPosy() == pacman.getPosy_F_marron())) {
                vidas--;
                pacman.setPosx(pacman.getPosx_reserva());
                pacman.setPosy(pacman.getPosy_reserva());
                cap = 0;
                direccion_pacman = 3;
            }

            this.pacman.move();

            repaint();
            try {
                Thread.sleep(pacman.getVelocidad());
            } catch (InterruptedException ex) {
                Logger.getLogger(PacmanUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
