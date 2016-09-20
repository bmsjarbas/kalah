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
    public void testStartsWithTopRowPlayer(){
        Player nextPlayer = game.getNextPlayer();
        Assert.assertEquals("Player topRow", nextPlayer.getName());
    }

}
