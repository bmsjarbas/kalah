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
        Assert.assertEquals(6, board.getLeftRow().length);
        Assert.assertEquals(6, board.getRightRow().length);
    }

    @Test
    public void testStartsWithSixStonesInEachPit(){
        int[] expectedRow = new int[6];
        Arrays.fill(expectedRow, 6);
        Assert.assertArrayEquals(expectedRow, board.getLeftRow());
        Assert.assertArrayEquals(expectedRow, board.getRightRow());
    }

    @Test
    public void testStartsWithZeroEachStore(){
        Assert.assertEquals(0, board.leftStore);
        Assert.assertEquals(0, board.rightStore);
    }

}
