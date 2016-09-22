package com.backbase.kalah.web;

import com.backbase.kalah.domain.services.Game;
import com.backbase.kalah.infrastructure.AppConfiguration;
import com.backbase.kalah.web.controllers.GameController;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
