package me.shufork.biz.component;

import com.ibasco.agql.core.exceptions.ServiceUnavailableException;
import com.ibasco.agql.protocols.supercell.coc.webapi.interfaces.CocLeagues;
import lombok.extern.slf4j.Slf4j;
import me.shufork.common.enums.CocStateEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
public class CocServerStateChecker {
    private static final long BASE_CHECK_INTERVAL = 10*1000l;
    private Timer checkTimer = new Timer(true);
    private AtomicBoolean running = new AtomicBoolean(false);
    private final AtomicLong nextCheck = new AtomicLong(BASE_CHECK_INTERVAL);

    @Autowired
    private CocLeagues leaguesApi;
    @Autowired
    private CocServerStateReporter cocServerStateReporter;

    public void start(){
        if(running.compareAndSet(false,true)){
            scheduleCheck();
        }
    }

    private void scheduleCheck(){
        checkTimer.schedule(new CheckTask(),nextCheck.get());
    }

    class CheckTask extends TimerTask{
        @Override
        public void run() {
            leaguesApi.getLeagueList(1).thenAccept(o->{
                log.debug("coc server state checked:{}",CocStateEnums.ONLINE.toString());
                nextCheck.set(BASE_CHECK_INTERVAL*6);
                cocServerStateReporter.reportState(CocStateEnums.ONLINE,"via league list");
            }).exceptionally(e->{
                if(e instanceof ServiceUnavailableException){
                    log.warn("coc server state checked:{}",CocStateEnums.OFFLINE.toString());
                    nextCheck.set(BASE_CHECK_INTERVAL);
                    cocServerStateReporter.reportState(CocStateEnums.OFFLINE,e.getMessage());
                }
                return null;
            }).join();
            scheduleCheck();
        }
    }
}
