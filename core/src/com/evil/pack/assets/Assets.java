package com.evil.pack.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public AssetManager manager;

    public static String badlogic = "img/badlogic.jpg";
    public static String apple = "apple/apple.jpg";
    public static String player = "player/player.jpg";
    public static String enemy = "deer/deer.jpg";

    public Assets(){

        manager = new AssetManager();
    }

    public void load(){

        manager.load(badlogic, Texture.class);
        manager.load(apple, Texture.class);
        manager.load(player, Texture.class);
        manager.load(enemy, Texture.class);
    }

    public void dispose(){

        manager.dispose();
    }

}
