package com.evil.pack.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.evil.pack.EvilPackGame;
import com.evil.pack.assets.Assets;
import com.evil.pack.model.Enemy;
import com.evil.pack.model.Node;
import com.evil.pack.model.Player;

import java.util.List;

public class GameWorld {
    private EvilPackGame packGame;
    private World physicWorld;
    private Player player;
    private Stage stage;
    private List<Enemy> enemies;
    private List<Node> board;
    private float worldWidth;
    private int score;

    public GameWorld(EvilPackGame packGame){
        this.packGame = packGame;
        this.physicWorld = new World(new Vector2(0,-9.8f),false);
        this.physicWorld.setContactListener(new B2dContactListener());
        this.player = new Player(packGame,physicWorld,packGame.assets.manager.get(Assets.player, Texture.class),
                0.25f,EvilPackGame.WORLD_HEIGHT / 2,1,1);
        float ratio = (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        this.worldWidth = EvilPackGame.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth,EvilPackGame.WORLD_HEIGHT));

        this.stage.addActor(player);

        this.initBoard();
        this.initEnemies();

        this.score = 0;
    }

    public void render(){

    }

    public void update(){
       
    }

}