package es.iespolitecnicomalaga.spaceracer;


import java.util.ArrayList;

/**
 * Clase DisparoEnemigo. Representa a uno de sus disparos
 */

public class Obstaculo extends ObjetoVolador {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////



    //Las constantes para definir la velocidad de estos "obstáculos"
    static private final float VELOCIDAD_INICIAL_Y = -2.0f;
    static private final float VELOCIDAD_INICIAL_X = 0.0f;
    protected ArrayList<Obstaculo> lluviaAsteroides;

    public boolean haPuntuado = false;


    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////


    //CONSTRUCTORES
    public Obstaculo(float nuevaPosX, float nuevaPosY, Dibujable miDibujo) {
        super(nuevaPosX,nuevaPosY,VELOCIDAD_INICIAL_X,VELOCIDAD_INICIAL_Y,miDibujo);
    }

    //Resto de comportamiento*/

    private static final String ANCHO = "";
    private static final String ALTO = "";

    public Obstaculo(float nuevaPosX, float nuevaPosY, float nuevaVelX, float nuevaVelY, Dibujable miDibujo) {
        super(nuevaPosX, nuevaPosY, nuevaVelX, nuevaVelY, miDibujo);
        lluviaAsteroides = new ArrayList<>();


        //esto genera posiciones aleatorias de los asteroides
        //this.posX = (int) (Math.random() * 390);
        //this.posY = (int) (Math.random() * 390);
    }


    public void avanzar() {
        posY += velY;
    }

    void mover() {
        posY += VELOCIDAD_INICIAL_Y;
    }

    public void pintarse(Lienzo miSB) {
        miSB.draw(img, posX-anchoDiv2, posY-altoDiv2);
    }}
