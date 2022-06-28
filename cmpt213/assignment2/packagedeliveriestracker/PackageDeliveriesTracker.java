package cmpt213.assignment2.packagedeliveriestracker;

import cmpt213.assignment2.packagedeliveriestracker.model.Book;
import cmpt213.assignment2.packagedeliveriestracker.model.JSONHandler;
import cmpt213.assignment2.packagedeliveriestracker.model.OptionsHandler;
import cmpt213.assignment2.packagedeliveriestracker.model.Package;
import cmpt213.assignment2.packagedeliveriestracker.textui.Display;
import cmpt213.assignment2.packagedeliveriestracker.textui.UserInput;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * The main class which contains both the main entry of the program, also instantiates objects for the system to work.
 */
public class PackageDeliveriesTracker {
    //Create Arraylist of packages. I made it static, so I could use it in main function, as well as communicator class.
    private static List<Package> packageList;


    /**
     * The PSVM, main function. Holds the arraylist of packages, initiates readin/writing to json ,and passes to Communicator class to do operations (1 to 6) on that list.
     * @param args is whatever the user inputs in the beginning.
     */
    public static void main(String[] args)
    {
        File theFile = new File("list.json");
        if(theFile.exists())
            packageList = JSONHandler.readFromJSON(theFile);
        else
            packageList = new ArrayList<>();
        while (true) {
            Display.ShowTextMenu();
            Display.AskUserForOptionSelection();
            int userOptionChoice = UserInput.OptionsInput();
            while (userOptionChoice > 7 || userOptionChoice < 1) {
                Display.UserInputNotValid();
                userOptionChoice = UserInput.OptionsInput();
            }
            if(userOptionChoice == 1)
                OptionsHandler.ListAllPackages(packageList);
            if(userOptionChoice == 2) {
                Display.TypeOfPackageMessage();
                int userPackageChoice = UserInput.OptionsInput();
                while (userPackageChoice > 3 || userPackageChoice < 1) {
                    Display.UserInputNotValid();
                    userPackageChoice = UserInput.OptionsInput();
                }
                OptionsHandler.AddPackage(packageList,userPackageChoice);
            }
            if(userOptionChoice == 3)
                OptionsHandler.RemovePackage(packageList);
            if(userOptionChoice == 4)
                OptionsHandler.ListOverduePackages(packageList);
            if(userOptionChoice == 5)
                OptionsHandler.ListUpcomingPackages(packageList);
            if(userOptionChoice == 6)
                OptionsHandler.MarkPackageDelivered(packageList);
            if(userOptionChoice == 7) {
                JSONHandler.writeToJSON(theFile,packageList);
                OptionsHandler.Exit();
            }
        }
    }
}