package me.shufork.biz.mq.publisher;

import me.shufork.common.dto.supercell.coc.PlayerBasicInfoDto;
import me.shufork.common.mq.payload.discovery.PlayerFoundPayload;
import me.shufork.common.mq.source.PlayerFoundSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class PlayerFoundPublisher {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PlayerFoundSource playerFoundSource;

    public void publishPlayerFound(PlayerBasicInfoDto player){
        PlayerFoundPayload payload = new PlayerFoundPayload();
        payload.setPlayer(player);
        Message<PlayerFoundPayload> message =MessageBuilder.withPayload(payload).build();
        playerFoundSource.output().send(message);
    }
    public void publishPlayerFound(Iterable<PlayerBasicInfoDto> players){
        players.forEach(o->publishPlayerFound(o));
    }
}
