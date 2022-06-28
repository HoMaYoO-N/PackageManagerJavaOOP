package cmpt213.assignment2.packagedeliveriestracker.textui;

import cmpt213.assignment2.packagedeliveriestracker.model.Package;
import cmpt213.assignment2.packagedeliveriestracker.model.PackageFactory;

import java.sql.Statement;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class UserInput {
    public static int OptionsInput(){
        Scanner console = new Scanner(System.in);
        int choice = console.nextInt();
        return choice;
    }

    public static Package NewPackageInput(int userPackageChoice){
        String objectName = null;
        if(userPackageChoice == 1)
            objectName = "book";
        else if (userPackageChoice == 2)
            objectName = "perishable";
        else if (userPackageChoice == 3)
            objectName = "electronic";
        System.out.println("Enter the name of the "+ objectName +": ");
        Scanner console = new Scanner(System.in);
        String packageName = console.nextLine();
        // package name cannot be empty!
        while (packageName == "")
        {
            System.out.println("Package name cannot be empty. Please try again!");
            packageName = console.nextLine();
        }
        System.out.println("Enter the notes of the "+ objectName  +": ");
        String packageNotes = console.nextLine();

        System.out.println("Enter the price of the " + objectName + "(in dollar): ");
        double packagePrice = console.nextDouble();

        System.out.println("Enter the weight of the " + objectName + "(in kg): ");
        double packageWeight = console.nextDouble();

        boolean inputIsInvalid = true;
        LocalDateTime packageDateTime = null;
        while (inputIsInvalid) {
            try {
                System.out.println("Enter the year of the expected delivery date: ");
                int packageYear = console.nextInt();

                System.out.println("Enter the month of the expected delivery date (1-12): ");
                int packageMonth = console.nextInt();

                System.out.println("Enter the day of the expected delivery date (1-28/29/30/31): ");
                int packageDay = console.nextInt();

                System.out.println("Enter the hour of the expected delivery date (0-23): ");
                int packageHour = console.nextInt();

                System.out.println("Enter the minute of the expected delivery date (0-59): ");
                int packageMinute = console.nextInt();
                packageDateTime = LocalDateTime.of(packageYear, packageMonth, packageDay, packageHour, packageMinute);
                inputIsInvalid = false;

            } catch (DateTimeException e) {
                System.out.println("Error: this date does not exist.");
            }
        }
        if(userPackageChoice == 1)
        {
            System.out.println("Enter the author of the book: ");
            console.nextLine();
            String bookAuthor = console.nextLine();
            System.out.println(packageName +" has been added to the list." + "\n");
            return PackageFactory.getInstance("Book",packageName,packageNotes,packagePrice,packageWeight, packageDateTime,bookAuthor,null,0);
        }
        else if(userPackageChoice == 2)
        {
            boolean choiceIsInvalid = true;
            LocalDateTime expiryDateTime = null;
            while (choiceIsInvalid) {
                try {
                    System.out.println("Enter the year of the expiry date: ");
                    int expiryYear = console.nextInt();

                    System.out.println("Enter the month of the expiry date (1-12): ");
                    int expiryMonth = console.nextInt();

                    System.out.println("Enter the day of the expiry date (1-28/29/30/31): ");
                    int expiryDay = console.nextInt();

                    System.out.println("Enter the hour of the expiry date (0-23): ");
                    int expiryHour = console.nextInt();

                    System.out.println("Enter the minute of the expiry date (0-59): ");
                    int expiryMinute = console.nextInt();
                    expiryDateTime = LocalDateTime.of(expiryYear, expiryMonth, expiryDay, expiryHour, expiryMinute);
                    choiceIsInvalid = false;

                } catch (DateTimeException e) {
                    System.out.println("Error: this date does not exist.");
                }
            }
            System.out.println(packageName +" has been added to the list." + "\n");
            return PackageFactory.getInstance("Perishable",packageName,packageNotes,packagePrice,packageWeight, packageDateTime,null,expiryDateTime,0);
        }
        else if(userPackageChoice == 3)
        {
            System.out.println("Enter the environmental handling fee (in dollar): ");
            double environmentalHandlingFee = console.nextDouble();
            System.out.println(packageName +" has been added to the list." + "\n");
            return PackageFactory.getInstance("Electronic",packageName,packageNotes,packagePrice,packageWeight, packageDateTime,null,null,environmentalHandlingFee);
        }

        return null;
    }
    public static int SelectUserInputPackageInList(List<Package> packageList){
        Scanner console = new Scanner(System.in);
        int packageNumber = console.nextInt();
        console.nextLine();
        if (packageNumber == 0) {
            System.out.println("Back to main menu." + "\n");
            return 0;
        }
        while(packageNumber > packageList.size() || packageNumber < 0) {
            System.out.println("Invalid package number. Please try again!");
            packageNumber = console.nextInt();
        }
        return packageNumber;
    }
}

