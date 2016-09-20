package com.backbase.kalah.domain.entities;

import java.util.Arrays;

/**
 * Created by js on 9/19/16.
 */
public class Board {
    private int[] topRow;
    private int[] bottomRow;
    public int leftStore;
    public int rightStore;

    public Board(){
        leftStore = 0;
        rightStore = 0;
        topRow = new int[6];
        bottomRow = new int[6];
        Arrays.fill(topRow, 6);
        Arrays.fill(bottomRow, 6);
    }
    public int[] getTopRow() {
        return topRow;
    }

    public int[] getBottomRow() {
        return bottomRow;
    }
}
