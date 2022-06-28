package cmpt213.assignment2.packagedeliveriestracker.model;

import cmpt213.assignment2.packagedeliveriestracker.textui.Display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * A class to generate our text menu, as well as our menu title.
 */
public class TextMenu {

    private String title;

    /**
     * Get menu title.
     * @return the menu title.
     */
    public String getTitle() {
        return title;
    }
    private List<String> options;

    /**
     * Get menu options.
     * @return the menu option.
     */
    public List<String> getOptions() {
        return options;
    }
    /**
     * Constructor for populating the menu options and title. Programmer can change the menu and title from here.
     */
    public TextMenu() {
        String title = "My Package Deliveries Tracker";
        List<String> options = new ArrayList<>(Arrays.asList("List all packages",
                "Add a package",
                "Remove a package",
                "List overdue packages",
                "List upcoming packages",
                "Mark package as delivered",
                "Exit"));
        this.title = title;
        this.options = options;
    }

}