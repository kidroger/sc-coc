package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.PlayerDetailsService;
import me.shufork.biz.service.PlayerService;
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
    private PlayerService playerService;
    @Autowired
    private PlayerDetailsService playerDetailsService;

    @Autowired
    private ModelMapper modelMapper;

    @StreamListener(PlayerDetailedInfoLoadedSink.INPUT)
    @Override
    public void handleMessage(PlayerDetailedInfoPayload message) {
        final PlayerDetailedInfoDto info = message.getPlayerDetailedInfo();
        log.trace("handing player detailed info:tag = {},name = {}",info.getTag(),info.getName());
        playerService.createOrUpdate(modelMapper.map(info, PlayerBasicInfoDto.class));
        playerDetailsService.createOrUpdate(info);
    }
}
