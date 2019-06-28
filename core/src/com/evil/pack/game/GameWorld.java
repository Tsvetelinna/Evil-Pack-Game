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

    public GameWorld(EvilPackGame packGame){
        this.packGame = packGame;
        this.physicWorld = new World(new Vector2(0,-9.8f),false);
        this.physicWorld.setContactListener(new B2dContactListener());
        this.player = new Player(packGame,physicWorld,packGame.assets.manager.get(Assets.player, Texture.class),
                0.25f,EvilPackGame.WORLD_HEIGHT/2,1,1);

        float ratio = (float) Gdx.graphics.getHeight() / (float)Gdx.graphics.getWidth();
        this.worldWidth = EvilPackGame.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth,EvilPackGame.WORLD_HEIGHT));

        this.stage.addActor(player);


        for (int i = 0; i < 10; i++ ) {

            for (int j = 0; j < 7; j++ ) {

                if(isRightNum(i) || isRightNum(j)) {
                    Node node = new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                            j*1.8f+0.2f, i*1.8f+3,1,1);

                    if(i == 0 && j == 0) {
                        //Node 0 1
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                1*1.8f+0.2f, 0*1.8f+3,1,1));

                        //Node 1 0
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                0*1.8f+0.2f, 1*1.8f+3,1,1));
                    }

                     if(i == 0 && j == 6) {
                         //Node 0 5
                         node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                 5*1.8f+0.2f, 0*1.8f+3,1,1));

                         //Node 1 6
                         node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                 6*1.8f+0.2f, 1*1.8f+3,1,1));
                     }

                    if(i == 9 && j == 0) {
                        //Node 9 1
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                1*1.8f+0.2f, 9*1.8f+3,1,1));

                        //Node 8 0
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                0*1.8f+0.2f, 8*1.8f+3,1,1));
                    }


                    if(i == 9 && j == 6) {
                        //Node 9 5
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                5*1.8f+0.2f, 9*1.8f+3,1,1));

                        //Node 8 6
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                6*1.8f+0.2f, 8*1.8f+3,1,1));
                    }

                    if(i == 0 && j == 3) {

                        //Node 0 2
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                2*1.8f+0.2f, 0*1.8f+3,1,1));

                        //Node 0 4
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                4*1.8f+0.2f, 0*1.8f+3,1,1));

                        //Node 1 3
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                3*1.8f+0.2f, 1*1.8f+3,1,1));
                    }

                    if(i == 9 && j == 3) {

                        //Node 9 2
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                2*1.8f+0.2f, 9*1.8f+3,1,1));

                        //Node 9 4
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                4*1.8f+0.2f, 9*1.8f+3,1,1));

                        //Node 8 3
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                3*1.8f+0.2f, 8*1.8f+3,1,1));
                    }

                    if(j == 3 && (i == 3 || i == 6)) {

                        //Node i-1 3
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                3*1.8f+0.2f, (i-1)*1.8f+3,1,1));

                        //Node i+1 3
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                3*1.8f+0.2f, (i+1)*1.8f+3,1,1));

                        //Node i 2
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                2*1.8f+0.2f, i*1.8f+3,1,1));

                        //Node i 4
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                4*1.8f+0.2f, i*1.8f+3,1,1));

                    }


                    if(j == 0 && (i == 3 || i == 6)) {

                        //Node i 1
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                1*1.8f+0.2f, i*1.8f+3,1,1));

                        //Node i-1 0
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                0*1.8f+0.2f, (i-1)*1.8f+3,1,1));

                        //Node i+1 0
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                0*1.8f+0.2f, (i+1)*1.8f+3,1,1));
                    }

                    if(j == 6 && (i == 3 || i == 6)) {

                        //Node i 5
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                5*1.8f+0.2f, i*1.8f+3,1,1));

                        //Node i-1 6
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                6*1.8f+0.2f, (i-1)*1.8f+3,1,1));

                        //Node i+1 6
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                6*1.8f+0.2f, (i+1)*1.8f+3,1,1));
                    }


                    if(isRightNum(i) && !isRightNum(j)) {
                        //Node i j-1
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                (j-1)*1.8f+0.2f, i*1.8f+3,1,1));

                        //Node i j+1
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                (j+1)*1.8f+0.2f, i*1.8f+3,1,1));
                    }

                    if(!isRightNum(i) && isRightNum(j)) {
                        //Node i-1 j
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                j*1.8f+0.2f, (i-1)*1.8f+3,1,1));

                        //Node i+1 j
                        node.addNeighbour(new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                                j*1.8f+0.2f, (i+1)*1.8f+3,1,1));
                    }

                    this.board.add(node);

                }
            }
        }

/*
        for (int i = 0; i < 10; i++ ) {

            for (int j = 0; j < 7; j++ ) {

                if(isRightNum(i) || isRightNum(j)) {
                    Node node = new Node(packGame,packGame.assets.manager.get(Assets.apple, Texture.class),
                            j*1.8f+0.2f, i*1.8f+3,1,1);


                    this.board.add(node);

                }
            }
        }

*/


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


    private void drawApples()
    {

    }

    public void render(){
        this.stage.draw();
    }

    public void update(){

    }

    void initBoard() {

    }

    void initEnemies() {

    }

}