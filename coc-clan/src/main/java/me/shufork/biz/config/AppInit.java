package me.shufork.biz.config;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.components.HotClans;
import me.shufork.biz.domain.CocClan;
import me.shufork.biz.domain.CocClanDetails;
import me.shufork.biz.repository.CocClanDetailsRepository;
import me.shufork.biz.repository.CocClanRepository;
import me.shufork.biz.vo.ClanInfoVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class AppInit implements ApplicationListener<ApplicationReadyEvent> {

    private final AtomicBoolean done = new AtomicBoolean(false);

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HotClans hotClans;
    @Autowired
    private CocClanRepository cocClanRepository;
    @Autowired
    private CocClanDetailsRepository cocClanDetailsRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(done.compareAndSet(false,true)){
            Page<CocClanDetails> data = cocClanDetailsRepository.findAllByOrderByModifiedTimeDesc(new PageRequest(0,100));
            List<ClanInfoVo> clanInfoVoList = data.map(o->modelMapper.map(o, ClanInfoVo.class)).getContent();
            clanInfoVoList.forEach(o->{
                CocClan basicInfo = cocClanRepository.findOne(o.getTag());
                if(basicInfo == null){
                    log.error("leak basic info ,clan tag = {}",o.getTag());
                }else{
                    o.setName(basicInfo.getName());
                    o.setClanLevel(basicInfo.getClanLevel());
                    o.setBadgeSmall(basicInfo.getBadgeSmall());
                    o.setBadgeMedium(basicInfo.getBadgeMedium());
                    o.setBadgeLarge(basicInfo.getBadgeLarge());
                }
            });
            hotClans.addAll(clanInfoVoList);
        }
    }
}
