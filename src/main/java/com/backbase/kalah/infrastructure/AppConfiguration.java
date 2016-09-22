package com.backbase.kalah.infrastructure;

import com.backbase.kalah.domain.entities.Player;
import com.backbase.kalah.domain.valueObjects.Row;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.UUID;

/**
 * Created by js on 9/21/16.
 */
@Configuration
public class AppConfiguration {
    static int playerCount = 0;
    @Bean
    @Scope("prototype")
    public Row row(){
        return new Row(6, 6);
    }

    @Bean
    @Scope("prototype")
    public Player player(){
        playerCount++;
        return new Player(UUID.randomUUID(), String.format("Player %d", playerCount));

    }
}
