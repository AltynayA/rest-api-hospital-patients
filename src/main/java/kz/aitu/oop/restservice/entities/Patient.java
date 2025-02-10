package kz.aitu.oop.restservice.entities;
import jakarta.persistence.*;
import kz.aitu.oop.restservice.entities.enums.Gender;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "patients")
@DiscriminatorColumn(name = "patient_type", discriminatorType = DiscriminatorType.STRING)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "patient_type", insertable = false, updatable = false)
//    private PatientType patientType;
    public Patient() {}
    public Patient(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
//        this.patientType = determinePatientType(age);
    }
//  getters and setters
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public Gender getGender() {
        return gender;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }



}
