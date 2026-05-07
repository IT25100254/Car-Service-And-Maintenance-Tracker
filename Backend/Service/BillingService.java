package com.example.Service_And_Maintenance.Service;

import com.example.Service_And_Maintenance.Dto.BillingRequestDTO;
import com.example.Service_And_Maintenance.Entity.Billing;
import com.example.Service_And_Maintenance.FileRepository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BillingService {
    @Autowired
    private BillingRepository billingRepository;

    public void processNewBill(BillingRequestDTO dto) {
        Billing billing = new Billing();
        billing.setBillId("INV-" + UUID.randomUUID().toString().substring(0, 5));
        billing.setVehicleNumber(dto.getVehicleNumber());
        billing.setServiceDescription(dto.getServiceDescription());
        billing.setAmount(dto.getAmount());
        billing.setTax(dto.getTax());
        billing.setPaymentStatus(dto.getPaymentStatus());
        billing.setDate(LocalDate.now().toString());
        billing.calculateTotal(); // Business logic call
        billingRepository.save(billing);
    }

    public List<Billing> getAllInvoices() { return billingRepository.findAll(); }

    public void updateStatus(String id, String status) {
        List<Billing> bills = billingRepository.findAll();
        bills.forEach(b -> { if(b.getBillId().equals(id)) b.setPaymentStatus(status); });
        billingRepository.updateAll(bills);
    }

    public void deleteInvoice(String id) {
        billingRepository.deleteById(id);
    }
}