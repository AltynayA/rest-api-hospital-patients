package kz.aitu.oop.restservice.entities;

public class Doctor extends Person {
    //private String name;
    private String specialty;
    private int yearsOfExperience;

    // default constructor
    public Doctor() {
        super("Unknown", 0);
        this.specialty = "General";
        this.yearsOfExperience = 0;
    }
    public Doctor(String name, int age, String specialty, int yearsOfExperience) {
        super(name,age);
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
    }
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println(getName() + " has " + yearsOfExperience + " years of experience in " + specialty);
    }
    //setters and getters
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getSpecialty() {
        return specialty;
    }
    //doctors' comparer method
    public boolean equals(Doctor other) {
        if (other.yearsOfExperience != yearsOfExperience) {
            return false;
        }
        if (!other.specialty.equals(specialty)) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
//        return getName() + " has " + yearsOfExperience + " years of experience" +" in " + specialty;
        return getName();
    }




}
