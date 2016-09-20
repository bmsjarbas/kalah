package com.backbase.kalah.domain.entities;

import java.util.Arrays;

/**
 * Created by js on 9/19/16.
 */
public class Board {
    private int[] leftRow;
    private int[] rightRow;
    public Board(){
        leftRow = new int[6];
        rightRow = new int[6];
        Arrays.fill(leftRow, 6);
        Arrays.fill(rightRow, 6);
    }
    public int[] getLeftRow() {
        return leftRow;
    }

    public int[] getRightRow() {
        return rightRow;
    }
}
