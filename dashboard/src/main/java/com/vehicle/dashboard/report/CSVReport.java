package com.vehicle.dashboard.report;

import com.vehicle.dashboard.file.ReportFileManager;

public class CSVReport extends report {

    public CSVReport(String reportName) {
        super(reportName);
    }

    @Override
    public void export() {

        String content =
                "CSV REPORT GENERATED: " + reportName;

        ReportFileManager.saveReport(content);

        System.out.println("CSV Report Exported");
    }
}