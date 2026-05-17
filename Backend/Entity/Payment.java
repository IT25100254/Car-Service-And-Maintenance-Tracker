package com.example.Service_And_Maintenance.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//attributes
public class Payment {
    private String paymentId;
    private String billId;
    private String vehicleNumber;
    private String paymentDate;
    private String paymentMethod;
    private double amountPaid;
    private String transactionRef;
    private String remarks;

    //Saving format
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%.2f,%s,%s,%s",
                paymentId, billId, vehicleNumber, paymentDate,
                amountPaid, paymentMethod, transactionRef, remarks);
    }

}
