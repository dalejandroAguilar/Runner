package com.rodion.runner.scenes.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.input.GestureDetector;
import com.rodion.runner.BaseScreen;
import com.rodion.runner.MainGame;
import com.rodion.runner.runnerWorld.Barrier;
import com.rodion.runner.runnerWorld.Frame;
import com.rodion.runner.runnerWorld.Node;
import com.rodion.runner.runnerWorld.Runner;
import com.rodion.runner.runnerWorld.World;
import com.rodion.runner.scenes.game.inputs.GameInput;

import java.util.ArrayList;

public class GameScreen extends BaseScreen {

    private ShapeRenderer shapeRenderer;
    private World world;



    public GameScreen(MainGame mainGame) {
        super(mainGame);
        ArrayList<Barrier> barrierWorld = new ArrayList<Barrier>();
        barrierWorld.add(new Barrier(new Frame(new Node(0,0), new Node(10,20)),0,1));
        barrierWorld.add(new Barrier(new Frame(new Node(0,0), new Node(10,60)),0,5));
        barrierWorld.add(new Barrier(new Frame(new Node(0,40), new Node(30,60)),10,5));
        barrierWorld.add(new Barrier(new Frame(new Node(0,-1), new Node(0,0)),0,100));
        barrierWorld.add(new Barrier(new Frame(new Node(0,0), new Node(30,60)),0,1));

        shapeRenderer = new ShapeRenderer();
        world = new World(
                new Frame(new Node(0,0), new Node(1000,200)),
                new Runner(new Frame(new Node(40,0), new Node(40,80)),200,10,500),
                barrierWorld,
                500,
                1500
        );
        Gdx.input.setInputProcessor(new GestureDetector(new GameInput(this)));
    }



    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(68.f / 255, 66.f / 255, 66.f / 255, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT| GL20.GL_DEPTH_BUFFER_BIT);
        world.step(delta);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        if (world.isCollision())
            shapeRenderer.setColor(80 / 255.0f, 80 / 255.0f, 50 / 255.0f, 1);
        else
            shapeRenderer.setColor(80 / 255.0f, 0 / 255.0f, 0 / 255.0f, 1);
        shapeRenderer.rect(world.getRunner().getBody().getPosition().getX(),
                world.getRunner().getBody().getPosition().getY(),
                world.getRunner().getBody().getDimension().getX(),
                world.getRunner().getBody().getDimension().getY());
        shapeRenderer.setColor(80 / 255.0f, 0 / 255.0f, 0 / 255.0f, 1);
        for (Barrier barrier: world.getBarrierBuffer()){
            shapeRenderer.rect(barrier.getBody().getPosition().getX(),
                    barrier.getBody().getPosition().getY(),
                    barrier.getBody().getDimension().getX(),
                    barrier.getBody().getDimension().getY());
        }
        shapeRenderer.end();
        Gdx.app.log("random",""+world.randomChoice(10));
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void dispose() {
        super.dispose();
        shapeRenderer.dispose();
    }

    public World getWorld() {
        return world;
    }
}
