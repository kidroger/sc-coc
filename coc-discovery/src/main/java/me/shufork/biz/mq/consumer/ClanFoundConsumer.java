package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.ClanTracker;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.discovery.ClanFoundPayload;
import me.shufork.common.mq.sink.ClanFoundSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClanFoundConsumer implements MessageConsumer<ClanFoundPayload> {

    @Autowired
    private ClanTracker clanTracker;

    @StreamListener(ClanFoundSink.INPUT)
    @Override
    public void handleMessage(ClanFoundPayload message) {
        final ClanBasicInfoDto clan = message.getClan();
        log.trace("handling for clan found,tag = {},name = {}",clan.getTag(),clan.getName());
        clanTracker.addClan(clan);
    }
}
