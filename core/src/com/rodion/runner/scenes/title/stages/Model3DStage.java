package com.rodion.runner.scenes.title.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.runner.runnerWorld.Frame;
import com.rodion.runner.runnerWorld.Node;
import com.rodion.runner.runnerWorld.Runner;
import com.rodion.runner.scene3d.Stage3D;
import com.rodion.runner.scenes.game3D.entities.RunnerEntity3D;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Model3DStage extends Stage3D {
    private RunnerEntity3D runner;
    private float elapsedTime;
    public Model3DStage(Viewport viewport) {
        super(viewport);
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1f));
        camera = new PerspectiveCamera(7f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(200f, 10f, 0f);

        //camera.position.set(-150f,100,100);
        camera.lookAt(0f, 10f, 0f);
        camera.near = 10f;
        camera.far = 500.0f;

        runner = new RunnerEntity3D( new Runner(new Frame(new Node(0f,0), new Node(2.5f,5))), modelBuilder);
        elapsedTime=0;
        addActor3D(runner);
    }

    @Override
    public void act(float delta) {
        elapsedTime += delta;
        super.act(delta);
        runner.rotate();
        camera.position.set(200f*(float)cos((double)1f*elapsedTime), 10f, 200f*(float)sin((double)1f*elapsedTime));
        camera.lookAt(0f, 10f, 0f);
    }

    @Override
    public void render() {
        super.render();
        camera.update();
    }


}
