package com.evil.pack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evil.pack.assets.Assets;

public class EvilPackGame extends ApplicationAdapter {

	public static float WIDTH = 2520;
	public static float HEIGHT = 4160;

	public static float WORLD_HEIGHT = 20;
	public Assets assets;

	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		int col = 20;
		for(int i = 0; i < 7; i++) {
			int row = 980;
			for(int j = 0; j < 10; j++) {

				if(isRightNum(i) || isRightNum(j)) {
					batch.draw(img, col, row, 50, 50);
				}
				row -= 90;
			}

			col += 90;
		}

		batch.end();
	}

	boolean isRightNum(int num) {

		return (num == 0 || num == 3 || num == 6 || num == 9);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
