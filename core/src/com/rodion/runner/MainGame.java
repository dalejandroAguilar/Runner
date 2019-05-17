package com.rodion.runner;

import com.badlogic.gdx.Game;
import com.rodion.runner.scenes.game3D.Game3DScreen;
import com.rodion.runner.scenes.title.TitleScreen;


public class MainGame extends Game {
    public MainGame(){
        //TODO: aquí no va nada
    }

    @Override
    public void create() {
        setScreen(new TitleScreen(this));
    }
}
