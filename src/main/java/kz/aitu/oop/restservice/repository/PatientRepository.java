package kz.aitu.oop.restservice.repository;

import kz.aitu.oop.restservice.entities.enums.Gender;
import kz.aitu.oop.restservice.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByGender(Gender gender);
}
