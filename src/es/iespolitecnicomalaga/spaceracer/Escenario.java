package es.iespolitecnicomalaga.spaceracer;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Escenario extends InputAdapter {
    /**
     * Clase contenedor. Capa Controladores. Contendrá los elementos del modelo y de la vista que sean necesarios en cada
     * "pantalla" del juego. Instanciada por ControladorJuego
     */

    //ESTADO
    protected int iAnchoPant, iAltoPant;
    Lienzo lienzoEscena;
    Camera camaraEscena;
    Music miMusica;
    ArrayList<ObjetoVolador> misObjetosEnPantalla;
    ParallaxEscena miPE;

    //COMPORTAMIENTO

    public Escenario(int iAnchoPant, int iAltoPant, Lienzo miLienzo, Camera miCamara) {
        this.iAnchoPant = iAnchoPant;
        this.iAltoPant = iAltoPant;
        lienzoEscena = miLienzo;
        camaraEscena = miCamara;
        misObjetosEnPantalla = new ArrayList<>();
        miMusica = null;
        miPE = new ParallaxEscena(false);
        this.create();

    }

    public void create () {
        //Aquí añadimos los objetos que utilizaremos al principio del videojuego
        //Como base, no hacemos nada. Nuestros hijos, sí crearán objetos
    }

    protected void controlEstado() {
        //No hacemos nada. Esta para ser heredada
    }

    public void render () {

        //primero, ¿Qué está pasando? El mundo se mueve, colisiona, etc...
        this.controlEstado();


        //Ahora refrescamos la pantalla. Sólo pintar
        //Borramos para eliminar imágenes previas
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //preparamos el batch
        lienzoEscena.setProjectionMatrix(camaraEscena.combined);
        lienzoEscena.begin();

        miPE.render(lienzoEscena);
        //pintamos los objetos sin más
        for (ObjetoVolador miOV:misObjetosEnPantalla) {
            miOV.pintarse(lienzoEscena);
        }

        lienzoEscena.end();

    }

    public void dispose () {
        for (ObjetoVolador miOV:misObjetosEnPantalla) {
            miOV.dispose();
        }
        if (miMusica != null)
            miMusica.dispose();
    }

    //Aquí no sabemos que se debe de hacer para tratar los "clicks" de dedos o ratón

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
        //return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
        //return super.touchUp(screenX, screenY, pointer, button);
    }
}
