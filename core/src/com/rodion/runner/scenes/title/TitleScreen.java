package com.rodion.runner.scenes.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.rodion.runner.BaseScreen;
import com.rodion.runner.MainGame;
import com.rodion.runner.scene3d.Stage3D;
import com.rodion.runner.scenes.game3D.Game3DScreen;
import com.rodion.runner.scenes.game3D.input.GameInput;
import com.rodion.runner.scenes.title.input.TitleInput;
import com.rodion.runner.scenes.title.stages.Model3DStage;

public class TitleScreen extends BaseScreen {

    Model3DStage model3DStage;


    public TitleScreen(MainGame mainGame) {
        super(mainGame);
        model3DStage = new Model3DStage(new FitViewport(720,1280));
        Gdx.input.setInputProcessor(new GestureDetector(new TitleInput(this)));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        model3DStage.act(delta);
        model3DStage.render();
    }

    public void onPlay(){
        mainGame.setScreen(new Game3DScreen(mainGame));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        model3DStage.resize(width, height);
    }
}
