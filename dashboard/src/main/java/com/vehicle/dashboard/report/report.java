package com.vehicle.dashboard.report;

public abstract class report {
    protected String reportName;

    public report(String reportName) {
        this.reportName = reportName;
    }

    public abstract void export();
}

