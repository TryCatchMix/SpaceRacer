package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

public class EscenarioInicio extends Escenario {
    //ESTADO

    //Aquí definimos los objetos que estarán en la ventana inicial
    NavesAliadas miNave;
    Mando miMando;
    ObjetoVolador TFront;
    ObjetoVolador TBack;


    //COMPORTAMIENTO
    public EscenarioInicio(int iAnchoPant, int iAltoPant, Lienzo miLienzo, Camera miCamara) {
        super(iAnchoPant,iAltoPant,miLienzo,miCamara);
    }

    @Override
    public void create() {
        super.create();
        //Ahora ponemos aquí objetos y los añadimos al contenedor (arraylist) heredado de nuestro padre

        //Nave principal
        //Titulo principal
        Dibujable DibujoFront = new DibujableAdaptador(new Texture("sp_front.png"));
        TFront = new ObjetoVolador(this.iAnchoPant/2, this.iAltoPant/2, 1.2f, 1.0f, DibujoFront);

        //Fondo
        Dibujable DibujoBack = new DibujableAdaptador(new Texture("sp_back.png"));
        TBack = new ObjetoVolador(this.iAnchoPant/2, this.iAltoPant/2, 0, 0, DibujoBack);

        misObjetosEnPantalla.add(TBack);
        misObjetosEnPantalla.add(TFront);



        Dibujable miDibujoNormal = new DibujableAdaptador(new Texture("aliada5azul.png"));
        Dibujable miExplosion = new DibujableAdaptador(new Texture("explosion.png"));
        miNave = new NavesAliadas(this.iAnchoPant/2,this.iAltoPant/8,this.iAnchoPant,miDibujoNormal,miExplosion);

        misObjetosEnPantalla.add(miNave);
        miMando = new Mando();
        //Aquí ponemos más objetos que se pintarán al principio.
        //...

        //Y por último la música de este escenario
        miMusica = Gdx.audio.newMusic(Gdx.files.internal("intro.ogg"));
        miMusica.setLooping(true);
        miMusica.play();

    }

    //Aquí haremos override si es necesario

    //Los touchDown y touchUp, hay que ponerlos sí o sí


    @Override
    protected void controlEstado() {
        super.controlEstado();

        //Aquí debemos de animar los objetos: moverlos. Controlar las colisiones. Controlar si hemos finalizado, sumar puntos
        //La escena parallax
        miPE.animar();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        //¿Qué pasa si pulsan la pantalla, pues que nos vamos a la pantalla del juego... Hay que notificar al controlador
        //principal para que cambie el escenario
        miMusica.stop();



        //CON ESTO SE CAMBIAAAAAAAAAAAAAAAAAA DE ESCENAAAAAAAAAAAAAAAAAAA
        ControladorJuego.getSingleton().cambiarEscena(ControladorJuego.EstadoJuego.JUGANDO);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
