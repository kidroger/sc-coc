package me.shufork.biz.service.impl;

import com.ibasco.agql.core.exceptions.AccessDeniedException;
import com.ibasco.agql.protocols.supercell.coc.webapi.interfaces.CocClans;
import com.ibasco.agql.protocols.supercell.coc.webapi.pojos.CocWarLogEntry;
import com.ibasco.agql.protocols.supercell.coc.webapi.pojos.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.WarLogService;
import me.shufork.common.dto.supercell.coc.WarLogEntryDto;
import me.shufork.common.mq.payload.Page;
import me.shufork.common.mq.payload.warlog.WarLogListPayload;
import me.shufork.common.mq.payload.warlog.WarLogRejectedPayload;
import me.shufork.common.mq.source.WarLogLoadedSource;
import me.shufork.common.mq.source.WarLogRejectedSource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static me.shufork.common.utils.CocHashTagUtil.ensurePrefix;

@Service
@Slf4j
public class WarLogServiceImpl implements WarLogService {


    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private WarLogLoadedSource warLogLoadedSource;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private WarLogRejectedSource warLogRejectedSource;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CocClans clanApi;

    @Override
    public void loadWarLogs(String clanTag, Optional<Page> paging) {
        Optional<Integer> limit = Optional.empty();
        Optional<String> before = Optional.empty();
        Optional<String> after = Optional.empty();
        if(paging.isPresent()){
            Page pagingOpt = paging.get();
            limit = Optional.ofNullable(pagingOpt.getLimit());
            if(paging.get().isAfter()){
                after = Optional.ofNullable(pagingOpt.getCursor());
            }else{
                before = Optional.ofNullable(pagingOpt.getCursor());
            }
        }
        clanApi.getClanWarLog(ensurePrefix(clanTag),limit,before,after)
                .thenAcceptAsync( o -> publishWarLogList(o))
                .exceptionally(e -> {
                    log.warn(e.getMessage(),e);
                    if(e instanceof AccessDeniedException){
                        // war logs is not public ATM.
                        publishWarLogRejected(clanTag);
                    }
                    return null;
                });
    }


    private void publishWarLogList(PageResult<List<CocWarLogEntry>> data){
        if(data == null ){
            log.error("bad data  (except CocWarLogEntry list ):{}",data);
            return;
        }
        List<WarLogEntryDto> warLogEntryDtoList = data.getItems().stream().map(o-> modelMapper.map(o,WarLogEntryDto.class)).collect(Collectors.toList());
        WarLogListPayload payload = new WarLogListPayload();
        payload.setWarLogList(warLogEntryDtoList);
        payload.setPageMeta(data.getPageMeta());
        Message<WarLogListPayload> message = MessageBuilder.withPayload(payload).build();

        warLogLoadedSource.output().send(message);
    }
    private void publishWarLogRejected(String clanTag){
        WarLogRejectedPayload payload = new WarLogRejectedPayload();
        payload.setClanTag(clanTag);
        Message<WarLogRejectedPayload> message = MessageBuilder.withPayload(payload).build();

        warLogRejectedSource.output().send(message);
    }
}
