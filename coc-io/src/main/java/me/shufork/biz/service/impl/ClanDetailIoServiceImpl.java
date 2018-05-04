package me.shufork.biz.service.impl;

import com.ibasco.agql.protocols.supercell.coc.webapi.interfaces.CocClans;
import com.ibasco.agql.protocols.supercell.coc.webapi.pojos.CocClanDetailedInfo;
import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.ClanDetailIoService;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
import me.shufork.common.mq.payload.clan.ClanDetailedInfoPayload;
import me.shufork.common.mq.source.ClanDetailedInfoLoadedSource;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static me.shufork.common.utils.CocHashTagUtil.ensurePrefix;

@Service
@Slf4j
public class ClanDetailIoServiceImpl implements ClanDetailIoService {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ClanDetailedInfoLoadedSource clanDetailedInfoLoadedSource;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CocClans clanApi;

    @Override
    public void loadClanDetailedInfo(String clanTag) {
        clanApi.getClanInfo(ensurePrefix(clanTag))
                .thenAcceptAsync( o -> publishClanDetailedInfo(o))
                .exceptionally(e -> {
                    log.warn(e.getMessage(),e);
                    return null;
                });
    }
    private void publishClanDetailedInfo(CocClanDetailedInfo data){
        if(data == null || StringUtils.isBlank(data.getTag()) ){
            log.error("bad data  (except CocClanDetailedInfo ):{}",data);
            return;
        }
        ClanDetailedInfoPayload payload = new ClanDetailedInfoPayload();
        payload.setClanDetailedInfo(modelMapper.map(data, ClanDetailedInfoDto.class));
        Message<ClanDetailedInfoPayload> message = MessageBuilder.withPayload(payload).build();

        clanDetailedInfoLoadedSource.output().send(message);
    }
}
