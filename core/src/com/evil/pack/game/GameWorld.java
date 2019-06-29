package com.evil.pack.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.evil.pack.EvilPackGame;
import com.evil.pack.assets.Assets;
import com.evil.pack.listener.B2dContactListener;
import com.evil.pack.model.Enemy;
import com.evil.pack.model.Node;

import com.evil.pack.controller.Controller;
import com.evil.pack.model.Player;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameWorld {
    private EvilPackGame packGame;
    private World physicWorld;
    private Player player;
    private Stage stage;
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<Node> board = new ArrayList<Node>();
    private float worldWidth;
    private int score;
    private Controller controller;
    private int iteratatble[][] =  new int[10][7];

    public GameWorld(EvilPackGame packGame){
        this.packGame = packGame;
        this.physicWorld = new World(new Vector2(0,-9.8f),false);
        this.physicWorld.setContactListener(new B2dContactListener());



        float ratio = (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        this.worldWidth = EvilPackGame.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth,EvilPackGame.WORLD_HEIGHT));

        this.controller = new Controller();
        this.initBoard();
        this.initEnemies();
        this.player = new Player(packGame,/*physicWorld,*/
                packGame.assets.manager.get(Assets.player, Texture.class),
                this.controller,
                this.board,this.iteratatble,this.board.get(0),1.5f,1.5f);
        this.stage.addActor(player);


        this.score = 0;
    }


    public void handleInput(){


    }

    public void render(){
        this.stage.draw();
        this.controller.draw();
    }

    public void update(){
//        if(controller.isRightPressed()) {
//            System.out.println("right pressed");
//        }
        this.stage.act();

    }

    private boolean isRightNum(int num) {

        return (num == 0 || num == 3 || num == 6 || num == 9);
    }


    private void initBoard()
    {
        for (int[] row: iteratatble)
            Arrays.fill(row, -1);

        Texture appleTexture = packGame.assets.manager.get(Assets.apple, Texture.class);
        int id = 0;
        for (int i = 0; i < 10; i++ )
        {
            for (int j = 0; j < 7; j++ )
            {
                if(isRightNum(i) || isRightNum(j))
                {
                    Node node = new Node(packGame,appleTexture, j, i,1,1, id);
                    this.iteratatble[i][j] = id;
                    this.board.add(node);
                    id++;
                }
            }
        }
        for(Node n : board) {
            this.stage.addActor(n);
        }
    }

    void initEnemies() {
        Texture texture = packGame.assets.manager.get(Assets.enemy, Texture.class);

        for (int i = 0; i < 30; i+=4) {
            Node start_node = this.board.get(i);
            enemies.add(new Enemy(packGame, texture, this.board, this.iteratatble,   start_node));

        }
        for(Enemy e : enemies) {
            this.stage.addActor(e);
        }
    }

}