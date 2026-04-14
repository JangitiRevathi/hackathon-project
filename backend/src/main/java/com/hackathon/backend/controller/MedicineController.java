package com.hackathon.backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.backend.dto.MedicineDTO;
import com.hackathon.backend.service.MedicineService;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin(origins = "*") // Critical for UI integration
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public List<MedicineDTO> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @GetMapping("/{id}")
    public MedicineDTO getMedicineById(@PathVariable Long id) {
        return medicineService.getMedicineById(id);
    }

    @GetMapping("/search")
    public List<MedicineDTO> search(@RequestParam String name) {
        return medicineService.searchMedicines(name);
    }
    @GetMapping("/low-stock")
    public List<MedicineDTO> getLowStock() {
        return medicineService.getAllMedicines().stream()
        .filter(m -> m.getStockQuantity() < 10)
        .collect(Collectors.toList());
    }
}