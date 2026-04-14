package com.hackathon.backend.service;

import com.hackathon.backend.model.Inventory;
import com.hackathon.backend.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repository;

    // ✅ GET
    public Inventory getStock(Long medicineId) {
        return repository.findByMedicineId(medicineId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    // ✅ POST (CREATE)
    public Inventory saveInventory(Inventory inventory) {
        inventory.setLastUpdated(LocalDateTime.now());
        return repository.save(inventory);
    }

    // ✅ PUT (UPDATE OR CREATE)
    public Inventory updateStock(Long medicineId, Integer quantity) {

        Inventory inventory = repository.findByMedicineId(medicineId)
                .orElse(new Inventory());   // 🔥 FIX

        inventory.setMedicineId(medicineId);
        inventory.setStockQuantity(quantity);
        inventory.setLastUpdated(LocalDateTime.now());

        return repository.save(inventory);
    }

    // ✅ REDUCE STOCK
    public void reduceStock(Long medicineId, Integer quantity) {

        Inventory inventory = repository.findByMedicineId(medicineId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));

        if (inventory.getStockQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        inventory.setStockQuantity(inventory.getStockQuantity() - quantity);
        inventory.setLastUpdated(LocalDateTime.now());

        repository.save(inventory);
    }

    // ✅ LOW STOCK
    public List<Inventory> getLowStock() {
        return repository.findByStockQuantityLessThan(10);
    }
}