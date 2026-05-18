package com.autocare.controllers;

import com.autocare.models.RegularUser;
import com.autocare.models.User;
import com.autocare.services.UserManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuthController {

    private final UserManager userManager = new UserManager();

    // Show login page
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Process login
    @PostMapping("/LoginServlet")
    public String loginUser(@RequestParam("username") String usernameOrEmail,
                            @RequestParam("password") String password,
                            @RequestParam("loginType") String loginType,
                            HttpSession session, Model model) {

        User user = userManager.loginUser(usernameOrEmail, password);

        if (user != null) {
            if (loginType.equals("ADMIN") && !user.getRole().equals("ADMIN")) {
                model.addAttribute("error", "Access Denied. You are not an Admin.");
                return "login";
            }
            if (loginType.equals("USER") && user.getRole().equals("ADMIN")) {
                model.addAttribute("error", "Admins must use the Admin Login tab.");
                return "login";
            }

            session.setAttribute("currentUser", user);

            if (user.getRole().equals("ADMIN")) {
                return "redirect:/admin-dashboard";
            } else {
                return "redirect:/";
            }
        } else {
            model.addAttribute("error", "Invalid Credentials");
            return "login";
        }
    }

    // Show register page
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // Process registration
    @PostMapping("/RegisterServlet")
    public String registerUser(@RequestParam("fullname") String fullName,
                               @RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("confirm-password") String confirmPassword,
                               Model model) {

        if (password != null && password.equals(confirmPassword)) {
            User newUser = new RegularUser("", fullName, username, email, password);
            boolean success = userManager.registerUser(newUser);

            if (success) {
                model.addAttribute("success", "Account created successfully! Please login.");
                return "login";
            } else {
                model.addAttribute("error", "Username or email already exists");
                return "register";
            }
        } else {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }
    }

    // Show forgot password page
    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    // Process forgot password
    @PostMapping("/ResetPasswordServlet")
    public String resetPassword(@RequestParam("username") String usernameOrEmail,
                                @RequestParam("newPassword") String newPassword,
                                @RequestParam("confirmPassword") String confirmPassword,
                                Model model) {

        if (newPassword != null && newPassword.equals(confirmPassword)) {
            boolean success = userManager.resetPassword(usernameOrEmail, newPassword);

            if (success) {
                model.addAttribute("success", "Password reset successfully. Please login.");
                return "login";
            } else {
                model.addAttribute("error", "No account found with that Username or Email.");
                return "forgot-password";
            }
        } else {
            model.addAttribute("error", "Passwords do not match.");
            return "forgot-password";
        }
    }

    // Logout
    @GetMapping("/LogoutServlet")
    public String logoutGet(HttpSession session, Model model) {
        return logout(session, model);
    }
    
    @PostMapping("/LogoutServlet")
    public String logout(HttpSession session, Model model) {
        if (session != null) {
            session.invalidate();
        }
        model.addAttribute("success", "Successfully logged out");
        return "login";
    }
}
