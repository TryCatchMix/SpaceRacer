package es.iespolitecnicomalaga.spaceracer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GdxGameSpaceRacer extends ApplicationAdapter {

	private ControladorJuego vgControlador;

	@Override
	public void create () {
		vgControlador = ControladorJuego.getSingleton();
	}

	@Override
	public void render () {
		vgControlador.render();
	}
	
	@Override
	public void dispose () {
		vgControlador.dispose();
	}
}
