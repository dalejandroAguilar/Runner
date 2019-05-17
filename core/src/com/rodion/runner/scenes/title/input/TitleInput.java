package com.rodion.runner.scenes.title.input;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.rodion.runner.runnerWorld.World;
import com.rodion.runner.scenes.title.TitleScreen;

public class TitleInput implements GestureDetector.GestureListener {
    private TitleScreen titleScreen;

    public TitleInput(TitleScreen titleScreen) {
        this.titleScreen = titleScreen;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {

        return true;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {

        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        if (Math.abs(velocityX) > Math.abs(velocityY))
            if (velocityX > 0)
                ;
                //gameScreen.getArenaStage().onAction(RIGHT);

            else
                //gameScreen.getArenaStage().onAction(LEFT);
                ;
        else if (Math.abs(velocityX) < Math.abs(velocityY))
            if (velocityY > 0)
                //gameScreen.getArenaStage().onAction(UP);
                //world.unjump()
                //

                ;
            else

                //gameScreen.getArenaStage().onAction(DOWN);
                //world.jump()
                titleScreen.onPlay()
                ;
        else
            return true;
        return true;

    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {

    }
}
