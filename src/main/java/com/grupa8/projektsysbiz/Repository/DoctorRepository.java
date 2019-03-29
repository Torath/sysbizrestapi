package com.grupa8.projektsysbiz.Repository;

import com.grupa8.projektsysbiz.model.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorModel,Long> {
}
