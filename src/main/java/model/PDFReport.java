package model;

public class PDFReport extends Report {
    private String vehicleNumber;
    private double totalExpense;

    public PDFReport(String reportId, String generatedDate, String vehicleNumber, double totalExpense) {
        super(reportId, generatedDate);
        this.vehicleNumber = vehicleNumber;
        this.totalExpense = totalExpense;
    }

    @Override
    public String export() {
        return "PDF_REPORT," + getReportId() + "," + getGeneratedDate() + "," + vehicleNumber + ",Rs." + totalExpense;
    }
}
