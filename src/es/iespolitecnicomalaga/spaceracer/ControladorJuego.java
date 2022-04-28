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

    //ESTO ELIMINAR
    protected EscenarioInicio escenaJuego;
    protected EscenarioInicio escenaFinPartida;


    //ESTO DESCOMENTAR
    //protected EscenarioJuego escenaJuego;
    //protected EscenarioFinal escenaFinPartida;

    protected Escenario escenaActiva;

    //Tendremos un Lienzo para dibujar en la pantalla
    protected Lienzo miLienzo;

    //Camara para tener en Android la misma resolución que en el Desktop
    private OrthographicCamera camera;



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

        //ESTO ELIMINAR
        escenaJuego = escenaInicio;
        escenaFinPartida = escenaJuego;

        //ESTO DESCOMENTAR
        //escenaJuego = new EscenarioJuego(PANTALLA_ANCHO,PANTALLA_ALTO,miLienzo,camera);
        //escenaFinPartida = new EscenarioFinPartida(PANTALLA_ANCHO,PANTALLA_ALTO,miLienzo,camera);
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

    }

    //El controlador tendrá que saber como finalizar y cerrar recursos
    public void dispose() {

         escenaJuego.dispose();
         escenaInicio.dispose();
         escenaFinPartida.dispose();

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

