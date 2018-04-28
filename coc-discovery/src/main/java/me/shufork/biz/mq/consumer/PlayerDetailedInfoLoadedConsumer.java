package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.PlayerTracker;
import me.shufork.common.dto.supercell.coc.PlayerBasicInfoDto;
import me.shufork.common.dto.supercell.coc.PlayerDetailedInfoDto;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.player.PlayerDetailedInfoPayload;
import me.shufork.common.mq.sink.PlayerDetailedInfoLoadedSink;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerDetailedInfoLoadedConsumer implements MessageConsumer<PlayerDetailedInfoPayload> {

    @Autowired
    private PlayerTracker playerTracker;

    @StreamListener(PlayerDetailedInfoLoadedSink.INPUT)
    @Override
    public void handleMessage(PlayerDetailedInfoPayload message) {
        final PlayerDetailedInfoDto player = message.getPlayerDetailedInfo();
        log.trace("handing player detailed info:tag = {},name = {}",player.getTag(),player.getName());
        playerTracker.addOrUpdatePlayer(player);
    }
}
