package com.arbostar.automation.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MainLeftNavBarItems implements AbstractTabs {

    DASHBOARD(0, "Dashboard"),
    CLIENTS(1, "Clients"),
    LEADS(2, "Leads"),
    TASKS(3, "Tasks"),
    ESTIMATES(4, "Estimates"),
    WORKORDERS(5, "Workorders"),
    SCHEDULE(6, "Schedule"),
    INVOICES(7, "Invoices"),
    EQUIPMENT(8, "Equipment"),
    PERSONNEL(9, "Personnel"),
    ACCOUNTING(10, "Accounting"),
    BUSINESS_INTELLIGENCE(11, "Business Intelligence"),
    IMPORTANT_CONTACTS(12, "Important Contacts");

    public final int index;
    public final String value;
}