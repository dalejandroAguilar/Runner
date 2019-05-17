package com.rodion.runner.scenes.game3D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.rodion.runner.BaseScreen;
import com.rodion.runner.MainGame;
import com.rodion.runner.runnerWorld.Barrier;
import com.rodion.runner.runnerWorld.Frame;
import com.rodion.runner.runnerWorld.Node;
import com.rodion.runner.runnerWorld.Runner;
import com.rodion.runner.runnerWorld.World;
import com.rodion.runner.scenes.game3D.input.GameInput;
import com.rodion.runner.scenes.game3D.stages.ArenaStage3D;
import com.rodion.runner.scenes.game3D.stages.TitleStage3D;
import com.rodion.runner.scenes.game3D.stages.UIStage;

import java.util.ArrayList;

import static com.badlogic.gdx.graphics.GL20.GL_DEPTH_TEST;

public class Game3DScreen extends BaseScreen {
    //private World world;
    private ArenaStage3D arenaStage3D;
    private TitleStage3D titleStage3D;
    private UIStage uiStage;

    public Game3DScreen(MainGame mainGame) {
        super(mainGame);

        arenaStage3D = new ArenaStage3D(new FitViewport(720,1280));
        titleStage3D = new TitleStage3D(new FitViewport(720,1280));
        uiStage = new UIStage(new FitViewport(720, 1280), arenaStage3D.getWorld());
        Gdx.input.setInputProcessor(new GestureDetector(new GameInput(arenaStage3D.getWorld())));
        //Gdx.gl.glClearDepthf(1.0f);
        //Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        //Gdx.gl.glDepthFunc(GL20.GL_LESS);
        //Gdx.gl.glDepthRangef(0f, 1f);
        //Gdx.gl.glEnable(GL20.GL_TEXTURE_2D);
//
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        //glEnable(GL_DEPTH_TEST);
        //Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);

        arenaStage3D.act(delta);
        arenaStage3D.render();
//
        //titleStage3D.act(delta);
        //titleStage3D.render();

        uiStage.act();
        uiStage.draw();
    }

    public ArenaStage3D getArenaStage3D() {
        return arenaStage3D;
    }

    public void setArenaStage3D(ArenaStage3D arenaStage3D) {
        this.arenaStage3D = arenaStage3D;
    }

    @Override
    public void dispose() {
        super.dispose();
        arenaStage3D.dispose();
        titleStage3D.dispose();
        uiStage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        uiStage.getViewport().update(width, height);
        arenaStage3D.resize(width, height);
        titleStage3D.resize(width, height);
    }
}