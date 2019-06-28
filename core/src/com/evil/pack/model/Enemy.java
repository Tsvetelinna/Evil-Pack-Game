package com.evil.pack.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.evil.pack.EvilPackGame;

public class Enemy extends Image {

    private EvilPackGame packGame;
    private World physicWorld;
    private Body body;


    public Enemy(EvilPackGame packGame, World physicWorld, Texture appearance,
                 float x, float y, float width, float height) {

        super(appearance);
        this.packGame = packGame;
        this.physicWorld = physicWorld;
        setPosition(x,y);
        setOrigin(x,y);
        setWidth(width);
        setHeight(height);

        initBody();
    }

    private void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(),getY());
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

        bodyShape.dispose();
    }


    @Override
    public void act(float delta) {

    }

    public void die() {

    }

}
