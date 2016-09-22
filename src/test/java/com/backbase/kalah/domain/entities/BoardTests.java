package com.backbase.kalah.domain.entities;

import com.backbase.kalah.domain.valueObjects.Row;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by js on 9/19/16.
 */
public class BoardTests {

    private Board board;

    @Before
    public void setUp(){

        board = new Board(new Row(6, 6), new Row(6, 6));
    }

    @Test
    public void givenANewBoardThenItShouldHasTwoRowsWithSixPitsEach(){
        Assert.assertEquals(6, board.getNumberOfPitsOfTopRow());
        Assert.assertEquals(6, board.getNumberOfPitsOfBottomRow());
    }

    @Test
    public void givenANewBoardThenWithSixStonesInEachPit(){
        int[] expectedRow = new int[6];
        Arrays.fill(expectedRow, 6);
        Assert.assertArrayEquals(expectedRow, board.getTopRowPits());
        Assert.assertArrayEquals(expectedRow, board.getBottomRowPits());
    }

    @Test
    public void givenANewBoardThenStartsWithZeroEachStore(){
        Assert.assertEquals(0, board.getTopRow().getStore());
        Assert.assertEquals(0, board.getBottomRow().getStore());
    }

    @Test
    public void givenABoardThenTheSumOffAllStonesInThePitsAndInTheStoresShouldBe36(){
       int allStones =  board.getSumOfAllStones();
        Assert.assertEquals(72, allStones);
    }



}
