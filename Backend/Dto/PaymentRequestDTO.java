package com.example.Service_And_Maintenance.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaymentRequestDTO {
    private  String billId;
    private String vehicleNumber;
    private double amountPaid;
    private String paymentMethod;
    private String transactionRef;
    private String remarks;
}
