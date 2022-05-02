package es.iespolitecnicomalaga.spaceracer;

/**
 * Clase NavesAliadas. Representa a una nave principal. Estas naves pueden disparar
 * y también explotan si colisionan, y son manejadas por jugadores a través del teclado
 * Esta clase hereda de la clase NaveEspacial
 */
public class NavesAliadas extends NaveEspacial {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////


    static private final float VELOCIDAD_INICIAL_Y = 0.0f;
    static private final float VELOCIDAD_INICIAL_X = 0.0f;
    int i = 0;

    //Ancho de la pantalla, para no movernos fuera...
    private int anchoPant;

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////


    //CONSTRUCTORES


    public NavesAliadas(float nuevaPosX,float nuevaPosY, int anchoPantalla, Dibujable miDibujoNormal, Dibujable miDibujoExplosion) {
        super(nuevaPosX, nuevaPosY, VELOCIDAD_INICIAL_X, VELOCIDAD_INICIAL_Y, miDibujoNormal, miDibujoExplosion);
        anchoPant = anchoPantalla;
    }

    //Esto se deberá cambiar para que muera cuando choque contra un asteroide, por ahora muere cada 10 segundos si descomentas
    public boolean estoyMuerto(){
        /*
        i++;
        if (i == 600){
            i = 0;
            return true;
        }
        return false;
        */
        return false;
    }

    //Resto de comportamiento

}
