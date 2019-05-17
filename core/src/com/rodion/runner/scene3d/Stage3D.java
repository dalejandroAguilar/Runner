package com.rodion.runner.scene3d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class Stage3D implements Disposable {

    public PerspectiveCamera camera;
    protected ModelBatch modelBatch;
    protected Environment environment;
    protected ModelBuilder modelBuilder;
    protected ArrayList<Actor3D> actors;
    protected ArrayList<Actor3D> bufferActors;
    protected Viewport viewport;


    public Stage3D(Viewport viewport) {

        camera = new PerspectiveCamera(
                75,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        camera.viewportHeight = viewport.getScreenHeight();
        camera.viewportWidth = viewport.getScreenWidth();
        modelBatch = new ModelBatch();
        environment = new Environment();
        actors = new ArrayList<Actor3D>();
        bufferActors = new ArrayList<Actor3D>();
        this.viewport = viewport;
        modelBuilder = new ModelBuilder();
    }

    public void addActor3D(Actor3D actor3D) {
        actors.add(actor3D);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
        //camera.viewportWidth =   viewport.getWorldWidth();
        //camera.viewportHeight = viewport.getWorldHeight();
        //camera.viewportWidth =  width ;
        //camera.viewportHeight = height;
       camera.viewportWidth =   viewport.getScreenWidth();
       camera.viewportHeight = viewport.getScreenHeight();
        camera.update();
    }

    public void act(float delta) {

        for (Actor3D actor3D : actors) {
            actor3D.act(delta);

        }
        for (Actor3D actor3D : bufferActors) {
            actor3D.act(delta);

        }
    }

    public void render() {
        //camera.update();
        modelBatch.begin(camera);
        for (Actor3D actor3D : actors)
            actor3D.render(modelBatch, environment);
        for (Actor3D actor3D : bufferActors)
            actor3D.render(modelBatch, environment);
        modelBatch.end();
    }

    public void changeBufferActor(ArrayList<Actor3D> bufferActors){
        this.bufferActors = null;
        this.bufferActors = bufferActors;
    }

    @Override
    public void dispose() {


    }
}