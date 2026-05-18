package com.vehicle.dashboard.report;
import com.vehicle.dashboard.file.ReportFileManager;
public class PDFReport extends report {
    public PDFReport(String reportName) {
        super(reportName);
    }

    @Override
    public void export() {

        String content =
                "PDF REPORT GENERATED: " + reportName;

        ReportFileManager.saveReport(content);

        System.out.println("PDF Report Exported");
    }
}
