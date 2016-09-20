package com.backbase.kalah.domain.entities;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by js on 9/19/16.
 */
public class BoardTest {

    @Test
    public void testHasTwoRowsWithSixPitsEach(){
        Board board = new Board();
        int[] leftRow =  board.getLeftRow();
        int[] rightRow =  board.getRightRow();
        Assert.assertEquals(6, leftRow.length);
        Assert.assertEquals(6, rightRow.length);

    }
}
