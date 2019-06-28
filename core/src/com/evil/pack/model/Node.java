package com.evil.pack.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.evil.pack.EvilPackGame;

import java.util.ArrayList;
import java.util.List;

public class Node extends Image{
    public int x;
    public int y;
    public int id;
    public float node_x;
    public float node_y;

    public boolean hasApple = true;
    List<Node> neighbours =  new ArrayList<Node>();


    private EvilPackGame packGame;
//    private World physicWorld;
//    private Body body;

    public Node(EvilPackGame packGame, Texture appearance, int x, int y,
                  float width, float height, int id) {

        super(appearance);
        this.x = x;
        this.y = y;
        this.node_x = x*1.8f+0.2f;
        this.node_y = y*1.8f+3;
        this.setX(this.node_x);
        this.setY(this.node_y);
        this.setOrigin(this.node_x, this.node_y);
        this.setWidth(width);
        this.setHeight(height);
        this.packGame = packGame;
//        this.physicWorld = physicWorld;
    }

    public void addNeighbour(Node node) {
        neighbours.add(node);
    }

    @Override
    public void act(float delta){

    }

    public void die(){
        // packGame.gameState = EvilPackGameGame.GAME_STATE.MENU;
    }

}
