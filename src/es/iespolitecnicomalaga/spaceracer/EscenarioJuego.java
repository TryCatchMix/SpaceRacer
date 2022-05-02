package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

public class EscenarioJuego extends Escenario{

    //Definimos los objetos que van a aparecer en esta escena
    NavesAliadas miNave;

    /*Clase para controlar que no se vaya por los bordes la nave*/
    Mando miMando;

    Obstaculo asteroide;
    PanelNumeros puntuacion;

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
        Dibujable miExplosion = new DibujableAdaptador(new Texture("explosion.png"));
        miNave = new NavesAliadas(this.iAnchoPant/2, this.iAltoPant/8, this.iAnchoPant, miDibujoNormal, miExplosion);

        miMando = new Mando();
        //Asteroide
        //Dibujable dibujoAsteroides = new DibujableAdaptador(new Texture(""));
        //Queremos que los asteroides aparezcan en una posicion aleatoria en el ejeX y que aparezca arriba por fuera de la pantalla.
       // asteroide = new Obstaculo((float) Math.random()*this.iAnchoPant,this.iAltoPant+10, dibujoAsteroides);

        //Añadimos nuestros objetos a la ArrayList heredada de Escenario
        misObjetosEnPantalla.add(miNave);
        //misObjetosEnPantalla.add(asteroide);
    }

    @Override
    protected void controlEstado() {
        super.controlEstado();

        //Aquí debemos de animar los objetos: moverlos. Controlar las colisiones. Controlar si hemos finalizado, sumar puntos

        //La escena parallax
        miPE.animar();
        if (miMando.chocaBorde(miNave.posX)){

        }else {
            miNave.moverse();
        }

        //colisiones
        //CON ESTO SE CAMBIAAAAAAAAAAAAAAAAAA DE ESCENAAAAAAAAAAAAAAAAAAA
        if (miNave.estoyMuerto())
            ControladorJuego.getSingleton().cambiarEscena(ControladorJuego.EstadoJuego.FINAL_PARTIDA);

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (screenX >= 0 && screenX <= 200)
            miNave.setVelX(-3);

        if (screenX >= 800 && screenX <= 1000)
            miNave.setVelX(3);

        miNave.moverse();


        return super.touchDown(screenX, screenY, pointer, button);
    }

}