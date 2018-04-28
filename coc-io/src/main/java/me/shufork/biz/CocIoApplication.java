package me.shufork.biz;

import me.shufork.common.mq.sink.CocIoTaskCreatedSink;
import me.shufork.common.mq.source.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"me.shufork.biz", "me.shufork.common.config.swagger"})
@EnableDiscoveryClient
@EnableBinding({
        CocStateNotificationSource.class,
        CocIoTaskCreatedSink.class,
        PlayerDetailedInfoLoadedSource.class,
        LeagueLoadedSource.class,
        ClanDetailedInfoLoadedSource.class,
        WarLogLoadedSource.class,
        WarLogRejectedSource.class,
        PlayerTroopLoadedSource.class,
        PlayerSpellLoadedSource.class,
        PlayerLegendStatisticLoadedSource.class,
        PlayerHeroLoadedSource.class,
        PlayerAchievementLoadedSource.class})
@EnableSwagger2
public class CocIoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CocIoApplication.class, args);
    }
}