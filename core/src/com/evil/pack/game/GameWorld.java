package com.evil.pack.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.evil.pack.EvilPackGame;
import com.evil.pack.assets.Assets;
import com.evil.pack.controller.Controller;
import com.evil.pack.listener.B2dContactListener;
import com.evil.pack.model.Enemy;
import com.evil.pack.model.Node;
import com.evil.pack.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    private EvilPackGame packGame;
    private World physicWorld;
    private Player player;
    private Stage stage;
    private List<Enemy> enemies;
    private List<Node> board = new ArrayList<Node>();
    private float worldWidth;
    private int score;
    private Controller controller;

    public GameWorld(EvilPackGame packGame){
        this.packGame = packGame;
        this.physicWorld = new World(new Vector2(0,-9.8f),false);
        this.physicWorld.setContactListener(new B2dContactListener());
        this.player = new Player(packGame,physicWorld,packGame.assets.manager.get(Assets.player, Texture.class),
                0.25f,EvilPackGame.WORLD_HEIGHT/2,1,1);

        float ratio = (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        this.worldWidth = EvilPackGame.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth,EvilPackGame.WORLD_HEIGHT));
        this.controller = new Controller();


        this.stage.addActor(player);


        for (int i = 0; i < 10; i++ ) {

            for (int j = 0; j < 7; j++ ) {

                if(isRightNum(i) || isRightNum(j)) {

                    this.board.add(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                            j*1.8f+0.2f, i*1.8f+3,1,1));

                }
            }
        }



        for(Node n : board) {

            this.stage.addActor(n);
        }

        this.initBoard();
        this.initEnemies();


        this.score = 0;
    }


    boolean isRightNum(int num) {

        return (num == 0 || num == 3 || num == 6 || num == 9);
    }

    public void handleInput(){
        if(controller.isRightPressed()) {

        }

    }



    private void drawApples()
    {

    }

    public void render(){
        this.stage.draw();
        this.controller.draw();
    }

    public void update(){
        handleInput();
    }

    void initBoard() {

    }

    void initEnemies() {

    }

}