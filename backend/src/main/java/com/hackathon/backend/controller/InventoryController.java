package com.hackathon.backend.controller;

import com.hackathon.backend.model.Inventory;
import com.hackathon.backend.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("*")
public class InventoryController {

    @Autowired
    private InventoryService service;

    // ✅ POST (CREATE)
    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return service.saveInventory(inventory);
    }

    // ✅ GET
    @GetMapping("/{medicineId}")
    public Inventory getStock(@PathVariable Long medicineId) {
        return service.getStock(medicineId);
    }

    // ✅ PUT (UPDATE / CREATE)
    @PutMapping("/{medicineId}")
    public Inventory updateStock(@PathVariable Long medicineId,
                                 @RequestParam Integer quantity) {
        return service.updateStock(medicineId, quantity);
    }

    // ✅ REDUCE
    @PutMapping("/reduce/{medicineId}")
    public String reduceStock(@PathVariable Long medicineId,
                              @RequestParam Integer quantity) {
        service.reduceStock(medicineId, quantity);
        return "Stock reduced successfully";
    }

    // ✅ LOW STOCK
    @GetMapping("/low-stock")
    public List<Inventory> lowStock() {
        return service.getLowStock();
    }
}