package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;

public class LienzoAdaptador implements Lienzo{
    //ESTADO
    protected SpriteBatch miSB;


    //Comportamiento

    public LienzoAdaptador(SpriteBatch miSB) {
        this.miSB = miSB;
    }

    @Override
    public void draw(Dibujable miDibujo, float fAncho, float fAlto) {
        miSB.draw(miDibujo.getTexture(),fAncho,fAlto);
    }

    @Override
    public void drawRegion(TextureRegion miRegion, float fAncho, float fAlto) {
        miSB.draw(miRegion,fAncho,fAlto);
    }

    @Override
    public void setProjectionMatrix(Matrix4 combined) {
        miSB.setProjectionMatrix(combined);
    }

    @Override
    public void begin() {
        miSB.begin();
    }

    @Override
    public void end() {
        miSB.end();
    }
}
