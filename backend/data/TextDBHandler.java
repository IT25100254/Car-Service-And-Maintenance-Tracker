package com.autocare.data;

import com.autocare.models.AdminUser;
import com.autocare.models.RegularUser;
import com.autocare.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextDBHandler {
    private static final String ADMIN_FILE = System.getProperty("user.dir") + File.separator + "admin.txt";
    private static final String CUSTOMER_FILE = System.getProperty("user.dir") + File.separator + "customer.txt";

    // Create / Append User
    public static boolean saveUser(User user) {
        String filePath = "ADMIN".equals(user.getRole()) ? ADMIN_FILE : CUSTOMER_FILE;
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(user.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read All Users
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        users.addAll(readUsersFromFile(ADMIN_FILE));
        users.addAll(readUsersFromFile(CUSTOMER_FILE));
        return users;
    }

    private static List<User> readUsersFromFile(String filePath) {
        List<User> users = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return users;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    if (data[5].equals("ADMIN")) {
                        users.add(new AdminUser(data[0], data[1], data[2], data[3], data[4]));
                    } else {
                        users.add(new RegularUser(data[0], data[1], data[2], data[3], data[4]));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Rewrite all users to file (used for Update/Delete)
    public static boolean saveAllUsers(List<User> users) {
        List<User> admins = new ArrayList<>();
        List<User> customers = new ArrayList<>();
        for (User user : users) {
            if ("ADMIN".equals(user.getRole())) {
                admins.add(user);
            } else {
                customers.add(user);
            }
        }
        
        boolean adminSaved = writeUsersToFile(ADMIN_FILE, admins);
        boolean customerSaved = writeUsersToFile(CUSTOMER_FILE, customers);
        return adminSaved && customerSaved;
    }

    private static boolean writeUsersToFile(String filePath, List<User> users) {
        try (FileWriter fw = new FileWriter(filePath, false); // false to overwrite
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for (User user : users) {
                out.println(user.toString());
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
