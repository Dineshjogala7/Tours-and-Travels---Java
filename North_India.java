package Travels;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.StringTokenizer;
import Billing.bill; // Importing the bill package
class InvalidDateException extends Exception {
        InvalidDateException(String msg) {
            System.out.println("Error: " + msg);
        }
    }


public class North_India {
    Scanner sc = new Scanner(System.in);

    // Method to display and select tour packages
    public int[] pack() {
        System.out.println("Choose the Available Packages we Provide:");
        System.out.println("1--- Premium-Class ---\nA. Hotels: Vivanta - 5 Star Hotels\nB. Transport: Flight & Cars\nC. 1-Person Cost per Day: 2500");
        System.out.println("2--- First-Class ---\nA. Hotels: Family Resorts\nB. Transport: Trains (AC) & Cars\nC. 1-Person Cost per Day: 1500");
        System.out.println("3--- Second-Class ---\nA. Hotels: A.C Lounges\nB. Transport: Trains (NON-AC)\nC. 1-Person Cost per Day: 1000");

        int choice = sc.nextInt();
        int amount = 0;
        switch (choice) {
            case 1:
                amount = 2500;
                break;
            case 2:
                amount = 1500;
                break;
            case 3:
                amount = 1000;
                break;
            default:
                System.out.println("Entered an Invalid Option");
                break;
        }

        System.out.println("Do you want a Tour Guide? (Additional Charge: 3000) Enter 'yes' or 'no':");
        sc.nextLine(); // Consume the leftover newline
        String ans = sc.nextLine();
        System.out.println("Enter the number of Persons:");
        int persons = sc.nextInt();

        int guideCharge = ans.equalsIgnoreCase("yes") ? 3000 : 0;
        return new int[]{amount, persons, amount * persons, guideCharge};
    }

    // Method for Agra tour
    public void Agra(String arrival, String stDate, String endDate,int journeyDays,String email) {
        int[] arr = pack();
        bill b = new bill();
        b.bill_calc(arr[0], arr[1], arrival, "Agra", stDate, endDate, arr[3],journeyDays,email);
        b.Write_bill(arr[0], arr[1], arrival, "Agra", stDate, endDate, arr[3],journeyDays,email);
    }

    // Method for Varanasi tour
    public void Varanasi(String arrival, String stDate, String endDate,int journeyDays,String email) {
        int[] arr = pack();
        bill b = new bill();
        b.bill_calc(arr[0], arr[1], arrival, "Varanasi", stDate, endDate, arr[3],journeyDays,email);
        b.Write_bill(arr[0], arr[1], arrival, "Varanasi", stDate, endDate, arr[3],journeyDays,email);
    }

    // Method for Manali tour
    public void Manali(String arrival, String stDate, String endDate,int journeyDays,String email) {
        int[] arr = pack();
        bill b = new bill();
        b.bill_calc(arr[0], arr[1], arrival, "Manali", stDate, endDate, arr[3],journeyDays,email);
        b.Write_bill(arr[0], arr[1], arrival, "Manali", stDate, endDate, arr[3],journeyDays,email);
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
	String email=args[0];
        North_India tour = new North_India();

        try {
            // Get current date details
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();
            int currentMonth = currentDate.getMonthValue();
            int currentDay = currentDate.getDayOfMonth();

            // Input start date
            System.out.println("Enter the Start Date (yyyy/mm/dd):");
            String stDate = sc.nextLine();
            StringTokenizer stDateTokens = new StringTokenizer(stDate, "/");
            int year = Integer.parseInt(stDateTokens.nextToken());
            int month = Integer.parseInt(stDateTokens.nextToken());
            int day = Integer.parseInt(stDateTokens.nextToken());

            // Validate the start date
            if (year < currentYear || (year == currentYear && month < currentMonth) || (year == currentYear && month == currentMonth && day < currentDay)) {
                throw new InvalidDateException("Start Date should not be earlier than today's date.");
            }

            // Input end date
            System.out.println("Enter the End Date (yyyy/mm/dd):");
            String endDate = sc.nextLine();
            StringTokenizer endDateTokens = new StringTokenizer(endDate, "/");
            int endYear = Integer.parseInt(endDateTokens.nextToken());
            int endMonth = Integer.parseInt(endDateTokens.nextToken());
            int endDay = Integer.parseInt(endDateTokens.nextToken());

            // Calculate the number of days between the two dates
            LocalDate start = LocalDate.of(year, month, day);
            LocalDate end = LocalDate.of(endYear, endMonth, endDay);
            int journeyDays = (int) ChronoUnit.DAYS.between(start, end);
    

            // Select tourist place
            System.out.println("------ Tourist Places ------");
            System.out.println("1. Agra-Delhi\n2. Varanasi\n3. Manali");
            int choice = sc.nextInt();

            // Input boarding point
            sc.nextLine(); // Consume newline left by nextInt()
            System.out.println("Enter your Boarding Point:");
            String arrival = sc.nextLine();

            // Switch case for different tours
            switch (choice) {
                case 1:
                    tour.Agra(arrival, stDate, endDate,journeyDays,email);
                    break;
                case 2:
                    tour.Varanasi(arrival, stDate, endDate,journeyDays,email);
                    break;
                case 3:
                    tour.Manali(arrival, stDate, endDate,journeyDays,email);
                    break;
                default:
                    System.out.println("Invalid Option!");
                    break;
            }
        } catch (InvalidDateException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
