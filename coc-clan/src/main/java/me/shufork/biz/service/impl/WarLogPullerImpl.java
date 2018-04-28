package me.shufork.biz.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.shufork.biz.service.WarLogPuller;
import me.shufork.common.dto.supercell.coc.ClanDetailedInfoDto;
import me.shufork.common.enums.CocIoTaskEnums;
import me.shufork.common.mq.payload.task.CocIoTaskPayload;
import me.shufork.common.mq.source.CocIoTaskCreatedSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WarLogPullerImpl implements WarLogPuller {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CocIoTaskCreatedSource cocIoTaskCreatedSource;

    @Value("${coc.war-log.auto-load:false}")
    private boolean autoLoadWarLog;

    private boolean skipLoadWarLogs(ClanDetailedInfoDto source){
        return !autoLoadWarLog ||
                !source.isWarLogPublic()  ||
                (source.getWarTies() + source.getWarWins() + source.getWarLosses() < 1) ;
    }
    @Override
    public void checkAndPullWarLog(ClanDetailedInfoDto source) {

        // TODO: maybe in background ?

        if(skipLoadWarLogs(source)){
            return;
        }
        log.debug("load war logs tag = {},name = {}",source.getTag(),source.getName());
        CocIoTaskPayload payload = new CocIoTaskPayload();
        payload.setGoal(CocIoTaskEnums.WAR_LOGS);
        payload.setResourceId(source.getTag());
        payload.setPaging(null);

        cocIoTaskCreatedSource.output().send(MessageBuilder.withPayload(payload).build());
    }

    @Override
    public void checkAndPullWarLog(Iterable<ClanDetailedInfoDto> sources) {
        sources.forEach(o-> checkAndPullWarLog(o));
    }

}
