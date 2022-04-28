package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;

public class EscenarioJuego extends Escenario{

    //Definimos los objetos que van a aparecer en esta escena
    NavesAliadas miNave;
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

        //Asteroide
        Dibujable dibujoAsteroides = new DibujableAdaptador(new Texture(""));
        //Queremos que los asteroides aparezcan en una posicion aleatoria en el ejeX y que aparezca arriba por fuera de la pantalla.
        asteroide = new Obstaculo((float) Math.random()*this.iAnchoPant,this.iAltoPant+10, dibujoAsteroides);

        //Contador de Puntos
        /*PanelNumeros puntuacion = new Texture(new Texture(""));
        puntuacion = new PanelNumeros(0,0,10);*/

        //Añadimos nuestros objetos a la ArrayList heredada de Escenario
        misObjetosEnPantalla.add(miNave);
        misObjetosEnPantalla.add(asteroide);
        //misObjetosEnPantalla.add(puntuacion);
    }

}
