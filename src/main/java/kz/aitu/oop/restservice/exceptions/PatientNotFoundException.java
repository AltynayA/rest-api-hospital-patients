package kz.aitu.oop.restservice.exceptions;

public class PatientNotFoundException  extends RuntimeException{
    public PatientNotFoundException(String message) {
        super(message);
    }
}
