package com.autocare.controllers;

import com.autocare.models.User;
import com.autocare.services.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    private final UserManager userManager = new UserManager();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/admin-dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("allUsers", userManager.getAllUsers());
        return "admin-dashboard";
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam("userId") String userId, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null || !currentUser.getRole().equals("ADMIN")) {
            return "redirect:/login";
        }

        if (currentUser.getId().equals(userId)) {
            return "redirect:/admin-dashboard?error=Cannot delete your own admin account.";
        }

        boolean success = userManager.deleteUser(userId);
        if (success) {
            return "redirect:/admin-dashboard?success=User deleted successfully.";
        } else {
            return "redirect:/admin-dashboard?error=Failed to delete user.";
        }
    }
}
