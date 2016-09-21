package com.backbase.kalah.domain.entities;

import com.backbase.kalah.domain.valueObjects.Row;

/**
 * Created by js on 9/19/16.
 */
public class Board {
   private Row topRow;
   private Row bottomRow;

    public Board(Row bottomRow, Row topRow){
        this.topRow = topRow;
        this.bottomRow = bottomRow;
    }
    public Row getTopRow() {
        return topRow;
    }
    public Row getBottomRow() {
        return bottomRow;
    }
    public int getNumberOfPitsOfTopRow(){
        return topRow.getNumberOfPits();
    }
    public int getNumberOfPitsOfBottomRow(){
        return bottomRow.getNumberOfPits();
    }
    public int[] getTopRowPits() {
        return topRow.getPits();
    }
    public int[] getBottomRowPits() {
        return bottomRow.getPits();
    }
    public int getSumOfAllStones() {
        return countAllStones(bottomRow) + countAllStones(topRow);
    }
    private int countAllStones(Row row){
        int sum = 0;
        for(int index = 0; index < row.getNumberOfPits(); index++)
            sum+=  row.getPitStones(index);
        return sum + row.getStore();
    }
}
