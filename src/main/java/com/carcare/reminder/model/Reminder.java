package com.carcare.reminder.model;

public class Reminder {

    private int reminderId;
    private String title;
    private String dueDate;
    private String status;

    // Default Constructor
    public Reminder() {
    }

    // Constructor
    public Reminder(int reminderId, String title,
                    String dueDate, String status) {

        this.reminderId = reminderId;
        this.title = title;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getter and Setter Methods

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentStatus() {

        try {

            java.time.LocalDate due =
                    java.time.LocalDate.parse(dueDate);

            java.time.LocalDate today =
                    java.time.LocalDate.now();

            if (due.isBefore(today)
                    && !status.equalsIgnoreCase("Completed")) {

                return "Overdue";
            }

        } catch (Exception e) {

            return status;
        }

        return status;

    }
}
