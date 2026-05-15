package com.carcare.reminder.controller;

import com.carcare.reminder.model.Notification;
import com.carcare.reminder.model.Reminder;
import com.carcare.reminder.service.NotificationService;
import com.carcare.reminder.service.ReminderService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class ReminderController {

    private final ReminderService reminderService;
    private final NotificationService notificationService;

    // Constructor
    public ReminderController(
            ReminderService reminderService,
            NotificationService notificationService
    ) {
        this.reminderService = reminderService;
        this.notificationService = notificationService;
    }

    // Home Page
    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    // View Reminders
    @GetMapping("/reminders")
    public String getReminders(Model model) {

        model.addAttribute(
                "reminders",
                reminderService.getAllReminders()
        );

        return "reminders";
    }

    // Open Add Reminder Page
    @GetMapping("/addReminder")
    public String addReminderPage(Model model) {

        model.addAttribute(
                "reminder",
                new Reminder()
        );

        return "add-reminder";
    }

    // Save Reminder
    @PostMapping("/saveReminder")
    public String saveReminder(
            @ModelAttribute Reminder reminder
    ) {

        // Save reminder
        reminderService.saveReminder(reminder);

        // Create notification
        Notification notification = new Notification(
                        reminder.getReminderId(),
                        reminder.getTitle()
                                + " is due soon ",

                        reminder.getDueDate()
                );

        // Save notification
        notificationService
                .saveNotification(notification);

        return "redirect:/reminders";
    }

    // View Notifications
    @GetMapping("/notifications")
    public String getNotifications(Model model) {

        model.addAttribute(
                "notifications",
                notificationService.getAllNotifications()
        );

        return "notifications";
    }
    // DELETE REMINDER
    @GetMapping("/deleteReminder/{id}")
    public String deleteReminder(
            @PathVariable int id
    ) {

        reminderService.deleteReminder(id);

        return "redirect:/reminders";
    }
    // DASHBOARD PAGE
    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        model.addAttribute(
                "total",
                reminderService.getTotalReminders()
        );

        model.addAttribute(
                "pending",
                reminderService.getPendingReminders()
        );

        model.addAttribute(
                "completed",
                reminderService.getCompletedReminders()
        );

        model.addAttribute(
                "overdue",
                reminderService.getOverdueReminders()
        );

        return "dashboard";
    }
    // MARK AS COMPLETED
    @GetMapping("/completeReminder/{id}")
    public String completeReminder(
            @PathVariable int id
    ) {

        reminderService.markAsCompleted(id);

        return "redirect:/reminders";
    }
    // SEARCH REMINDERS
    @GetMapping("/search")
    public String searchReminder(
            @RequestParam String keyword,
            Model model
    ) {

        model.addAttribute(
                "reminders",
                reminderService.searchReminders(keyword)
        );

        return "reminders";
    }
}