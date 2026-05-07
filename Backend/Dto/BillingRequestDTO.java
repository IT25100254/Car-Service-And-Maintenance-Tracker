package com.example.Service_And_Maintenance.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingRequestDTO {

    // connect with HTML
    private String vehicleNumber;
    private String serviceDescription;
    private double amount;
    private double tax;
    private String paymentStatus;


}
