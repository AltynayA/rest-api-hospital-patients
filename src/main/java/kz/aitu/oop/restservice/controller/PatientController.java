package kz.aitu.oop.restservice.controller;
import kz.aitu.oop.restservice.entities.Patient;
import kz.aitu.oop.restservice.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;
//    dependency injection
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @GetMapping("")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
//    adding a new patient
    @PostMapping("")
    public Patient addNewPatient(@RequestBody Patient patient) {
        System.out.println("Received patient: " + patient);
        return patientRepository.save(patient);
    }
// get a patient by id
    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Integer id) {
        return patientRepository.findById(id).orElse(null);
    }
//    updating an existing patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient updatedPatient) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setName(updatedPatient.getName());
                    patient.setAge(updatedPatient.getAge());
                    patient.setGender(updatedPatient.getGender());
                    patientRepository.save(patient);
                    return ResponseEntity.ok(patient); // Return 200 OK
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // Return 404 Not Found
    }
//    deleting patients
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Integer id) {
        patientRepository.deleteById(id);}
    //    fix the handling if no id
    }



