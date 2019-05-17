package com.rodion.runner.scenes.game3D.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rodion.runner.runnerWorld.World;
import com.rodion.runner.utilities.Utilities;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class UIStage extends Stage {
    private Texture textureZero;
    private Texture textureOne;
    private Texture textureTwo;
    private Texture textureThree;
    private Texture textureFour;
    private Texture textureFive;
    private Texture textureSix;
    private Texture textureSeven;
    private Texture textureEight;
    private Texture textureNine;
    private Image imageZero;
    private Image imageOne;
    private Image imageTwo;
    private Image imageThree;
    private Image imageFour;
    private Image imageFive;
    private Image imageSix;
    private Image imageSeven;
    private Image imageEight;
    private Image imageNine;
    private Image imageFirstSlotScore;
    private Image imageSecondSlotScore;
    private Image imageThirdSlotScore;

    private Texture textureBackGround;
    private World world;


    public UIStage(Viewport viewport, World world) {
        super(viewport);
        this.world = world;
        textureZero = new Texture(Gdx.files.internal("numbers/0.png"));
        textureOne = new Texture(Gdx.files.internal("numbers/1.png"));
        textureTwo = new Texture(Gdx.files.internal("numbers/2.png"));
        textureThree = new Texture(Gdx.files.internal("numbers/3.png"));
        textureFour = new Texture(Gdx.files.internal("numbers/4.png"));
        textureFive = new Texture(Gdx.files.internal("numbers/5.png"));
        textureSix = new Texture(Gdx.files.internal("numbers/6.png"));
        textureSeven = new Texture(Gdx.files.internal("numbers/7.png"));
        textureEight = new Texture(Gdx.files.internal("numbers/8.png"));
        textureNine = new Texture(Gdx.files.internal("numbers/9.png"));

        textureBackGround = new Texture(Gdx.files.internal("background.png"));

        imageZero = new Image(textureZero);
        imageOne = new Image(textureOne);
        imageTwo = new Image(textureTwo);
        imageThree = new Image(textureThree);
        imageFour = new Image(textureFour);
        imageFive = new Image(textureFive);
        imageSix = new Image(textureSix);
        imageSeven = new Image(textureSeven);
        imageEight = new Image(textureEight);
        imageNine = new Image(textureNine);

        imageFirstSlotScore = new Image();
        imageSecondSlotScore = new Image();
        imageThirdSlotScore = new Image();

        Table table = new Table();
        table.setFillParent(true);
        table.top();
        table.add(imageFirstSlotScore);
        table.add(imageSecondSlotScore);
        table.add(imageThirdSlotScore);
        //table.row();
        //table.add(new Image(textureBackGround)).colspan(3);

        addActor(table);
    }




    @Override
    public void act(float delta) {
        super.act(delta);
        int score = (int)world.getElapsedDistance();
        setScore(Utilities.number2Array(score));
    }

    @Override
    public void dispose() {
        super.dispose();
        textureZero.dispose();
        textureOne.dispose();
        textureTwo.dispose();
        textureThree.dispose();
        textureFour.dispose();
        textureFive.dispose();
        textureSix.dispose();
        textureSeven.dispose();
        textureEight.dispose();
        textureNine.dispose();
        textureFive.dispose();
    }

    public void resize(int width,int height){
        getViewport().update(width, height);
    }

    private void setScore(ArrayList<Integer> digits) {
        if (digits.size() > 0)
            setDigit(imageThirdSlotScore, digits.get(0));
        if (digits.size() > 1)
            setDigit(imageSecondSlotScore, digits.get(1));
        if (digits.size() > 2)
            setDigit(imageFirstSlotScore, digits.get(2));
    }

    private void setDigit(Image image, int digit) {
        switch (digit) {
            case 0:
                image.setDrawable(imageZero.getDrawable());
                break;
            case 1:
                image.setDrawable(imageOne.getDrawable());
                break;
            case 2:
                image.setDrawable(imageTwo.getDrawable());
                break;
            case 3:
                image.setDrawable(imageThree.getDrawable());
                break;
            case 4:
                image.setDrawable(imageFour.getDrawable());
                break;
            case 5:
                image.setDrawable(imageFive.getDrawable());
                break;
            case 6:
                image.setDrawable(imageSix.getDrawable());
                break;
            case 7:
                image.setDrawable(imageSeven.getDrawable());
                break;
            case 8:
                image.setDrawable(imageEight.getDrawable());
                break;
            case 9:
                image.setDrawable(imageNine.getDrawable());
                break;
            default:
                image.setDrawable(imageZero.getDrawable());
        }
    }
}
