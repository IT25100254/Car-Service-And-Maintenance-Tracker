package com.carcare.reminder.service;

import com.carcare.reminder.model.Notification;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

    @Service
    public class NotificationService {

        private final String FILE_PATH = "notifications.txt";

        // SAVE NOTIFICATION
        public void saveNotification(Notification notification) {

            try {

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(FILE_PATH, true)
                );

                writer.write(
                        notification.getNotificationId() + "," +
                                notification.getMessage() + "," +
                                notification.getDate()
                );

                writer.newLine();

                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // GET ALL NOTIFICATIONS
        public List<Notification> getAllNotifications() {

            List<Notification> notifications =
                    new ArrayList<>();

            try {

                BufferedReader reader = new BufferedReader(
                        new FileReader(FILE_PATH)
                );

                String line;

                while ((line = reader.readLine()) != null) {

                    String[] data = line.split(",");

                    Notification notification =
                            new Notification(
                                    Integer.parseInt(data[0]),
                                    data[1],
                                    data[2]
                            );

                    notifications.add(notification);
                }

                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return notifications;
        }
    }

