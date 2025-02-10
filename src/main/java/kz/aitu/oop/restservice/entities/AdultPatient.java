package kz.aitu.oop.restservice.entities;

import jakarta.persistence.*;
import kz.aitu.oop.restservice.entities.enums.Gender;

@Entity
@DiscriminatorValue("ADULT")
public class AdultPatient extends Patient {
    @Column(name = "occupation")
    private String occupation;

    public AdultPatient() {
        super();
    }

    public AdultPatient(String name, Integer age, Gender gender, String occupation) {
        super(name, age, gender);
        this.occupation = occupation;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
