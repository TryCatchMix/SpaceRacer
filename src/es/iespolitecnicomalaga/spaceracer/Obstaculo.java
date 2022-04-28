package es.iespolitecnicomalaga.spaceracer;


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
    static private final float VELOCIDAD_INICIAL_Y = -4.0f;
    static private final float VELOCIDAD_INICIAL_X = 0.0f;


    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////


    //CONSTRUCTORES
    public Obstaculo(float nuevaPosX, float nuevaPosY, Dibujable miDibujo) {
        super(nuevaPosX,nuevaPosY,VELOCIDAD_INICIAL_X,VELOCIDAD_INICIAL_Y,miDibujo);
    }

    //Resto de comportamiento
}