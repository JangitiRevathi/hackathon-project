package com.hackathon.backend.service;

import java.util.List;

import com.hackathon.backend.dto.MedicineDTO;

public interface MedicineService {
    List<MedicineDTO> getAllMedicines();
    MedicineDTO getMedicineById(Long id);
    MedicineDTO addMedicine(MedicineDTO medicineDTO);

    List<MedicineDTO> searchByName(String name);
}