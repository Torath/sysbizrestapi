package com.grupa8.projektsysbiz.Repository;

import com.grupa8.projektsysbiz.model.DutyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DutyRepository extends JpaRepository<DutyModel,Long> {
}
