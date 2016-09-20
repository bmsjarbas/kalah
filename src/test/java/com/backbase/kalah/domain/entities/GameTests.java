package com.backbase.kalah.domain.entities;

import com.backbase.kalah.domain.exceptions.InvalidMoveException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by js on 9/19/16.
 */
public class GameTests {
    Game game;

    @Before
    public void setUp() {
        game = new Game("Player topRow", "Player bottomRow");
    }

    @Test
    public void testHasTwoPlayers() {
        Assert.assertNotNull(game.getTopRowPlayer());
        Assert.assertNotNull(game.getBottomRowPlayer());
    }

    @Test
    public void testHasABoard() {
        Assert.assertNotNull(game.getBoard());
    }

    @Test
    public void shouldStartsWithBottomPlayerInTheFirstMove() {
        Player nextPlayer = game.getNextPlayer();
        assertEquals("Player bottomRow", nextPlayer.getName());
    }

    @Test
    public void givenTheMoveFrom3rdPitShouldAddOneStoneOnCurrentPlayerStore() throws InvalidMoveException {
        Player player = game.getNextPlayer();
        int pitIndex = 2;
        game.move(player, pitIndex);
        assertEquals(1, game.getBottomPlayerStore());
    }

    @Test
    public void givenAMoveShouldChangeTheNextPlayer() throws InvalidMoveException {
        Player firstPlayer = game.getNextPlayer();
        int pitIndex = 2;
        game.move(firstPlayer, pitIndex);
        Player currentPlayer = game.getNextPlayer();
        Assert.assertNotEquals(firstPlayer, currentPlayer);
    }

    @Test
    public void testMoveFrom3rdPitInTheFirstTurn() throws InvalidMoveException {
        Player player = game.getNextPlayer();
        int pitIndex = 2;
        game.move(player, pitIndex);
        int[] expectedTopRow = new int[]{7, 7, 6, 6, 6, 6};
        int[] expectedBottomRow = new int[]{6, 6, 0, 7, 7, 7};
        Assert.assertArrayEquals(expectedTopRow, game.getCurrentTopRowPits());
        Assert.assertArrayEquals(expectedBottomRow, game.getCurrentBottomRowPits());
    }

    @Test
    public void givenTwoMovesFrom3rdPitShouldAddOneStoneForEachPlayerStore() throws InvalidMoveException {
        Player player = game.getNextPlayer();
        int pitIndex = 2;
        game.move(player, pitIndex);
        Player nextPlayer = game.getNextPlayer();
        game.move(nextPlayer, pitIndex);
        assertEquals(1, game.getBottomPlayerStore());
        assertEquals(1, game.getTopPlayerStore());
    }

    @Test
    public void givenTwoMovesTheTopRowShouldBe770777() throws InvalidMoveException {
        Player player = game.getNextPlayer();
        int pitIndex = 2;
        game.move(player, pitIndex);
        Player nextPlayer = game.getNextPlayer();
        game.move(nextPlayer, pitIndex);
        int[] expectedTopRow = new int[]{7, 7, 0, 7, 7, 7};
        Assert.assertArrayEquals(expectedTopRow, game.getCurrentTopRowPits());
    }

    @Test
    public void givenTwoMovesTheBottomRowShouldBe770777() throws InvalidMoveException {
        Player player = game.getNextPlayer();
        int pitIndex = 2;
        game.move(player, pitIndex);
        Player nextPlayer = game.getNextPlayer();
        game.move(nextPlayer, pitIndex);
        int[] expectedBottomRow = new int[]{7, 7, 0, 7, 7, 7};
        Assert.assertArrayEquals(expectedBottomRow, game.getCurrentBottomRowPits());
    }

    @Test
    public void givenAMoveWhenTheLastStoneIsDepositedOnCurrentPlayerStoreShouldHimGetAFreeTurn() throws InvalidMoveException {
        Player player = game.getNextPlayer();
        int pitIndex = 0;
        game.move(player, pitIndex);
        Player nextPlayer = game.getNextPlayer();
        assertEquals(player, nextPlayer);
    }


    @Test
    public void giveAMoveWhenTheLastStoneIsDroppedInAEmptyPitOnCurrentPlayerSideThenThisStoneAndAllFromOppositePitWillBeTakenAndDepositedOnCurrentPlayerStore() throws InvalidMoveException {

        game.move(game.getNextPlayer(), 0);
        game.move(game.getNextPlayer(), 1);

        int[] expectedTopRow = new int[]{7,7,6,6,6,6};
        int[] expectedBottomRow = new int[]{0,0,8,8,8,8};
        expectedTopRow = new int[]{7,7,6,6,6,6};

        game.move(game.getNextPlayer(), 0);
        expectedBottomRow = new int[]{1,1,8,8,8,8};
        expectedTopRow = new int[]{0,8,7,7,7,7};

        //Assert.assertEquals(13, game.getBottomPlayerStore());


    }


    @Test(expected = InvalidMoveException.class)
    public void givenAMoveFromAEmptyPitShouldThrowAnException() throws InvalidMoveException {
        game.move(game.getNextPlayer(), 0);
        game.move(game.getNextPlayer(), 0);

    }

   

}
