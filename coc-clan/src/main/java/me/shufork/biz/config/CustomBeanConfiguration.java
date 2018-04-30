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

    /*
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/coc/*").allowedOrigins("http://localhost:8080");
            }
        };
    }
    */
}
