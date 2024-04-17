package com.techbank.account.common.events;

import com.techbank.cqrs.core.events.BaseEvent;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor //to de-serialize you need this
public class CloseAllAccountsEvent extends BaseEvent {
}
