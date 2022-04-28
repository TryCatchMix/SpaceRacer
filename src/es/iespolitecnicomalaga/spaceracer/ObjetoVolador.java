package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Clase ObjetoVolador. Representa a cualquiera de nuestros sprites: nave principal, enemigos,
 * disparos, disparos enemigos.
 * De esta clase heredarán las demás clases
 */
public class ObjetoVolador {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //Posiciones en la pantalla. Pasamos a Float para poder realizar movimientos más suaves
    protected float posX;
    protected float posY;

    // Los objetos voladores tienen una velocidad en X y otra en Y
    protected float velX;
    protected float velY;

    //Necesitamos el ancho/2 y el alto/2. Se usará en muchas operaciones, por lo que interesa
    //tenerlo almacenado en el estado del objeto por eficiencia. No sería estrictamente
    //necesario, pero es una práctica aceptada por la mejora sustancial de eficiencia
    protected float anchoDiv2;
    protected float altoDiv2;

    //Tambien almacenaremos un sprite "texture" que es el aspecto real del objeto volador
    //Al menos, el principal. Si tiene más sprites, pues se implementarán en las clases
    //Hijas
    protected Dibujable img;

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTO
    //
    /////////////////////////////////////////////////////////////////////////////////////


    //CONSTRUCTORES


    public ObjetoVolador(float nuevaPosX,float nuevaPosY,float nuevaVelX, float nuevaVelY,Dibujable miDibujo) {
        posX = nuevaPosX;
        posY = nuevaPosY;
        velX = nuevaVelX;
        velY = nuevaVelY;
        img = miDibujo;
        anchoDiv2 = img.getWidth()/2.0f;
        altoDiv2 = img.getHeight()/2.0f;
    }


    //Resto de Comportamientos

    //Moverse por la pantalla
    public void moverse() {
        posX+=velX;
        posY+=velY;
    }

    //Pintarse, en un SpriteBatch (Es la zona de pantalla visible en LibGDX)
    public void pintarse(Lienzo miSB) {

        miSB.draw(img, posX-anchoDiv2, posY-altoDiv2);

    }


    //Getters para obtener información de posiciones y tamaños

    public float getPosX() {
        return posX;
    }

    public float getPosY() {
        return posY;
    }

    public float getAnchoDiv2() {
        return anchoDiv2;
    }

    public float getAltoDiv2() {
        return altoDiv2;
    }

    //Colisiones
    public boolean colisiona(ObjetoVolador otro) {
        //Columnas
        boolean resultado,colisionX,colisionY;

        colisionX = (Math.abs(posX - otro.getPosX()) <= (anchoDiv2 + otro.getAnchoDiv2()));
        colisionY = (Math.abs(posY - otro.getPosY()) <= (altoDiv2 + otro.getAltoDiv2()));
        resultado = colisionX && colisionY;

        return resultado;
    }

    public void setVelX(float newVel) {
        velX = newVel;
    }

    public void setVelY(float newVel) {
        velY = newVel;
    }

    public void dispose() {
        this.img.dispose();
    }
}
