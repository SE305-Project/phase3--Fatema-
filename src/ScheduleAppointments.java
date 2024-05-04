//USE CASE 1 ( SCHEDULE AN APPOINTMENTS )

//importing several required classes and packages:
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap; //A HashMap, store items in "key/value" pairs, and let  us can access them by an index of another type.
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate; //Represents a date (year, month, day (yyyy-MM-dd))


//FOR USE CASE 1 ( SCHEDULE AN APPOINTMENT ).

//Doctor class to represent a doctor
class Doctor {
    private String name;
    private Map<String, Double> services;
    private Map<Date, List<String>> availableTimes; // Store available times for each date


    public Doctor(String name) {
        this.name = name;
        this.services = new HashMap<>();
        this.availableDates = new ArrayList<>();
        this.availableTimes = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public Map<String, Double> getServices() {
        return services;
    }

    // methods to add services, available dates, and available times.

    public void addService(String serviceName, double price) {
        services.put(serviceName, price);
    }

    private List<Date> availableDates;
    //private List<String> availableTimes;

    public List<Date> getAvailableDates() {

        return availableDates;
    }

    public Map<Date, List<String>> getAvailableTimes() {
        return availableTimes;
    }

    public void addAvailableDate(Date date) {
        availableDates.add(date);
    }

    public void addAvailableTime(Date date, String time ) {
        List<String> times = availableTimes.getOrDefault(date, new ArrayList<>());
        times.add(time);
        availableTimes.put(date, times);
    }



}

// the main class
public class ScheduleAppointments {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Doctor doctor = new Doctor("Dr. ");

        //printing a greeting message to the user.
        System.out.println("Hello ");

        // Prompt the doctor to select an action
        System.out.println("Select an action:");
        System.out.println("1. Schedule an appointment");
        System.out.println("2. View patient profile");

