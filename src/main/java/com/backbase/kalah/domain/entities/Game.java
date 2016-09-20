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
        nextPlayer = bottomRowPlayer;
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

    public int[] getCurrentTopRowPits() {
        return board.getTopRowPits();
    }

    public int[] getCurrentBottomRowPits() {
        return board.getBottomRowPits();
    }

    public void move(final Player player, int pitIndex) {
        Row row = mapPlayerRow.get(player);
        int stonesInPit = row.getPitStones(pitIndex);
        row.setStonesInThePit(pitIndex, 0);

        for(int currentPitIndex =  pitIndex+1; stonesInPit > 0 && currentPitIndex < row.getNumberOfPits() ; currentPitIndex++){
            stonesInPit--;
            row.incrementAStoneInThePit(currentPitIndex);
        }

        if(stonesInPit > 0){
            stonesInPit--;
            row.incrementAStoneInTheStore();
        }

         Map.Entry<Player, Row> opponentPlayer = mapPlayerRow.entrySet()
                 .parallelStream()
                 .filter(e -> e.getKey() != player)
                 .findFirst().get();

        Row oponnentRow = opponentPlayer.getValue();

        for(int currentPitIndex = oponnentRow.getNumberOfPits() - 1; stonesInPit > 0 &&  currentPitIndex >= 0; currentPitIndex--){
            stonesInPit--;
            oponnentRow.incrementAStoneInThePit(currentPitIndex);
        }
    }

    public int getBottomPlayerStore() {
        return mapPlayerRow.get(bottomRowPlayer).getStore();
    }
}
