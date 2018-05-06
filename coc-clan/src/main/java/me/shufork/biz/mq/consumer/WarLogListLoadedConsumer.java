package me.shufork.biz.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.mq.publisher.ClanFoundPublisher;
import me.shufork.biz.service.ClanService;
import me.shufork.biz.service.WarLogService;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.dto.supercell.coc.WarLogEntryDto;
import me.shufork.common.mq.consumer.MessageConsumer;
import me.shufork.common.mq.payload.warlog.WarLogListPayload;
import me.shufork.common.mq.sink.WarLogLoadedSink;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class WarLogListLoadedConsumer implements MessageConsumer<WarLogListPayload> {

    @Autowired
    private ClanService clanService;
    @Autowired
    private WarLogService warLogService;
    @Autowired
    private ClanFoundPublisher clanFoundPublisher;
    @Autowired
    private ModelMapper modelMapper;

    @StreamListener(WarLogLoadedSink.INPUT)
    @Override
    public void handleMessage(WarLogListPayload message) {
        final List<WarLogEntryDto> warLogList = message.getWarLogList();
        log.trace("handing war log list,size = {}",warLogList.size());
        List<ClanBasicInfoDto> clans =  warLogList.stream()
                .map( o -> modelMapper.map(o.getOpponent(),ClanBasicInfoDto.class) )
                .collect(Collectors.toList());
        //List<PlayerBasicInfoDto> players;
        clanFoundPublisher.publishClanFound(clans);
        clanService.insertOrUpdate(clans);
        warLogService.updateWarLog(warLogList);
    }
}
