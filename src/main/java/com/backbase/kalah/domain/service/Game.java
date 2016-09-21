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
    private int status;

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
        status = Status.IN_PROGRESS;
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
        int stonesToDistribute = row.getPitStones(pitIndex);
        if(stonesToDistribute == 0)
            throw new InvalidMoveException();

        row.setStonesInThePit(pitIndex, 0);
        while (stonesToDistribute > 0){
            for(int currentPitIndex =  pitIndex+1; stonesToDistribute > 0 && currentPitIndex < row.getNumberOfPits() ; currentPitIndex++){
                stonesToDistribute--;
                if(stonesToDistribute == 0 && row.isThePitEmpty(currentPitIndex)){
                    row.incrementAStoneInTheStore();
                    int opponentStones = getOpponentStones(player, currentPitIndex);
                    row.addStonesInTheStore(opponentStones);
                }
                row.incrementAStoneInThePit(currentPitIndex);
            }

            if(stonesToDistribute > 0){
                stonesToDistribute--;
                row.incrementAStoneInTheStore();

                if(stonesToDistribute == 0) {
                    if(row.isEmpty())
                        status = Status.FINISHED;
                    return;
                }
            }

            Map.Entry<Player, Row> opponentPlayer = getOppositePlayerRowEntry(player);
            Row oponnentRow = opponentPlayer.getValue();

            for(int currentPitIndex = 0; stonesToDistribute > 0 &&  currentPitIndex < oponnentRow.getNumberOfPits(); currentPitIndex++){
                stonesToDistribute--;
                oponnentRow.incrementAStoneInThePit(currentPitIndex);
            }

            if(oponnentRow.isEmpty() || row.isEmpty()){
                status = Status.FINISHED;
                return;
            }

        }

        this.nextPlayer = getOppositePlayerRowEntry(player).getKey();
    }

    private int getOpponentStones(Player player, int currentPitIndex) {
        Row opponentRow = getOppositePlayerRowEntry(player).getValue();
        int opponentStones = opponentRow.getPitStones(currentPitIndex);
        opponentRow.setStonesInThePit(currentPitIndex, 0);
        return opponentStones;
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

    public int getStatus() {
        return this.status;
    }

    public static class Status{
        public static int IN_PROGRESS = 1;
        public static int FINISHED = 2;
    }
}
