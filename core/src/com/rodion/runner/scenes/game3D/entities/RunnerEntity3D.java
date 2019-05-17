package com.rodion.runner.scenes.game3D.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.AnimationController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.UBJsonReader;
import com.rodion.runner.runnerWorld.Node;
import com.rodion.runner.runnerWorld.Runner;
import com.rodion.runner.scene3d.Actor3D;

public class RunnerEntity3D extends Actor3D implements Disposable{
    private Runner runner;
    private Model model;
    private AnimationController controller;
    public RunnerEntity3D(Runner runner, ModelBuilder modelBuilder) {
        super();
        this.runner = runner;
        this.modelBuilder = modelBuilder;
        build(modelBuilder);

    }

    @Override
    public void build(ModelBuilder modelBuilder) {
        super.build(modelBuilder);

        Node bodyDimension = runner.getBody().getDimension();
        //model = modelBuilder.createBox(bodyDimension.getX(),bodyDimension.getY(),5f,
        //        new Material(ColorAttribute.createDiffuse(Color.BLUE)),
        //        VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);

        //ModelLoader modelLoader = new ObjLoader();
        //model = modelLoader.loadModel(Gdx.files.getFileHandle("ship.g3db", Files.FileType.Internal));
        model = (new G3dModelLoader(new UBJsonReader())).loadModel(Gdx.files.internal("dragon.g3db"));
        //model = modelLoader.loadModel(Gdx.files.internal("dragon.obj"));
        modelInstance = new ModelInstance(model);

        modelInstance.transform.setToRotation(Vector3.Z, 90);
        modelInstance.transform.rotate(Vector3.X, 90);

        //modelInstance.calculateTransforms();
        moveTo();




    }

    private void moveTo(){
        Node bodyDimension = runner.getBody().getDimension();
        Node bodyPosition = runner.getBody().getPosition();
        //modelInstance.transform.setToRotation(new Vector3(0,1,0),90);
        //modelInstance.calculateTransforms();
        modelInstance.transform.setToTranslation(bodyPosition.getX() + 0.5f*bodyDimension.getX()
                ,bodyPosition.getY(),0);
    }

    @Override
    public void render(ModelBatch modelBatch, Environment environment) {
        super.render(modelBatch, environment);
        modelBatch.render(modelInstance, environment);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        ModelBuilder modelBuilder = new ModelBuilder();
        moveTo();
        Node bodyDimension = runner.getBody().getDimension();


    }


    public void onCrash(boolean status){
        Node bodyDimension = runner.getBody().getDimension();
        if (status) {
            model = modelBuilder.createBox(bodyDimension.getX(), bodyDimension.getY(), 25f,
                    new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
            modelInstance = new ModelInstance(model,0,0,0);
            moveTo();
        }
        else {
            model = modelBuilder.createBox(bodyDimension.getX(), bodyDimension.getY(), 5f,
                    new Material(ColorAttribute.createDiffuse(Color.BLUE)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
            modelInstance = new ModelInstance(model,0,0,0);
            moveTo();
        }
    }

    @Override
    public void dispose() {
        model.dispose();
    }

    public void rotate(){
        modelInstance.transform.setToRotation(Vector3.Z, 10);
    }
}
