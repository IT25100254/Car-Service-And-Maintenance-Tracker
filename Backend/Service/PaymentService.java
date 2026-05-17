package com.example.Service_And_Maintenance.Service;

import com.example.Service_And_Maintenance.Dto.PaymentRequestDTO;
import com.example.Service_And_Maintenance.Entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Service_And_Maintenance.FileRepository.PaymentRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private BillingService billingService;

    public void recordPayment(PaymentRequestDTO dto) {
        //create
        Payment payment = new Payment();

        //generate ID
        String payId = "PAY-" + UUID.randomUUID().toString().substring(0, 5);

        //time
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        payment.setPaymentDate(now);

        //call Data from DTO
        payment.setBillId(dto.getBillId());
        payment.setVehicleNumber(dto.getVehicleNumber());
        payment.setAmountPaid(dto.getAmountPaid());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setTransactionRef(dto.getTransactionRef());
        payment.setRemarks(dto.getRemarks());

        //save to pay.txt  via repo
        paymentRepository.save(payment);
        //Update bill
        billingService.updateStatus(dto.getBillId(), "paid");
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByBillId(String billId) {
        return paymentRepository.findByBillId(billId);
    }
}



