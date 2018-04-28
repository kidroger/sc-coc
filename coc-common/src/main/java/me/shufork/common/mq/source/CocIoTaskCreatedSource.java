package me.shufork.common.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CocIoTaskCreatedSource {
    String OUTPUT = "coc_io_task_created_output";

    @Output(OUTPUT)
    MessageChannel output();
}
