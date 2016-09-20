package com.backbase.kalah.domain.entities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by js on 9/19/16.
 */
public class GameTests {
    Game game;

    @Before
    public void setUp(){
        game = new Game("Player topRow", "Player bottomRow");
    }

    @Test
    public void testHasTwoPlayers(){
        Assert.assertNotNull(game.getTopRowPlayer());
        Assert.assertNotNull(game.getBottomRowPlayer());
    }

    @Test
    public void testHasABoard(){
        Assert.assertNotNull(game.getBoard());
    }
    @Test
    public void shouldStartsWithBottomPlayerInTheFirstMove(){
        Player nextPlayer = game.getNextPlayer();
        Assert.assertEquals("Player bottomRow", nextPlayer.getName());
    }

    @Test
    public void givenTheMoveFrom3rdPitShouldAddOneStoneOnCurrentPlayerStore(){
        Player player = game.getNextPlayer();
        int pitIndex = 2;
        game.move(player, pitIndex);
        Assert.assertEquals(1, game.getBottomPlayerStore());
    }

    @Test
    public void givenAMoveShouldChangeTheNextPlayer(){
        Player firstPlayer = game.getNextPlayer();
        int pitIndex = 2;
        game.move(firstPlayer, pitIndex);
        Player currentPlayer = game.getNextPlayer();
        Assert.assertNotEquals(firstPlayer, currentPlayer);
    }

    @Test
    public void testMoveFrom3rdPitInTheFirstTurn(){
        Player player = game.getNextPlayer();
        int pitIndex = 2;
        game.move(player, pitIndex);
        int[] expectedTopRow = new int[]{7,7,6,6,6,6};
        int[] expectedBottomRow = new int[]{6,6,0,7,7,7};
        Assert.assertArrayEquals(expectedTopRow, game.getCurrentTopRowPits());
        Assert.assertArrayEquals(expectedBottomRow, game.getCurrentBottomRowPits());
    }

    @Test
    public void givenTwoMovesFrom3rdPitShouldAddOneStoneForEachPlayerStore(){
        Player player = game.getNextPlayer();
        int pitIndex = 2;
        game.move(player, pitIndex);
        Player nextPlayer = game.getNextPlayer();
        game.move(nextPlayer, pitIndex);
        Assert.assertEquals(1, game.getBottomPlayerStore());
        Assert.assertEquals(1, game.getTopPlayerStore());
    }

    @Test
    public void givenTwoMovesTheTopRowShouldBe770777(){
        Player player = game.getNextPlayer();
        int pitIndex = 2;
        game.move(player, pitIndex);
        Player nextPlayer = game.getNextPlayer();
        game.move(nextPlayer, pitIndex);
        int[] expectedTopRow = new int[]{7,7,0,7,7,7};
        Assert.assertArrayEquals(expectedTopRow, game.getCurrentTopRowPits());
    }

    @Test
    public void givenTwoMovesTheBottomRowShouldBe770777(){
        Player player = game.getNextPlayer();
        int pitIndex = 2;
        game.move(player, pitIndex);
        Player nextPlayer = game.getNextPlayer();
        game.move(nextPlayer, pitIndex);
        int[] expectedBottomRow = new int[]{7,7,0,7,7,7};
        Assert.assertArrayEquals(expectedBottomRow, game.getCurrentBottomRowPits());
    }

    @Test
    public void givenAMoveWhenTheLastStoneIsDepositedOnCurrentPlayerStoreShouldHimGetAFreeTurn(){
        Player player = game.getNextPlayer();
        int pitIndex = 0;
        game.move(player, pitIndex);
        Player nextPlayer = game.getNextPlayer();
        Assert.assertEquals(player, nextPlayer);
    }

    @Test
    public void giveAMoveWhenTheLastStoneIsDroppedInAEmptyPitOnCurrentPlayerSideThenThisStoneAndAllFromOppositePitWillBeTakenAndDepositedOnCurrentPlayerStore(){
        Player player = game.getNextPlayer();
        int pitIndex = 2;
        game.move(player, pitIndex);
        game.move(game.getNextPlayer(), pitIndex);
        game.move(game.getNextPlayer(), pitIndex);
        int[] expectedTopRow = new int[]{7,7,0,7,7,7};
        int[] expectedBottomRow = new int[]{7,7,0,7,7,7};


    }

}
