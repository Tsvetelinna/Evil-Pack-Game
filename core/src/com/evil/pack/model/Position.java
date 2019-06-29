package com.evil.pack.model;

import com.badlogic.gdx.Gdx;

public class Position {
    public double X;
    public double Y;

    public Position(float x, float y)
    {
        this.X = (double)x;
        this.Y = (double)y;
    }

    public void moveTo( float goalX, float goalY){
        double destX = goalX - this.X;
        double destY = goalY - this.Y;

        double dist = Math.sqrt(destX * destX + destY * destY);
        destX = destX / dist;
        destY = destY / dist;

        float speed= 3f;
        double travelX = destX * speed * Gdx.graphics.getDeltaTime();
        double travelY = destY * speed * Gdx.graphics.getDeltaTime();

        double distTravel = Math.sqrt(travelX * travelX + travelY * travelY);


        if ( distTravel > dist )
        {
            this.X = goalX;
            this.Y = goalY;
        }
        else
        {
            this.X += travelX;
            this.Y += travelY;
        }


    }
}
