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
import com.evil.pack.game.GameWorld;

public class GameScreen implements Screen {

    private EvilPackGame packGame;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private GameWorld gameWorld;
    private Texture background;
    private BitmapFont font;

    public GameScreen(EvilPackGame packGame) {
        this.packGame = packGame;
    }

    @Override
    public void show() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false,EvilPackGame.WIDTH,EvilPackGame.HEIGHT);
        this.gameWorld = new GameWorld(this.packGame);
        this.background = packGame.assets.manager.get(Assets.background, Texture.class);
        this.font = new BitmapFont();

        this.font.getData().scale(8);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(55/255f, 51/255f, 102/255f, 1); // 	0, 51, 102
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background,0,0, this.packGame.WIDTH, this.packGame.HEIGHT);
        batch.end();
        gameWorld.render();
        batch.begin();
        //drawScore(gameWorld.getScore(),batch);
        batch.end();

        gameWorld.update();
        camera.update();
    }

    private void drawScore(int score,SpriteBatch batch){

        GlyphLayout glyphLayout = new GlyphLayout();
        String item = "Score: " + score;
        glyphLayout.setText(font,item);
        float w = glyphLayout.width;
        font.draw(batch, glyphLayout, camera.position.x - w/2, EvilPackGame.HEIGHT - 50);
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
        batch.dispose();
    }
}
