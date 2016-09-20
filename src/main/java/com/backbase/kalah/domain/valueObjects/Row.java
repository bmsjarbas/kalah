package com.backbase.kalah.domain.valueObjects;

import java.util.Arrays;

/**
 * Created by js on 9/19/16.
 */
public class Row {
    private int[] pits;
    private int store;
    public Row(){
        pits = new int[6];
        store = 0;
        Arrays.fill(pits, 6);
    }

    public int getNumberOfPits(){
        return pits.length;
    }

    public int[] getPits() {
        return pits;
    }

    public int getStore() {
        return store;
    }

    public int getPitStones(int index){
        return pits[index];
    }

    public void setStonesInThePit(int index, int stones){
        pits[index] = stones;
    }

    public void incrementAStoneInThePit(int index) {
        pits[index]++;
    }

    public void incrementAStoneInTheStore() {
        store++;
    }
}
