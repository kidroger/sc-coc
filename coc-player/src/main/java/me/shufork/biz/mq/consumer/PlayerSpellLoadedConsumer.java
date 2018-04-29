package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.PlayerSpellService;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.player.PlayerSpellLoadedPayload;
import me.shufork.common.mq.sink.PlayerSpellLoadedSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerSpellLoadedConsumer implements MessageConsumer<PlayerSpellLoadedPayload> {

    @Autowired
    private PlayerSpellService playerSpellService;

    @StreamListener(PlayerSpellLoadedSink.INPUT)
    @Override
    public void handleMessage(PlayerSpellLoadedPayload message) {
        final String owner = message.getPlayerTag();
        log.trace("handling spells for player({})",owner);
        if(message.getSpells()!=null && !message.getSpells().isEmpty() ){
            playerSpellService.relinkPlayerSpells(owner,message.getSpells());
        }
    }
}
