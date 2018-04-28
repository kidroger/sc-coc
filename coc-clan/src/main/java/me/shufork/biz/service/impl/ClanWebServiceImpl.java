package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.CocClan;
import me.shufork.biz.domain.CocClanDetails;
import me.shufork.biz.repository.CocClanDetailsRepository;
import me.shufork.biz.repository.CocClanRepository;
import me.shufork.biz.service.ClanWebService;
import me.shufork.common.dto.supercell.coc.ClanBasicInfoDto;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
import me.shufork.common.enums.CocIoTaskEnums;
import me.shufork.common.exceptions.RecordNotFoundException;
import me.shufork.common.mq.payload.task.CocIoTaskPayload;
import me.shufork.common.mq.source.CocIoTaskCreatedSource;
import me.shufork.common.utils.CocHashTagUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service
@Slf4j
public class ClanWebServiceImpl implements ClanWebService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CocClanRepository cocClanRepository;
    @Autowired
    private CocClanDetailsRepository cocClanDetailsRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CocIoTaskCreatedSource cocIoTaskCreatedSource;

    @Override
    public ClanBasicInfoDto getClanBasicInfo(String tag) {
        CocClan data = cocClanRepository.findOne(CocHashTagUtil.ensurePrefix(tag));
        if(data == null){
            throw new RecordNotFoundException("coc.clan",tag);
        }
        return modelMapper.map(data,ClanBasicInfoDto.class);
    }

    @Override
    public ClanDetailedInfoDto getClanDetailedInfo(String tag) {
        ClanBasicInfoDto basicInfoDto =  getClanBasicInfo(tag);
        CocClanDetails data = cocClanDetailsRepository.findOne(CocHashTagUtil.ensurePrefix(tag));
        if(data == null){
            throw new RecordNotFoundException("coc.clan",tag);
        }
        ClanDetailedInfoDto target = modelMapper.map(data,ClanDetailedInfoDto.class);
        target.setName(basicInfoDto.getName());
        target.setClanLevel(basicInfoDto.getClanLevel());
        target.setBadgeUrls(basicInfoDto.getBadgeUrls());
        return target;
    }

    @Override
    public void pullClanDetailedInfo(String tag) {
        log.debug("try load clan detail,tag = {}",tag);
        CocIoTaskPayload payload = new CocIoTaskPayload();
        payload.setGoal(CocIoTaskEnums.CLAN_DETAIL);
        payload.setResourceId(tag);
        payload.setPaging(null);

        cocIoTaskCreatedSource.output().send(MessageBuilder.withPayload(payload).build());
    }
}
