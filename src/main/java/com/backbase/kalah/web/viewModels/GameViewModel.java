package com.backbase.kalah.web.viewModels;

import com.backbase.kalah.domain.services.Game;

/**
 * Created by js on 9/21/16.
 */
public class GameViewModel {
    private String topRowPlayerName;
    private String bottomRowPlayerName;
    private int bottomPlayerStore;
    private int topPlayerStore;
    private int[] topRow;
    private int[] bottomRow;

    public GameViewModel() {
    }

    public GameViewModel(Game game) {
        topPlayerStore = game.getTopPlayerStore();
        bottomPlayerStore = game.getBottomPlayerStore();
        topRow = game.getCurrentTopRowPits();
        bottomRow = game.getCurrentBottomRowPits();
        topRowPlayerName = game.getTopRowPlayer().getName();
        bottomRowPlayerName = game.getBottomRowPlayer().getName();

    }

    public String getTopRowPlayerName() {
        return topRowPlayerName;
    }

    public String getBottomRowPlayerName() {
        return bottomRowPlayerName;
    }

    public int getBottomPlayerStore() {
        return bottomPlayerStore;
    }

    public int getTopPlayerStore() {
        return topPlayerStore;
    }

    public int[] getTopRow() {
        return topRow;
    }

    public int[] getBottomRow() {
        return bottomRow;
    }
}
