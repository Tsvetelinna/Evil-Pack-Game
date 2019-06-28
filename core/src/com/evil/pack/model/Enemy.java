package com.evil.pack.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.evil.pack.EvilPackGame;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends Image {

    private EvilPackGame packGame;
    private World physicWorld;
    private Body body;
    private Node prevNode;
    private Node targetNode;
    private ArrayList<Node> board;
    double posX;
    double posY;
    private int[][] iteratatble;


    private void checkForTarget(List<Integer> targets, int x, int y )
    {
        if(x >= 0 && x <= 6 && y >= 0 && y <= 9)
        {
            int target = this.iteratatble[y][x];
            if( target != -1 && target != prevNode.id)
            {
                targets.add(target);
            }
        }
    }

    private Node getTarget()
    {
        List<Integer> targets = new ArrayList<Integer>();

        for(int i = 1; i>=-1;i-=2)
        {
            checkForTarget(targets, prevNode.x + i, prevNode.y );
            checkForTarget(targets, prevNode.x, prevNode.y + i);
        }

        Random rand = new Random();
        int randomElement = targets.get(rand.nextInt(targets.size()));
        return this.board.get(randomElement);

    }


    public Enemy(EvilPackGame packGame, /*World physicWorld,*/ Texture appearance,
                 ArrayList<Node> board, int[][] iteratatble, Node startNode) {

        super(appearance);
        float width=1.5f; float height=1.5f;
        this.iteratatble =  iteratatble;
        this.prevNode = startNode;
        float x = prevNode.node_x;
        float y = prevNode.node_y;
        this.posX = x;
        this.posY = y;
        this.packGame = packGame;
        this.board = board;
        this.targetNode = startNode;

//        this.physicWorld = physicWorld;
        setPosition(x,y);
        setOrigin(x,y);
        setWidth(width);
        setHeight(height);

//        initBody();
    }

    private void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(),getY());
        bodyDef.type = BodyDef.BodyType.StaticBody;

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

        bodyShape.dispose();
    }


    public void travel( float goalX, float goalY){
        double destX = goalX - this.posX;
        double destY = goalY - this.posY;

        double dist = Math.sqrt(destX * destX + destY * destY);
        destX = destX / dist;
        destY = destY / dist;

        float speed= 3f;
        double travelX = destX * speed * Gdx.graphics.getDeltaTime();
        double travelY = destY * speed * Gdx.graphics.getDeltaTime();

        double distTravel = Math.sqrt(travelX * travelX + travelY * travelY);


        if ( distTravel > dist )
        {
            this.posX = goalX;
            this.posY = goalY;
        }
        else
        {
            this.posX += travelX;
            this.posY += travelY;
        }

        setPosition((float)posX, (float)posY);
    }

    @Override
    public void act(float delta) {

        if(posX == targetNode.node_x && posY == targetNode.node_y) {

            //eating apple
            targetNode.setColor(0,0,0,0);
            targetNode.hasApple = false;

            prevNode = targetNode;
            targetNode = getTarget();

        }else
        {
            travel(targetNode.node_x, targetNode.node_y);
        }
    }

    public void die() {

    }

}
