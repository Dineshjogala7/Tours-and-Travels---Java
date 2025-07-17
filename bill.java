package Billing;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class bill {
    
    // Method to calculate and display the bill
  public void bill_calc(int base, int persons, String arrival, String departure, String st_date, String end_date, int guide, int journeyDays, String email) {
    // Width for the box
    int boxWidth = 45;

    // Centered Header
    System.out.printf("%" + boxWidth + "s%n", "-------------------------------------------");
    System.out.printf("%" + boxWidth + "s%n", "|             VRSEC-Tours && Travels     |");
    System.out.printf("%" + boxWidth + "s%n", "-------------------------------------------");

    // Centered Bill Details
    System.out.printf("| %-37s: %s%n", "Tourist Place", departure);
    System.out.printf("| %-37s: %s%n", "Start Date", st_date);
    System.out.printf("| %-37s: %s%n", "End Date", end_date);
    System.out.printf("| %-37s: %s%n", "Boarding Point", arrival);
    System.out.printf("| %-37s: %d%n", "Cost Per Person", base);
    System.out.printf("| %-37s: %d%n", "For " + persons + " Persons", base * persons);

    // Tour Guide Information
    System.out.printf("| %-37s: %s%n", "Tour Guide", guide > 0 ? "YES" : "NO");

    // Total Bill Calculation
    int totalBill = base * persons * journeyDays + guide;
    System.out.printf("| %-37s: %d%n", "Total Bill", totalBill);

    // Centered Footer
    System.out.printf("%" + boxWidth + "s%n", "-------------------------------------------");
}

    
    // Method to write bill details to a file
    public void Write_bill(int base, int persons, String arrival, String departure, String st_date, String end_date, int guide, int journeyDays, String email) {
        String gd = guide > 0 ? "YES" : "NO";

        
        try (BufferedWriter fp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("UserDetails.txt", true), StandardCharsets.UTF_8))) {
            int totalprice = (base * persons * journeyDays) + guide;
            int base_num = base * persons;
            fp.write(email + "--" + departure + "--" + st_date + "--" + end_date + "--" + arrival + "--" + base + "--" + persons + "--" + base_num + "--" + gd + "--" + guide + "--" + totalprice);
            fp.write(System.lineSeparator()); // Ensures a new line is added after each write
            // or you can use: fp.write("\n"); to explicitly add a newline character
            System.out.println("Bill details written successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to file: " + e.getMessage());
        }
    }
}
