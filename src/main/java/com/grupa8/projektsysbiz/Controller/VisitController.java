package com.grupa8.projektsysbiz.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grupa8.projektsysbiz.Repository.VisitRepository;
import com.grupa8.projektsysbiz.exception.NotFoundException;
import com.grupa8.projektsysbiz.model.VisitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VisitController {

    @Autowired
    VisitRepository visitRepository;

    @GetMapping("/visits")
    public List<VisitModel> getAllVisits(){
        return visitRepository.findAll();
    }

    @PostMapping("/visits")
    public VisitModel addVisit(@Valid @RequestBody VisitModel visit){
        return visitRepository.save(visit);
    }

    @GetMapping("/visits/{id}")
    public VisitModel getVisitById(@PathVariable(value = "id") Long visitId){
        return visitRepository.findById(visitId).orElseThrow(()->new NotFoundException("Wiztyta","id",visitId));
    }

    //get visits by patient
    @GetMapping("/visits/patient/{id}")
    public Page<VisitModel> getVisitByPatient(@PathVariable(value = "id") Long patientId, Pageable pageable){
        return visitRepository.findByPatient_Id(patientId,pageable);
    }

    //get visits by doctor
    @GetMapping("/visits/doctor/{id}")
    public Page<VisitModel> getVisitByDoctor(@PathVariable(value = "id") Long doctorId,Pageable pageable){
        return visitRepository.findByDoctor_Id(doctorId,pageable);
    }

    @PutMapping("/visits/{id}")
    public VisitModel updateVisit(@PathVariable(value = "id") Long visitId,
                                      @Valid @RequestBody VisitModel visitDetails){
        VisitModel visit = visitRepository.findById(visitId).orElseThrow(()->new NotFoundException("Wizyta","id",visitId));

        visit.setDoctor(visitDetails.getDoctor());
        visit.setPatient(visitDetails.getPatient());
        visit.setSymptoms(visitDetails.getSymptoms());
        visit.setTreatment(visitDetails.getTreatment());
        visit.setVisitDate(visitDetails.getVisitDate());


        VisitModel updatedVisit=visitRepository.save(visit);
        return updatedVisit;
    }

    @DeleteMapping("/visits/{id}")
    public ResponseEntity<?> getDeleteVist(@PathVariable(value = "id") Long doctorId){
        VisitModel doctor = visitRepository.findById(doctorId).orElseThrow(()->new NotFoundException("Wizyta","id",doctorId));

        visitRepository.delete(doctor);

        return ResponseEntity.ok().build();
    }
}
