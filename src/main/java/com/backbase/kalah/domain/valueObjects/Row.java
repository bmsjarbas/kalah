package com.backbase.kalah.domain.valueObjects;

import java.util.Arrays;

/**
 * Created by js on 9/19/16.
 */
public class Row {
    private final int maxCapacityPerPit;
    private int[] pits;
    private int store;
    private int size;
    public Row(int size, int maxCapacityPerPity){
        pits = new int[size];
        store = 0;
        Arrays.fill(pits, maxCapacityPerPity);
        this.size = size;
        this.maxCapacityPerPit = maxCapacityPerPity;
    }

    public int getNumberOfPits(){
        return this.size;
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

    public int getMaxCapacityPerPit() {
        return maxCapacityPerPit;
    }

    public void addStonesInTheStore(int quantity) {
        this.store += quantity;
    }

    public boolean isEmpty() {
        for(int index = 0; index < pits.length; index++)
            if(pits[index] != 0)
                return false;
        return true;
    }
}
