package com.backbase.kalah.domain.entities;

import com.backbase.kalah.domain.valueObjects.Row;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by js on 9/19/16.
 */
public class Game {
    private Player topRowPlayer;
    private Player bottomRowPlayer;
    private Board board;
    private Map<Player, Row> mapPlayerRow;
    private Player nextPlayer;

    public Game(String topRowPlayerName, String bottomRowPlayerName) {
        initializeObjects(topRowPlayerName, bottomRowPlayerName);
        mapPlayerWithRow();
    }

    private void initializeObjects(String topRowPlayerName, String bottomRowPlayerName) {
        this.topRowPlayer = new Player(topRowPlayerName);
        this.bottomRowPlayer = new Player(bottomRowPlayerName);
        this.board = new Board();
        this.mapPlayerRow = new HashMap<Player, Row>(2);
        nextPlayer = topRowPlayer;
    }

    private void mapPlayerWithRow(){
        mapPlayerRow.put(topRowPlayer, board.getTopRow());
        mapPlayerRow.put(bottomRowPlayer, board.getBottomRow());
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

    public Player getNextPlayer() {
        return nextPlayer;
    }
}
