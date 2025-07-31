package com.arbostar.automation.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LeftSideNavMenuNestedItems implements AbstractTabs {

    CLIENTS_LIST(0, "Clients List"),
    FOLLOW_UP(1, "Follow Up"),
    PROGRAMMED_MESSAGES(2, "Programmed Messages"),
    EMAIL_TEMPLATES(3, "Email Templates"),
    NEWSLETTERS(4, "Newsletters"),
    SMS_TEMPLATES(5, "SMS Templates"),
    BULK_COPY(6, "Bulk Copy");

    public final int index;
    public final String value;
}