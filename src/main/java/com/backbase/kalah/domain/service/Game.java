package com.backbase.kalah.domain.service;


import com.backbase.kalah.domain.entities.Board;
import com.backbase.kalah.domain.entities.Player;
import com.backbase.kalah.domain.exceptions.InvalidMoveException;
import com.backbase.kalah.domain.valueObjects.Row;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by js on 9/19/16.
 */
public class Game{
    private Player topRowPlayer;
    private Player bottomRowPlayer;
    private Board board;
    private Map<Player, Row> mapPlayerRow;
    private Player nextPlayer;

    public Game(Board board, String topRowPlayerName, String bottomRowPlayerName) {
        initializeObjects(board, topRowPlayerName, bottomRowPlayerName);
        mapPlayerWithRow();
    }

    private void initializeObjects(Board board, String topRowPlayerName, String bottomRowPlayerName) {

        this.topRowPlayer = new Player(topRowPlayerName);
        this.bottomRowPlayer = new Player(bottomRowPlayerName);
        this.board = board;
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

    public void move(final Player player, int pitIndex) throws InvalidMoveException {
        Row row = mapPlayerRow.get(player);
        int stonesInPit = row.getPitStones(pitIndex);
        if(stonesInPit == 0)
            throw new InvalidMoveException();
        row.setStonesInThePit(pitIndex, 0);

        for(int currentPitIndex =  pitIndex+1; stonesInPit > 0 && currentPitIndex < row.getNumberOfPits() ; currentPitIndex++){
            stonesInPit--;

            if(stonesInPit == 0 && row.getPitStones(currentPitIndex) == 0){
                Row opponentRow = getOppositePlayerRowEntry(player).getValue();
                int opponentStones = opponentRow.getPitStones(currentPitIndex);
                opponentRow.setStonesInThePit(currentPitIndex, 0);
                row.addStonesInTheStore(opponentStones + 1);
            }
            row.incrementAStoneInThePit(currentPitIndex);
        }

        if(stonesInPit > 0){
            stonesInPit--;
            row.incrementAStoneInTheStore();

            if(stonesInPit == 0)
                return;
        }

        Map.Entry<Player, Row> opponentPlayer = getOppositePlayerRowEntry(player);

        Row oponnentRow = opponentPlayer.getValue();

        for(int currentPitIndex = 0; stonesInPit > 0 &&  currentPitIndex < oponnentRow.getNumberOfPits(); currentPitIndex++){
            stonesInPit--;
            oponnentRow.incrementAStoneInThePit(currentPitIndex);
        }

        this.nextPlayer = opponentPlayer.getKey();
    }

    private Map.Entry<Player, Row> getOppositePlayerRowEntry(Player player) {
        return mapPlayerRow.entrySet()
                     .stream()
                     .filter(e -> e.getKey() != player)
                     .findFirst().get();
    }

    public int getBottomPlayerStore() {
        return mapPlayerRow.get(bottomRowPlayer).getStore();
    }

    public int getTopPlayerStore() {
        return mapPlayerRow.get(topRowPlayer).getStore();
    }
}