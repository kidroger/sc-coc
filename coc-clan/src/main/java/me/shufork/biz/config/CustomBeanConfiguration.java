package me.shufork.biz.config;

import me.shufork.biz.components.HotClans;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomBeanConfiguration {

    @Bean
    public HotClans hotClans(){
        return new HotClans(100);
    }
}
