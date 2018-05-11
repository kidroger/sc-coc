package me.shufork.biz;

import me.shufork.common.mq.sink.WarLogLoadedSink;
import me.shufork.common.mq.sink.WarLogRejectedSink;
import me.shufork.common.mq.source.ClanFoundSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(scanBasePackages = {"me.shufork.biz", "me.shufork.common"})
@EnableDiscoveryClient
@EnableBinding({
        ClanFoundSource.class,
        WarLogLoadedSink.class,
        WarLogRejectedSink.class})
@EnableJpaRepositories(basePackages = "me.shufork.biz.repository")
@EnableSwagger2
public class CocWarApplication {
    public static void main(String[] args) {
        SpringApplication.run(CocWarApplication.class, args);
    }
}