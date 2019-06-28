package com.evil.pack.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public AssetManager manager;

    public static String badlogic = "img/badlogic.jpg";
    public static String menu_background = "img/icence.jpg";
    public static String background = "background/grass.jpg";
    public static String apple = "apple/apple.png";
    public static String player = badlogic;
    public static String enemy = "deer/deer.png";
    //public static String leftButton = "buttons/left.png";
    //public static String rightButton = "buttons/right.png";
    //public static String upButton = "buttons/up.png";
    //public static String downButton = "buttons/down.png";

    public Assets(){

        manager = new AssetManager();
    }

    public void load(){

        manager.load(badlogic, Texture.class);
        manager.load(apple, Texture.class);
        manager.load(player, Texture.class);
        manager.load(enemy, Texture.class);
        manager.load(menu_background, Texture.class);
        manager.load(background, Texture.class);

        /*
        manager.load(leftButton, Texture.class);
        manager.load(rightButton, Texture.class);
        manager.load(upButton, Texture.class);
        manager.load(downButton, Texture.class);
        */
    }

    public void dispose(){

        manager.dispose();
    }

}
