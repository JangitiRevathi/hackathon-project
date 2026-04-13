package com.hackathon.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.backend.model.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
}