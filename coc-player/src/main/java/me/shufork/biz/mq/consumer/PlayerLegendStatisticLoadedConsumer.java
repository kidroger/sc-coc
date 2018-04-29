package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.PlayerLegendStatisticService;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.player.PlayerLegendStatisticLoadedPayload;
import me.shufork.common.mq.sink.PlayerLegendStatisticLoadedSink;
import me.shufork.common.vo.PlayerLegendStatisticsVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PlayerLegendStatisticLoadedConsumer implements MessageConsumer<PlayerLegendStatisticLoadedPayload> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlayerLegendStatisticService playerLegendStatisticService;

    @StreamListener(PlayerLegendStatisticLoadedSink.INPUT)
    @Override
    public void handleMessage(PlayerLegendStatisticLoadedPayload message) {
        final String owner = message.getPlayerTag();
        log.trace("handling legend statistics for player({})",owner);
        if(message.getLegendStatistics()!=null){
            PlayerLegendStatisticsVo vo = modelMapper.map(message.getLegendStatistics(),PlayerLegendStatisticsVo.class);
            vo.setOwner(owner);
            playerLegendStatisticService.createOrUpdate(vo);
        }
    }
}
