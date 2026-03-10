/**
 * Name: Chloe Barnes
 * Date: 2/27/2026
 * Course: COMP 167
 * Assignment: Major Program 1
 * Description: This class acts as the primary controller for a medical practice.
 * It manages collections of physicians and patients, providing functionality
 * for data persistence through file I/O (reading and writing .txt files).
 * It also includes custom sorting algorithms to organize medical records
 * alphabetically by last name and first name.
 */

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MedicalOffice {
    // Data storage for the office name, list of doctors, and list of patients
    private String practiceName;
    private ArrayList<String> physicians;
    private ArrayList<Patient> patients;

    // Sets up a new, empty medical office
    public MedicalOffice() {
        this.physicians = new ArrayList<>();
        this.patients = new ArrayList<>();
    }
    public MedicalOffice(String practiceName) {
        this.practiceName = practiceName;
        physicians = new ArrayList<>();
        patients = new ArrayList<>();
    }


    // Physician management
    public int getPhysicianSize() { return physicians.size(); }
    public String getPhysician(int index) { return physicians.get(index); }
    public void setPhysician(int index, String name) { physicians.set(index, name); }
    public void addPhysician(String name) { physicians.add(name); }
    public String removePhysician(int index) { return physicians.remove(index); }

    // Patient management
    public int getPatientSize() { return patients.size(); }
    public Patient getPatient(int index) { return patients.get(index); }
    public void setPatient(int index, Patient p) { patients.set(index, p); }
    public void addPatient(Patient p) { patients.add(p); }
    public Patient removePatient(int index) { return patients.remove(index); }

    // Opens a file and loads all the office, doctor, and patient data into the program
    public void readMedicalOfficeData(String inFileName) {
        try {
            Scanner input = new Scanner(new File(inFileName));

            practiceName = input.nextLine();

            // Read Physicians
            int numPhysicians = Integer.parseInt(input.nextLine());
            for (int i = 0; i < numPhysicians; i++) {
                String physician = input.nextLine();
                physicians.add(physician);
            }

            // Read Patients
            int numPatients = Integer.parseInt(input.nextLine());
            for (int i = 0; i < numPatients; i++) {
                // Read patient demographic data
                int patientID = Integer.parseInt(input.nextLine());
                String firstName = input.nextLine();
                String lastName = input.nextLine();
                char gender = input.nextLine().charAt(0);
                String dob = input.nextLine();
                String primaryPhysician = input.nextLine();

                Patient patient = new Patient(patientID, firstName, lastName, gender, dob, primaryPhysician);

                // Read appointments for patient
                int numAppointments = Integer.parseInt(input.nextLine());
                for (int j = 0; j < numAppointments; j++) {
                    String apptLine = input.nextLine();
                    String[] parts = apptLine.split("\\*");

                    // Parse appointment data
                    int apptPatientID = Integer.parseInt(parts[0]);
                    String apptDate = parts[1];
                    String physician = parts[2];
                    double height = Double.parseDouble(parts[3]);
                    double weight = Double.parseDouble(parts[4]);
                    double temperature = Double.parseDouble(parts[5]);
                    int pulse = Integer.parseInt(parts[6]);
                    int bpSys = Integer.parseInt(parts[7]);
                    int bpDia = Integer.parseInt(parts[8]);
                    String notes = parts[9];

                    // Create and add appointment
                    Appointment appt = new Appointment(apptPatientID, apptDate, physician,
                            height, weight, temperature,
                            pulse, bpSys, bpDia, notes);
                    patient.addAppointment(appt);
                }
                patients.add(patient);
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found - " + inFileName);
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            System.exit(-1);
        }
    }

    // save data to file (Physicians, Patients)
    public void saveMedicalOfficeData(String fileName) {
        try {
            PrintWriter output = new PrintWriter(fileName);

            output.println(practiceName);

            output.println(physicians.size());
            for (String physician : physicians) {
                output.println(physician);
            }

            output.println(patients.size());
            for (Patient patient : patients) {
                output.println(patient.getPatientID());
                output.println(patient.getFirstName());
                output.println(patient.getLastName());
                output.println(patient.getGender());
                output.println(patient.getDateOfBirth());
                output.println(patient.getPrimaryPhysician());

                output.println(patient.getAppointmentSize());
                for (int i = 0; i < patient.getAppointmentSize(); i++) {
                    Appointment appt = patient.getAppointment(i);
                    output.println(appt.toString());
                }
            }

            output.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error: Cannot create file - " + fileName);
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
            System.exit(-1);
        }
    }

    //sorts physicians by name
    private void sortPhysicians() {
        for (int i = 0; i < physicians.size() - 1; i++) {

            for (int j = i + 1; j < physicians.size(); j++) {

                String p1 = physicians.get(i);
                String p2 = physicians.get(j);

                String[] name1 = p1.split(",");
                String[] name2 = p2.split(",");

                String last1 = name1[0].trim();
                String first1 = name1[1].trim();

                String last2 = name2[0].trim();
                String first2 = name2[1].trim();

                int lastCompare = last1.compareToIgnoreCase(last2);

                boolean shouldSwap = false;

                if (lastCompare > 0) {
                    shouldSwap = true;
                }

                else if (lastCompare == 0) {
                    if (first1.compareToIgnoreCase(first2) > 0) {
                        shouldSwap = true;
                    }
                }

                if (shouldSwap) {
                    physicians.set(i, p2);
                    physicians.set(j, p1);
                }
            }
        }
    }

    //sort Patients by name
    private void sortPatients() {
        // Loop through each patient
        for (int i = 0; i < patients.size() - 1; i++) {

            // Compare with remaining patients
            for (int j = i + 1; j < patients.size(); j++) {

                // Get the two patients to compare
                Patient p1 = patients.get(i);
                Patient p2 = patients.get(j);

                // Compare last names first (case-insensitive)
                int lastCompare = p1.getLastName().compareToIgnoreCase(p2.getLastName());

                boolean shouldSwap = false;

                // If last name of p1 comes after p2, swap
                if (lastCompare > 0) {
                    shouldSwap = true;
                }
                // If last names are equal, compare first names
                else if (lastCompare == 0) {
                    if (p1.getFirstName().compareToIgnoreCase(p2.getFirstName()) > 0) {
                        shouldSwap = true;
                    }
                }

                // Swap patients if needed
                if (shouldSwap) {
                    patients.set(i, p2);
                    patients.set(j, p1);
                }
            }
        }
    }

    //runs sorted data
    public void sortData() {
        sortPhysicians();
        sortPatients();
    }

    // Convert data to string
    @Override
    public String toString() {
        String result = "";

        result += "Practice Name: " + practiceName + "\n";
        result += "Physicians:\n";
        for (String physician : physicians) {
            result += "  " + physician + "\n";
        }

        result += "Patients:\n";
        for (Patient patient : patients) {
            result += patient.toString() + "\n";
        }

        return result;
    }
}