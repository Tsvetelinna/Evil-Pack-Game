package com.evil.pack.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.evil.pack.EvilPackGame;
import com.evil.pack.controller.Controller;
import com.evil.pack.game.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class Player extends Image {

    private EvilPackGame packGame;
    private World physicWorld;
    private Body body;
    private Position pos;
    private Controller controller;
    private Node curNode;
    private ArrayList<Node> board;
    private int[][] iteratatble;

    public Player(EvilPackGame packGame, World physicWorld, Texture appearance,
                  Controller controller,
                  ArrayList<Node> board, int[][] iteratatble,
                  Node startNode,
                  float width, float height)
    {

        super(appearance);
        this.controller =  controller;
        this.iteratatble = iteratatble;
        this.board = board;
        this.pos = new Position(startNode.node_x, startNode.node_y);
        setPosition((float)this.pos.X, (float)this.pos.Y);
        this.setOrigin((float)pos.X, (float)pos.Y);
        this.setWidth(width);
        this.setHeight(height);
        this.packGame = packGame;
        curNode = startNode;
        this.physicWorld = physicWorld;
        this.initBody();
    }

    private void initBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((float)this.pos.X, (float)this.pos.Y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = physicWorld.createBody(bodyDef);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth() / 2,getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        body.setUserData(this);
        body.setLinearVelocity(5f,0);

        bodyShape.dispose();
    }

    private void repostiBody()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((float)this.pos.X, (float)this.pos.Y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        this.physicWorld.destroyBody(body);


        body = this.physicWorld.createBody(bodyDef);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth() / 2,getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        body.setUserData(this);
        body.setLinearVelocity(5f,0);

        bodyShape.dispose();
    }


    private void move(int x, int y)
    {
        if(x >=0 && x < iteratatble[0].length && y >=0 && y < iteratatble.length)
        {
            if(iteratatble[y][x] != -1)
            {
                Node target = this.board.get(iteratatble[y][x]);
                pos.moveTo(target.node_x, target.node_y);
                if(pos.X == target.node_x && pos.Y == target.node_y )
                {
                    curNode = target;
                }
                setPosition((float)this.pos.X, (float)this.pos.Y);
                repostiBody();
            }
        }
    }


    @Override
    public void act(float delta){
        if(this.controller.isRightPressed()) {
            move(curNode.x + 1, curNode.y);
        }
        else if(this.controller.isLeftPressed()) {
            move(curNode.x - 1, curNode.y);
        }
        else if(this.controller.isDownPressed()) {
            move(curNode.x, curNode.y - 1);
        }
        else if(this.controller.isUpPressed()) {
            move(curNode.x , curNode.y + 1);
        }
    }


}
