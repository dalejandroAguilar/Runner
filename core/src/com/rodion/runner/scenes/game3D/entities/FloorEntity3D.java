package com.rodion.runner.scenes.game3D.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Disposable;
import com.rodion.runner.runnerWorld.Frame;
import com.rodion.runner.runnerWorld.Node;
import com.rodion.runner.scene3d.Actor3D;

public class FloorEntity3D extends Actor3D implements Disposable {
    private Frame frame;
    private Model model;

    public FloorEntity3D(Frame frame, ModelBuilder modelBuilder) {
        this.frame = frame;
        this.modelBuilder = modelBuilder;
        build(modelBuilder);
    }

    @Override
    public void build(ModelBuilder modelBuilder) {
        super.build(modelBuilder);
        Node frameDimension = frame.getDimension();

        model = modelBuilder.createBox(frameDimension.getX(),/*frameDimension.getY()*/ 1,10f,
                new Material(ColorAttribute.createDiffuse(Color.SALMON)),
                VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);
        modelInstance = new ModelInstance(model,0,0,0);
        moveTo();
    }

    private void moveTo(){
        Node framePosition = frame.getPosition();
        Node frameDimension = frame.getDimension();
        modelInstance.transform.setToTranslation(framePosition.getX() + 0.5f*frameDimension.getX()
                ,framePosition.getY()- 0.5f,0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //moveTo();
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        super.render(modelBatch, environment);
        modelBatch.render(modelInstance, environment);
    }

    @Override
    public void dispose() {
        model.dispose();
    }
}
