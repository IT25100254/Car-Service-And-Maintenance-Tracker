package com.carcare.reminder.model;

public class Notification {

        private int notificationId;
        private String message;
        private String date;

        // Default Constructor
        public Notification() {
        }

        // Constructor
        public Notification(int notificationId,
                            String message,
                            String date) {

            this.notificationId = notificationId;
            this.message = message;
            this.date = date;
        }

        // Getter and Setter Methods

        public int getNotificationId() {
            return notificationId;
        }

        public void setNotificationId(int notificationId) {
            this.notificationId = notificationId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

