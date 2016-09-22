package com.backbase.kalah.web.controllers;

import com.backbase.kalah.domain.exceptions.InvalidMoveException;
import com.backbase.kalah.domain.services.Game;
import com.backbase.kalah.web.viewModels.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by js on 9/21/16.
 */
@Controller
@RequestMapping("/game")
public class GameController {
    
    private Game game;
    @Autowired( required = true )
    public GameController(Game game){
        this.game = game;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        GameViewModel viewModel = new GameViewModel(game);
        return new ModelAndView("board", "game", viewModel);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String move(@RequestParam(name = "pits")  int pits) throws InvalidMoveException {
        game.move(game.getNextPlayer(), pits);
            if(game.isFinished())
                return "redirect:/end";
        return "redirect:/game";
    }

    @RequestMapping(path = "/end", method = RequestMethod.GET)
    public ModelAndView end(){
        return new ModelAndView("end");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public String restart(){
        return "redirect:/game";
    }


}
