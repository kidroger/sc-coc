package me.shufork.common.mq.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CocIoTaskCreatedSink {

    String INPUT = "coc_io_task_created_input";

    @Input(INPUT)
    SubscribableChannel input();
}
