package com.hackathon.backend.service;

import com.hackathon.backend.dto.MedicineDTO;
import java.util.List;

public interface MedicineService {
    List<MedicineDTO> getAllMedicines();
    MedicineDTO getMedicineById(Long id);
    MedicineDTO addMedicine(MedicineDTO medicineDTO);
    
    // ENSURE THIS NAME MATCHES THE ERROR:
    List<MedicineDTO> searchMedicines(String name); 
}