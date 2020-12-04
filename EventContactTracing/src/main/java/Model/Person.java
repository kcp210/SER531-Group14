package Model;

import java.time.LocalDate;
import java.time.Period;

public class Person {
    private String fName;
    private String lName;
    private int age;
    private String contact;
    private String medicalConditions;

    public Person (String fName, String lName, LocalDate dob, String contact, String medicalConditions) {
        this.fName = fName;
        this.lName = lName;
        this.age = Period.between(dob, LocalDate.now()).getYears();
        this.contact = contact;
        this.medicalConditions = medicalConditions;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public int getAge() {
        return age;
    }

    public String getContact() {
        return contact;
    }

    public String getMedicalConditions() {
        return medicalConditions;
    }
}
