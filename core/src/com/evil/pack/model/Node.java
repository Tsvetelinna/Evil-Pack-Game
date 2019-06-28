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
    public float node_x;
    public float node_y;
    public boolean hasApple = true;
    List<Integer> neighbours =  new ArrayList<Integer>();


    private EvilPackGame packGame;
//    private World physicWorld;
//    private Body body;

    public Node(EvilPackGame packGame, Texture appearance, float x, float y,
                  float width, float height) {

        super(appearance);

        this.node_x = x;
        this.node_y = y;
        this.setX(x);
        this.setY(y);
        this.setOrigin(x,y);
        this.setWidth(width);
        this.setHeight(height);
        this.packGame = packGame;
//        this.physicWorld = physicWorld;
    }

    public void addNeighbour(Integer num) {

        neighbours.add(num);

    }

    @Override
    public void act(float delta){

    }

    public void die(){
        // packGame.gameState = EvilPackGameGame.GAME_STATE.MENU;
    }

}
