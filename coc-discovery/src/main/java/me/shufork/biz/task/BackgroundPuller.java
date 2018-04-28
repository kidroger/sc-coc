package me.shufork.biz.task;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.domain.ClanTracking;
import me.shufork.biz.service.ClanTracker;
import me.shufork.common.enums.CocIoTaskEnums;
import me.shufork.common.mq.payload.task.CocIoTaskPayload;
import me.shufork.common.mq.source.CocIoTaskCreatedSource;
import me.shufork.common.utils.CocHashTagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BackgroundPuller {

    /*@Autowired
    private ClanTracker clanTracker;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CocIoTaskCreatedSource cocIoTaskCreatedSource;

    private void pullClan(String tag){
        CocIoTaskPayload payload = new CocIoTaskPayload();
        payload.setGoal(CocIoTaskEnums.CLAN_DETAIL);
        payload.setResourceId(CocHashTagUtil.ensurePrefix(tag));
        cocIoTaskCreatedSource.output().send(MessageBuilder.withPayload(payload).build());
    }
    @Scheduled(fixedDelayString="${app.task.auto-pull.clan.rate:720000}", initialDelayString="${app.task.auto-pull.clan.init-delay:300000}")
    public void pull(){
        ClanTracking seed = clanTracker.retrieveOneForAutoPull();
        if(seed != null){
            log.debug("pull clan info,tag = {},name = {}",seed.getClan(),seed.getName());
            pullClan(seed.getClan());
        }
    }*/
}
