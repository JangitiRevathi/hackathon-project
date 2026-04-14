package com.hackathon.backend.repository;



import com.hackathon.backend.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByMedicineId(Long medicineId);

    List<Inventory> findByStockQuantityLessThan(Integer quantity);
}
