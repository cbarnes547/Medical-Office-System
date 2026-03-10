/**
 * Name: Chloe Barnes
 * Date: 2/27/2026
 * Course: COMP 167
 * Assignment: Major Program 1
 * Description: This class defines an Appointment object used to manage patient
 * clinic visits. It encapsulates patient identification, appointment metadata,
 * and clinical vitals (height, weight, BP, etc.). It includes a specialized
 * toString() method that uses an asterisk delimiter for easy data
 * serialization and storage.
 */

 import java.util.ArrayList;

public class Appointment {
    // Attributes to store details about a specific clinic visit
    private int patientID;
    private String apptDate;
    private String physician;
    private double height;
    private double weight;
    private double temperature;
    private int pulse;
    private int bpSystolic;
    private int bpDiastolic;
    private String notes;

    // Default constructor: creates an empty appointment object
    public Appointment() {

    }

    // Constructor: fills in all the medical data for the appointment at once
    public Appointment(int patientID, String apptDate, String physician, double height, double weight, double temperature, int pulse, int bpSystolic, int bpDiastolic, String notes) {
        this.patientID = patientID;
        this.apptDate = apptDate;
        this.physician = physician;
        this.height = height;
        this.weight = weight;
        this.temperature = temperature;
        this.pulse = pulse;
        this.bpSystolic = bpSystolic;
        this.bpDiastolic = bpDiastolic;
        this.notes = notes;
    }

    // Returns attributes (getters)
    public int getPatientID() { return patientID; }
    public String getApptDate() { return apptDate; }
    public String getPhysician() { return physician; }
    public double getHeight() { return height; }
    public double getWeight() { return weight; }
    public double getTemperature() { return temperature; }
    public int getPulse() { return pulse; }
    public int getBpSystolic() { return bpSystolic; }
    public int getBpDiastolic() { return bpDiastolic; }
    public String getNotes() { return notes; }

    //Setters
    public void setPatientID(int patientID) { this.patientID = patientID; }
    public void setApptDate(String apptDate) { this.apptDate = apptDate; }
    public void setPhysician(String physician) { this.physician = physician; }
    public void setHeight(double height) { this.height = height; }
    public void setWeight(double weight) { this.weight = weight; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    public void setPulse(int pulse) { this.pulse = pulse; }
    public void setBpSystolic(int bpSystolic) { this.bpSystolic = bpSystolic; }
    public void setBpDiastolic(int bpDiastolic) { this.bpDiastolic = bpDiastolic; }
    public void setNotes(String notes) { this.notes = notes; }

    // Converts all the appointment details into one long string for display
    @Override
    public String toString() {
        return patientID + "*" +
                apptDate + "*" +
                physician + "*" +
                height + "*" +
                weight + "*" +
                temperature + "*" +
                pulse + "*" +
                bpSystolic + "*" +
                bpDiastolic + "*" +
                notes;
    }
}