        int action = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (action) {
            case 1:
                // Schedule an appointment


                // Define available doctors and their services
                //It creates instances of Doctor representing different doctors and their services. Each doctor object is initialized with a name and a set of services.

                Doctor doctor1 = new Doctor("Dr.Mohammed Rashid");
                doctor1.addService("Teeth Cleaning", 10);
                doctor1.addService("Teeth Whitening", 30);

                Doctor doctor2 = new Doctor("Dr.Salwa Nasser");
                doctor2.addService("Teeth Cavity", 15);
                doctor2.addService("Dental & Cosmetic Fillings",25);

                Doctor doctor3 = new Doctor("Dr.Ali Hassan");
                doctor3.addService("Orthodontecs (per month)",30);

                Doctor doctor4 = new Doctor("Dr.Sara Redha");
                doctor4.addService("Veneer (per tooth)",105);


                Doctor doctor5 = new Doctor("Dr.Salman Abdulla");
                doctor5.addService("Tooth Polishing",15);
                doctor5.addService("Gum Depigmentation", 95);

                Doctor doctor6 = new Doctor("Dr.Maryam Ahmed");
                doctor6.addService("Root Canal Treatments ", 70);

                // list of available doctors

                List<Doctor> availableDoctors = new ArrayList<>();
                availableDoctors.add(doctor1);
                availableDoctors.add(doctor2);
                availableDoctors.add(doctor3);
                availableDoctors.add(doctor4);
                availableDoctors.add(doctor5);
                availableDoctors.add(doctor6);



                // Add available dates for each doctor
                // Add available dates from today up to the next 7 days
                LocalDate currentDate = LocalDate.now();
                for (int i = 0; i < 7; i++) {
                    Date date = java.sql.Date.valueOf(currentDate.plusDays(i));
                    doctor1.addAvailableDate(date);
                    doctor2.addAvailableDate(date);
                    doctor3.addAvailableDate(date);
                    doctor4.addAvailableDate(date);
                    doctor5.addAvailableDate(date);
                    doctor6.addAvailableDate(date);
                }

                // Add available times for each doctor on the available dates

                for (int i = 0; i < 7; i++) {
                    Date date = java.sql.Date.valueOf(currentDate.plusDays(i));

                    // Doctor 1
                    doctor1.addAvailableTime(date, "10:00 AM");
                    doctor1.addAvailableTime(date, "2:00 PM");
                    doctor1.addAvailableTime(date, "4:00 PM");

                    // Doctor 2
                    doctor2.addAvailableTime(date, "9:00 AM");
                    doctor2.addAvailableTime(date, "1:00 PM");
                    doctor2.addAvailableTime(date, "4:30 PM");

                    // Doctor 3
                    doctor3.addAvailableTime(date, "11:00 AM");
                    doctor3.addAvailableTime(date, "3:00 PM");
                    doctor3.addAvailableTime(date, "5:00 PM");

                    // Doctor 4
                    doctor4.addAvailableTime(date, "8:00 AM");
                    doctor4.addAvailableTime(date, "12:00 PM");
                    doctor4.addAvailableTime(date, "5:30 PM");
                    // Doctor 5
                    doctor5.addAvailableTime(date, "10:30 AM");
                    doctor5.addAvailableTime(date, "2:30 PM");
                    doctor5.addAvailableTime(date, "6:00 PM");

                    // Doctor 6
                    doctor6.addAvailableTime(date, "11:30 AM");
                    doctor6.addAvailableTime(date, "3:30 PM");
                    doctor6.addAvailableTime(date, "6:30 PM");
                }

                // Get user input for the desired service
                //let the user choose the service, by displaying the available services provided by each doctor
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Our Services:");
                int serviceIndex = 1;
                for (Doctor availableDoctor : availableDoctors) {
                    Map<String, Double> services = availableDoctor.getServices();
                    for (String service : services.keySet()) {
                        double price = services.get(service);
                        System.out.println(serviceIndex + ". " + service + " -  " + availableDoctor.getName() );
                        serviceIndex++;
                    }
                }

                System.out.println(" ");
                System.out.print("Enter the number corresponding to the service you want: ");
                int selectedServiceIndex = scanner.nextInt();

                // Validate service index
                if (selectedServiceIndex < 1 || selectedServiceIndex >= serviceIndex) {
                    System.out.println("Invalid service selection!");
                    scanner.close();
                    return;
                }

                // Find the doctor and service based on the selected index
                int doctorIndex = 0;
                int serviceCount = 0;
                while (serviceCount < selectedServiceIndex) {
                    Map<String, Double> services = availableDoctors.get(doctorIndex).getServices();
                    serviceCount += services.size();
                    doctorIndex++;
                }

                doctorIndex--; // Adjust the index to the correct doctor
                Map<String, Double> selectedServices = availableDoctors.get(doctorIndex).getServices();
                String selectedService = "";
                double selectedPrice = 0.0;
                for (Map.Entry<String, Double> entry : selectedServices.entrySet()) {
                    if (selectedServiceIndex > (serviceCount - selectedServices.size())) {
                        selectedService = entry.getKey();
                        selectedPrice = entry.getValue();
                        break;
                    }
                    serviceCount--;
                }

// Display the cost and selected doctor
                Doctor selectedDoctor = availableDoctors.get(doctorIndex);

                System.out.println("Cost of " + selectedService + ": " + selectedPrice + " BD " );
                System.out.println("Selected Doctor: " + selectedDoctor.getName());

// Prompt the user to select the date and time

// Display available dates
                System.out.println("Available Dates:");
                List<Date> availableDates = selectedDoctor.getAvailableDates();
                for (int i = 0; i < availableDates.size(); i++) {
                    System.out.println((i + 1) + ". " + availableDates.get(i));
                }

                System.out.print("Enter the number corresponding to the date you prefer: ");
                int selectedDateIndex = scanner.nextInt();

// Validate date index
                if (selectedDateIndex < 1 || selectedDateIndex > availableDates.size()) {
                    System.out.println("Invalid date selection!");
                    scanner.close();
                    return;
                }


// Retrieve the selected date
                Date selectedDate = availableDates.get(selectedDateIndex - 1);

// Retrieve the available times for the selected date
                List<String> availableTimes = selectedDoctor.getAvailableTimes().get(selectedDate);

// Display available times for the selected date
                System.out.println("Available Times:");
                for (int i = 0; i < availableTimes.size(); i++) {
                    System.out.println((i + 1) + ". " + availableTimes.get(i));
                }

//The user selects a time by entering the corresponding number
                System.out.print("Enter the number corresponding to the time you prefer: ");
                int selectedTimeIndex = scanner.nextInt();

// Validate time index and retrieve the selected time
                if (selectedTimeIndex < 1 || selectedTimeIndex > availableTimes.size()) {
                    System.out.println("Invalid time selection!");
                    scanner.close();
                    return;
                }

                String selectedTime = availableTimes.get(selectedTimeIndex - 1);


//Summary for the appointment
// Scheduled an appointment
// take the user information
                System.out.println("---------------------");
                System.out.println("Enter your name: ");
                scanner.nextLine(); // Consume the remaining newline character
                String patientName = scanner.nextLine();
                System.out.println("Enter CPR: ");
                String cpr = scanner.nextLine();
                System.out.println("Enter your phone number: ");
                String PH_NUM = scanner.nextLine();

                //displays a success message indicating that the appointment has been booked
                System.out.println("You have successfully booked your appointment. ");
                System.out.println("(You can contact us in case you want change your appointment or cancel it)");

                break;

            case 2:
                // writting prescription
                // in CreatePrescription file (USE-CASE2)
                //.....
                break;

            default:
                System.out.println("Invalid action.");
                break;


        }
        scanner.close();
    }
}




//Fatema Alawi-202005764
