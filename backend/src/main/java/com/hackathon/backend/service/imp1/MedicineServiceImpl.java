package com.hackathon.backend.service.imp1;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.backend.dto.MedicineDTO;
import com.hackathon.backend.exception.ResourceNotFoundException;
import com.hackathon.backend.model.Medicine;
import com.hackathon.backend.repository.MedicineRepository;
import com.hackathon.backend.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public List<MedicineDTO> getAllMedicines() {
        return medicineRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public MedicineDTO getMedicineById(Long id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with id: " + id));
        return convertToDTO(medicine);
    }

    @Override
    public MedicineDTO addMedicine(MedicineDTO medicineDTO) {
        Medicine medicine = new Medicine();
        BeanUtils.copyProperties(medicineDTO, medicine);
        Medicine saved = medicineRepository.save(medicine);
        return convertToDTO(saved);
    }

    // THIS METHOD NAME MUST MATCH THE INTERFACE EXACTLY
    @Override
    public List<MedicineDTO> searchMedicines(String name) {
        return medicineRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MedicineDTO convertToDTO(Medicine medicine) {
    MedicineDTO dto = new MedicineDTO();
    BeanUtils.copyProperties(medicine, dto);
    
    // If category is null in DB, give it a default for the demo
    if (dto.getCategory() == null) {
        dto.setCategory("General"); 
    }
    return dto;
}
}