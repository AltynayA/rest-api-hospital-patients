package kz.aitu.oop.restservice.controller;
import kz.aitu.oop.restservice.entities.AdultPatient;
import kz.aitu.oop.restservice.entities.ChildPatient;
import kz.aitu.oop.restservice.entities.enums.Gender;
import kz.aitu.oop.restservice.entities.Patient;
import kz.aitu.oop.restservice.exceptions.PatientNotFoundException;
import kz.aitu.oop.restservice.repository.PatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientRepository patientRepository;

    //    dependency injection
    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/readall")
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
    

    //          C - Create
//   adding a new patient
    @PostMapping("/add")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient newPatient;
        if (patient.getAge() < 18) {
            newPatient = new ChildPatient(patient.getName(), patient.getAge(), patient.getGender(), "Unknown School");
        } else {
            if (patient.getAge() >= 18) {
                newPatient = new AdultPatient(patient.getName(), patient.getAge(), patient.getGender(),"Unknown Occupation");
            } else {
                throw new IllegalArgumentException("Occupation is required for AdultPatient");
            }
        }

        return ResponseEntity.ok(patientRepository.save(newPatient));
    }

    //   adding multiple patients
    @PostMapping("/add/bulk")
    public ResponseEntity<?> addMultiplePatients(@RequestBody List<Patient> patients) {
        List<Patient> processedPatients = new ArrayList<>();

        // going through
        for (Patient patient : patients) {
            Patient newPatient;
            if (patient.getAge() < 18) {
                newPatient = new ChildPatient(patient.getName(), patient.getAge(), patient.getGender(), "Unknown School");
            } else {
                newPatient = new AdultPatient(patient.getName(), patient.getAge(), patient.getGender(), "Unknown Occupation");
            }
            processedPatients.add(newPatient);
        }

        List<Patient> savedPatients = patientRepository.saveAll(processedPatients);

        // Prepare response
        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Patients successfully added");
        response.put("patients", savedPatients);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


//          R - Read
//  getting a patient by gender
    @GetMapping("/gender/{gender}")
    public List<Patient> getPatientsByGender(@PathVariable Gender gender) {
        return patientRepository.findByGender(gender);
    }

    //  getting a patient by id
    @GetMapping("/read/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Integer id) {
        // Attempt to find patient, if not found, it will be handled by the @ExceptionHandler
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));

        return ResponseEntity.ok(patient); // 200 OK with patient data
    }

//    U - Update
//    updating the occupation of an adult patient
    @PutMapping("/update/foradults/{id}")
    public ResponseEntity<Patient> updateAdultPatient(@PathVariable Integer id,
                                                      @RequestParam String occupation) {
        // find ID
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));

        // setting the occupation
        if (patient instanceof AdultPatient adultPatient) {
            adultPatient.setOccupation(occupation);
            return ResponseEntity.ok(patientRepository.save(adultPatient));
        } else {
            throw new IllegalArgumentException("Patient with ID " + id + " is not an AdultPatient");
        }
    }
//    updating the school of a child patient
    @PutMapping("/update/forchildren/{id}")
    public ResponseEntity<Patient> updateChildPatient(@PathVariable Integer id, @RequestParam String schoolName) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + id + " not found"));

        // Check if it's an instance of AdultPatient
        if (patient instanceof ChildPatient childPatient) {
            childPatient.setSchoolName(schoolName);
            return ResponseEntity.ok(patientRepository.save(childPatient));
        } else {
            throw new IllegalArgumentException("Patient with ID " + id + " is not an ChildPatient");
        }
    }

//  updating an existing patient (basic attributes)
    @PutMapping("/update/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Integer id, @RequestBody Patient updatedPatient) {
        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setName(updatedPatient.getName());
                    patient.setAge(updatedPatient.getAge());
                    patient.setGender(updatedPatient.getGender());  // Updating the gender
                    patientRepository.save(patient);  // Save the updated patient to the database
                    return ResponseEntity.ok(patient);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());  // Return 404 if patient not found
    }


    //          D - delete
//  deleting multiple patients by entering IDs
    @DeleteMapping("/delete/bulk")
    public void deleteMultiplePatients(@RequestBody List<Integer> patientIds) {
        patientRepository.deleteAllById(patientIds);
    }

    //    deleting a patient by id
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        if (!patientRepository.existsById(id)) {
            throw new PatientNotFoundException("Patient with ID " + id + " not found");
        }

        patientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
//    exception handlers
    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<String> handlePatientNotFoundException(PatientNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}





