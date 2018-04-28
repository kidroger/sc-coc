package me.shufork.biz;

import me.shufork.common.mq.sink.*;
import me.shufork.common.mq.source.CocIoTaskCreatedSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"me.shufork.biz", "me.shufork.common"})
@EnableDiscoveryClient
@EnableBinding({
        CocIoTaskCreatedSource.class,
        LeagueLoadedSink.class,
        PlayerDetailedInfoLoadedSink.class,
        PlayerTroopLoadedSink.class,
        PlayerSpellLoadedSink.class,
        PlayerLegendStatisticLoadedSink.class,
        PlayerHeroLoadedSink.class,
        PlayerAchievementLoadedSink.class})
@EnableJpaRepositories(basePackages = "me.shufork.biz.repository")
@EnableSwagger2
public class CocPlayerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CocPlayerApplication.class, args);
    }
}