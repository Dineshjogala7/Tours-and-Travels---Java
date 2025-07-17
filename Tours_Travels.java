import java.util.Scanner;
import java.util.regex.*;
import java.io.*;

import Travels.North_India;
import Travels.South_India;

class Tours {
    Scanner sc = new Scanner(System.in);
    public void fetch_data(String email){
	String fp="UserDetails.txt";
	try(BufferedReader br=new BufferedReader(new FileReader(fp))){
		String line;
		int cnt=0;
		while ((line = br.readLine()) != null) {
    String[] words = line.split("--");
    if (words[0].equalsIgnoreCase(email)) {
        cnt += 1;
        
        System.out.println("\t\t\t\t=====================================================");
        System.out.printf("\t\t\t\t                   Tour #%d\n", cnt);
        System.out.println("\t\t\t\t=====================================================");
        System.out.printf("\t\t\t\t   Email                  : %s\n", words[0]);
        System.out.printf("\t\t\t\t   Tourist Place          : %s\n", words[1]);
        System.out.printf("\t\t\t\t   Starting Date          : %s\n", words[2]);
        System.out.printf("\t\t\t\t   Ending Date            : %s\n", words[3]);
        System.out.printf("\t\t\t\t   Boarding Point         : %s\n", words[4]);
        System.out.printf("\t\t\t\t   Cost per Person/Day    : %s\n", words[5]);
        System.out.printf("\t\t\t\t   Number of Persons      : %s\n", words[6]);
        System.out.println("\t\t\t\t-----------------------------------------------------");
        System.out.printf("\t\t\t\t   Total per Person       : %s\n", words[7]);
        System.out.printf("\t\t\t\t   Guide                  : %s\n", words[8]);
        System.out.printf("\t\t\t\t   Guide Cost             : %s\n", words[9]);
        System.out.println("\t\t\t\t-----------------------------------------------------");
        System.out.printf("\t\t\t\t   Total Cost             : %s\n", words[10]);
        System.out.println("\t\t\t\t=====================================================\n\n");
    }
}



		if(cnt==0){
			System.out.println("NO DATA FOUND \n\n");
		}
	}
	catch(IOException e){
		System.out.println(e.getMessage());
	}
}	
			

    public void avail_Tours( String email) {

System.out.printf("%30s%s\n", "", "-----------------------------------------\n");
System.out.printf("%30s%s\n", "", "|                                       |");
System.out.printf("%30s%s\n", "", "|         ** Available Tours **         |");
System.out.printf("%30s%s\n", "", "|                                       |");
System.out.printf("%30s%s\n", "", "-----------------------------------------");


	String mail[]={email};
	int choice=0;
	while(choice!=4){
		System.out.printf("%30s%-15s %-15s %-15s %-15s\n", "", "1. North-India", "2. South-India", "3. MY Bookings", "4. EXIT");


		choice = sc.nextInt();
		if(choice==1){
			System.out.println("North-India Tour Selected..");
                	North_India northIndiaTour = new North_India();                	
			northIndiaTour.main(mail);
		}
		else if(choice==2){
			System.out.println(" Selected  South-India Tour..");
			South_India south_IndiaTour=new South_India();
                	south_IndiaTour.main(mail);
		}
		else if(choice==3){
			fetch_data(email);
			
		}
		else if(choice==4){
			System.out.println("Exiting the Program.....");
			System.exit(0);
		}
		else{
			System.out.println("Enter a valid choice  (1 to 4)");
		}
	}
			
        /*switch (choice) {
            case 1:
                System.out.println("North-India Tour Selected");
                North_India northIndiaTour = new North_India(); // Create an instance of the North_India class
                northIndiaTour.main(mail); // Call the main method of North_India to start the tour options
                break;
            case 2:
                System.out.println("South-India");
		South_India south_IndiaTour=new South_India();
                south_IndiaTour.main(mail);
                break;
	    case 3:
		fetch_data(email);
		break;
	    case 4:
		System.exit(0);
            default:
                System.out.println("Invalid Option");
                break;
        }*/
    }
}

public class Tours_Travels {
    static Scanner sc = new Scanner(System.in);

    // Email validation using regex
    public static boolean validate(String email) {
        String regex = "^[a-zA-Z0-9._+%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Check if the email already exists in the file
    public static boolean checkMatch(String email) {
    String filePath = "emails.txt";
    System.out.println("Checking email in file: " + filePath);
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] words = line.split(":");
            if (words[0].equalsIgnoreCase(email)) {
                return true;
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("File not found: " + e.getMessage());
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
    return false;
}


    // Password validation (at least 6 characters, uppercase, lowercase, digit, special character)
    public static boolean validatepass(String pass) {
        int capscnt = 0, smallcnt = 0, numcnt = 0, specialcnt = 0;
        if (pass.length() < 6) {
            return false;
        }
        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)) {
                capscnt++;
            } else if (Character.isLowerCase(c)) {
                smallcnt++;
            } else if (Character.isDigit(c)) {
                numcnt++;
            } else if ((c >= 33 && c <= 54) || c == 95 ||c==64) { // Special chars
                specialcnt++;
            }
        }
        return capscnt >= 1 && smallcnt >= 1 && numcnt >= 1 && specialcnt >= 1;
    }

    // Check if email and password match
    public static boolean checkmail(String email, String pass) {
        String filePath = "emails.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(":");
                if (words[0].equalsIgnoreCase(email) && words[1].equals(pass)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    // User signup method
    public static void signup(Tours tr) {
        System.out.println("Enter the registered email:");
        String email = sc.next();
        System.out.println("Enter your password:");
        String pass = sc.next();

        if (!checkmail(email, pass)) {
            System.out.println("Please provide registered email/password!!");
        } else {
            System.out.println("Login Successful!!");
            tr.avail_Tours(email);
	    
        }
    }

    // User registration method
    public static void register(Tours tr) {
        System.out.println("Enter a valid email address:");
        String email = sc.next();
        while (!validate(email)) {
            System.out.println("Invalid Email..! provide  valid email");
            email=sc.nextLine();
        }
        
            if (checkMatch(email)) {
                System.out.println("Email Already Exists!");
            } else {
                System.out.println("Set up a new password (It should contain caps, symbols, nums, and more than 6 characters):");
                sc.nextLine(); // Clear buffer
                String pass = sc.nextLine();
                while(!validatepass(pass)){
                    System.out.println("Provide a Strong Password");
                    pass = sc.nextLine();
                }

                    try (FileWriter fw = new FileWriter("emails.txt", true)) {
                        fw.write(email + ":" + pass + "\n");
                        System.out.println("Registration Successful!");
                        tr.avail_Tours(email);
                    } catch (IOException e) {
                        System.out.println("Error Occurred: " + e.getMessage());
                        
                    }
                
            }
        
        
    }

    public static void main(String[] args) {
        Tours tr = new Tours();  // Create an instance of the Tours class
       System.out.printf("%30s%s\n", "", "============================================================================");
System.out.printf("%30s%s\n", "", "|                                                                          |");
System.out.printf("%30s%s\n", "", "|                             WE Tours & Travels                           |");
System.out.printf("%30s%s\n", "", "|                                                                          |");
System.out.printf("%30s%s\n", "", "============================================================================");

        while (true) {
            System.out.printf("%34s%-25s %-25s\n", "", "1. Sign Up (New User)", "2. Login (Existing User)");

            int ch=sc.nextInt();
            switch (ch) {
                case 1:
                    register(tr);
                    break;
                case 2:
                    signup(tr);
                    break;
                default:
		    System.out.println("enter a valid choice(1 or 2)");
                    break;
            }
        }
        
    }
}
