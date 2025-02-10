package kz.aitu.oop.restservice.entities;

import jakarta.persistence.*;
import kz.aitu.oop.restservice.entities.enums.Gender;

@Entity
@DiscriminatorValue("CHILD") // Indicates this subclass in the database
public class ChildPatient extends Patient {

    private String schoolName;
    public ChildPatient() {}
    public ChildPatient(String name, int age, Gender gender, String schoolName) {
        super(name, age, gender);
        this.schoolName = schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public String getSchoolName() {
        return schoolName;
    }

}
