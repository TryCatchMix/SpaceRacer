package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Matrix4;

public interface Lienzo {

    void draw(Dibujable miDibujo, float fAncho, float fAlto);
    void drawRegion(TextureRegion miRegion, float fAncho, float fAlto);
    void setProjectionMatrix(Matrix4 combined);
    void begin();
    void end();
}
