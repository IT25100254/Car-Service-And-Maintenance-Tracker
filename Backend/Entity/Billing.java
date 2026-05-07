package com.example.Service_And_Maintenance.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//billing records
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Billing {

    private String billId;
    private String vehicleNumber;
    private String date;
    private String serviceDescription;
    private double amount;
    private double tax;
    private double totalAmount;
    private String paymentStatus;

    public void calculateTotal() {
        this.totalAmount = this.amount + this.tax;
    }
}


