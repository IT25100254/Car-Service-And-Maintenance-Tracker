package com.example.Service_And_Maintenance.FileRepository;

import com.example.Service_And_Maintenance.Entity.Billing;
import org.springframework.stereotype.Repository;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BillingRepository {
    private final String FILE_PATH = "./data/bills.txt";

    public void save(Billing billing) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(String.format("%s,%s,%s,%s,%.2f,%.2f,%.2f,%s",
                    billing.getBillId(), billing.getVehicleNumber(), billing.getDate(),
                    billing.getServiceDescription(), billing.getAmount(), billing.getTax(),
                    billing.getTotalAmount(), billing.getPaymentStatus()));
            writer.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public List<Billing> findAll() {
        List<Billing> billings = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return billings;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] d = line.split(",");
                billings.add(new Billing(d[0], d[1], d[2], d[3],
                        Double.parseDouble(d[4]), Double.parseDouble(d[5]),
                        Double.parseDouble(d[6]), d[7]));
            }
        } catch (IOException e) { e.printStackTrace(); }
        return billings;
    }

    public void updateAll(List<Billing> billings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Billing b : billings) {
                writer.write(String.format("%s,%s,%s,%s,%.2f,%.2f,%.2f,%s",
                        b.getBillId(), b.getVehicleNumber(), b.getDate(), b.getServiceDescription(),
                        b.getAmount(), b.getTax(), b.getTotalAmount(), b.getPaymentStatus()));
                writer.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void deleteById(String billId) {
        List<Billing> allBills = findAll();
        List<Billing> updatedBills = allBills.stream()
                .filter(b -> !b.getBillId().equals(billId))
                .toList();

        // Overwrite bill
        updateAll(updatedBills);
    }
}