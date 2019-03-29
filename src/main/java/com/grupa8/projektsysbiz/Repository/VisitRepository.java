package com.grupa8.projektsysbiz.Repository;

import com.grupa8.projektsysbiz.model.VisitModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitRepository extends JpaRepository<VisitModel,Long> {
    Page<VisitModel> findByPatient_Id(Long patientId, Pageable pageable);
    Page<VisitModel> findByDoctor_Id(Long doctorId,Pageable pageable);
}
