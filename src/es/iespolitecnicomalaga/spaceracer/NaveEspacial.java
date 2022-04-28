package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import jdk.nashorn.internal.runtime.ConsString;

/**
 * Clase NaveEspacial. Representa a una nave principal o enemiga. Estas naves pueden disparar
 * y también explotan si colisionan
 * Esta clase hereda de la clase Objeto Volador
 */
public class NaveEspacial extends ObjetoVolador {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //Sólo necesita una Texture más, la que pintamos cuando explote...
    //Y un semáforo (boolean) que indique si estamos integros o nos hemos chocado con
    //un disparo enemigo
    protected Dibujable imgExplosion;
    protected boolean explotar;
    protected int pasos;

    //Sonidos
    protected Sound sonidoExplotar;

    public static final int PASOS_EXP = 20;  //tenia 20


    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////


    //CONSTRUCTORES


    public NaveEspacial(float nuevaPosX,float nuevaPosY,float nuevaVelX, float nuevaVelY, Dibujable miDibujoNormal,Dibujable miDibujoExplosion) {
        super(nuevaPosX, nuevaPosY, nuevaVelX, nuevaVelY, miDibujoNormal);
        imgExplosion = miDibujoExplosion;
        sonidoExplotar = Gdx.audio.newSound(Gdx.files.internal("damage1.wav"));
        explotar = false;
        pasos = 0;

    }

    //Resto de comportamiento

    //Modificamos el método pintarse, para que en caso de necesitar pintar una explosión, lo haga
    @Override
    public void pintarse(Lienzo miSB) {
        if (explotar) {
            pasos++;
            if (pasos < PASOS_EXP) {
                miSB.draw(imgExplosion, posX - anchoDiv2, posY - altoDiv2);
            }
        } else {
            super.pintarse(miSB);
        }
    }


    public void explota() {
        explotar = true;
        sonidoExplotar.play();
        anchoDiv2 = imgExplosion.getWidth()/2.0f;
        altoDiv2 = imgExplosion.getHeight()/2.0f;
    }

    public int getPasosExplosion() {
        return pasos;
    }
}
