package com.rodion.runner.scenes.game3D.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.runner.runnerWorld.Barrier;
import com.rodion.runner.runnerWorld.Frame;
import com.rodion.runner.runnerWorld.Node;
import com.rodion.runner.runnerWorld.Runner;
import com.rodion.runner.runnerWorld.World;
import com.rodion.runner.scenes.game3D.entities.RunnerEntity3D;
import com.rodion.runner.scene3d.Stage3D;

import java.util.ArrayList;


public class TitleStage3D extends Stage3D {
    private World world;
    private float elapsedTime;

    public TitleStage3D(Viewport viewport) {
        super(viewport);
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f, 0.8f, 1f));
        camera = new PerspectiveCamera(7f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(50f, 2.5f, 0f);

        //camera.position.set(-150f,100,100);
        camera.lookAt(20f, 2.5f, 0f);
        camera.near = 10f;
        camera.far = 300.0f;
        //init world
        ArrayList<Barrier> barrierWorld = new ArrayList<Barrier>();
        barrierWorld.add(new Barrier(new Frame(new Node(0, 0), new Node(1, 2)), 0, 1));
        barrierWorld.add(new Barrier(new Frame(new Node(0, 0), new Node(1, 6)), 0, 5));
        barrierWorld.add(new Barrier(new Frame(new Node(0, 5), new Node(3, 6)), 10, 5));
        barrierWorld.add(new Barrier(new Frame(new Node(0, -1), new Node(0, 0)), 0, 1));
        barrierWorld.add(new Barrier(new Frame(new Node(0, 0), new Node(3, 6)), 0, 1));
        world = new World(
                new Frame(new Node(0, 0), new Node(50, 20)),
                new Runner(new Frame(new Node(2.5f, 0), new Node(2.5f, 5)), 10, 0.5f, 25),
                barrierWorld,
                25,
                50
        );

        RunnerEntity3D runner = new RunnerEntity3D(world.getRunner(), modelBuilder);
        environment.add(new PointLight().set(1f, 1f, 1f, 0, 100, 100f, 5000f));
        addActor3D(runner);
        elapsedTime = 0;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        elapsedTime += delta;
        //camera.position.set(-150f,100,100);
        //camera.update();
    }

    @Override
    public void render() {
        super.render();
        Gdx.app.log("elapsedTime",""+elapsedTime);
        Node node = trajectory(new Node(-50f, 2.5f, 0f), new Node(-150f,100,100), .075f*elapsedTime);
        camera.update();
        //camera.position.set(node.getX(), node.getY(), node.getZ());

        //camera.position.set(-50f, 2.5f, 0f);
        //camera.lookAt(20f, 2.5f, 0f);
        camera.position.add(-150f,100,100);
        //camera.position.
        //camera.translate(-150f,100,100);
        //camera.lookAt(20f, 2.5f, 0f);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    private Node trajectory(Node node1, Node node2, float t) {
        Node node = new Node(0, 0,0);

        float   x1 = node1.getX(),
                y1 = node1.getY(),
                x2 = node2.getX(),
                y2 = node2.getY(), xm, ym,
                z1 = node1.getZ(),
                z2 = node2.getZ()

                ;
        //xm = (x2-x1)/2.f;
        //ym = (y2-y1)/2.f;
        //float radius = (float) Math.sqrt((xm - x1) * (xm - x1) + (ym - y1) * (ym - y1));
        //float teta1, teta2;
        //teta1 = (float) Math.atan(y1 / x1);
        //teta2 = (float) Math.atan(y2 / x2);
        //node.setX(radius * (float) Math.cos((teta2 - teta1) * t + teta1) + xm);
        //node.setY(radius * (float) Math.sin((teta2 - teta1) * t + teta1) + ym);
        //return node;

        node.setX((x2 - x1) * t + x1);
        node.setY((y2 - y1) * t + y1);
        node.setZ((z2 - z1) * t + z1);
        return node;
    }
}
