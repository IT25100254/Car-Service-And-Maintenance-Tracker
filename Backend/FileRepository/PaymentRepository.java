package com.example.Service_And_Maintenance.FileRepository;


import com.example.Service_And_Maintenance.Entity.Payment;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PaymentRepository {
    private final String FILE_PATH = "data/payments.txt";

    //create
    public void save(Payment payment) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(payment.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to payments file: " + e.getMessage());
        }
    }

    //Read Data
    public List<Payment> findAll() {
        List<Payment> payments = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) return payments;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                payments.add(parsePayment(line));
            }
        } catch (IOException e) {
            System.err.println("Error reading payments file: " + e.getMessage());
        }
        return payments;
    }

    //Search
    public List<Payment> findByBillId(String billId) {
        return findAll().stream()
                .filter(p -> p.getBillId().equals(billId))
                .collect(Collectors.toList());
    }
    // HELPER
    private Payment parsePayment(String line) {
        String[] parts = line.split(",");
        // match order with entity
        return new Payment(
                parts[0],
                parts[1],
                parts[2],
                parts[3],
                parts[4],
                Double.parseDouble(parts[5]),
                parts[6],
                parts[7]
        );
    }
}
