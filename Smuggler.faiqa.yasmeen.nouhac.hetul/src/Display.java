import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Display {

    private Scanner scanner = new Scanner(System.in);
    private List<City> cities= new ArrayList<City>();
    private String SmugglerName = "";
    private boolean loseStatus = false;
    private boolean loginChecker = false;

    public Smuggler s1= new Smuggler();

    // This function displays the menu for the smuggler Smuggler.
    boolean smugglerMenu()
    {

        // Check if the Smuggler has been initialized yet. If not, create a new Smuggler and set their inventory.
        int ans = 0;

        if(s1.getName().equals(""))
        {
            Item iu1 = new Item("Lsd", 8, 200);
            Item iu2 = new Item("Weed", 12, 30);
            Item iu3 = new Item("Heroin", 25, 50);
            System.out.println("Enter the name of the Smuggler : ");
            SmugglerName = scanner.nextLine();

            s1 = new Smuggler(SmugglerName, cities);

            List<Item> lui = new ArrayList<Item>();
            lui.add(iu1 );
            lui.add(iu2 );
            lui.add(iu3 );
            s1.setInventories(lui);
        }

        // Clear the console and display the menu options.
        //System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("#######################");
        System.out.println("Smuggler menu :");
        System.out.println("1. To access Smuggler Menu.");
        System.out.println("2. To go to Main Page.");
        System.out.println("#######################");

        // Prompt the Smuggler to enter a choice, and validate their input.
        boolean checker = true;
        do
        {
            try
            {
                checker = false;
                System.out.println("Please enter the value :");
                ans = Integer.parseInt(scanner.nextLine());
                // Check if the input was valid (not an integer).


                if (ans < 1 || ans > 2) throw new CustomException("error1");   // Check if the input was within the valid range (1-2).
            }
            // If the input is not between 1 to 2, display an error message and try again.
            catch (CustomException ce)
            {
                checker = true;

                System.out.println("#########################");
                CustomException.processError(CustomException.msg, 2);
                System.out.println("#########################");

            }

            catch (Exception e) {

                // If the input was not valid, display an error message and try again.
                checker = true;
                System.out.println("#########################");
                System.out.println(HardCodedValue.ERROR2);
                System.out.println("#########################");

            }
        } while (checker) ;

        // Based on the Smuggler's choice, either display the Smuggler menu or return to the main page.
        switch (ans)
        {
            case 1:
                s1.SmugglerMenu();
                break;
            case 2:
                return false;
        }

        // Return whether the Smuggler has lost the game or not.
        return s1.getGameLose();
    }
    void citiesMenu() throws Exception {
        int ans = 0;
        int i = 0;
        System.out.println("Cities data : ");

        // Loop through the cities List to print out each city's name
        for (i=0; i < cities.size(); i++)
        {
            System.out.println((i + 1) + ". " + cities.get(i).getCityName() + ".");
        }

        // Print out an option to go back to the main page
        System.out.println((i + 1) + ". To go to main page.");

        boolean checker = true;
        do
        {
            try
            {
                checker = false;

                // Ask the Smuggler to input a number corresponding to the city they want to access
                System.out.println("Please enter the number to access specific city menu : ");
                ans = Integer.parseInt(scanner.nextLine());

                // Check if the input is valid

                if (ans < 1 || ans > i + 1) throw new CustomException("error1");
            }
            catch (CustomException ce)
            {
                checker = true;

                System.out.println("#########################");
                CustomException.processError(CustomException.msg, i+1);
                System.out.println("#########################");

            }
            catch (Exception e) {

                // If the input is not valid, catch the exception and set the checker to true
                checker = true;
                System.out.println("#########################");
                System.out.println(HardCodedValue.ERROR2);
                System.out.println("#########################");
            }
        } while (checker) ;

        // If the Smuggler chose to go back to the main page, return from the function
        if (ans == i + 1)
        {
            return;
        }

        // Otherwise, access the city's menu using the cityMenu function
        cities.get(ans - 1).cityMenu();
    }

    public void menu() throws Exception {
        int a = 0;
        // system("cls"); // clears the console screen

        // Display "GAME OVER" message if the game is lost
        if (loseStatus)
        {

            System.out.println("#####################");
            System.out.println("##### GAME OVER #####");
            System.out.println("#####################");
            loseStatus = false;
        }

        // Display "Welcome to Merchant Game" message if Smuggler is not logged in
        if (loginChecker)
        {
            System.out.println("###########################");
            System.out.println("###########################");
            System.out.println("Welcome to Merchant Game : ");
            System.out.println("###########################");
        }
        else
            System.out.println("Welcome to Main Page: ");

        // Display menu options
        System.out.println("1. To access cities : ");
        System.out.println("2. To access Smuggler account : ");
        System.out.println("3. To exit from the Game : ");

        boolean checker = true;
        do
        {
            try
            {
                checker = false;
                System.out.println("please enter number to play game : ");
                a = Integer.parseInt(scanner.nextLine());


                // Check if input is a valid option
                if (a < 1 || a > 3) throw new CustomException("error1");
            }
            catch (CustomException ce)
            {
                checker = true;

                System.out.println("#########################");
                CustomException.processError(CustomException.msg, 3);
                System.out.println("#########################");

            }
            catch (Exception e) {
                checker = true;
                System.out.println("#########################");
                System.out.println(HardCodedValue.ERROR2);
                System.out.println("#########################");
            }
        } while (checker) ;

        // Execute the corresponding menu option
        switch (a)
        {
            case 1:
                citiesMenu();
                break;
            case 2:
                loseStatus = smugglerMenu();

                // Reset the Smuggler's name if the game is lost
                if (loseStatus)
                {
                    s1.setName("");
                }
                break;
            case 3:
                System.out.println("####################");
                System.out.println("The end of the Game!");
                System.out.println("####################");
                return;
        }

        loginChecker = true;
        menu();
    }

    // Definition of a getter function that returns the List of Cities
    public List < City> getCities() {
        return cities; // Return the "cities" List
    }


    // Definition of a function that sets the List of cities to a new List of Cities
    void setCities(List < City > c) {
        cities = c;
    }

    // Definition of a getter function that returns the Smugglername
    String getSmugglerName() {
        return SmugglerName;
    }

    // Definition of a function that sets the Smugglername
    void setSmugglerName(String uName) {
        SmugglerName = uName;
    }

    // Definition of a getter function that returns the login status
    boolean getLoginChecker() {
        return loginChecker;
    }

    // Definition of a function that sets the login status
    void setLoginChecker(boolean lChecker) {
        loginChecker = lChecker;
    }

}
