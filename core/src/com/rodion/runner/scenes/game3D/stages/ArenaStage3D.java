package com.rodion.runner.scenes.game3D.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalShadowLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.DepthShaderProvider;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.runner.runnerWorld.Barrier;
import com.rodion.runner.runnerWorld.Frame;
import com.rodion.runner.runnerWorld.Node;
import com.rodion.runner.runnerWorld.Runner;
import com.rodion.runner.runnerWorld.World;
import com.rodion.runner.scenes.game3D.entities.BarrierEntity3D;
import com.rodion.runner.scenes.game3D.entities.FloorEntity3D;
import com.rodion.runner.scenes.game3D.entities.RunnerEntity3D;
import com.rodion.runner.scene3d.Actor3D;
import com.rodion.runner.scene3d.Stage3D;

import java.util.ArrayList;

public class ArenaStage3D extends Stage3D {
    private World world;
    //private Game3DScreen game3DScreen;
    private RunnerEntity3D runner;
    private DirectionalShadowLight shadowLight;
    private ModelBatch shadowBatch;
    public ArenaStage3D(Viewport viewport) {
        super(viewport);
        shadowBatch = new ModelBatch(new DepthShaderProvider());
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.8f,0.8f,0.8f,1f));
        camera  = new PerspectiveCamera(7f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        camera.position.set(-250f,100,100);
        camera.lookAt(25f,2.5f,0f);
        camera.near = 10f;
        camera.far = 400.0f;

        //init world
        ArrayList<Barrier> barrierWorld = new ArrayList<Barrier>();
        barrierWorld.add(new Barrier(new Frame(new Node(0,0), new Node(1,2)),0,1));
        barrierWorld.add(new Barrier(new Frame(new Node(0,0), new Node(1,4)),0,5));
        barrierWorld.add(new Barrier(new Frame(new Node(0,6), new Node(3,4)),3,5));
        barrierWorld.add(new Barrier(new Frame(new Node(0,-1), new Node(0,0)),0,1));
        barrierWorld.add(new Barrier(new Frame(new Node(0,0), new Node(3,4)),0,1));

        world = new World(
                new Frame(new Node(-25,0), new Node(100,20)),
                new Runner(new Frame(new Node(2.5f,0), new Node(2.5f,5)),15,0.5f,25),
                barrierWorld,
                32,
                60
        );

        runner = new RunnerEntity3D( world.getRunner(), modelBuilder);
        environment.add(new PointLight().set(1f, 1f, 1f, 0, 100, 100f, 5000f));
        shadowLight = new DirectionalShadowLight(1024, 1024, 5f, 100f, 10f, 300f);
                // .set(1f, 1f, 1f, 400.0f, -35f, -35f));
                //.set(1f, 1f, 1f, -5.0f, -10f, 0f);
        //environment
        shadowLight.set(1f, 1f, 1f, -5.0f, -10f, 0f);
        //environment.shadowMap = shadowLight;

        addActor3D(new FloorEntity3D(world.getFrame(),modelBuilder));
        addActor3D(runner);

    }

    @Override
    public void render() {
        super.render();

        //shadowLight.begin(Vector3.Zero, camera.direction);
        //shadowBatch.begin(shadowLight.getCamera());
//
        //shadowBatch.render(runner.modelInstance);
//
        //for(Actor3D actor3D: bufferActors){
        //    shadowBatch.render(actor3D.modelInstance);
        //}
        //shadowBatch.end();
        //shadowLight.end();

        camera.update();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        ArrayList<Actor3D> bufferActors = new ArrayList<Actor3D>();
        for (Barrier barrier : world.getBarrierBuffer())
            bufferActors.add(new BarrierEntity3D(barrier,  modelBuilder));
        changeBufferActor(bufferActors);


        if(!world.isCollision()) {
            world.step(delta);
        }
    }

    @Override
    public void dispose() {
        super.dispose();

    }

    public World getWorld() {
        return world;
    }
    public void setWorld(World world) {
        this.world = world;
    }
}
