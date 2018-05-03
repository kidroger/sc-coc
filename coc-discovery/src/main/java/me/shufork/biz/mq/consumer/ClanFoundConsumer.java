package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.ClanTracking;
import me.shufork.biz.service.ClanTracker;
import me.shufork.biz.utils.TrackerCache;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.discovery.ClanFoundPayload;
import me.shufork.common.mq.sink.ClanFoundSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClanFoundConsumer implements MessageConsumer<ClanFoundPayload> {

    @Autowired
    private ClanTracker clanTracker;

    @Autowired(required = false)
    @Qualifier("clanTrackerCache")
    private TrackerCache<ClanTracking.ClanTracker> clanTrackerCache;

    @StreamListener(ClanFoundSink.INPUT)
    @Override
    public void handleMessage(ClanFoundPayload message) {
        final ClanBasicInfoDto clan = message.getClan();
        log.trace("handling for clan found,tag = {},name = {}",clan.getTag(),clan.getName());
        clanTracker.addClan(clan);
        if(clanTrackerCache!=null){
            clanTrackerCache.add(clan.getTag(),new TrackerCache.SimpleClanTracker(clan.getTag(),clan.getName()));
        }
    }
}
