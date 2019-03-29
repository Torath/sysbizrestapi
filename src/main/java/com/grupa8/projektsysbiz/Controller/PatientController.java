package com.grupa8.projektsysbiz.Controller;

import com.grupa8.projektsysbiz.Repository.PatientRepository;
import com.grupa8.projektsysbiz.exception.NotFoundException;
import com.grupa8.projektsysbiz.model.PatientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    @GetMapping("/patients")
    public List<PatientModel> getAllPatients(){
        return patientRepository.findAll();
    }

    @PostMapping("/patients")
    public PatientModel addPatient(@Valid @RequestBody PatientModel doctor){
        return patientRepository.save(doctor);
    }

    @GetMapping("/patients/{id}")
    public PatientModel getPatientById(@PathVariable(value = "id") Long doctorId){
        return patientRepository.findById(doctorId).orElseThrow(()->new NotFoundException("Pacjent","id",doctorId));
    }

    @PutMapping("/patients/{id}")
    public PatientModel updatePatient(@PathVariable(value = "id") Long doctorId,
                                    @Valid @RequestBody PatientModel doctorDetails){
        PatientModel patient = patientRepository.findById(doctorId).orElseThrow(()->new NotFoundException("Pacjent","id",doctorId));

        patient.setFirstName(doctorDetails.getFirstName());
        patient.setLastName(doctorDetails.getLastName());
        patient.setPesel(doctorDetails.getPesel());
        patient.setBirthDate(doctorDetails.getBirthDate());
        patient.setAddress(doctorDetails.getAddress());

        PatientModel updatedPatient=patientRepository.save(patient);
        return updatedPatient;
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<?> getDeletePatient(@PathVariable(value = "id") Long doctorId){
        PatientModel doctor = patientRepository.findById(doctorId).orElseThrow(()->new NotFoundException("Pacjent","id",doctorId));

        patientRepository.delete(doctor);

        return ResponseEntity.ok().build();
    }
}
