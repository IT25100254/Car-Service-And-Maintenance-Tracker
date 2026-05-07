package com.example.Service_And_Maintenance.Controller;

import com.example.Service_And_Maintenance.Dto.BillingRequestDTO;
import com.example.Service_And_Maintenance.Entity.Billing;
import com.example.Service_And_Maintenance.Service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    // READ
    @GetMapping("/all")
    public ResponseEntity<List<Billing>> getAllBills() {
        return ResponseEntity.ok(billingService.getAllInvoices());
    }

    @GetMapping("/")
    public ResponseEntity<Void> index() {
        return ResponseEntity.status(302).header("Location", "/billing-dashboard.html").build();
    }

    //CREATE
    @PostMapping("/save")
    public ResponseEntity<Map<String, String>> saveBill(@RequestBody BillingRequestDTO dto) {
        billingService.processNewBill(dto);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Bill saved successfully"
        ));
    }

    //UPDATE
    @PostMapping("/pay/{id}")
    public ResponseEntity<Map<String, String>> markAsPaid(@PathVariable String id) {
        billingService.updateStatus(id, "Paid");
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Bill " + id + " marked as Paid"
        ));
    }

    //DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteBill(@PathVariable String id) {
        billingService.deleteInvoice(id);
        return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Bill " + id + " deleted"
        ));
    }
}