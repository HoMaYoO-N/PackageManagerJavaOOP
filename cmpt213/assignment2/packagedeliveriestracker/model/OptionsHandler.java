package cmpt213.assignment2.packagedeliveriestracker.model;

import cmpt213.assignment2.packagedeliveriestracker.textui.Display;
import cmpt213.assignment2.packagedeliveriestracker.textui.UserInput;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.*;

public class OptionsHandler {

    /**
     * Option1: Shows a current list of packages for arbitrary package list.
     * @param packageList is arbitrary package list. Arbitrary because in step 4 and 5 we also use this function after creating new array list and removing unwanted packages.
     */
    public static void ListAllPackages(List<Package> packageList)
    {
        if (packageList.isEmpty() == true)
            System.out.println("No packages to show." + "\n");
        else
        {
            //first sort based on descending order.
            //got the idea from here: https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
            Collections.sort(packageList);
            for (int i = 0; i < packageList.size(); i++)
                System.out.println("Package #" + (i + 1) + "\n"
                        + packageList.get(i));
        }
    }

    /**
     * Option2: To add a new package, we get the specific information line by line, and then add the package to the top of the list.
     */
    public static List<Package> AddPackage(List<Package> packageList,int userPackageChoice)
    {
        Package newPackage = UserInput.NewPackageInput(userPackageChoice);
        packageList.add(newPackage);
        return packageList;
    }

    /**
     * Option3: To remove a specific package if it exists. Asks the user for the index of that package.
     */
    public static List<Package> RemovePackage(List<Package> packageList)
    {
        if(packageList.isEmpty() == true)
            Display.PackageListEmptyMessage();
        else {
            ListAllPackages(packageList);
            Display.RemovalQuestion();
            int removalNumber = UserInput.SelectUserInputPackageInList(packageList);
            if (removalNumber == 0)
                return packageList;
            Display.RemovalConfirmationMessage(packageList,removalNumber);
            packageList.remove(removalNumber);
        }
        return packageList;
    }

    /**
     * Option4: a package is overdue if today's DateTime is more than package's getExpectedDeliveryDate.
     */
    public static void ListOverduePackages(List<Package> packageList)
    {
        List <Package> container = new ArrayList<>();
        for(int i = 0; i < packageList.size(); i++)
            if(LocalDateTime.now().compareTo(packageList.get(i).getExpectedDeliveryDate()) > 0 && packageList.get(i).getIsDelivered() == false)
                container.add(packageList.get(i));
        Collections.sort(container);
        if(container.isEmpty() == true) {
            Display.NoPackageMessage();
            return;
        }
        ListAllPackages(container);
    }

    /**
     * Option5: a package is upcoming if today's DateTime is less or equal than package's getExpectedDeliveryDate.
     */
    public static void ListUpcomingPackages(List<Package> packageList)
    {
        List <Package> container = new ArrayList<>();
        for(int i = 0; i < packageList.size(); i++)
            if(LocalDateTime.now().compareTo(packageList.get(i).getExpectedDeliveryDate()) <= 0 && packageList.get(i).getIsDelivered() == false)
                container.add(packageList.get(i));
        Collections.sort(container);
        if(container.isEmpty() == true) {
            Display.NoPackageMessage();
            return;
        }
        ListAllPackages(container);
    }

    /**
     * Option6: To change isDelivery field of a package.
     */
    public static void MarkPackageDelivered(List<Package> packageList)
    {
        List <Package> container = new ArrayList<>();
        if(packageList.isEmpty() == true) {
            Display.PackageListEmptyMessage();
            return;
        }
        else
        {
            Collections.sort(packageList);
            ListAllPackages(packageList);
            Display.SetDeliveredQuestion();
            int packageNumber = UserInput.SelectUserInputPackageInList(packageList);
            if(packageNumber == 0)
                return;
            /** because in packagelist everything starts from 0*/
            packageNumber = packageNumber - 1;
            Display.DeliveryConfirmationMessage(packageList,packageNumber);
            packageList.get(packageNumber).setDelivered(true);
        }
    }

    /**
     * Option7: To save the results in list.json and exit the system.
     */
    public static void Exit()
    {
        Display.ExitMessage();
        System.exit(0);
    }
}

