package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.components.HotClans;
import me.shufork.biz.mq.publisher.PlayerFoundPublisher;
import me.shufork.biz.service.ClanDetailsService;
import me.shufork.biz.service.ClanService;
import me.shufork.biz.service.WarLogPuller;
import me.shufork.biz.vo.ClanInfoVo;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.clan.ClanDetailedInfoPayload;
import me.shufork.common.mq.sink.ClanDetailedInfoLoadedSink;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class ClanDetailedInfoLoadedConsumer implements MessageConsumer<ClanDetailedInfoPayload> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClanService clanService;
    @Autowired
    private ClanDetailsService clanDetailsService;
    @Autowired
    private WarLogPuller warLogPuller;
    @Autowired
    private PlayerFoundPublisher playerFoundPublisher;
    @Autowired
    private HotClans hotClans;

    @StreamListener(ClanDetailedInfoLoadedSink.INPUT)
    @Override
    public void handleMessage(ClanDetailedInfoPayload message) {
        final ClanDetailedInfoDto clan = message.getClanDetailedInfo();
        log.trace("handing clan detailed info:tag = {},name = {}",clan.getTag(),clan.getName());

        clanService.insertOrUpdate( modelMapper.map(clan,ClanBasicInfoDto.class));
        clanDetailsService.insertOrUpdate(clan);

        CompletableFuture.runAsync(()-> {
            warLogPuller.checkAndPullWarLog(clan);
            playerFoundPublisher.publishPlayerFound(clan.getClanMembers());
            hotClans.add(modelMapper.map(clan, ClanInfoVo.class));
        });
    }
}
