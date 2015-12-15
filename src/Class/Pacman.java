package Class;

import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 * @author atlas
 */
public class Pacman {

    private byte[][] escenario;
    private int posx, posy, galletas; // Posicion del pacman.
    private int posx_reserva, posy_reserva; // Posicion del pacman.
    private int posx_F_rojo, posy_F_rojo; // Posicion del fantasma.    
    private int posx_F_marron, posy_F_marron; // Posicion del fantasma.    
    private int dir_f_rojo, dir_f_marron;
    private int Puntuacion = 0;
    private int velocidad = 0;

    public Pacman() {
        Random r = new Random();
        int dir_random_f_rojo = r.nextInt(4) + 1;
        int dir_random_f_marron = r.nextInt(4) + 1;
        dir_f_rojo = dir_random_f_rojo;
        dir_f_marron = dir_random_f_marron;
    }

    public void Iniciar(int nivel) {

        ResourceBundle re = ResourceBundle.getBundle("Escenario.PacmanEs");
        String[] cadena = re.getString("nivel" + nivel).split(" ");
        int filas = Integer.parseInt(cadena[0]);
        int columnas = Integer.parseInt(cadena[1]);
        this.escenario = new byte[filas][columnas];

        this.posx = Integer.parseInt(cadena[3]);
        this.posy = Integer.parseInt(cadena[2]);
        this.posx_reserva = Integer.parseInt(cadena[3]);
        this.posy_reserva = Integer.parseInt(cadena[2]);
        this.galletas = Integer.parseInt(cadena[6]);
        this.posx_F_rojo = Integer.parseInt(cadena[5]);
        this.posy_F_rojo = Integer.parseInt(cadena[4]);
        this.posx_F_marron = Integer.parseInt(cadena[5]);
        this.posy_F_marron = Integer.parseInt(cadena[4]);
        this.velocidad = Integer.parseInt(cadena[7]);

        int k = 8;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                this.escenario[i][j] = Byte.parseByte(cadena[k]);
                k++;

            }
        }
    }

    int getColumnas() {
        return this.escenario[0].length;
    }

    int getFilas() {
        return this.escenario.length;
    }

    byte valor(int fila, int col) {
        return this.escenario[fila][col];
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public int getPosx_F_rojo() {
        return posx_F_rojo;
    }

    public int getPosy_F_rojo() {
        return posy_F_rojo;
    }

    public int getDir_f_rojo() {
        return dir_f_rojo;
    }

    public int getPosx_F_marron() {
        return posx_F_marron;
    }

    public int getPosy_F_marron() {
        return posy_F_marron;
    }

    public int getDir_f_marron() {
        return dir_f_marron;
    }

    public int getPuntuacion() {
        return Puntuacion;
    }

    public int getVelocidad() {
        return velocidad;
    }
    
    

    public void move() {
        posx = posx;
        posy = posy;

    }

    public int getPosx_reserva() {
        return posx_reserva;
    }

    public int getPosy_reserva() {
        return posy_reserva;
    }

    public void move_down() {
        int old = this.posy;
        posy++;

        if (posy >= getFilas()) {
            posy = 0;
        }
        if (this.valor(this.posy, this.posx) == 1) {
            this.posy = old;
        }
    }

    public void move_Up() {
        int old = this.posy;
        posy--;
        if (posy == -1) {
            posy = getColumnas();
        }
        if (this.valor(this.posy, this.posx) == 1) {
            this.posy = old;
        }

    }

    public void move_Left() {
        int old = this.posx;
        posx--;
        if (this.posx == -1) {
            this.posx = getColumnas() - 1;
        } else if (this.valor(this.posy, this.posx) == 1) {
            this.posx = old;
        }

    }

    public void move_Right() {
        int old = this.posx;
        posx++;
        if (this.posx == this.getColumnas()) {
            this.posx = 0;
        } else if (this.valor(this.posy, this.posx) == 1) {
            this.posx = old;
        }

    }

    // movimiento fantasma rojo
    public void move_down_f_rojo() {
        int old = this.posy_F_rojo;
        Random r = new Random();
        int valorDado = r.nextInt(4) + 1; 
        posy_F_rojo++;
        if (posy_F_rojo >= getFilas()) {
            posy_F_rojo = 0;
        }
        if (this.valor(this.posy_F_rojo, this.posx_F_rojo) == 1) {
            this.posy_F_rojo = old;
            dir_f_rojo = valorDado;
        }
    }

    public void move_Up_f_rojo() {
        int old = this.posy_F_rojo;
        Random r = new Random();
        int valorDado = r.nextInt(4) + 1;
        posy_F_rojo--;
        if (posy_F_rojo == -1) {
            posy_F_rojo = getColumnas();
        }
        if (this.valor(this.posy_F_rojo, this.posx_F_rojo) == 1) {
            this.posy_F_rojo = old;
            dir_f_rojo = valorDado;
        }

    }

    public void move_Right_f_rojo() {
        int old = this.posx_F_rojo;
        Random r = new Random();
        int valorDado = r.nextInt(4) + 1; 
        posx_F_rojo++;
        if (this.posx_F_rojo == this.getColumnas()) {
            this.posx_F_rojo = 0;
        } else if (this.valor(this.posy_F_rojo, this.posx_F_rojo) == 1) {
            this.posx_F_rojo = old;
            dir_f_rojo = valorDado;
        }

    }

    public void move_Left_f_rojo() {
        int old = this.posx_F_rojo;
        Random r = new Random();
        int valorDado = r.nextInt(4) + 1;
        posx_F_rojo--;
        if (this.posx_F_rojo == -1) {
            this.posx_F_rojo = getColumnas() - 1;
        } else if (this.valor(this.posy_F_rojo, this.posx_F_rojo) == 1) {
            this.posx_F_rojo = old;
            dir_f_rojo = valorDado;
        }

    }

    // movimiento fantasma marron
    public void move_down_f_marron() {
        int old = this.posy_F_marron;
        Random r = new Random();
        int valorDado = r.nextInt(4) + 1;  
        posy_F_marron++;
        if (posy_F_marron >= getFilas()) {
            posy_F_marron = 0;
        }
        if (this.valor(this.posy_F_marron, this.posx_F_marron) == 1) {
            this.posy_F_marron = old;
            dir_f_marron = valorDado;
        }
    }

    public void move_Up_f_marron() {
        int old = this.posy_F_marron;
        Random r = new Random();
        int valorDado = r.nextInt(4) + 1; 
        posy_F_marron--;
        if (posy_F_marron == -1) {
            posy_F_marron = getColumnas();
        }
        if (this.valor(this.posy_F_marron, this.posx_F_marron) == 1) {
            this.posy_F_marron = old;
            dir_f_marron = valorDado;
        }

    }

    public void move_Right_f_marron() {
        int old = this.posx_F_marron;
        Random r = new Random();
        int valorDado = r.nextInt(4) + 1; 
        posx_F_marron++;
        if (this.posx_F_marron == this.getColumnas()) {
            this.posx_F_marron = 0;
        } else if (this.valor(this.posy_F_marron, this.posx_F_marron) == 1) {
            this.posx_F_marron = old;
            dir_f_marron = valorDado;
        }

    }

    public void move_Left_f_marron() {
        int old = this.posx_F_marron;
        Random r = new Random();
        int valorDado = r.nextInt(4) + 1;
        posx_F_marron--;
        if (this.posx_F_marron == -1) {
            this.posx_F_marron = getColumnas() - 1;
        } else if (this.valor(this.posy_F_marron, this.posx_F_marron) == 1) {
            this.posx_F_marron = old;
            dir_f_marron = valorDado;
        }

    }

    public void comer(int x, int y) {
        this.escenario[y][x] = 0;
        galletas = galletas - 1;
        Puntuacion = Puntuacion + 2;

    }

    public byte Valor_Posicion(int x, int y) {
        byte i = this.escenario[y][x];
        return i;
    }

    public int getGalletas() {
        return galletas;
    }

    public void setDir_f_rojo(int dir_f_rojo) {
        this.dir_f_rojo = dir_f_rojo;
    }

    public void setDir_f_marron(int dir_f_marron) {
        this.dir_f_marron = dir_f_marron;
    }

    public void setPuntuacion(int Puntuacion) {
        this.Puntuacion = Puntuacion;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

}
