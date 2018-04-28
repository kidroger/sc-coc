package me.shufork.common.mq.payload.notify;

import lombok.Data;
import me.shufork.common.enums.CocStateEnums;

@Data
public class CocStateNotificationPayload {
    private long time;
    private CocStateEnums prevState;
    private CocStateEnums state;
    private String message;
}
