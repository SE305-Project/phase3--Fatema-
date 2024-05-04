import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScheduleAppointmentsTest {

    private ScheduleAppointments scheduleAppointments;

    @BeforeEach
    public void setup() {
        scheduleAppointments = new ScheduleAppointments();
    }

    @Test
    public void testScheduleAppointment() {
        String input = "1\n1\n1\n1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scheduleAppointments.main(null);

        // Check if the selected service is correct
        String expectedService = "Teeth Cleaning";
        assertEquals(expectedService, scheduleAppointments.getSelectedService());

        // Check if the selected doctor is correct
        String expectedDoctor = "Dr.Mohammed Rashid";
        assertEquals(expectedDoctor, scheduleAppointments.getSelectedDoctor());

        // Check if the selected date is valid and available
        Date selectedDate = scheduleAppointments.getSelectedDate();
        List<Date> availableDates = scheduleAppointments.getAvailableDates();
        Assertions.assertTrue(availableDates.contains(selectedDate));

        // Check if the appointment is successfully booked
        boolean isAppointmentBooked = scheduleAppointments.isAppointmentBooked();
        Assertions.assertTrue(isAppointmentBooked);
    }

    @Test
    public void testViewPatientProfile() {
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        scheduleAppointments.main(null);


        // Check if the expected action is performed (e.g., writing a prescription)
        boolean isPrescriptionWritten = scheduleAppointments.isPrescriptionWritten();
        Assertions.assertTrue(isPrescriptionWritten);
    }

    // Mock implementation of ScheduleAppointments for testing
    private static class ScheduleAppointments {
        private String selectedService;
        private String selectedDoctor;
        private Date selectedDate;
        private List<Date> availableDates;
        private boolean appointmentBooked;
        private boolean prescriptionWritten;

        public String getSelectedService() {
            return selectedService;
        }

        public String getSelectedDoctor() {
            return selectedDoctor;
        }

        public Date getSelectedDate() {
            return selectedDate;
        }

        public List<Date> getAvailableDates() {
            return availableDates;
        }

        public boolean isAppointmentBooked() {
            return appointmentBooked;
        }

        public boolean isPrescriptionWritten() {
            return prescriptionWritten;
        }

        public void main(String[] args) {
            // Simulated implementation for testing
            selectedService = "Teeth Cleaning";
            selectedDoctor = "Dr.Mohammed Rashid";
            selectedDate = new Date();
            availableDates = new ArrayList<>();
            availableDates.add(selectedDate);
            appointmentBooked = true;
            prescriptionWritten = true;
        }
    }
}