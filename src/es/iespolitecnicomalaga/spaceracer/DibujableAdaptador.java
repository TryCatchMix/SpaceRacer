package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.graphics.Texture;

public class DibujableAdaptador implements Dibujable {

    //ESTADO
    protected Texture miImg;


    //Comportamiento

    public DibujableAdaptador(Texture miImg) {
        this.miImg = miImg;
    }

    @Override
    public int getWidth() {
        return miImg.getWidth();
    }

    @Override
    public int getHeight() {
        return miImg.getHeight();
    }

    @Override
    public Texture getTexture() {
        return miImg;
    }

    @Override
    public void dispose() {
        miImg.dispose();
    }

    @Override
    public boolean isNullTexture() {
        return (miImg == null);
    }
}
