package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.ClanDetailService;
import me.shufork.biz.service.LeagueService;
import me.shufork.biz.service.PlayerDetailService;
import me.shufork.biz.service.WarLogService;
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
    private PlayerDetailService playerDetailService;
    @Autowired
    private LeagueService leagueService;
    @Autowired
    private ClanDetailService clanDetailService;
    @Autowired
    private WarLogService warLogService;

    @Override
    @StreamListener(CocIoTaskCreatedSink.INPUT)
    public void handleMessage(CocIoTaskPayload message) {
        final CocIoTaskEnums goal = message.getGoal();
        log.trace("handling coc io task,goal : {},target {}",goal.toString(),message.getResourceId());
        switch (goal) {
            case LEAGUES:
                leagueService.loadLeagues();
                break;
            case PLAYER_DETAIL:
                playerDetailService.loadPlayerDetailedInfo(message.getResourceId());
                break;
            case CLAN_DETAIL:
                clanDetailService.loadClanDetailedInfo(message.getResourceId());
                break;
            case WAR_LOGS:
                warLogService.loadWarLogs(message.getResourceId(), Optional.ofNullable(message.getPaging()));
                break;
            default:
                log.warn("unknown IO task type:{}", goal.toString());
        }
    }
}
