package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class EscenarioJuego extends Escenario{

    private boolean estoyMuerto;
    private int contador = 30;
    //Definimos los objetos que van a aparecer en esta escena
    NavesAliadas miNave;

    /*Clase para controlar que no se vaya por los bordes la nave*/
    Mando miMando;

    //Almacenamos los asteroides en una array list
    protected ArrayList<Obstaculo> lluviaAsteroides;

    //Definimos los obstaculos como los asteroides
    Obstaculo asteroide;

    int voyAEstarMuerto = 0;

    //Definimos la puntuacion
    public PanelNumeros puntuacion;

    //Definimos el objeto astronauta
    Astronauta astronauta;
    int aparicionBonus=900;

    //Musica del juego
    //private Music musicaJuego;

    //Comportamiento
    public EscenarioJuego(int iAnchoPant, int iAltoPant, Lienzo miLienzo, Camera miCamara) {
        super(iAnchoPant,iAltoPant,miLienzo,miCamara);



    }

    @Override
    public void create(){
        super.create();

        //Añadimos los objetos que apareceran en pantalla con su imagen

        //Nave Espacial
        Dibujable miDibujoNormal = new DibujableAdaptador(new Texture("aliada5azul.png"));
        Dibujable miDibujoAsteroide = new DibujableAdaptador(new Texture("asteroid.png"));
        Dibujable miExplosion = new DibujableAdaptador(new Texture("explosion.png"));
        miNave = new NavesAliadas(this.iAnchoPant/2, this.iAltoPant/8, this.iAnchoPant, miDibujoNormal, miExplosion);

        //Inicializamos la puntuacion y la posicionamos en la escena
        puntuacion = new PanelNumeros( ControladorJuego.PANTALLA_ANCHO - ControladorJuego.tamañoDigitosPuntuacion,ControladorJuego.PANTALLA_ALTO - ControladorJuego.tamañoDigitosPuntuacion,ControladorJuego.tamañoDigitosPuntuacion);
        puntuacion.setData(0);
        //Mando
        miMando = new Mando();

        //Asteroide
        //Queremos que los asteroides aparezcan en una posicion aleatoria en el ejeX y que aparezca arriba por fuera de la pantalla.
        lluviaAsteroides = new ArrayList<>();
        int i = 0;
        while (i < Math.random()* (21 - 2) + 2){
            asteroide = new Obstaculo((float) Math.random()*this.iAnchoPant,this.iAltoPant+10,0,-(float)(Math.random()* (4 - 1) + 1), miDibujoAsteroide);
            lluviaAsteroides.add(asteroide);
            i++;
        }

        //Astronauta
        Dibujable miAstronauta = new DibujableAdaptador(new Texture("astronauta.png"));
        astronauta = new Astronauta(iAnchoPant,iAltoPant, miAstronauta);

        //Añadimos nuestros objetos a la ArrayList heredada de Escenario
        misObjetosEnPantalla.add(miNave);
        misObjetosEnPantalla.addAll(lluviaAsteroides);
        misObjetosEnPantalla.add(astronauta);


    }

    Dibujable miDibujoNormal = new DibujableAdaptador(new Texture("asteroid.png"));

    @Override
    protected void controlEstado() {
        super.controlEstado();

        //Aquí debemos de animar los objetos: moverlos. Controlar las colisiones. Controlar si hemos finalizado, sumar puntos

        //La escena parallax

        contador--;
        if (contador == 0){
            asteroide = new Obstaculo((float) Math.random()*this.iAnchoPant,this.iAltoPant+10,0,-(float)(Math.random()* (4 - 1) + 1), miDibujoNormal);
            lluviaAsteroides.add(asteroide);
            misObjetosEnPantalla.addAll(lluviaAsteroides);
            contador = 30;

        }

        for (Obstaculo asteroide : lluviaAsteroides) {
            asteroide.moverse();
            if (miNave.colisiona(asteroide) && miNave!=null && voyAEstarMuerto <= 0){
                miNave.explota();
                miNave.velX = 0;
                voyAEstarMuerto = 180;
            }

            if (voyAEstarMuerto <= 0 && asteroide.getPosY() < 0 && asteroide.haPuntuado == false) {
                puntuacion.incrementa(1);
                asteroide.haPuntuado = true;
            }

            if (asteroide.getPosX() <0 || estoyMuerto) {
                asteroide.dispose();
            }
        }

        //hemos puesto un contador para dar tiempo entre la muerte y el cambio de escenario
        if(voyAEstarMuerto > 0)
        {
            voyAEstarMuerto--;
            if(voyAEstarMuerto <= 0)
                estoyMuerto = true;
        }

        miPE.animar();
        if (miMando.chocaBorde(miNave.posX + miNave.velX)){

        }else {
            miNave.moverse();
        }

        //colisiones

        //Si la nave colisiona con un asteroide explota y activa estoymuerto


        //CON ESTO SE CAMBIAAAAAAAAAAAAAAAAAA DE ESCENAAAAAAAAAAAAAAAAAAA
        if (estoyMuerto){
            ControladorJuego.getSingleton().paramusicaJuego();
            ControladorJuego.getSingleton().cambiarEscena(ControladorJuego.EstadoJuego.FINAL_PARTIDA);
            puntuacion.fPosY = (iAltoPant /2) +120;
            puntuacion.fPosX = (iAnchoPant / 2)-100;
            puntuacion.fAncho = 100;
        }

        if (asteroide != null){
            asteroide.mover();
        }

        if (astronauta != null && !miMando.chocaBorde(astronauta.posX + astronauta.velX))
            astronauta.moverse();
        else
            astronauta.cambioMovimiento();

        aparicionBonus--;
        if (aparicionBonus <= 0){
            astronauta.posY = iAltoPant;
            astronauta.posX = (float)Math.random() * iAnchoPant;
            aparicionBonus=900;
        }
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (screenX <= iAnchoPant/2)
            miNave.setVelX(-3);

        if (screenX > iAnchoPant/2)
            miNave.setVelX(3);

        return super.touchDown(screenX, screenY, pointer, button);
    }

    //Aqui se pone todas las variables iniciales a su estado inicial
    // y limpiamos los arrays. Despues se vuelven a crear
    public void reiniciarAEstadoInicial() {
        estoyMuerto = false;
        //lluviaAsteroides.clear();
        misObjetosEnPantalla.clear();
        ControladorJuego.tamañoPuntuacionMostradaActual = 1;
        create();
    }
}