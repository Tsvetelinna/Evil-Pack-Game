package com.evil.pack.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.evil.pack.EvilPackGame;
import com.evil.pack.assets.Assets;

public class MenuScreen implements Screen {

    private EvilPackGame EvilPackGame;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture menuBackground;
    private BitmapFont font;

    public MenuScreen(EvilPackGame EvilPackGame){
        this.EvilPackGame = EvilPackGame;
    }

    @Override
    public void show() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,EvilPackGame.WIDTH,EvilPackGame.HEIGHT);
        //this.camera.position.set(-0)
        this.camera.update();
        this.EvilPackGame.assets.load();
        while(!this.EvilPackGame.assets.manager.update())
        {
            System.out.println("Loading: " + this.EvilPackGame.assets.manager.getProgress());
        }
        this.menuBackground = this.EvilPackGame.assets.manager.get(Assets.menu_background, Texture.class);
        this.font = new BitmapFont();

        this.font.getData().scale(8);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0/255f, 51/255f, 102/255f, 1); // 	0, 51, 102
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();
        batch.draw(menuBackground,0,0, this.EvilPackGame.WIDTH, this.EvilPackGame.HEIGHT);
        batch.end();

        if(Gdx.input.justTouched()){
            EvilPackGame.gameState = com.evil.pack.EvilPackGame.GAME_STATE.PLAYING;
            EvilPackGame.setScreen(new GameScreen(EvilPackGame));
        }
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}