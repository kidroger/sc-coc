package me.shufork.biz.service.impl;

import com.ibasco.agql.protocols.supercell.coc.webapi.interfaces.CocLeagues;
import com.ibasco.agql.protocols.supercell.coc.webapi.pojos.CocLeague;
import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.LeaguePublisher;
import me.shufork.common.dto.supercell.coc.LeagueDto;
import me.shufork.common.mq.payload.league.LeagueListPayload;
import me.shufork.common.mq.source.LeagueLoadedSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LeaguePublisherImpl implements LeaguePublisher {


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private LeagueLoadedSource leagueLoadedSource;

    @Autowired
    private CocLeagues leaguesApi;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void loadLeagues() {
        leaguesApi.getLeagueList()
                .thenAcceptAsync( o -> publishLeagueList(o))
                .exceptionally(e -> {log.warn(e.getMessage(),e);return null;});
    }
    private void publishLeagueList(List<CocLeague> data){
        if(data == null ){
            log.error("bad data (except CocLeague list ) :{}",data);
            return;
        }
        List<LeagueDto> leagueDtoList = data.stream().map(o ->modelMapper.map(o,LeagueDto.class)).collect(Collectors.toList());
        LeagueListPayload payload = new LeagueListPayload();
        payload.setLeagueList(leagueDtoList);
        Message<LeagueListPayload> message = MessageBuilder.withPayload(payload).build();

        leagueLoadedSource.output().send(message);
    }
}
