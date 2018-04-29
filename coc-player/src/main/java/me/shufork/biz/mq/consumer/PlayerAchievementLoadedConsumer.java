package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.PlayerAchievementService;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.player.PlayerAchievementLoadedPayload;
import me.shufork.common.mq.sink.PlayerAchievementLoadedSink;
import me.shufork.common.vo.PlayerAchievementsVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PlayerAchievementLoadedConsumer  implements MessageConsumer<PlayerAchievementLoadedPayload> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlayerAchievementService playerAchievementService;

    @StreamListener(PlayerAchievementLoadedSink.INPUT)
    @Override
    public void handleMessage(PlayerAchievementLoadedPayload message) {
        final String owner = message.getPlayerTag();
        log.trace("handling achievements for player({})",owner);
        if(message.getAchievements()!=null && !message.getAchievements().isEmpty() ){
            List<PlayerAchievementsVo> voList = message.getAchievements().stream().map(o ->{
                PlayerAchievementsVo vo = modelMapper.map(o,PlayerAchievementsVo.class);
                vo.setOwner(owner);
                return vo;
            }).collect(Collectors.toList());
            playerAchievementService.createOrUpdate(voList);
        }
    }
}
