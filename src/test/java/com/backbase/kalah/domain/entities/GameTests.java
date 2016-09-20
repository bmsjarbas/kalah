package com.backbase.kalah.domain.entities;

import com.backbase.kalah.domain.exceptions.InvalidMoveException;
import com.backbase.kalah.domain.service.Game;
import com.backbase.kalah.domain.valueObjects.Row;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by js on 9/19/16.
 */
public class GameTests {
    Game game;
    Board board;
    Row topRow;
    Row bottomRow;

    @Before
    public void setUp() {
        topRow = new Row(6, 6);
        bottomRow = new Row(6,6);
        board = new Board(bottomRow, topRow);
        game = new Game(board, "Player topRow", "Player bottomRow");
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

    @Test(expected = InvalidMoveException.class)
    public void givenAMoveFromAEmptyPitShouldThrowAnException() throws InvalidMoveException {
        game.move(game.getNextPlayer(), 0);
        game.move(game.getNextPlayer(), 0);
    }

    @Test
    public void  giveAMoveWhenTheLastStoneYouDropIsInAnEmptyPitOnCurrentPlayerSideThenCaptureThatAndAllStonesInThePitDirectlyOpposite(){
        Row bottomRow = new Row(6,6);
        Row topRow = new Row(6,6);
        board = new Board(bottomRow, topRow);

    }

}
