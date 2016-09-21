package com.backbase.kalah.infrastructure;

import com.backbase.kalah.domain.entities.Player;
import com.backbase.kalah.domain.valueObjects.Row;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by js on 9/21/16.
 */
@Configuration
public class AppConfiguration {
    @Bean
    public Row row(){
        return new Row(6, 6);
    }

    @Bean
    public Player player(){
        return new Player("asd");
    }
}
