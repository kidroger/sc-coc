package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.PlayerTracker;
import me.shufork.common.dto.supercell.coc.PlayerBasicInfoDto;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.discovery.PlayerFoundPayload;
import me.shufork.common.mq.sink.PlayerFoundSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerFoundConsumer implements MessageConsumer<PlayerFoundPayload> {

    @Autowired
    private PlayerTracker playerTracker;

    @StreamListener(PlayerFoundSink.INPUT)
    @Override
    public void handleMessage(PlayerFoundPayload message) {
        final PlayerBasicInfoDto player = message.getPlayer();
        log.trace("handling for player found,tag = {},name = {}",player.getTag(),player.getName());
        playerTracker.addPlayer(player);
    }
}
