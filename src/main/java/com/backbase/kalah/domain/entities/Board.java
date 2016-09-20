package com.backbase.kalah.domain.entities;

import com.backbase.kalah.domain.valueObjects.Row;

/**
 * Created by js on 9/19/16.
 */
public class Board {
   private Row topRow;
   private Row bottomRow;

    public Board(){
        topRow = new Row();
        bottomRow = new Row();

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
}
