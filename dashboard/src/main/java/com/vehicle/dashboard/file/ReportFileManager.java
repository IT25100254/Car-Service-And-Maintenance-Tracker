package com.vehicle.dashboard.file;

import java.io.*;

public class ReportFileManager {

    private static final String FILE_NAME = "reports.txt";

    // CREATE
    public static void saveReport(String content) {

        try {

            FileWriter writer =
                    new FileWriter(FILE_NAME, true);

            writer.write(content + "\n");

            writer.close();

            System.out.println("Report Saved");

        } catch (IOException e) {

            System.out.println("Error Saving Report");
        }
    }

    // READ
    public static void readReports() {

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(FILE_NAME)
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                System.out.println(line);
            }

            reader.close();

        } catch (IOException e) {

            System.out.println("Error Reading Reports");
        }
    }

    // DELETE
    public static void clearReports() {

        try {

            FileWriter writer =
                    new FileWriter(FILE_NAME);

            writer.write("");

            writer.close();

            System.out.println("Reports Cleared");

        } catch (IOException e) {

            System.out.println("Error Clearing Reports");
        }
    }
}