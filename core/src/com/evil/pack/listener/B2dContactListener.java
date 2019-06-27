package com.evil.pack.listener;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.evil.pack.model.Enemy;
import com.evil.pack.model.Player;

public class B2dContactListener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        String classA = contact.getFixtureA().getBody().getUserData().getClass().getName();
        String classB = contact.getFixtureB().getBody().getUserData().getClass().getName();

        if(classA.equals("com.evil.pack.model.Player") && classB.equals("com.evil.pack.model.Enemy")){
            Enemy enemy = (Enemy)(contact.getFixtureA().getBody().getUserData());
           // enemy.die();
        }
        else if(classA.equals("com.evil.pack.model.Enemy") && classB.equals("com.evil.pack.model.Player")){
            //Player player = (Player)(contact.getFixtureB().getBody().getUserData());
            //player.die();

        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
