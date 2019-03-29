package com.grupa8.projektsysbiz.Controller;


import com.grupa8.projektsysbiz.Repository.DoctorRepository;
import com.grupa8.projektsysbiz.exception.NotFoundException;
import com.grupa8.projektsysbiz.model.DoctorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("/doctors")
    public List<DoctorModel> getAllDoctors(){
        return doctorRepository.findAll();
    }

    @PostMapping("/doctors")
    public DoctorModel addDoctor(@Valid @RequestBody DoctorModel doctor){
        return doctorRepository.save(doctor);
    }

    @GetMapping("/doctors/{id}")
    public DoctorModel getDoctorById(@PathVariable(value = "id") Long doctorId){
        return doctorRepository.findById(doctorId).orElseThrow(()->new NotFoundException("Lekarz","id",doctorId));
    }

    @PutMapping("/doctors/{id}")
    public DoctorModel updateDoctor(@PathVariable(value = "id") Long doctorId,
                                    @Valid @RequestBody DoctorModel doctorDetails){
        DoctorModel doctor = doctorRepository.findById(doctorId).orElseThrow(()->new NotFoundException("Lekarz","id",doctorId));

        doctor.setFirstName(doctorDetails.getFirstName());
        doctor.setLastName(doctorDetails.getLastName());
        doctor.setPesel(doctorDetails.getPesel());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setTelephone(doctorDetails.getPesel());

        DoctorModel updatedDoctor=doctorRepository.save(doctor);
        return updatedDoctor;
    }

    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<?> getDeleteDoctor(@PathVariable(value = "id") Long doctorId){
        DoctorModel doctor = doctorRepository.findById(doctorId).orElseThrow(()->new NotFoundException("Lekarz","id",doctorId));

        doctorRepository.delete(doctor);

        return ResponseEntity.ok().build();
    }

}
