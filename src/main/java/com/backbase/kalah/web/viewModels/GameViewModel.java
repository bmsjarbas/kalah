package com.backbase.kalah.web.viewModels;

import com.backbase.kalah.domain.entities.Player;
import com.backbase.kalah.domain.services.Game;

/**
 * Created by js on 9/21/16.
 */
public class GameViewModel {
    private Player topRowPlayer;
    private Player bottomRowPlayer;
    private Player currentPlayer;
    private int bottomPlayerStore;
    private int topPlayerStore;
    private int[] topRow;
    private int[] bottomRow;

    public GameViewModel(Game game) {
        topPlayerStore = game.getTopPlayerStore();
        bottomPlayerStore = game.getBottomPlayerStore();
        topRow = game.getCurrentTopRowPits();
        bottomRow = game.getCurrentBottomRowPits();
        topRowPlayer = game.getTopRowPlayer();
        bottomRowPlayer = game.getBottomRowPlayer();
        currentPlayer = game.getNextPlayer();

    }

    public String getTopRowPlayerName() {
        return topRowPlayer.getName();
    }

    public String getBottomRowPlayerName() {
        return bottomRowPlayer.getName();
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

    public boolean isTopRowEnabled() {
        return  currentPlayer.equals(topRowPlayer);
    }
    public boolean isBottomRowEnabled() {
        return  currentPlayer.equals(bottomRowPlayer);
    }
}
