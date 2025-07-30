package com.arbostar.automation.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LeftSideNavMenuItems implements AbstractTabs {

    DASHBOARD(0, "Dashboard"),
    CLIENTS(1, "Clients"),
    OLD_STUMPS(2,"Old Stumps"),
    LEADS(3, "Leads"),
    TASKS(4, "Tasks"),
    ESTIMATES(5, "Estimates"),
    WORKORDERS(6, "Workorders"),
    SCHEDULE(7, "Schedule"),
    INVOICES(8, "Invoices"),
    EQUIPMENT(9, "Equipment"),
    PERSONNEL(10, "Personnel"),
    ACCOUNTING(11, "Accounting"),
    BUSINESS_INTELLIGENCE(12, "Business Intelligence"),
    IMPORTANT_CONTACTS(13, "Important Contacts");

    public final int index;
    public final String value;
}