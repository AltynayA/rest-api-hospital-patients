package kz.aitu.oop.restservice.entities;
import jakarta.persistence.*;

@Entity
@Table(name = "patients")

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    public Patient() {}
    public Patient(String name, Integer age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public Integer getPatientID() {
        return id;
    }
    public void setPatientId(Integer id ) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }
    public void setAge(int age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
