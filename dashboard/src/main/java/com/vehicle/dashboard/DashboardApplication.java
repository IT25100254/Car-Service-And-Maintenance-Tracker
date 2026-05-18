package com.vehicle.dashboard;

import com.vehicle.dashboard.file.ReportFileManager;
import com.vehicle.dashboard.report.CSVReport;
import com.vehicle.dashboard.report.PDFReport;
import com.vehicle.dashboard.report.report;
import com.vehicle.dashboard.report.report;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DashboardApplication {

	public static void main(String[] args) {

		SpringApplication.run(
				DashboardApplication.class,
				args
		);

		report pdf =
				new PDFReport("Vehicle Service Report");

		report csv =
				new CSVReport("Fuel Economy Report");

		pdf.export();

		csv.export();

		System.out.println("\nSaved Reports:");

		ReportFileManager.readReports();
	}
}