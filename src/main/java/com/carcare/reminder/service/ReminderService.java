package com.carcare.reminder.service;

import com.carcare.reminder.model.Reminder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

    @Service
    public class ReminderService {

        private final String FILE_PATH = "reminders.txt";

        // SAVE REMINDER
        public void saveReminder(Reminder reminder) {

            try {

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(FILE_PATH, true)
                );

                writer.write(
                        reminder.getReminderId() + "," +
                                reminder.getTitle() + "," +
                                reminder.getDueDate() + "," +
                                reminder.getStatus()
                );

                writer.newLine();

                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // READ ALL REMINDERS
        public List<Reminder> getAllReminders() {

            List<Reminder> reminders = new ArrayList<>();

            try {

                BufferedReader reader = new BufferedReader(
                        new FileReader(FILE_PATH)
                );

                String line;

                while ((line = reader.readLine()) != null) {

                    String[] data = line.split(",");

                    Reminder reminder = new Reminder(
                            Integer.parseInt(data[0]),
                            data[1],
                            data[2],
                            data[3]
                    );

                    reminders.add(reminder);
                }

                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return reminders;
        }
        // DELETE REMINDER
        public void deleteReminder(int reminderId) {

            List<Reminder> reminders =
                    getAllReminders();

            try {

                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(FILE_PATH)
                        );

                for (Reminder reminder : reminders) {

                    if (reminder.getReminderId()
                            != reminderId) {

                        writer.write(
                                reminder.getReminderId() + "," +
                                        reminder.getTitle() + "," +
                                        reminder.getDueDate() + "," +
                                        reminder.getStatus()
                        );

                        writer.newLine();
                    }
                }

                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // TOTAL REMINDERS
        public int getTotalReminders() {

            return getAllReminders().size();
        }

        // PENDING REMINDERS
        public int getPendingReminders() {

            int count = 0;

            for (Reminder reminder : getAllReminders()) {

                if (reminder.getCurrentStatus()
                        .equalsIgnoreCase("Pending")) {

                    count++;
                }
            }

            return count;
        }

        // COMPLETED REMINDERS
        public int getCompletedReminders() {

            int count = 0;

            for (Reminder reminder : getAllReminders()) {

                if (reminder.getCurrentStatus()
                        .equalsIgnoreCase("Completed")) {

                    count++;
                }
            }

            return count;
        }

        // OVERDUE REMINDERS
        public int getOverdueReminders() {

            int count = 0;

            for (Reminder reminder : getAllReminders()) {

                if (reminder.getCurrentStatus()
                        .equalsIgnoreCase("OVERDUE")) {

                    count++;
                }
            }

            return count;
        }
        // MARK AS COMPLETED
        public void markAsCompleted(int reminderId) {

            List<Reminder> reminders =
                    getAllReminders();

            try {

                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(FILE_PATH)
                        );

                for (Reminder reminder : reminders) {

                    if (reminder.getReminderId()
                            == reminderId) {

                        reminder.setStatus("Completed");
                    }

                    writer.write(
                            reminder.getReminderId() + "," +
                                    reminder.getTitle() + "," +
                                    reminder.getDueDate() + "," +
                                    reminder.getStatus()
                    );

                    writer.newLine();
                }

                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // SEARCH REMINDERS
        public List<Reminder> searchReminders(String keyword) {

            List<Reminder> allReminders =
                    getAllReminders();

            List<Reminder> results =
                    new ArrayList<>();

            for (Reminder reminder : allReminders) {

                if (reminder.getTitle()
                        .toLowerCase()
                        .contains(keyword.toLowerCase())) {

                    results.add(reminder);
                }
            }

            return results;
        }
    }

