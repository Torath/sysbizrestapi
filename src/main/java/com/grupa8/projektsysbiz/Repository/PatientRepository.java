package com.grupa8.projektsysbiz.Repository;

import com.grupa8.projektsysbiz.model.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientModel,Long> {
}
