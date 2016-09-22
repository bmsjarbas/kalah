package com.backbase.kalah.domain.entities;

import com.backbase.kalah.domain.valueObjects.StatusGame;
import com.backbase.kalah.domain.exceptions.InvalidMoveException;
import com.backbase.kalah.domain.services.Game;
import com.backbase.kalah.domain.valueObjects.Row;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * Created by js on 9/19/16.
 */
public class GameTest {
    Game game;
    Board board;
    Row topRow;
    Row bottomRow;

    @Before
    public void setUp() {
        topRow = new Row(6, 6);
        bottomRow = new Row(6,6);
        board = new Board(bottomRow, topRow);

        game = new Game(board, new Player(UUID.randomUUID(), "Player topRow"), new Player(UUID.randomUUID(),"Player bottomRow"));
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
    public void  givenAMoveWhenTheLastStoneYouDropIsInAnEmptyPitOnCurrentPlayerSideThenCaptureThatAndAllStonesInThePitDirectlyOpposite() throws InvalidMoveException {

        game.move(game.getNextPlayer(), 0);
        game.move(game.getNextPlayer(), 1);
        game.move(game.getNextPlayer(), 0);
        game.move(game.getNextPlayer(), 0);
        Assert.assertEquals(11,  game.getBottomPlayerStore());

    }

    @Test
    public void givenAGameWhenSomeRowHasAllPitsEmptyThenTheGameEnds() throws InvalidMoveException {
        bottomRow = getRowWithOnlyOnePitFilled();
        topRow = new Row(6, 6);
        board = new Board(bottomRow, topRow);
        game = new Game(board, new Player(UUID.randomUUID(), "Player topRow"), new Player(UUID.randomUUID(),"Player bottomRow"));
        game.move(game.getNextPlayer(), 5);
        Assert.assertEquals(StatusGame.FINISHED,  game.getStatus());

    }

    @Test
    public void givenANewGameTheStatusShouldBeNotStarted(){
        Assert.assertEquals(StatusGame.IN_PROGRESS, game.getStatus());
    }

    @Test
    public void givenANewGameAndANewMoveTheStatusShouldBeInProgress() throws InvalidMoveException {
        game.move(game.getNextPlayer(), 0);
        Assert.assertEquals(StatusGame.IN_PROGRESS, game.getStatus());
    }

    @Test
    public void givenANewGameWithOneTurnPlayedThenAfterResetTheValuesShouldBeDefault() throws InvalidMoveException {
        game.move(game.getNextPlayer(), 0);
        game.reset();
        int[] topRow = game.getCurrentTopRowPits();
        int[] bottomRow = game.getCurrentBottomRowPits();
        int topStore = game.getTopPlayerStore();
        int bottomStore = game.getBottomPlayerStore();
        int[] initialRow = new int[]{6,6,6,6,6,6};
        Assert.assertArrayEquals(initialRow, topRow);
        Assert.assertArrayEquals(initialRow, bottomRow);
        Assert.assertEquals(0, bottomStore);
        Assert.assertEquals(0, topStore);
        Assert.assertEquals(game.getBottomRowPlayer(), game.getNextPlayer());
    }

    private Row getRowWithOnlyOnePitFilled(){
        Row row = new Row(6, 6);
        for(int index = 0; index < row.getNumberOfPits() -1; index ++){
            row.setStonesInThePit(index, 0);
        }
        row.setStonesInThePit(row.getNumberOfPits()-1, 1);
        return row;
    }



}
