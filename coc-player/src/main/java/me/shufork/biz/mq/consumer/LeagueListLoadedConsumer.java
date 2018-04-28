package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.LeagueService;
import me.shufork.common.dto.supercell.coc.LeagueDto;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.league.LeagueListPayload;
import me.shufork.common.mq.sink.LeagueLoadedSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class LeagueListLoadedConsumer implements MessageConsumer<LeagueListPayload> {
    @Autowired
    private LeagueService leagueService;

    @StreamListener(LeagueLoadedSink.INPUT)
    @Override
    public void handleMessage(LeagueListPayload message) {
        final List<LeagueDto> leagueDtoList = message.getLeagueList();
        log.trace("handing league list:{}",leagueDtoList.size());
        leagueService.createOrUpdate(leagueDtoList);
    }
}
