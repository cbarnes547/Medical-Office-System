/**
 * Name: Chloe Barnes
 * Date: 2/27/2026
 * Course: COMP 167
 * Assignment: Major Program 1
 * Description: This class represents a patient within the medical system.
 * It stores demographic information such as ID, name, gender, and birthdate,
 * while maintaining an internal ArrayList of Appointment objects. This allows
 * for the management of a patient's complete clinical history within a
 * single object.
 */

import java.util.ArrayList;

public class Patient {
    // Attributes to store patient information
    private int patientID;
    private String firstName;
    private String lastName;
    private char gender;
    private  String dateOfBirth;
    private String primaryPhysician;
    private ArrayList<Appointment> appointments;

    // Default constructor: creates a new empty list of appointments
    public Patient() {
        appointments = new ArrayList<>();
    }
    // Constructor: sets up a patient with all their details
    public Patient(int patientID, String firstName, String lastName, char gender, String dateOfBirth, String primaryPhysician) {
        this.patientID = patientID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.primaryPhysician = primaryPhysician;
        this.appointments = new ArrayList<>();
    }

    //Getters and Setters
    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPrimaryPhysician() {
        return primaryPhysician;
    }

    public void setPrimaryPhysician(String primaryPhysician) {
        this.primaryPhysician = primaryPhysician;
    }

    // Appointment Management
    public int getAppointmentSize() {
        return appointments.size();
    }
    public ArrayList<Appointment> getAppointments() {

        return appointments;
    }
    public Appointment getAppointment(int index) {
        return appointments.get(index);
    }
    public void setAppointment(int index, Appointment appointment) {
        appointments.set(index, appointment);
    }
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
    public Appointment removeAppointment(int index) {
        return appointments.remove(index);
    }


    // Converts data and appointments into string
    @Override
    public String toString() {
        String result = "";

        result += patientID + "\n";
        result += firstName + "\n";
        result += lastName + "\n";
        result += gender + "\n";
        result += dateOfBirth + "\n";
        result += primaryPhysician + "\n";
        result += "Appointments:\n";

        for (Appointment appt : appointments) {
            result += appt.toString() + "\n";
        }

        return result;
    }
}
