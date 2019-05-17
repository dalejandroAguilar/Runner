package com.rodion.runner.scenes.gameOver;

import com.rodion.runner.BaseScreen;
import com.rodion.runner.MainGame;
import com.rodion.runner.scenes.game3D.Game3DScreen;

public class GameOverScreen extends BaseScreen {
    private Game3DScreen game3DScreen;
    public GameOverScreen(MainGame mainGame, Game3DScreen game3DScreen) {
        super(mainGame);
        this.game3DScreen = game3DScreen;
    }
}
