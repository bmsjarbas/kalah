package com.backbase.kalah.domain.entities;

/**
 * Created by js on 9/19/16.
 */
public class Game {
    private Player topRowPlayer;
    private Player bottomRowPlayer;
    private Board board;

    public Game(String topRowPlayerName, String bottomRowPlayerName) {
        this.topRowPlayer = new Player(topRowPlayerName);
        this.bottomRowPlayer = new Player(bottomRowPlayerName);
        this.board = new Board();
    }

    public Player getTopRowPlayer() {
        return this.topRowPlayer;
    }

    public Player getBottomRowPlayer() {
        return this.bottomRowPlayer;
    }

    public Board getBoard() {
        return this.board;
    }
}
