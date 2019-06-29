package com.evil.pack.model;

import com.badlogic.gdx.graphics.Color;
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
import java.util.Random;

public class Enemy extends Image {

    private EvilPackGame packGame;
    private World physicWorld;
    private Body body;
    private Node prevNode;
    private Node targetNode;
    private Position pos;
    private ArrayList<Node> board;
    private int id;

    private int[][] iteratatble;
    private List<Integer> enemiesTodie = new ArrayList<Integer>();
    private List<Integer> path =  new ArrayList<Integer>();



    private void checkForTarget(List<Integer> targets, int x, int y )
    {
        if(x >= 0 && x <= 6 && y >= 0 && y <= 9)
        {
            int target = this.iteratatble[y][x];
            if( target != -1 && !path.contains(target))
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
        if(targets.size() == 0 || path.size() > 10)
        {
            path.clear();
            return prevNode;
        }
        Random rand = new Random();
        int randomElement = targets.get(rand.nextInt(targets.size()));
        path.add(randomElement);

        return this.board.get(randomElement);

    }


    public Enemy(EvilPackGame packGame, World physicWorld, Texture appearance,
                 ArrayList<Node> board, int[][] iteratatble, Node startNode, List<Integer> dieList, int id) {

        super(appearance);
        float width=1.5f; float height=1.5f;
        this.iteratatble =  iteratatble;
        this.prevNode = startNode;
        float x = prevNode.node_x;
        float y = prevNode.node_y;
        this.pos = new Position(x, y);

        this.packGame = packGame;
        this.board = board;
        this.targetNode = startNode;

        this.physicWorld = physicWorld;

        Random rand = new Random();
        int randNum = rand.nextInt(3);
        Color curColor = getColor();
        if(randNum==0)
            curColor.b -= 0.2;
        if(randNum==1)
            curColor.r -= 0.2;
        if(randNum==2)
            curColor.g -= 0.2;

        setColor(curColor);
        setPosition(x,y);
        setOrigin(x,y);
        setWidth(width-(randNum/10));
        setHeight(height-(randNum/10));
        this.enemiesTodie = dieList;
        initBody();
        this.id = id;
    }

    private void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set((float)this.pos.X, (float)this.pos.Y);
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


    private void repostiBody()
    {
//        System.out.println("X:"+getX()+" Y:"+getY());
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



    @Override
    public void act(float delta) {

        if(this.pos.X == targetNode.node_x && this.pos.Y == targetNode.node_y) {

            //eating apple
            targetNode.setColor(0,0,0,0);
            targetNode.hasApple = false;
            prevNode = targetNode;
            targetNode = getTarget();

        }else
        {
            this.pos.moveTo(targetNode.node_x, targetNode.node_y);
            setPosition((float)this.pos.X, (float)this.pos.Y);
            repostiBody();
        }
    }

    public void die() {
        if(id!=-2)
        {
            System.out.println("die");
            this.remove();

//        System.out.println(id);
            enemiesTodie.add(id);
            id = -2;
//        Gdx.app.
        }


    }

}
