package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import javax.naming.ldap.Control;

/**
 * Clase que implementa el controlador de nuestro videojuego. Realizará la gestión de la entrada del teclado,
 * la gestión de la inicialización, del control del estado del videojuego
 *
 * Implementa patrón SINGLETON
 */
public class ControladorJuego {

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //SINGLETON
    public static ControladorJuego miSingleton;


    //CONSTANTES

    public static final int PANTALLA_ANCHO = 1000;
    public static final int PANTALLA_ALTO = 600;

    //RESTO DEL ESTADO

    //Variable para saber el estado en el que estamos:
    // 0. Pantalla inicio
    // 1. Jugando
    // 2. Final de partida
    public enum EstadoJuego {PANTALLA_INICIO, JUGANDO, FINAL_PARTIDA};

    protected EstadoJuego miEstadoJuego;

    //Escenas
    protected EscenarioInicio escenaInicio;
    protected EscenarioJuego escenaJuego;
    protected EscenarioFinal escenaFinPartida;

    protected Escenario escenaActiva;

    //Tendremos un Lienzo para dibujar en la pantalla
    protected Lienzo miLienzo;

    //Camara para tener en Android la misma resolución que en el Desktop
    private OrthographicCamera camera;

    //Declaramos la puntuacion para que aparezca en los escenarios
    private PanelNumeros puntuacion;

    //Para que siempre guarde el mismo espacio con el borde aunque aumente la puntuacion
    private int tamañoPuntuacionMostradaActual = 1;

    //Tamaño constante de los digitos de la puntuacion
    private final int tamañoDigitosPuntuacion=30;

    //Tendremos un SpriteBatch para dibujar en la pantalla
    protected SpriteBatch batch;


    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////


    //El constructor creará a su vez: personajes iniciales y fondo
    private ControladorJuego() {

        miLienzo = new LienzoAdaptador(new SpriteBatch());

        camera = new OrthographicCamera();
        camera.setToOrtho(false, PANTALLA_ANCHO, PANTALLA_ALTO);
        escenaInicio = new EscenarioInicio(PANTALLA_ANCHO,PANTALLA_ALTO,miLienzo,camera);
        batch = new SpriteBatch();


        //Inicializamos la puntuacion y la posicionamos en la escena
        puntuacion = new PanelNumeros(PANTALLA_ANCHO - tamañoDigitosPuntuacion,PANTALLA_ALTO - tamañoDigitosPuntuacion,tamañoDigitosPuntuacion);
        puntuacion.setData(100);

        //ESTO ELIMINAR
        //escenaJuego = escenaInicio;
        //escenaFinPartida = escenaJuego;

        //ESTO DESCOMENTAR
        escenaJuego = new EscenarioJuego(PANTALLA_ANCHO,PANTALLA_ALTO,miLienzo,camera);
        escenaFinPartida = new EscenarioFinal(PANTALLA_ANCHO,PANTALLA_ALTO,miLienzo,camera);
        escenaActiva = escenaInicio;
        miEstadoJuego = EstadoJuego.PANTALLA_INICIO;
        Gdx.input.setInputProcessor(escenaActiva);
    }

    public static ControladorJuego getSingleton() {
        if (miSingleton == null) {
            miSingleton = new ControladorJuego();
        }
        return miSingleton;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    //Resto de comportamientos
    /////////////////////////////////////////////////////////////////////////////////////////

    //El controlador tendrá que saber que pasa cuando hay que pintarse
    public void render() {

        //Ahora renderizo según la escena Activa
        escenaActiva.render();

        //Se inicia el batch
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        //Con esto hacemos que solo aparezca la puntuacion en la EscenaJuego.
        //*******Ahora mismo esta comentado para que podamos verla ya que aun no esta terminada la
        //EscenaJuego********//

        /*if(escenaActiva == escenaJuego)*/

        //Aqui conseguimos que la puntuacion siempre respete el espacio con el borde de la pantalla
        //por mucho que aumente las cifras
        if(puntuacion.listaMostrada.size() > tamañoPuntuacionMostradaActual)
        {
            tamañoPuntuacionMostradaActual = puntuacion.listaMostrada.size();
            puntuacion.fPosX = PANTALLA_ANCHO - (tamañoPuntuacionMostradaActual * tamañoDigitosPuntuacion);
        }

        puntuacion.pintarse(batch);

        batch.end();
    }

    //El controlador tendrá que saber como finalizar y cerrar recursos
    public void dispose() {

        escenaJuego.dispose();
        escenaInicio.dispose();
        escenaFinPartida.dispose();
        puntuacion.dispose();
    }

    public void cambiarEscena(EstadoJuego nuevoEstado) {
        miEstadoJuego = nuevoEstado;
        switch (miEstadoJuego) {
            case JUGANDO: escenaActiva = escenaJuego;
                break;
            case PANTALLA_INICIO: escenaActiva = escenaInicio;
                break;
            case FINAL_PARTIDA: escenaActiva = escenaFinPartida;
                break;
        }

        //Importante, el control de los eventos de entrada, lo toma la escenaActiva en este momento
        Gdx.input.setInputProcessor(escenaActiva);
    }
}
