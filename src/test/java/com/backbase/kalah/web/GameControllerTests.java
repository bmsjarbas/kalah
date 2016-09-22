package com.backbase.kalah.web;

import com.backbase.kalah.domain.services.Game;
import com.backbase.kalah.infrastructure.AppConfiguration;
import com.backbase.kalah.web.controllers.GameController;
import com.backbase.kalah.web.viewModels.GameViewModel;
import com.sun.xml.internal.ws.policy.AssertionSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


/**
 * Created by js on 9/22/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("WEB-INF/web.xml")
@ContextHierarchy({
        @ContextConfiguration("/WEB-INF/kalah-servlet.xml"),
        @ContextConfiguration(classes = AppConfiguration.class)
})

public class GameControllerTests {
    private MockMvc mockMvc;
    @Autowired
    Game game;
    @Before
    public void setup() {
        mockMvc = standaloneSetup(new GameController(game))
                .defaultRequest(get("/")
                        .servletPath("/kalah")
                        .accept(MediaType.ALL)).build();
    }

    @Test
    public void givenAGetRequestShouldReturnBoardViewModel() throws Exception {
        mockMvc.perform(get("/kalah/game"))
                .andExpect(status().isOk())
                .andExpect(view().name("board"));
    }

    @Test
    public void givenAPostWithMoveFromIndex0WhenItWasTheFirstMoveThenTheBottomPlayerStoreShouldBe1() throws Exception {
        mockMvc.perform(put("/kalah/game").param("pits", "0"))
                .andExpect(status().is3xxRedirection());

        ResultActions perform = mockMvc.perform(get("/kalah/game"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("game"));

        GameViewModel vm = (GameViewModel)perform.andReturn().getModelAndView().getModel().get("game");
        Assert.assertEquals(1, vm.getBottomPlayerStore());


    }

    @Test
    public void givenADeleteThenTheGameShouldBeRestarted() throws Exception {
        mockMvc.perform(put("/kalah/game").param("pits", "0"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(delete("/kalah/game"))
                .andExpect(status().is3xxRedirection());

        ResultActions perform = mockMvc.perform(get("/kalah/game"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("game"));

        GameViewModel vm = (GameViewModel)perform.andReturn().getModelAndView().getModel().get("game");
        int[] topRow = vm.getTopRow();
        int[] bottomRow = vm.getBottomRow();
        int topStore = vm.getTopPlayerStore();
        int bottomStore = vm.getBottomPlayerStore();
        int[] initialRow = new int[]{6,6,6,6,6,6};
        Assert.assertArrayEquals(initialRow, topRow);
        Assert.assertArrayEquals(initialRow, bottomRow);
        Assert.assertEquals(0, bottomStore);
        Assert.assertEquals(0, topStore);
        Assert.assertTrue(vm.isBottomRowEnabled());
    }
}
