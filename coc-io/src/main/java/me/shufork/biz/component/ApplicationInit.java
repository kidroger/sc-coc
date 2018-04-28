package me.shufork.biz.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class ApplicationInit implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private CocServerStateChecker cocServerStateChecker;
    private final AtomicBoolean init = new AtomicBoolean(false);
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(init.compareAndSet(false,true)){
            cocServerStateChecker.start();
        }
    }
}
