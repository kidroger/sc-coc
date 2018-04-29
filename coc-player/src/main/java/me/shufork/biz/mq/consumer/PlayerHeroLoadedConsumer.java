package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.PlayerHeroService;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.player.PlayerHeroLoadedPayload;
import me.shufork.common.mq.sink.PlayerHeroLoadedSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerHeroLoadedConsumer implements MessageConsumer<PlayerHeroLoadedPayload> {

    @Autowired
    private PlayerHeroService playerHeroService;

    @StreamListener(PlayerHeroLoadedSink.INPUT)
    @Override
    public void handleMessage(PlayerHeroLoadedPayload message) {
        final String owner = message.getPlayerTag();
        log.trace("handling heroes for player({})",owner);
        if(message.getHeroes()!=null && !message.getHeroes().isEmpty() ){
            playerHeroService.relinkPlayerHeroes(owner,message.getHeroes());
        }
    }
}
