package com.evil.pack;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evil.pack.assets.Assets;
import com.evil.pack.screens.MenuScreen;

public class EvilPackGame extends Game {

	public enum GAME_STATE{
		PLAYING,
		MENU
	}

	public static float WIDTH = 2520; //pixels
	public static float HEIGHT = 4160;

	public static float WORLD_HEIGHT = 20; // the unit is meters

	public Assets assets;
	public GAME_STATE gameState;

	public static SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		this.assets = new Assets();
		this.batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
		this.assets.dispose();
	}
}
