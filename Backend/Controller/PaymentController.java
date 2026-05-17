package com.example.Service_And_Maintenance.Controller;

import com.example.Service_And_Maintenance.Dto.PaymentRequestDTO;
import com.example.Service_And_Maintenance.Entity.Payment;
import com.example.Service_And_Maintenance.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // CREATE payment
    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> processPayment(@RequestBody PaymentRequestDTO dto) {
        paymentService.recordPayment(dto);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Payment processed for Bill ID: " + dto.getBillId()
        ));
    }

    //READ all payment
    @GetMapping("/history")
    public ResponseEntity<List<Payment>> getPaymentHistory() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    //  Find bill
    @GetMapping("/bill/{billId}")
    public ResponseEntity<List<Payment>> getPaymentsByBill(@PathVariable String billId) {
        return ResponseEntity.ok(paymentService.getPaymentsByBillId(billId));
    }
}