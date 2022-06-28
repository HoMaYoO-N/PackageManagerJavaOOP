package cmpt213.assignment2.packagedeliveriestracker.textui;

import cmpt213.assignment2.packagedeliveriestracker.model.Package;
import cmpt213.assignment2.packagedeliveriestracker.model.TextMenu;

import java.time.LocalDate;
import java.util.List;

public class Display {
    static TextMenu textMenu = new TextMenu();
    /**
     * Show the default title with a line of hashtags one on top, one on bottom.
     * In next line, current date, then adds a number from 1 to 7 on left-hand side of textMenu options, and shows them one in a line.
     */
    public static void ShowTextMenu()
    {
        int titleSize = textMenu.getTitle().length();
        String hashtags = "";
        for(int i = 0; i <= titleSize + 3; i++)
            hashtags += '#';
        System.out.println(hashtags + '\n' +
                "# " + textMenu.getTitle() + " #" + '\n' +
                hashtags);
        System.out.println("Today is: " + LocalDate.now());
        for(int i = 0; i < textMenu.getOptions().size(); i++)
            System.out.println(i + 1 + ": " + textMenu.getOptions().get(i));
    }
    public static void AskUserForOptionSelection()
    {
        System.out.println("Choose an option by entering 1-7: ");
    }
    public static void UserInputNotValid()
    {
        System.out.println("Invalid selection. Enter a valid number");
    }
    public static void PackageListEmptyMessage()
    {
        System.out.println("There is no package in the list. Add a package first!" + "\n");
    }
    public static void RemovalQuestion()
    {
        System.out.println("Enter the item number you want to remove (0 to cancel): " + "\n");
    }
    public static void RemovalConfirmationMessage(List<Package> packageList,int removalNumber)
    {
        System.out.println(packageList.get(removalNumber).getName() + " has been removed from the list." + "\n");

    }
    public static void SetDeliveredQuestion()
    {
        System.out.println("Enter the item number you want to mark (0 to cancel): " + "\n");

    }
    public static void DeliveryConfirmationMessage(List<Package> packageList,int packageNumber)
    {
        System.out.println(packageList.get(packageNumber).getName() + " has been delivered." + "\n");
    }
    public static void ExitMessage()
    {
        System.out.println("Thank you for using the system.");
    }
    public static void NoPackageMessage()
    {
        System.out.println("No packages to show.");
    }
    public static void TypeOfPackageMessage()
    {
        System.out.println("Enter the type of the package (1:Book, 2:Perishable, 3:Electronic):");
    }
}



