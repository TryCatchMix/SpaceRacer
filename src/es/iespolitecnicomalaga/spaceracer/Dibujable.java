package es.iespolitecnicomalaga.spaceracer;


import com.badlogic.gdx.graphics.Texture;

public interface Dibujable {
    int getWidth();
    int getHeight();
    Texture getTexture();
    boolean isNullTexture();

    void dispose();

}
