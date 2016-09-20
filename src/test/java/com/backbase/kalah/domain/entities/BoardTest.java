package com.backbase.kalah.domain.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by js on 9/19/16.
 */
public class BoardTest {

    private Board board;

    @Before
    public void setUp(){
        board = new Board();
    }

    @Test
    public void testHasTwoRowsWithSixPitsEach(){
        Assert.assertEquals(6, board.getNumberOfPitsOfTopRow());
        Assert.assertEquals(6, board.getNumberOfPitsOfBottomRow());
    }

    @Test
    public void testStartsWithSixStonesInEachPit(){
        int[] expectedRow = new int[6];
        Arrays.fill(expectedRow, 6);
        Assert.assertArrayEquals(expectedRow, board.getTopRowPits());
        Assert.assertArrayEquals(expectedRow, board.getBottomRowPits());
    }

    @Test
    public void testStartsWithZeroEachStore(){
        Assert.assertEquals(0, board.getTopRow().getStore());
        Assert.assertEquals(0, board.getBottomRow().getStore());
    }

}
