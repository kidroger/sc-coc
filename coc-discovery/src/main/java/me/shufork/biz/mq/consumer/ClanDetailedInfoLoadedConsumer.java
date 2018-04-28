package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.config.CocDiscoveryAutoConfiguration;
import me.shufork.biz.service.ClanTracker;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.clan.ClanDetailedInfoPayload;
import me.shufork.common.mq.sink.ClanDetailedInfoLoadedSink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClanDetailedInfoLoadedConsumer implements MessageConsumer<ClanDetailedInfoPayload> {

    @Autowired
    private ClanTracker clanTracker;

    @Autowired
    @Qualifier("clanFetchErrorDetector")
    CocDiscoveryAutoConfiguration.FetchErrorDetector fetchErrorDetector;
    @StreamListener(ClanDetailedInfoLoadedSink.INPUT)
    @Override
    public void handleMessage(ClanDetailedInfoPayload message) {
        fetchErrorDetector.responded();
        final ClanDetailedInfoDto clan = message.getClanDetailedInfo();
        log.trace("handing clan detailed info:tag = {},name = {}",clan.getTag(),clan.getName());
        clanTracker.addOrUpdateClan(clan);
    }
}
