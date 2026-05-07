package com.example.Service_And_Maintenance.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for capturing Billing information from the web form.
 * This separates the UI data from the internal File Entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingRequestDTO {

    // These fields match the input fields in your HTML form
    private String vehicleNumber;
    private String serviceDescription;
    private double amount;
    private double tax;
    private String paymentStatus;

    // We exclude 'billId' and 'totalAmount' here if they are
    // calculated automatically in the backend.
}
