package com.example.Service_And_Maintenance.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a Billing record.
 * This applies the OOP concept of Encapsulation by using private fields
 * and public accessors (provided by Lombok @Data).
 */
@Data // Generates Getters, Setters, toString, equals, and hashCode
@NoArgsConstructor // Generates a no-args constructor
@AllArgsConstructor // Generates a constructor with all fields
public class Billing {

    // Unique identifier for the bill
    private String billId;

    // Links the bill to a specific vehicle
    private String vehicleNumber;

    // The date the bill was generated or service was provided
    private String date;

    // The description of the service (e.g., "Oil Change", "Brake Repair")
    private String serviceDescription;

    // Financial details
    private double amount;

    // Tax or additional charges if applicable
    private double tax;

    // Total amount (amount + tax)
    private double totalAmount;

    // Payment status: e.g., "Paid", "Pending", or "Overdue"
    private String paymentStatus;

    // Optional: A method to calculate total if you don't want to pass it manually
    public void calculateTotal() {
        this.totalAmount = this.amount + this.tax;
    }
}


