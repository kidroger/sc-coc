package me.shufork.biz.mq.publisher;

import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.mq.payload.discovery.ClanFoundPayload;
import me.shufork.common.mq.source.ClanFoundSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class ClanFoundPublisher {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ClanFoundSource clanFoundSource;
    public void publishClanFound(ClanBasicInfoDto clan){
        ClanFoundPayload payload = new ClanFoundPayload();
        payload.setClan(clan);
        Message<ClanFoundPayload> message = MessageBuilder.withPayload(payload).build();
        clanFoundSource.output().send(message);
    }

    public void publishClanFound(Iterable<ClanBasicInfoDto> clans){
        clans.forEach(o->publishClanFound(o));
    }
}
