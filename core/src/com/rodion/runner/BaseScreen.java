package com.rodion.runner;

import com.badlogic.gdx.Screen;

public abstract class BaseScreen implements Screen {
    protected com.rodion.runner.MainGame mainGame;

    public BaseScreen(com.rodion.runner.MainGame mainGame) {
        this.mainGame = mainGame;
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
