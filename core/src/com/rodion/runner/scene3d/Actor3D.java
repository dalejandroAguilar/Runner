package com.rodion.runner.scene3d;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class Actor3D {
    public ModelInstance modelInstance;
    protected ModelBuilder modelBuilder;

    public Actor3D() {
        //
    }

    public void build(ModelBuilder modelBuilder){

    }

    public void render(ModelBatch modelBatch, Environment environment) {
        //modelBatch.render(modelInstance, environment );
    }

    public void act(float delta){
        //
    }
}
