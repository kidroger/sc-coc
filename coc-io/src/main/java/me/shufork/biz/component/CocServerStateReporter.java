package me.shufork.biz.component;

import com.ibasco.agql.protocols.supercell.coc.webapi.interfaces.CocClans;
import lombok.extern.slf4j.Slf4j;
import me.shufork.common.enums.CocStateEnums;
import me.shufork.common.mq.payload.notify.CocStateNotificationPayload;
import me.shufork.common.mq.source.CocStateNotificationSource;
import me.shufork.common.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * TODO: maybe interceptor impl is need ?
 */
@Component
@Slf4j
public class CocServerStateReporter {
    @Autowired
    private CocClans clanApi;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private CocStateNotificationSource cocStateNotificationSource;

    private final static long REPORT_INTERVAL_MIN = 1000 * 30L;
    private final AtomicReference<CocStateEnums> lastState = new AtomicReference<>(CocStateEnums.UNKNOWN);
    private final AtomicReference<CocStateEnums> prevState = new AtomicReference<>(CocStateEnums.UNKNOWN);
    private final AtomicLong lastReport = new AtomicLong(DateTimeUtil.CurrentTimestamp());

    public void reportState(CocStateEnums state,String message){
        final long now = DateTimeUtil.CurrentTimestamp();
        if(!lastState.get().equals(state) || (now - lastReport.get() > REPORT_INTERVAL_MIN)){
            prevState.set(lastState.get());
            lastState.set(state);
            lastReport.set(now);
            publish(message);
        }
    }
    private void publish(String message){
        CocStateNotificationPayload payload = new CocStateNotificationPayload();
        payload.setTime(DateTimeUtil.CurrentTimestamp());
        payload.setPrevState(prevState.get());
        payload.setState(lastState.get());
        payload.setMessage(message);
        cocStateNotificationSource.output().send(MessageBuilder.withPayload(payload).build());
    }
}
