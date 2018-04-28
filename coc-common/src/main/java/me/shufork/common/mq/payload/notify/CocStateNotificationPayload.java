package me.shufork.common.mq.payload.notify;

import lombok.Data;
import me.shufork.common.enums.CocStateEnums;

@Data
public class CocStateNotificationPayload {
    long time;
    CocStateEnums prevState;
    CocStateEnums state;
    String message;
}
