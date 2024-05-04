import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoctorTest {
    private Doctor doctor;

    @BeforeEach
    void setUp() {
        doctor = new Doctor("Hamed");
    }

    @Test
    void testGetName() {
        assertEquals("Hamed", doctor.getName());
    }

    @Test
    void testGetServices() {
        Map<String, Double> expectedServices = new HashMap<>();
        assertEquals(expectedServices, doctor.getServices());

        doctor.addService("Laser Treatments", 100.0);
        expectedServices.put("Laser Treatments", 100.0);
        assertEquals(expectedServices, doctor.getServices());
    }

    @Test
    void testGetAvailableDates() {
        List<Date> expectedDates = new ArrayList<>();
        assertEquals(expectedDates, doctor.getAvailableDates());

        Date date1 = new Date();
        doctor.addAvailableDate(date1);
        expectedDates.add(date1);
        assertEquals(expectedDates, doctor.getAvailableDates());
    }

    @Test
    void testGetAvailableTimes() {
        Map<Date, List<String>> expectedTimes = new HashMap<>();
        assertEquals(expectedTimes, doctor.getAvailableTimes());

        Date date1 = new Date();
        doctor.addAvailableTime(date1, "10:00 AM");
        List<String> times1 = new ArrayList<>();
        times1.add("10:00 AM");
        expectedTimes.put(date1, times1);
        assertEquals(expectedTimes, doctor.getAvailableTimes());

        Date date2 = new Date();
        doctor.addAvailableTime(date2, "2:00 PM");
        List<String> times2 = new ArrayList<>();
        times2.add("2:00 PM");
        expectedTimes.put(date2, times2);
        assertEquals(expectedTimes, doctor.getAvailableTimes());
    }
}