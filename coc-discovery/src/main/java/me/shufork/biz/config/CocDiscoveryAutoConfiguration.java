package me.shufork.biz.config;

import me.shufork.biz.domain.ClanTracking;
import me.shufork.biz.service.ClanTracker;
import me.shufork.common.enums.CocIoTaskEnums;
import me.shufork.common.enums.CocStateEnums;
import me.shufork.common.mq.payload.notify.CocStateNotificationPayload;
import me.shufork.common.mq.payload.task.CocIoTaskPayload;
import me.shufork.common.mq.sink.CocStateNotificationSink;
import me.shufork.common.mq.source.CocIoTaskCreatedSource;
import me.shufork.common.util.DateTimeUtil;
import me.shufork.common.utils.CocHashTagUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
@EnableConfigurationProperties(CocDiscoveryProperties.class)
public class CocDiscoveryAutoConfiguration {

    @Bean("clanFetchErrorDetector")
    public FetchErrorDetector fetchErrorDetector(){
        return new FetchErrorDetector();
    }


    public static class FetchErrorDetector{
        private final AtomicLong request = new AtomicLong(0L);
        private final AtomicLong response = new AtomicLong(0L);

        public long requested(){return request.getAndIncrement();}
        public long responded(){return response.getAndIncrement();}
        public long totalRequested(){return request.get();}
        public long totalResponded(){return response.get();}

        public long lost(){return request.get() - response.get();}
    }

    @Component
    @ConditionalOnProperty(prefix = "shufork.sc.coc.discovery", name = "enableMetrics", matchIfMissing = true)
    public static class FetchMetric implements PublicMetrics {
        @Autowired
        @Qualifier("clanFetchErrorDetector")
        private FetchErrorDetector fetchErrorDetector;

        @Override
        public Collection<Metric<?>> metrics() {
            Collection< Metric< ?> > result = new LinkedHashSet<>();
            result.add(new Metric<>("fetch-clan.total-requested", fetchErrorDetector.totalRequested()));
            result.add(new Metric<>("fetch-clan.total-responded", fetchErrorDetector.totalResponded()));
            result.add(new Metric<>("fetch-clan.lost-hint", fetchErrorDetector.lost()));
            return result;
        }
    }

    @Component
    static class ClanFetcher implements ApplicationListener<ApplicationReadyEvent>{

        private final static long IO_SERVICE_TTL = 1000L*60;
        private final Logger log = LoggerFactory.getLogger(ClanFetcher.class);
        private final AtomicBoolean started = new AtomicBoolean(false);
        private final Timer  clanFetchTimer = new Timer(true);
        private final AtomicLong lastIoServiceAck = new AtomicLong(DateTimeUtil.currentTimestamp());
        private final List<ClanTracking.ClanTracker> fetchList = new LinkedList<>();
        @Autowired
        private  CocDiscoveryProperties cocDiscoveryProperties;
        @Autowired
        private  ClanTracker clanTracker;
        @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
        @Autowired
        private  CocIoTaskCreatedSource cocIoTaskCreatedSource;
        @Autowired
        @Qualifier("clanFetchErrorDetector")
        private  FetchErrorDetector fetchErrorDetector;

        private final AtomicBoolean skipFetch = new AtomicBoolean(true);

        @StreamListener(CocStateNotificationSink.INPUT)
        public void handleMessage(CocStateNotificationPayload message) {
            lastIoServiceAck.set(DateTimeUtil.currentTimestamp());
            final CocStateEnums prevState = message.getPrevState();
            final CocStateEnums currentState = message.getState();
            log.debug("coc io service state:{} -> {}",prevState,currentState );
            if(currentState.equals(CocStateEnums.ONLINE)){
                skipFetch.set(false);
            }else{
                skipFetch.set(true);
            }
        }
        @Override
        public void onApplicationEvent(ApplicationReadyEvent event) {
            if(!cocDiscoveryProperties.isEnabled()){
                log.warn("discovery disabled");
                return;
            }
            if(!started.get()){
                started.set(true);
                clanFetchTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        pullClan();
                    }
                },cocDiscoveryProperties.getClanFetch().getInitDelay(),cocDiscoveryProperties.getClanFetch().getRate());
            }
        }

        private void pullClan(){
            if(skipFetch.get() || (DateTimeUtil.currentTimestamp() - lastIoServiceAck.get() > IO_SERVICE_TTL) ){
                log.warn("pause clan fetch, reason :coc io service not ready");
                return;
            }
            int size = Math.max(1,cocDiscoveryProperties.getClanFetch().getSize());
            ensureFetchCache();
            fetchSome(size);
        }
        private int ensureFetchCache(){
            final int fetchSize = cocDiscoveryProperties.getClanFetch().getSize();
            final int fetchRatePerSec = fetchSize / Math.max(1,(int)cocDiscoveryProperties.getClanFetch().getRate()/1000) ;
            if(fetchList.size() >= fetchSize){
                return 0;
            }

            List<ClanTracking.ClanTracker> seeds = clanTracker.retrieveSomeForAutoPull(fetchRatePerSec * 60 );
            fetchList.addAll(seeds);
            log.debug("update clan fetch list,size = ",fetchList.size());
            return seeds.size();
        }
        private void fetchSome(int size){
            List<ClanTracking.ClanTracker> seeds = new LinkedList<>();
            for(int i=0;i<size && !fetchList.isEmpty() ;++i){
                seeds.add(fetchList.remove(0));
            }
            if(seeds.isEmpty()){
                log.warn("no clan to fetch");
            }
            Optional.ofNullable(seeds).ifPresent( o->{
                o.forEach(seed->{
                    log.debug("fetch clan info,tag = {},name = {}",seed.getClan(),seed.getName());
                    CocIoTaskPayload payload = new CocIoTaskPayload();
                    payload.setGoal(CocIoTaskEnums.CLAN_DETAIL);
                    payload.setResourceId(CocHashTagUtil.ensurePrefix(seed.getClan()));
                    cocIoTaskCreatedSource.output().send(MessageBuilder.withPayload(payload).build());
                    fetchErrorDetector.requested();
                });
            } );
        }
    }
}
