package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Clase Fondo. Se encarga de dibujar y avanzar una simulación de "camara" por una imagen dada
 * En una escena tendremos varios fondos.
 */

public class Fondo {
    /////////////////////////////////////////////////////////////////////////////////////
    //
    //ESTADO
    //
    /////////////////////////////////////////////////////////////////////////////////////
    private Dibujable imgFondo; //Nuestra imagen a pintar.

    private float velocidadX;
    private float velocidadY;

    private float posX;
    private float posY;
    private int anchoVentana;
    private int altoVentana;
    private int ancho;
    private int alto;

    /////////////////////////////////////////////////////////////////////////////////////
    //
    //COMPORTAMIENTOS
    //
    /////////////////////////////////////////////////////////////////////////////////////

    //CONSTRUCTOR/ES
    public Fondo(String fichero, float velImagenX, float velImagenY, float posIniX, float posIniY, int anV, int alV) {

        velocidadX = velImagenX;
        velocidadY = velImagenY;
        posX = posIniX;
        posY = posIniY;

        altoVentana = alV;
        anchoVentana = anV;
        imgFondo = new DibujableAdaptador(new Texture(fichero));
        ancho = imgFondo.getWidth();
        alto = imgFondo.getHeight();


    }

    //RESTO DE COMPORTAMIENTOS


    //método para avanzar la cámara
    public void avanzar() {

        posX = posX + velocidadX;
        posY = posY + velocidadY;
        if (posX +anchoVentana >= ancho && velocidadX >0.0f) {
            //nos hemos pasado del final del fondo, volvemos al principio
            posX = posX + anchoVentana - ancho;
        }
        if (posX <=0 && velocidadX <0.0f){
            posX = ancho - anchoVentana;
        }
        if (posY +altoVentana >= alto && velocidadY >0.0f) {
            posY = posY + altoVentana - alto;
        }
        if (posY <=0 && velocidadY <0.0f) {
            posY = alto - altoVentana;
        }
    }

    //Comportamiento para pintar la ventana del fondo a utilizar
    public void pintate(Lienzo miSB) {
        TextureRegion ventana; //Nos sirve para mapear la imagen y pintarla.

        ventana = new TextureRegion(imgFondo.getTexture(),(int)posX,(int)posY,anchoVentana,altoVentana);

        miSB.drawRegion(ventana,0,0);


    }


    //Método para liberar recursos
    public void dispose() {
        imgFondo.dispose();
    }


    public int getAltoFondo() {
        return (int)imgFondo.getHeight();
    }

    public int getAnchoFondo() {
        return (int)imgFondo.getWidth();
    }
}















