package model;

public abstract class Report {
    private String reportId;
    private String generatedDate;

    public Report(String reportId, String generatedDate) {
        this.reportId = reportId;
        this.generatedDate = generatedDate;
    }

    public String getReportId() { 
        return reportId; 
    }
    
    public String getGeneratedDate() { 
        return generatedDate; 
    }

    public abstract String export();
}
