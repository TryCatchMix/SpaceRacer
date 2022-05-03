package es.iespolitecnicomalaga.spaceracer;

public class Astronauta extends ObjetoVolador{
//ESTADO

    static private final float VELOCIDAD_INICIAL_X = -5.0f;

    int iContador;
    private static final float tasa_Cambio =30;
    private static final float velXConstante =-1.0f;

    //COMPORTAMIENTO

    //Constructor
    public Astronauta(int anchoPantalla, int altoPantalla, Dibujable NOMBRE_SPRITE) {
        super((float)Math.random()*(float)anchoPantalla,altoPantalla-10,VELOCIDAD_INICIAL_X,velXConstante,NOMBRE_SPRITE);

    }

    //Movimiento
    @Override
    public void moverse() {

        iContador++;

        if (iContador==tasa_Cambio){

            cambioMovimiento();
            iContador=0;
        }


        super.moverse();
    }

    public void cambioMovimiento()
    {
        if(velX==velXConstante){
            velX=-velXConstante;
        }else {
            velX=velXConstante;
        }
    }
}
