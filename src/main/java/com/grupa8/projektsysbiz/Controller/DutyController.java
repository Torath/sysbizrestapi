package com.grupa8.projektsysbiz.Controller;


import com.grupa8.projektsysbiz.Repository.DutyRepository;
import com.grupa8.projektsysbiz.exception.NotFoundException;
import com.grupa8.projektsysbiz.model.DutyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DutyController {

    @Autowired
    DutyRepository dutyRepository;

    @GetMapping("/duties")
    public List<DutyModel> getAllDuties(){
        return dutyRepository.findAll();
    }

    @PostMapping("/duties")
    public DutyModel addDuty(@Valid @RequestBody DutyModel duty){
        return dutyRepository.save(duty);
    }

    @GetMapping("/duties/{id}")
    public DutyModel getDutytById(@PathVariable(value = "id") Long dutyId){
        return dutyRepository.findById(dutyId).orElseThrow(()->new NotFoundException("Dyzur","id",dutyId));
    }

    @PutMapping("/duties/{id}")
    public DutyModel updateDuty(@PathVariable(value = "id") Long visitId,
                                    @Valid @RequestBody DutyModel dutyDetails){
        DutyModel duty = dutyRepository.findById(visitId).orElseThrow(()->new NotFoundException("Dyzur","id",visitId));

        duty.setDoctor(dutyDetails.getDoctor());
        duty.setStartTime(dutyDetails.getStartTime());
        duty.setEndTime(dutyDetails.getEndTime());

        DutyModel updatedDuty=dutyRepository.save(duty);
        return updatedDuty;
    }

    @DeleteMapping("/duties/{id}")
    public ResponseEntity<?> getDeleteDuty(@PathVariable(value = "id") Long doctorId){
        DutyModel duty = dutyRepository.findById(doctorId).orElseThrow(()->new NotFoundException("Dyzur","id",doctorId));

        dutyRepository.delete(duty);

        return ResponseEntity.ok().build();
    }
}
