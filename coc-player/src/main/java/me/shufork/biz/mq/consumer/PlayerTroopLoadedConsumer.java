package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.PlayerTroopService;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.player.PlayerTroopLoadedPayload;
import me.shufork.common.mq.sink.PlayerTroopLoadedSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerTroopLoadedConsumer  implements MessageConsumer<PlayerTroopLoadedPayload> {
    @Autowired
    private PlayerTroopService playerTroopService;

    @StreamListener(PlayerTroopLoadedSink.INPUT)
    @Override
    public void handleMessage(PlayerTroopLoadedPayload message) {
        final String owner = message.getPlayerTag();
        log.debug("handling troops for player({})",owner);
        if(message.getTroops()!=null && !message.getTroops().isEmpty() ){
            playerTroopService.relinkPlayerTroops(owner,message.getTroops());
        }
    }
}
