package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.ClanDetailIoService;
import me.shufork.biz.service.LeagueIoService;
import me.shufork.biz.service.PlayerDetailIoService;
import me.shufork.biz.service.WarLogIoService;
import me.shufork.common.enums.CocIoTaskEnums;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.task.CocIoTaskPayload;
import me.shufork.common.mq.sink.CocIoTaskCreatedSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class CocIoTaskConsumer implements MessageConsumer<CocIoTaskPayload> {

    @Autowired
    private PlayerDetailIoService playerDetailIoService;
    @Autowired
    private LeagueIoService leagueIoService;
    @Autowired
    private ClanDetailIoService clanDetailIoService;
    @Autowired
    private WarLogIoService warLogIoService;

    @Override
    @StreamListener(CocIoTaskCreatedSink.INPUT)
    public void handleMessage(CocIoTaskPayload message) {
        final CocIoTaskEnums goal = message.getGoal();
        log.trace("handling coc io task,goal : {},target {}",goal.toString(),message.getResourceId());
        switch (goal) {
            case LEAGUES:
                leagueIoService.loadLeagues();
                break;
            case PLAYER_DETAIL:
                playerDetailIoService.loadPlayerDetailedInfo(message.getResourceId());
                break;
            case CLAN_DETAIL:
                clanDetailIoService.loadClanDetailedInfo(message.getResourceId());
                break;
            case WAR_LOGS:
                warLogIoService.loadWarLogs(message.getResourceId(), Optional.ofNullable(message.getPaging()));
                break;
            default:
                log.warn("unknown IO task type:{}", goal.toString());
        }
    }
}
