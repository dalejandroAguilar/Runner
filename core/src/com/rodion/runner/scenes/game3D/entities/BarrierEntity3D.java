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
import com.rodion.runner.runnerWorld.Barrier;
import com.rodion.runner.runnerWorld.Node;
import com.rodion.runner.scene3d.Actor3D;

public class BarrierEntity3D extends Actor3D implements Disposable {
    private Barrier barrier;
    private Model model;
    private Model model2;
    private ModelInstance modelInstance2;

    public BarrierEntity3D(Barrier barrier, ModelBuilder modelBuilder) {
        this.barrier = barrier;
        this.modelBuilder = modelBuilder;
        build(modelBuilder);
    }

    @Override
    public void build(ModelBuilder modelBuilder) {
        Node bodyDimension = barrier.getBody().getDimension();

        model = modelBuilder.createBox(bodyDimension.getX(), bodyDimension.getY(), 5f,
                new Material(ColorAttribute.createDiffuse(Color.RED)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        model2 = modelBuilder.createBox(bodyDimension.getX()+5f, 0f, 5f,
                new Material(ColorAttribute.createDiffuse(Color.GRAY)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        modelInstance = new ModelInstance(model, 0, 0, 0);

        modelInstance2 = new ModelInstance(model2, 0, 0, 0);


        moveTo();
    }

    private void moveTo() {
        Node bodyPosition = barrier.getBody().getPosition();
        Node bodyDimension = barrier.getBody().getDimension();
        modelInstance.transform.setToTranslation(bodyPosition.getX() + 0.5f * bodyDimension.getX()
                , bodyPosition.getY() + 0.5f * bodyDimension.getY(), 0);

        modelInstance2.transform.setToTranslation(bodyPosition.getX() + 0.5f * bodyDimension.getX()
                -5f,  0.5f * 0.125f, 0);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveTo();
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        super.render(modelBatch, environment);
        modelBatch.render(modelInstance, environment);
        modelBatch.render(modelInstance2, environment);
    }

    @Override
    public void dispose() {
        model.dispose();
    }
}